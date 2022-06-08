<template>
  <div>
    <a-form-model ref="form" :model="model" :rules="validatorRules">
      <a-form-model-item required prop="email">
        <a-input v-model="model.email" size="large" type="text" placeholder="请输入邮箱">
          <a-icon slot="prefix" type="mail" :style="{ color: 'rgba(0,0,0,.25)' }"/>
        </a-input>
      </a-form-model-item>
      <a-row :gutter="16">
        <a-col class="gutter-row" :span="16">
          <a-form-model-item required prop="captcha">
            <a-input v-model="model.captcha" size="large" type="text" placeholder="请输入验证码">
              <a-icon slot="prefix" type="code" :style="{ color: 'rgba(0,0,0,.25)' }"/>
            </a-input>
          </a-form-model-item>
        </a-col>
        <a-col class="gutter-row" :span="8">
          <a-button
            class="getCaptcha"
            tabindex="-1"
            :disabled="state.smsSendBtn"
            @click.stop.prevent="getCaptcha2"
            v-text="!state.smsSendBtn && '获取验证码' || (state.time+' s')"></a-button>
        </a-col>
      </a-row>
    </a-form-model>
  </div>
</template>

<script>
  import { postAction } from '@/api/manage'
  import { mapActions } from 'vuex'

  export default {
    name: 'LoginPhone',
    data(){
      return {
        model:{
          email: '',
          captcha: ''
        },
        //手机号登录用
        state: {
          time: 60,
          smsSendBtn: false,
        },
        validatorRules:{
          email: [
            { required: true, message: '请输入邮箱!' },
            { validator: this.validateEMail }
          ],
          captcha: [{
            required: true, message: '请输入验证码!'
          }]
        }

      }
    },
    methods:{
      ...mapActions(['PhoneLogin']),
      handleLogin(rememberMe){
        this.validateFields([ 'email', 'captcha' ], (err) => {
          if (!err) {
            let loginParams = {
              email: this.model.email,
              captcha: this.model.captcha,
              remember_me: rememberMe
            }
            console.log("登录参数", loginParams)
            this.PhoneLogin(loginParams).then((res) => {
              this.$emit('success', res.result)
            }).catch((err) => {
              this.$emit('fail', err)
            });
          }else{
            this.$emit('validateFail')
          }
        })
      },
      validateEMail(rule, value,callback) {
        var reg =/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        if(value==''||value==undefined||value==null){
          callback();
        }else{
          if (!reg.test(value)){
            callback(new Error('请输入正确的邮箱'));
          } else {
            callback();
          }
        }
      },
      // // 校验手机号
      // validateEmail(rule,value,callback){
      //   if (!value || new RegExp(/^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/).test(value)){
      //     callback();
      //   }else{
      //     callback("您的手机号码格式不正确!");
      //   }
      // },
      //获取验证码
      getCaptcha (e) {
        e.preventDefault();
        let that = this;
        that.validateFields([ 'email' ], (err) => {
            if (!err) {
              that.state.smsSendBtn = true;
              let interval = window.setInterval(() => {
                if (that.state.time-- <= 0) {
                  that.state.time = 60;
                  that.state.smsSendBtn = false;
                  window.clearInterval(interval);
                }
              }, 1000);

              const hide = that.$message.loading('验证码发送中..', 0);
              let smsParams = {};
              smsParams.email=that.model.email;
              smsParams.smsmode="0";
              postAction("/sys/sms",smsParams)
                .then(res => {
                  if(!res.success){
                    setTimeout(hide, 0);
                    that.cmsFailed(res.message);
                  }
                  console.log(res);
                  setTimeout(hide, 500);
                })
                .catch(err => {
                  setTimeout(hide, 1);
                  clearInterval(interval);
                  that.state.time = 60;
                  that.state.smsSendBtn = false;
                  that.requestFailed(err);
                });
            }
          }
        );
      },

      getCaptcha2(e){
        e.preventDefault()
        let that = this
        that.validateFields(['email'], async (err) => {
            if (!err) {
              this.state.smsSendBtn = true;
              let interval = window.setInterval(() => {
                if (that.state.time-- <= 0) {
                  that.state.time = 60;
                  that.state.smsSendBtn = false;
                  window.clearInterval(interval);
                }
              }, 1000);
              const hide = this.$message.loading('验证码发送中..', 3);
              const params = {
                email: this.model.email,
              };

              await this.$axios.get("sdu/mail/sendMail",{
                params:{
                  email:that.model.email
                }
              }).then((res)=>{
                console.log(res)
                if (!res.success) {
                  //this.registerFailed(res.message);
                  setTimeout(hide, 0);
                }
                setTimeout(hide, 500);
              }).catch(err => {
                console.log(err)
                setTimeout(hide, 1);
                clearInterval(interval);
                that.state.time = 60;
                that.state.smsSendBtn = false;
                //this.requestFailed(err);
              });
            }
          }
        );
      },
      cmsFailed(err){
        this.$notification[ 'error' ]({
          message: '获取验证码失败',
          description:err,
          duration: 4,
        });
      },
      /**
       * 验证字段
       * @param arr
       * @param callback
       */
      validateFields(arr, callback){
        let promiseArray = []
        for(let item of arr){
          let p = new Promise((resolve, reject) => {
            this.$refs['form'].validateField(item, (err)=>{
              if(!err){
                resolve();
              }else{
                reject(err);
              }
            })
          });
          promiseArray.push(p)
        }
        Promise.all(promiseArray).then(()=>{
          callback()
        }).catch(err=>{
          callback(err)
        })
      },


    }

  }
</script>

<style scoped>
.getCaptcha{
  display: block;
  width: 100%;
  height: 40px;
}
</style>