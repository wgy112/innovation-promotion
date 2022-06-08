<template>
  <a-card>
    <a-form-model ref="ruleForm" :model="scheme" :rules="rules" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" @submit="handleSubmit">
      <a-form-model-item label="方案名称" prop="name">
        <a-input
          v-model="scheme.name"
        />
      </a-form-model-item>
      <a-form-model-item label="企业创新应用需求" prop="description">
        <a-input type="textarea" v-model="scheme.description" style="min-height: 300px"
        />
      </a-form-model-item>

<!--      <a-form-item label="富文本编辑器" style="min-height: 300px" prop="jEditor">-->
<!--        <j-editor/>-->
<!--      </a-form-item>-->
<!--      <a-form-model-item label="方案详情" prop="solution">-->
<!--        <editor-bar v-model="scheme.solution"/>-->
<!--      </a-form-model-item>-->
      <a-form-model-item label="方案详情" prop="applyeffect">
        <JEditor v-model="scheme.solution"/>
        <!--        <a-input type="textarea" v-model="scheme.applyeffect" style="min-height: 200px"-->
        <!--        />-->
      </a-form-model-item>
      <a-form-model-item label="应用效果" prop="applyeffect">
        <JEditor v-model="scheme.applyeffect"/>
<!--        <a-input type="textarea" v-model="scheme.applyeffect" style="min-height: 200px"-->
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
export default {
  name: 'SoulutionCEdit',
  components:{JEditor},
  data(){
    return{
      rules:{
        name: [
          { required: true, message: '请填写方案名称', trigger: 'blur' },
        ],
        description: [
          { required: true, message: '请填写企业创新应用需求', trigger: 'blur' },
        ],
        solution:[
          { required: true, message: '请输入方案详情', trigger: 'blur' },
        ],
        applyeffect:[
          { required: true, message: '请填写应用效果', trigger: 'blur' },
        ],
      },
      formLayout: 'horizontal',
      form: this.$form.createForm(this, { name: 'coordinated' }),
      str:"",
      id:"",
    }
  },
  methods:{
    async handleSubmit(e){
      e.preventDefault();
      //this.str=removeTAG(this.scheme.solution)

      this.$refs.ruleForm.validate(async valid => {
        if (valid) {
          //this.scheme.solution=removeTAG(this.str)
          await this.$axios.put("sdu/solutiondemand/editById",this.scheme).then(res=>{
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
  },
  mounted() {
    this.str=this.scheme.solution
    this.id=this.scheme.id
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