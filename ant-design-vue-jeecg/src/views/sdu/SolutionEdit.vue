<template>
  <a-card>
    <h2>编辑创新方法</h2>
    <a-form-model ref="ruleForm" :model="scheme" :label-col="{ span: 5 }" :rules="rules" :wrapper-col="{ span: 12 }" @submit="handleSubmit">
      <a-form-model-item label="方案名称" prop="name">
        <a-input
          v-model="scheme.name"
        />
      </a-form-model-item>
      <a-form-model-item label="企业名称" prop="companyname">
        <a-input v-model="scheme.companyname"
        />
      </a-form-model-item>
      <a-form-model-item label="企业简介" prop="companyprofile">
        <a-input type="textarea" style="min-height: 200px" v-model="scheme.companyprofile"
        />
      </a-form-model-item>
      <a-form-model-item label="创新应用场景" prop="description">
        <a-input type="textarea" v-model="scheme.description" style="min-height: 200px"
        />
      </a-form-model-item>
      <a-form-model-item label="选择分类" prop="classify">
        <a-radio-group v-model="scheme.classify" @change="onChange">
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
        </a-radio-group>
      </a-form-model-item>
      <a-form-model-item label="方案详情" style="min-height: 200px" prop="solution">
        <JEditor v-model="scheme.solution"/>
      </a-form-model-item>
      <a-form-model-item label="应用效果" prop="applyeffect">
        <JEditor v-model="scheme.applyeffect"/>
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
export default {
  name: 'SoulutionEdit',
  components:{JEditor},
  data(){
    return{
      rules:{
        name: [
          { required: true, message: '请填写方案名称', trigger: 'blur' },
        ],
        companyname: [
          { required: true, message: '请填写企业名称', trigger: 'blur' },
        ],
        companyprofile: [
          { required: true, message: '请填写企业简介', trigger: 'blur' },
        ],
        description: [
          { required: true, message: '请填写企业创新应用需求', trigger: 'blur' },
        ],
        classify: [
          { required: true, message: '请选择分类', trigger: 'change' },
        ],
        solution:[
          { required: true, message: '请输入方案详情', trigger: 'blur' },
        ],
        applyeffect:[
          { required: true, message: '请填写应用效果', trigger: 'blur' },
        ],
      },
      value:"1",
      formLayout: 'horizontal',
      form: this.$form.createForm(this, { name: 'coordinated' }),
      str:"",
      id:"",
      radioStyle: {
        display: 'block',
        height: '30px',
        lineHeight: '30px',
      },
      name:""
    }
  },
  methods:{
    async handleSubmit(e){
      e.preventDefault();
      this.$refs.ruleForm.validate(async valid => {
        if (valid) {
          console.log('34563534');
          //this.str=removeTAG(this.scheme.solution)
          //this.scheme.solution = removeTAG(this.str)
          // await this.$axios.put("sdu/asda").then(res=>{
          //   console.log(this.solution)
          // })
          await this.$axios.put("sdu/solution/editById", this.scheme).then(res => {
            this.$message.success('发布成功!')
            this.$router.push("/dashboard/analysis")
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      })

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
      this.item.date=strDate


      //this.item.id=nanoid()
    },
    onChange(e) {
      console.log('radio checked', e.target.value);
      this.scheme.classify=e.target.value
    },
  },
  mounted() {
    this.str=this.scheme.solution
    this.id=this.scheme.id
    //this.name=this.scheme.name
  },
  computed:{
    scheme(){
      return this.$route.query.solution
    }
  }
}
</script>

<style scoped>

</style>