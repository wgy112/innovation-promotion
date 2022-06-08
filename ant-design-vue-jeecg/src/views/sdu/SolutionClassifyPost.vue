<template>
  <a-card class="box" title="针对性创新方法发布">
    <a-form-model :form="form" ref="ruleForm" :model="item" :rules="rules" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" @submit="handleSubmit">
      <a-form-model-item label="方案名称" prop="name">
        <a-input v-model="item.name"
        />
      </a-form-model-item>
<!--      <a-form-item label="企业名称">-->
<!--        <a-input-->
<!--          v-decorator="['companyname', { rules: [{ required: true, message: '请输入企业名称!' }] }]"-->
<!--        />-->
<!--      </a-form-item>-->
<!--      <a-form-item label="企业简介">-->
<!--        <a-input type="textarea" style="min-height: 200px"-->
<!--                 v-decorator="['companyprofile', { rules: [{ required: true, message: '请输入企业创新应用需求!' }] }]"-->
<!--        />-->
<!--      </a-form-item>-->
      <a-form-model-item label="创新应用需求" prop="description" >
        <div v-html="item.description"></div>
<!--        <a-input type="textarea" v-model="item.description" style="min-height: 200px"-->
<!--        />-->
      </a-form-model-item>
      <a-form-model-item label="方案详情" prop="solution">
        <JEditor v-model="item.solution"/>
        <a-alert message="您也可以使用mindfall工具获得的txt文件上传您的解决方案" type="warning"/>
        <label class="text-reader" >
          <input type="file" ref="file" id="file" @change="loadTextFile" style="margin-top: -40px">
          <a-button icon="upload" @click="fileClick()">导入mindfall工具创意方案</a-button>
        </label>
      </a-form-model-item>
      <a-form-model-item label="应用效果" prop="applyeffect">
        <JEditor v-model="item.applyeffect"/>
<!--        <a-input type="textarea" style="min-height: 200px" v-model="item.applyeffect"-->
<!--        />-->
      </a-form-model-item>
      <a-form-item :wrapper-col="{ span: 12, offset: 5 }">
        <a-button type="primary" html-type="submit">
          发布
        </a-button>
      </a-form-item>
    </a-form-model>
  </a-card>
</template>

