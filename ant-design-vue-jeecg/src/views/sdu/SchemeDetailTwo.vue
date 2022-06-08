<template>
  <div id="box">
    <a-descriptions class="ant-des"  bordered>
      <template slot="title">
        <a-form layout="inline">
          <a-row :gutter="24">
            <a-col :span="19">
              <h3>解决方案具体内容</h3>
            </a-col>
            <a-col span="2">
               <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
                 <a-button v-if="myRole" type="primary"   class="buttonSet" @click="judgeType">意向采用</a-button>

               </span>
            </a-col>
          </a-row>
        </a-form>

      </template>

      <a-descriptions-item label="方案工程师">
        <a-button block type="primary" @click="showdialogue">
          {{this.scheme2.companyname}}
          <a-icon type="message" theme="filled" />
        </a-button>
      </a-descriptions-item>
      <a-descriptions-item label="方案名称">
        <h2>{{this.scheme2.name}}</h2>
      </a-descriptions-item>
      <a-descriptions-item label="发布时间">
        <h2>{{this.scheme2.date}}</h2>
      </a-descriptions-item>

      <a-descriptions-item :span="3">
        <template v-slot:label>
         <span style="font: 16px 'STXihei';">
            需求描述
         </span>
        </template>
        <div  v-html="this.scheme2.description"></div>
        <!--        <h3>{{this.scheme2.description}}</h3>-->
      </a-descriptions-item>

      <a-descriptions-item label="详细内容" :span="3">
        <div id="t1" v-html="this.s"></div>
        <!--        {{this.scheme2.solution}}-->
      </a-descriptions-item>
      <a-descriptions-item label="应用效果" :span="3">
        <div id="t2" v-html="this.apply"></div>
        <!--        {{this.scheme2.solution}}-->
      </a-descriptions-item>
    </a-descriptions>

    <div class="centerModal" ref='centerModal'>
      <a-modal
        title="意向订单"
        :visible="visible"
        :confirm-loading="confirmLoading"
        @ok="handleOk"
        @cancel="handleCancel"
        class="setModal"
        width="33%"
      >
        <a-form-model
          ref="ruleForm"
          :model="form"
          :label-col="labelCol"
          :wrapper-col="wrapperCol"
          :rules="rules"
          layout='vertical'
        >
          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item ref="name"  prop="name">
                <template slot="label">
                  <div style="font-size: medium">
                    企业名称：
                  </div>
                </template>
                <div>
                  <div   style="font-weight: bold;font-family:'Microsoft YaHei'" >{{form.duserRealname}}</div>
                </div>
              </a-form-model-item>
            </a-col>

          </a-row>

          <a-divider style="margin: 8px"></a-divider>

          <a-form-model-item ref="demandPhone"  prop="demandPhone" >
            <template slot="label">
              <span style="font-size: medium">
                企业电话：
              </span>
            </template>
            <div>
              <a-input  v-model="form.demandPhone">{{form.demandPhone}}</a-input>
            </div>
          </a-form-model-item>


          <a-form-model-item ref="demandName" prop="demandName" v-show="hasDemand">
            <template slot="label">
              <div style="font-size: medium">
                企业需求名称：
              </div>
            </template>
            <div>
              <div    style="font-weight: bold">{{form.demandName}}</div>
            </div>
          </a-form-model-item>

          <a-divider style="margin: 8px"></a-divider>

          <a-form-model-item ref="solutionName"  prop="solutionName">
            <template slot="label">
              <div style="font-size: medium">
                意向方案名称：
              </div>
            </template>
            <div>

              <div  style="font-weight: bold" >{{form.solutionName}}</div>
            </div>
          </a-form-model-item>

          <a-divider style="margin: 8px"></a-divider>

          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item ref="orderCreate"  prop="orderCreate">
                <template slot="label">
                  <div style="font-size: medium">
                    方案供方：
                  </div>
                </template>
                <div>
                  <div  style="font-weight: bold">{{form.suserRealname}}</div>
                </div>
              </a-form-model-item>
            </a-col>
          </a-row>

          <a-divider style="margin: 8px"></a-divider>

          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item ref="OrderSuccess"  prop="OrderSuccess">
                <template slot="label">
                  <div style="font-size: medium">
                    企业名称：
                  </div>
                </template>
                <div>
                  <div    style="font-weight: bold">{{form.suserPhone}}</div>
                </div>
              </a-form-model-item>
            </a-col>
          </a-row>

          <a-divider style="margin: 8px"></a-divider>

          <a-row :gutter="24">
            <a-col :span="22">
              <a-form-model-item ref="priceSingle"  prop="priceSingle">
                <template slot="label">
                  <span style="font-size: medium">
                    意向订价：
                  </span>
                </template>
                <span>
                  <a-input  placeholder="请输入价格" style="width:50%" v-model.number="form.priceSingle" ></a-input>
                </span>
                <span style="margin-left: 5%">
                  ￥
                </span>
              </a-form-model-item>
            </a-col>
          </a-row>

          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item ref="orderCreate"  prop="orderCreate">
                <template slot="label">
                  <div style="font-size: medium">
                    订单生成时间：
                  </div>
                </template>
                <div>
                  <div   style="font-weight: bold">{{form.orderCreate}}</div>
                </div>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item ref="OrderSuccess"  prop="OrderSuccess">
                <template slot="label">
                  <div style="font-size: medium">
                    订单确认时间：
                  </div>
                </template>
                <div>
                  <div  style="font-weight: bold" >{{form.orderSuccess}}</div>
                </div>
              </a-form-model-item>
            </a-col>
          </a-row>
        </a-form-model>

      </a-modal>
    </div>
    <a-modal
      :title="this.scheme2.companyname"
      :visible="visibler"
      :confirm-loading="confirmLoad"
      @ok="handleOkey"
      @cancel="handleCancelly"
    >
      <a-textarea placeholder="输入留言内容" :rows="4" v-model="ModalText"/>
    </a-modal>
    <!--    <a-button @click="accept">感兴趣</a-button>-->
    <CommentMessage2 @handleReply="handleReply" :commentList="comments" v-if="hasReset"/>
