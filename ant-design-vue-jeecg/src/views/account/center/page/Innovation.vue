<template>
  <a-list :dataSource="dataSource">
    <a-list-item slot="renderItem" slot-scope="item, index">
      <a-card :hoverable="true" style='width: 100%'>
        <template>
          <a-form
            id="components-form-demo-validate-other"
            :form="form"
            v-bind="formItemLayout"
            @submit="handleSubmit"
          >
            <a-form-item label="标题">
              <span class="ant-form-text">
                {{item.title}}
              </span>
            </a-form-item>

            <a-form-item label="分类">
  <!--            <a-input-number v-decorator="['input-number', { initialValue: 3 }]" :min="1" :max="10" />-->
              <span class="ant-form-text">
                {{item.category}}
              </span>
            </a-form-item>
            <a-form-item label='详细描述'>
              <span class='ant-form-text'>
                {{item.detail}}
              </span>
            </a-form-item>

            <a-form-item label="评分">
<!--              <a-rate v-decorator="['rate', { initialValue: 3.5 }]" allow-half />-->
              <a-rate :default-value='2.5' allow-half />
            </a-form-item>

            <a-form-item label="上传" >
              <a-upload
                v-decorator="[
            'upload',
            {
              valuePropName: 'fileList',
              getValueFromEvent: normFile,
            },
          ]"
                name="logo"
                action="/upload.do"
                list-type="picture"
              >
                <a-button> <a-icon type="upload" /> Click to upload </a-button>
              </a-upload>
            </a-form-item>

          </a-form>
        </template>

      </a-card>
    </a-list-item>
  </a-list>
</template>

<script>
const dataSource=[
  {
    title:'关于服务计算的创新应用平台1',
    category:'机械类',
    detail:'容机械类关于服务计算的创新应用平台详细内容机械类关于服务计算的创新应用平台详细内容机械类关于服务计算的创新应用平台详细内容'
  },
  {
    title:'关于服务计算的创新应用平台2',
    category:'生活类',
    detail:'生活类关于服务计算的创新应用平台生活类关于服务计算的创新应用平台生活类关于服务计算的创新应用平台生活类关于服务计算的创新应用平台生活类关于服务计算的创新应用平台生活类关于服务计算的创新应用平台'
  },
  {
    title:'关于服务计算的创新应用平台3',
    category:'技术类',
    detail:'技术类关于服务计算的创新应用平台技术类关于服务计算的创新应用平台技术类关于服务计算的创新应用平台技术类关于服务计算的创新应用平台技术类关于服务计算的创新应用平台技术类关于服务计算的创新应用平台技术类关于服务计算的创新应用平台'
  },
]
// for (let i = 0; i < 11; i++) {
//   dataSource.push({
//     title: 'Alipay',
//     activeUser: 17,
//     newUser: 1700
//   })
// }
export default {
  name: 'Innovation',

  data: () => ({
    dataSource:dataSource,
    formItemLayout: {
      labelCol: { span: 6 },
      wrapperCol: { span: 14 },
    },
  }),
  beforeCreate() {
    this.form = this.$form.createForm(this, { name: 'validate_other' });
  },
  methods: {
    handleSubmit(e) {
      e.preventDefault();
      this.form.validateFields((err, values) => {
        if (!err) {
          console.log('Received values of form: ', values);
        }
      });
    },
    normFile(e) {
      console.log('Upload event:', e);
      if (Array.isArray(e)) {
        return e;
      }
      return e && e.fileList;
    },
  },
};
</script>

<style scoped>
  #components-form-demo-validate-other .dropbox {
    height: 180px;
    line-height: 1.5;
  }
</style>