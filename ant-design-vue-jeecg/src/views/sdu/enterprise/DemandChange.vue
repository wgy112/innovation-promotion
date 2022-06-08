<template>
  <a-card  style="background-color: white"  >
    <div style="margin-bottom: 3px" slot="title">
      <div style="margin-left: 200px">创新方法推广平台--需求修改  </div>
    </div>
    <a-form-model
      ref="ruleForm"
      :model="form"
      :rules="rules"
      :label-col="labelCol"
      :wrapper-col="wrapperCol"
    >
      <a-form-model-item ref="name" label="企业需求名称" prop="name">
        <a-input
          placeholder="请输入你想编辑的方案的名称"
          v-model="form.name"
          style="height: 40px"
          @blur="
            () => {
              $refs.name.onFieldBlur();
            }
          "
        />
      </a-form-model-item>


      <a-form-model-item label="企业需求描述" prop="description">
        <J-editor v-model="form.description" class="text"></J-editor>
      </a-form-model-item>

      <a-form-model-item label="企业需求分类" prop="classify" >
        <a-radio-group v-model="form.classify" >
          <a-radio :style="radioStyle" :value="1">
            数字化管理
          </a-radio>
          <a-radio :style="radioStyle" :value="2">
            个性化定制提升
          </a-radio>
          <a-radio :style="radioStyle" :value="3">
            网络化协同提升
          </a-radio>
          <a-radio :style="radioStyle" :value="4">
            服务型制造提升
          </a-radio>
          <a-radio :style="radioStyle" :value="5">
            中小企业创成
          </a-radio>
          <a-radio :style="radioStyle" :value="6">
            技术发明创造
          </a-radio>
        </a-radio-group>
      </a-form-model-item>

      <a-form-model-item :wrapper-col="{ span: 14, offset: 4 }">
        <a-button type="primary" @click="forwardPath()" class="button1">
          返回列表
        </a-button>
        <a-button type="primary" @click="onSubmit()" class="button2">
          需求提交
        </a-button>

      </a-form-model-item>
    </a-form-model>
    <a-card title="注意事项" style="width:80%;margin-left: 200px;margin-top: 100px">
      <div>1、需求企业名称一栏为填写企业需求的名称，请尽可能体现你需求的最核心要点，注意方便简洁</div>
      <div>2、企业需求描述一栏为企业需求的详细描述（可插入图片描述），包括但不局限于需求需要的场景，外形，功能，达到的效果等需求实现，功能需求描述越详细越好</div>
      <div>3、企业需求分类为企业需求所在的分类，详细可见前面的首页分类介绍</div>
    </a-card>

  </a-card>






</template>
<script>
import JEditor from '@/components/jeecg/JEditor'
export default {
  name: 'DemandChange',
  components:{
    JEditor:JEditor
  },
  data(){

    return{
      demandItem:[],

      isClear:false,
      demandDes:'',
      demandName:'',
      collapsed: false,
      form:{
        name:"",
        description:"",
        user:"",
        status:"",
        date:"",
        classify:'',
      },
      value: 1,
      radioStyle: {
        display: 'block',
        height: '30px',
        lineHeight: '30px',
      },
      classifyName:"请选择分类",
      valueNumber:'',
      labelCol: { span: 4 },
      wrapperCol: { span: 14 },
      other: '',
      rules: {
        name: [{ required: true, message: '请输入需求名字', trigger: 'blur' }],
        description: [{ required: true, message: '请输入需求描述', trigger: 'blur' }],
        classify: [{ required: true, message: '请选择分类', trigger: 'change' }],
      },

    }

  },
  methods:{
    async onSubmit() {
      this.$refs.ruleForm.validate(async valid => {
        if (valid) {

          console.log(this.form)
          await this.$axios.post('sdu/creative/demandChange', this.form).then(res=>{
            console.log(this.form)
          })
          alert('submit!');
          await this.$router.push('/sdu/enterprise/DemandList')
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm() {
      this.$refs.ruleForm.resetFields();
    },

    change(val){
      console.log(val)
      this.news=val;
    },


    delHtmltag(str){
      return str.replace(/<\/?.+?>/g,"")
    },

    forwardPath(){
      this.$router.push({
        path:'/sdu/enterprise/DemandList',
      });
      // this.$forceUpdate()


    },

    getCookie(){
      console.log("........"+this.$cookies.get('innovation_promotion_username'))
      this.form.user=this.$cookies.get('innovation_promotion_username')
      console.log("这是传递的值")
      console.log(this.demandItem)
    },
    getData(){
      const date = new Date()
      const year = date.getFullYear() //月份从0~11，所以加一
      const dateArr = [date.getMonth() + 1, date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds()]
      for(let i=0; i<dateArr.length; i++){
        if (dateArr[i] >= 1 && dateArr[i] <= 9) {
          dateArr[i] = "0" + dateArr[i];
        }
      }
      const strDate = year + '-' + dateArr[0] + '-' + dateArr[1] + ' ' + dateArr[2] + ':' + dateArr[3] + ':' + dateArr[4]
      this.form.date=strDate
      //this.item.id=nanoid()
      console.log('strDate',strDate)
    },
    getDemand(){
      // this.$forceUpdate()
      this.demandItem=this.$route.query.demandItem,
      console.log("需求名字为")
      console.log(this.demandItem)
      this.form=this.demandItem
    }
  },


  created() {

    this.getData()
    this.getCookie()
    this.getDemand()


  },


}



</script>

<style scoped>
.button1{
  position: absolute;
  left: 120px;
  margin-top: 40px;
  width: 150px;
  height: 40px;
  font-size: 18px;
  color:white;
  /*background: #ff3333;*/
}
.button2{
  position: absolute;
  left: 780px;
  margin-top: 40px;
  width: 150px;
  height: 40px;
  font-size: 18px;
  color:white;
  /*background: #ff3333;*/
}
textarea,textarea.ant-input:hover,textarea:focus{
  border: 1px solid #DAE2F3;
  -webkit-box-shadow: none;
  box-shadow: none;
}
.text /deep/ .text{
  border: 1px solid #ccc;
  min-height: 400px;
}
.ant-form-item {
  margin-bottom: 8px;
}
</style>


