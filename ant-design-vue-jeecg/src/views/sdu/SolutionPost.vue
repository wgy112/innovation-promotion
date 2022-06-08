<template>
<!--  <a-card class="box">-->
<!--    <h2>创新方法发布</h2>-->
<!--    <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" @submit="handleSubmit">-->
<!--      <a-form-item label="方案名称">-->
<!--        <a-input-->
<!--          v-decorator="['name', { rules: [{ required: true, message: '请输入方案名称!' }] }]"-->
<!--        />-->
<!--      </a-form-item>-->
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
<!--      <a-form-model-item label="创新应用场景">-->
<!--        <a-input type="textarea" style="min-height: 200px"-->
<!--                 v-decorator="['description', { rules: [{ required: true, message: '请输入企业创新应用需求!' }] }]"-->
<!--        />-->
<!--      </a-form-model-item>-->
<!--      <a-form-item label="选择分类">-->
<!--        <a-radio-group v-model="value" v-decorator="{ rules: [{ required: true, message: '请输入问题描述!' }] }">-->
<!--          <a-radio :style="radioStyle" :value="1">-->
<!--            数字化管理-->
<!--          </a-radio>-->
<!--          <a-radio :style="radioStyle" :value="2">-->
<!--            个性化定制提升-->
<!--          </a-radio>-->
<!--          <a-radio :style="radioStyle" :value="3">-->
<!--            网络化协同提升-->
<!--          </a-radio>-->
<!--          <a-radio :style="radioStyle" :value="4">-->
<!--            服务型制造提升-->
<!--          </a-radio>-->
<!--          <a-radio :style="radioStyle" :value="5">-->
<!--            中小企业创成-->
<!--          </a-radio>-->
<!--        </a-radio-group>-->
<!--      </a-form-item>-->
<!--      <a-form-item label="方案详情" style="min-height: 300px" prop="jEditor">-->
<!--        <editor-bar v-model="str"/>-->
<!--      </a-form-item>-->
<!--      <a-form-item>-->
<!--        <a-row :gutter="24">-->
<!--            <a-form-model-item label="MarkdownEditor" prop="content" style="min-height: 300px">-->
<!--              <j-markdown-editor v-model="formData.content"></j-markdown-editor>-->
<!--            </a-form-model-item>-->
<!--        </a-row>-->
<!--      </a-form-item>-->
<!--      <a-form-item label="应用效果">-->
<!--        <a-input type="textarea" style="min-height: 200px"-->
<!--                 v-decorator="['applyeffect', { rules: [{ required: true, message: '请输入企业创新应用需求!' }] }]"-->
<!--        />-->
<!--      </a-form-item>-->
<!--      <a-form-item :wrapper-col="{ span: 12, offset: 5 }">-->
<!--        <a-button type="primary" html-type="submit">-->
<!--          发布-->
<!--        </a-button>-->
<!--      </a-form-item>-->
<!--    </a-form>-->
<!--  </a-card>-->
  <a-card>
    <h2>编辑创新方法</h2>
    <a-form-model ref="ruleForm" :model="item" :label-col="{ span: 5 }" :rules="rules" :wrapper-col="{ span: 12 }" @submit="handleSubmit">
      <a-form-model-item label="方案名称" prop="name">
        <a-input
          v-model="item.name"
        />
      </a-form-model-item>
      <a-form-model-item label="企业名称" prop="companyname">
        <a-input v-model="item.companyname"
        />
      </a-form-model-item>
      <a-form-model-item label="企业简介" prop="companyprofile">
        <a-input type="textarea" style="min-height: 200px" v-model="item.companyprofile"
        />
      </a-form-model-item>
      <a-form-model-item label="创新应用场景" prop="description">
        <a-input type="textarea" v-model="item.description" style="min-height: 200px"
        />
      </a-form-model-item>
      <a-form-model-item label="选择分类" prop="classify">
        <a-radio-group v-model="item.classify" @change="onChange">
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
<!--        <a-form-model-item label="富文本编辑器" style="min-height: 300px" prop="jEditor">-->
<!--          <JMarkdownEditor v-model="item.solution"/>-->
<!--        </a-form-model-item>-->
      <!--      <a-form-model-item label="方案详情" style="min-height: 200px" prop="jEditor">-->
      <!--        <editor-bar v-model="str"/>-->
      <!--      </a-form-model-item>-->
      <a-form-model-item label="方案详情" style="min-height: 200px" prop="solution">
        <J-editor v-model="item.solution"/>
        <a-alert message="您也可以使用mindfall工具获得的txt文件上传您的解决方案" type="warning"/>
        <label class="text-reader" >
          <input type="file" ref="file" id="file" @change="loadTextFile" style="margin-top: -40px">
          <a-button icon="upload" @click="fileClick()">导入mindfall工具创意方案</a-button>
        </label>
      </a-form-model-item>

      <a-form-model-item label="应用效果" prop="applyeffect">
        <J-editor v-model="item.applyeffect"/>