<script>
import { removeTAG } from './components/removeTAG';
import JEditor from '@/components/jeecg/JEditor'
import { mapGetters } from 'vuex';
export default {
  name: 'SolutionClassifyPost',
  components:{JEditor},
  data(){
    return{
      str:"",
      item:{
        name:"",
        description:"",
        date:"",
        user:"",
        solution:"",
        demandid:"",
        applyeffect:"",
        companyname:"",
      },
      notice:{
        messagetype:'',
        name:'',
        user:'',
        date:'',
        demandname:'',
        demanduser:'',
      },
      formLayout: 'horizontal',
      form: this.$form.createForm(this, { name: 'coordinated' }),
      rules:{
        name: [
          { required: true, message: '请填写方案名称', trigger: 'blur' },
        ],
        // companyname: [
        //   { required: true, message: '请填写企业名称', trigger: 'blur' },
        // ],
        // companyprofile: [
        //   { required: true, message: '请填写企业简介', trigger: 'blur' },
        // ],
        description: [
          { required: true, message: '请填写企业创新应用需求', trigger: 'blur' },
        ],
        // classify: [
        //   { required: true, message: '请选择分类', trigger: 'change' },
        // ],
        solution:[
          { required: true, message: '请输入方案详情', trigger: 'blur' },
        ],
        applyeffect:[
          { required: false, message: '请填写应用效果', trigger: 'blur' },
        ],
      }
    }
  },
  methods: {
    handleSubmit(e) {
      e.preventDefault();
      this.$refs.ruleForm.validate(async valid=>{
        console.log(this.scheme)
        if(valid){
          this.item.user = this.$cookies.get("innovation_promotion_username");
          this.getData();
          this.item.demandid = this.scheme.id
          this.item.classify = this.cid
          this.item.classify = this.cid
          this.item.companyname = this.nickname
          //this.item.solution = removeTAG(this.item.solution)
          console.log(this.item)
          this.$axios.post("sdu/solutiondemand/add", this.item).then(res => {
            console.log("已发送方案")
            //console.log(res.data)
          })
          //console.log(this.item)
          this.notice.name=this.item.name
          this.notice.messagetype=1
          this.notice.user = this.$cookies.get("innovation_promotion_username");
          this.getData();
          this.notice.demandname=this.scheme.name
          this.notice.demanduser=this.scheme.user
          // this.notice.demandname=this.demandData.name;
          // this.notice.demanduser=this.demandData.user;
          console.log(this.notice)
          this.$axios.post("sdu/messageNotice/addMessageNotice", this.notice).then(res => {
            this.$message.success('发布成功!')
            console.log(res.data)
            //this.$router.push("/dashboard/analysis")

          })
          if(this.scheme.status!==3){
            this.scheme.status=2


            this.$axios.post('sdu/creative/demandChange',this.scheme).then(res=>{
              console.log("需求状态改变成功")
              this.$router.push("/dashboard/analysis")
            })
          }
        }else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    // async handleSubmit(e) {
    //   e.preventDefault();
    //
    //   this.$refs.ruleForm.validate(async valid=>{
    //     if(valid){
    //       this.item.user = this.$cookies.get("innovation_promotion_username");
    //       this.getData();
    //       this.item.demandid = this.scheme.id
    //       this.item.classify = this.cid
    //       this.item.classify = this.cid
    //       this.item.solution = removeTAG(this.item.solution)
    //       await this.$axios.post("sdu/solutiondemand/add", this.item).then(res => {
    //         this.$message.success('发布成功!')
    //         this.$router.push("/dashboard/analysis")
    //       })
    //       console.log(this.item)
    //     }else {
    //       console.log('error submit!!');
    //       return false;
    //     }
    //   })
    // },

      // this.form.validateFields(async (err, values) => {
      //   if (!err) {
      //     this.item.name = values.name;
      //     this.item.user = this.$cookies.get("innovation_promotion_username");
      //     this.getData();
      //     this.item.solution = removeTAG(this.str)
      //     this.item.description = this.scheme.description
      //     this.item.demandid = this.scheme.id
      //     this.item.classify = this.cid
      //     this.item.applyeffect = values.applyeffect
      //     await this.$axios.post("sdu/solutiondemand/add", this.item).then(res => {
      //       this.$message.success('发布成功!')
      //       this.$router.push("/dashboard/analysis")
      //     })
      //   }
      //
      // });

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
      this.item.date=strDate;
      this.notice.date=strDate;


      //this.item.id=nanoid()
    },
    handleSelectChange(value) {
      console.log(value);
      this.form.setFieldsValue({
        note: `Hi, ${value === 'male' ? 'man' : 'lady'}!`,
      });
    },
    loadTextFile(e) {
      // let self = this
      const file = e.target.files[0];
      const reader = new FileReader();
      reader.onload = (ev) => {
        // self.fileContent = ev.target.result
        // console.log(self.fileContent)
        this.item.solution=ev.target.result
      }
      reader.readAsText(file)
    },
    fileClick(){
      this.$refs.file.click()
    }
  //   printData(){
  //     this.demandData=this.scheme
  //     this.notice.demandname=this.scheme.name
  //     this.notice.demanduser=this.scheme.user
  //
  //     console.log(this.notice.demandname)
  //     console.log(this.notice.demanduser)
  //
  //   },
   },
  mounted() {
    console.log(this.scheme)
    this.item.description=this.scheme.description
  },
  // created() {
  //   this.printData()
  // },
  computed:{
    ...mapGetters(['nickname']),
    scheme(){
      return this.$route.query.scheme
    },
    cid(){
      return this.$route.query.cid
    }
  },
}
</script>

<style scoped>
.ant-form-item {
  margin-bottom: 8px;
}
.box{
  /*border:2px solid #a1a1a1;*/
  /*border-radius:25px;*/
  /*background-color:white;*/
  /*height: 500px;*/
  /*width: 100%;*/
}
#file{
  opacity: 0;
}

</style>