<!--    <CommentEdit2 @reload="reload" :parentMsgId="replyMsgId" :replyMsgUsername="replyMsgUsername" :scheme="this.scheme2"/>-->
  </div>

</template>

<script>
import CommentMessage2 from './components/CommentMessage2'
//import CommentEdit2 from './components/CommentEdit2'
import store from '@/store/'
import {  mapGetters } from 'vuex'
export default {
  components:{
//    CommentEdit2,
    CommentMessage2
  },
  name: 'SchemeDetailTwo.vue',
  data(){
    return{
      demandItem:[],
      replyMsgId:"",
      replyMsgUsername:"",
      comments:[],
      hasReset:true,
      //scheme:{},
      scheme2:this.scheme,
      a:"",
      s:"",
      apply:"",
      ModalText: "",
      visibler: false,
      confirmLoad: false,
      messageUserId:"",
      visible: false,
      confirmLoading: false,
      labelCol: { span: 18 },
      wrapperCol: { span: 24 },
      form: {
        demandId: '',
        solutionId:'',

        demandPhone:'',
        duserName:'',
        suserName:'',
        duserRealname:'',
        suserRealname:'',
        suserPhone:'',
        priceSingle: '',

        orderCreate:'',
        demandName:'',
        solutionName:'',
        solutionClassify:'',
      },
      demandUsername:'',
      count:1 ,
      min:0,
      rules: {
        //demandAddress: [{ required: true, message: '请输入地址', trigger: 'blur' }],
        priceSingle: [{ required: true, message: '请输入采购价格', trigger: 'blur' }],
        //orderCount: [{ required: true, message: '选择方案数量', trigger: 'blur' }],
        demandPhone: [{ required: true, message: '请输入联系人电话', trigger: 'blur' }],

      },
      hasDemand:false,
      notice:{
        messagetype:'',
        name:'',
        user:'',
        date:'',
        demandname:'',
        demanduser:'',
        orderstate:''
      },
      haveOrderData:[],
    }
  },
  methods:{
    reload(){
      this.do()
    },
    handleReply(data){

      this.replyMsgId = data.msgId;
      this.replyMsgUsername = data.msgUsername;
      console.log(this.scheme)
      this.scheme2=this.scheme
    },
    // 确认按钮
    // findDemand(id){
    //   this.$axios.get('sdu/creative/findById',{
    //     params:{
    //       id:id
    //     }
    //   }).then(res=>{
    //     this.demandItem = res.data.result;
    //     console.log(this.demandItem)
    //
    //   })
    //   return this.demandItem
    //
    // },
    cancel(e) {
      console.log(e);

    },

    async  findMessage2(){
      let a = this.scheme.id
      await this.$axios.get("sdu/comments/findAll",{
        params:{
          id:this.a
        }
      }).then(res=>{
        this.comments = JSON.parse(JSON.stringify(res.data.result));
        console.log(this.comments)
      })
    },
    do(){
      console.log("123123123")
      this.hasReset=false
      this.$nextTick(()=>{
        this.findMessage2()
        this.hasReset=true
      })
    },
    demo(data){
      this.scheme=data
      console.log(data)
    },
    showdialogue(){
      if(this.scheme2.user === this.$cookies.get('innovation_promotion_username')){
        this.$notification['warning']({
          message: '警告',
          description:
            '您不能向该用户发送信息.',
        });
      }else{
        this.visibler = true
      }
    },
    ajax1() {
      return new Promise((resolve, reject) => {
        this.$axios.get("sys/role/findIdByUserName", {
          params: {
            username: this.scheme2.user
          }
        }).then(res => {
          this.messageUserId = res.data.result
          console.log(res.data.result)
        })
        resolve();
      })

    },
    ajax2(json) {
      return new Promise((resolve,reject)=>{
        json.userId = this.messageUserId
        console.log(json.userId)
        json.message = this.ModalText
        //json.userName = this.scheme2.companyname
        json.senderId = store.getters.userInfo.id
        json.senderName = this.nickname
        console.log(json.senderName)
        const date = new Date()
        const year = date.getFullYear() //月份从0~11，所以加一
        const dateArr = [date.getMonth() + 1, date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds()]
        for (let i = 0; i < dateArr.length; i++) {
          if (dateArr[i] >= 1 && dateArr[i] <= 9) {
            dateArr[i] = "0" + dateArr[i];
          }
        }
        json.date = year + '-' + dateArr[0] + '-' + dateArr[1] + ' ' + dateArr[2] + ':' + dateArr[3] + ':' + dateArr[4]
        this.$axios.post("sys/socketTest/sendUser", json).then(res => {
          this.confirmLoad = true;

          setTimeout(() => {
            this.visibler = false;
            this.confirmLoad = false;
          }, 2000);
        })
        resolve()
      })

    },

    async handleOkey(e) {
      //this.ModalText = 'The modal will be closed after two seconds';
      const json = {}
      //await this.ajax1(json)
      await this.ajax2(json)
    },
    handleCancelly(e) {
      //console.log('Clicked cancel button');
      this.visibler = false;
    },
    accept(){
      this.scheme2.status=2
    },
    getNewSolution(){
      console.log(this.$route.query)
      this.scheme2=this.$route.query.solution
      this.a= this.scheme2.id
      this.findMessage2()
      this.s= this.scheme2.solution
      this.apply = this.scheme2.applyeffect
    },
    // add(){
    //   this.form.orderCount++;
    //   this.getPriceAll()
    // },
    // sub(){
    //   if(this.form.orderCount>0)
    //     this.form.orderCount--
    //   this.getPriceAll()
    // },
    async getRole(){
      await this.$axios.get('sys/role/findRoleByUserName',{
        params:{
          username:this.$cookies.get('innovation_promotion_username'),
        }
      }).then(res=>{

        this.name=res.data
        this.$axios.get("sdu/enterpriseOrder/queryOrderByThreeName",{
          params:{
            duserName:store.getters.userInfo.username,
            suserName:this.scheme.user,
            solutionName:this.scheme.name
          }
        }).then(res=>{
          this.orderData=res.data.result[0]
          console.log(this.orderData)



          console.log(this.orderData)
          console.log(JSON.stringify(this.orderData) === undefined)
          if(this.name===this.judgeName&& JSON.stringify(this.orderData) == undefined){

            this.myRole=true
          }else{
            this.myRole=false
            console.log("已经有订单了")
          }
        })
      })
    },
    //   判断方案的类型生成的订单种类
    judgeType(){

      if(this.scheme.demandid===undefined){
        console.log("  这是通用方案项目")
        this.hasDemand=false
        this.form.solutionClassify=2
        this.showModal()
      }else{
        console.log("这是需求方案订单")
        this.hasDemand=true
        this.form.solutionClassify=1;
        this.showModal()
      }


    },
    //   对话框打开
    showModal() {
      this.visible = true;
      this.queryPhoneByUsername();
      this.getDemandName();
      this.getOrderDate();
      this.getUserData();
      //this.getPriceAll();
    },

    //  提交订单
    handleOk(e) {
      this.$refs.ruleForm.validate(async valid => {
        if (valid) {
          this.confirmLoading = true;
          this.$axios.post('sdu/enterpriseOrder/add', this.form).then(res => {
            this.visible = false;
            this.confirmLoading = false;
            this.$message.info('订单提交成功');

          })

          if(this.form.demandId==null || this.form.demandid=='' || this.form.demandId==undefined){
            this.sendServiceNotice()
            this.scheme.status = 3
            this.$axios.put("sdu/solution/editById",this.scheme).then(res=>{
              console.log("方案已经被采用")
            });
          }else
          {

            this.scheme.status = 3
            // console.log(this.scheme.demandid)
            // await this.$axios.post('',this.scheme)
            this.$axios.put('sdu/solutiondemand/editById', this.scheme).then(res => {
              console.log('方案已被采用')
              // alert('方案已被采用');
            });
            this.$axios.get('sdu/creative/findById', {
              params: {
                id: this.scheme.demandid
              }
            }).then(res =>
            {
              this.demandItem = res.data.result[0];
              // console.log(this.demandItem)
              this.demandItem.status = 3;
              // console.log(this.demandItem)
              this.$axios.post('sdu/creative/demandChange', this.demandItem).then(res => {
                console.log("需求状态已设为 已采用")
                this.sendServiceNotice()

              })

            });
          }
        }else{
          console.log('error submit!!');
          return false;
        }
      })
    },

    //  表单验证
    resetForm() {
      this.$refs.ruleForm.resetFields();
    },
    //  对话框取消
    handleCancel(e) {
      console.log('Clicked cancel button');
      this.visible = false;
    },

    //  获取订单所必要的数据
    getUserData(){
      this.form.duserRealname=store.getters.nickname;
      this.form.demandPhone=store.getters.userInfo.phone;
      this.form.demandId=this.scheme.demandid;
      this.form.solutionId=this.scheme.id;
      this.form.suserRealname=this.scheme.companyname;
      this.form.solutionName=this.scheme.name;
      this.form.demandphone=store.getters.userInfo.phone;
      this.form.duserName=store.getters.userInfo.username;
      this.form.suserName=this.scheme.user;
    },

    //  获取需求名字
    async getDemandName(){
      // console.log(this.scheme)
      await this.$axios.get('sdu/creative/findById',{
        params:{
          id:this.scheme.demandid
        }
      }).then(res=>{
        // console.log(res.data.result[0].name)
        this.form.demandName=res.data.result[0].name
      })
    }
    ,
    //  查询服务商的电话联系方式
    async queryPhoneByUsername(){
      console.log("已被执行")
      await this.$axios.get('sys/user/queryPhoneByUsername',{
        params:{
          username:this.scheme.user
        }
      }).then(res=>{
        console.log(res.data)
        this.form.suserPhone=res.data
      })

    },
    getOrderDate(){
      const date = new Date()
      const year = date.getFullYear() //月份从0~11，所以加一
      const dateArr = [date.getMonth() + 1, date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds()]
      for(let i=0; i<dateArr.length; i++){
        if (dateArr[i] >= 1 && dateArr[i] <= 9) {
          dateArr[i] = "0" + dateArr[i];
        }
      }
      const strDate = year + '-' + dateArr[0] + '-' + dateArr[1] + ' ' + dateArr[2] + ':' + dateArr[3] + ':' + dateArr[4]
      this.form.orderCreate=strDate

    },
    //   获取订单总价
    // getPriceAll(){
    //   if(this.form.orderCount!==''&&this.form.priceSingle!==''){
    //     this.form.priceAll=this.form.orderCount*this.form.priceSingle
    //   }else{
    //     this.form.priceAll=0;
    //   }
    // },

    //    生成通知

    sendServiceNotice(){

      this.notice.name=this.form.solutionName;
      this.notice.user=this.form.suserName;
      this.notice.demanduser=this.form.duserName;
      this.notice.demandname=this.form.demandName;
      this.notice.date=this.form.orderCreate;
      this.notice.orderstate=2
      if(this.scheme.demandid===undefined){
        this.notice.messagetype=3;
      }else{
        this.notice.messagetype=2
      }

      // console.log(this.notice)
      this.$axios.post('sdu/messageNotice/addMessageNotice',this.notice).then(res=>{
        console.log("需求企业发送订单通知成功")
        this.$router.push({
          path: '/sdu/orderItem/EnterpriseOrder'
        })
      })
    },


    // 查询是否有重复订单
    async isSameOrder(){
      await  this.$axios.get("sdu/enterpriseOrder/queryOrderByThreeName",{
        params:{
          duserName:store.getters.userInfo.username,
          suserName:this.scheme.user,
          solutionName:this.scheme.name
        }
      }).then(res=>{
        this.orderData=res.data.result[0]
        console.log(this.orderData)

      })
    }
  },
  created() {
    this.getRole()
    this.getOrderDate()
  },
  beforeMount(){
    // console.log(this.scheme)
    this.a= this.scheme.id
    this.findMessage2()
    // console.log(this.comments)
    // console.log(this.scheme)
    this.scheme2=this.scheme
    //this.$bus.$on("flag",this.demo)
    this.s= this.scheme2.solution
    this.apply = this.scheme2.applyeffect
    this.ajax1()
  },
  computed:{
    scheme:function (){
      console.log(this.$route.query.solution)
      return this.$route.query.solution
    },
    ...mapGetters(["nickname"]),
  },
  // mounted() {
  //   window.onload = function(){
  //     const s = this.scheme2.solution
  //     const t = document.getElementById('t')
  //     t.innerHTML = s
  //   }
  // },
  watch:{
    $route:{
      immediate: true,
      handler(to,from){
        console.log(to.path)
        console.log(from)
        if(from.path === '/sdu/schemedetailtwo'){
          this.getNewSolution()
        }
      }
    }
    // '$route.path':function(newVal,oldVal){
    //   if(newVal === '/sdu/schemeView'){
    //     this.do()
    //
    //   }
    // },
    // scheme:{
    //   handler(a,b){
    //     console.log(a+"\n"+b)
    //   }
  }
}
</script>

<style scoped>
#box{
  padding: 24px;
  background: #fbfbfb;
  border: 1px solid #d9d9d9;
  border-radius: 6px;
}
.buttonSet{
  width: 120px;
  height: 40px;
}

</style>