<!--        <J-editor v-model="this.string"/>-->

<!--        <a-input type="textarea" style="min-height: 200px" v-model="item.applyeffect" />-->
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
import JEditor from '@/components/jeecg/JEditor'
import { removeTAG } from './components/removeTAG';
import { mapGetters } from 'vuex';
import JMarkdownEditor from '@/components/jeecg/JMarkdownEditor/index';
import axios from "axios"
export default {
  name: 'SolutionPost',
  components:{JMarkdownEditor,JEditor},
  data(){
    return{
      value:1,
      str:"",
      item:{
        user:"",
        name:"",
        description:"",
        date:"",
        companyname:"",
        solution:"",
        classify:"",
        companyprofile:"",
        applyeffect:""
      },
      radioStyle: {
        display: 'block',
        height: '30px',
        lineHeight: '30px',
      },
      formLayout: 'horizontal',
      form: this.$form.createForm(this, { name: 'coordinated' }),
      formData:{
        content:""
      },
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

    }
  },
  methods: {
    bd(){
      this.item.applyeffect = this.string2
    },
    save(){
      request.post("/solution/save", this.draft).then(res => {
        if(res.code === '200') {
          this.sendSuccessMessage('保存成功')
        } else {
          this.sendWarnMessage('保存失败')
        }
      })
    },
    imgAdd(pos, $file) { //上传图片
      let $vm = this.$refs.md
      // 第一步.将图片上传到服务器.
      const formData = new FormData();
      formData.append('file', $file);
      axios({
        url: "http://127.0.0.1:8080/jeecg-boot/file/upload/",
        method: 'post',
        data: formData,
        headers: {'Content-Type': 'multipart/form-data'},
      }).then((res) => {
        // 第二步.将返回的url替换到文本原位置![...](./0) -> ![...](url)
        console.log(res)
        $vm.$img2Url(pos, res.data.url);
      })
    },
    change(value, render) {
      console.log(render)
      console.log(value)
    },
    async handleSubmit(e) {
      e.preventDefault();
      this.$refs.ruleForm.validate(async valid => {
        if (valid) {
          this.item.user = this.$cookies.get("innovation_promotion_username");
          this.getData();
          await this.$axios.post("sdu/solution/add", this.item).then(res => {
            console.log(this.item)
            this.$message.success('发布成功!')
            this.$router.push("/dashboard/analysis")
          })
        }
      })
      // this.form.validateFields(async (err, values) => {
      //   if (!err) {
      //     //this.item.name = values.name;
      //     //this.item.description = values.description;
      //     this.item.user = this.$cookies.get("innovation_promotion_username");
      //     //this.item.companyname = values.companyname
      //     //this.item.companyprofile = values.companyprofile
      //     //this.item.applyeffect = values.applyeffect
      //     this.getData();
      //     //this.item.classify=this.value,
      //     //this.item.solution = removeTAG(this.str)
      //     console.log(this.item)
      //     await this.$axios.post("sdu/solution/add", this.item).then(res => {
      //       console.log(this.item)
      //       this.$message.success('发布成功!')
      //       this.$router.push("/dashboard/analysis")
      //     })
      //   }
      // });
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
    handleSelectChange(value) {
      console.log(value);
      this.form.setFieldsValue({
        note: `Hi, ${value === 'male' ? 'man' : 'lady'}!`,
      });
    },
    onChange(e) {
      console.log('radio checked', e.target.value);
      this.item.classify=e.target.value
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
  },
  computed:{
    ...mapGetters(['nickname'])
  },
  mounted() {
    this.item.companyname = this.nickname
  }
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