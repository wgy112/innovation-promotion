<template>
  <div>
    <a-list :dataSource="dataSource">
      <a-list-item slot="renderItem" slot-scope="item, index">
        <a-card :hoverable="true" style='width: 100%' @click='showDetail(item)'>
          <template>
            <a-form
              id="components-form-demo-validate-other"
              :form="form"
              v-bind="formItemLayout"
              @submit="handleSubmit"
            >
              <a-form-item label="">
                <span class="ant-form-text">
                  {{item.title}}
                </span>
              </a-form-item>

              <a-form-item label=''>
                <span class='ant-form-text'>
                  {{item.detail}}
                </span>
              </a-form-item>


            </a-form>
          </template>
        </a-card>

        <div id="components-modal-demo-position">
          <!--      <a-button type="primary" @click="() => (modal2Visible = true)">-->
          <!--        Vertically centered modal dialog-->
          <!--      </a-button>-->
          <a-modal
            v-model="modal2Visible"
            title="详细信息"
            centered
            @ok="() => (modal2Visible = false)"
          >
            <p>{{forContent}}</p>
          </a-modal>
        </div>

      </a-list-item>
    </a-list>



  </div>
</template>

<script>
const dataSource=[
  {
    title:'专利1',
    detail:'专利1专利1专利1专利1专利1专利1专利1专利1专利1专利1专利1专利1专利1专利1专利1专利1专利1专利1'
  },
  {
    title:'专利2',
    detail:'专利2专利2专利2专利2专利2专利2专利2专利2专利2'
  },
  {
    title:'专利3',
    detail:'专利3专利3专利3专利3专利3专利3专利3专利3专利3'
  },
]

export default {
  name: 'patent',
  data: () => ({
    dataSource:dataSource,
    ifShow:false,
    modal2Visible: false,
    forContent:"",
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
    showDetail(item) {
      console.log(item);
      this.modal2Visible=true;
      this.forContent = item.detail;
    },
    // setModal1Visible(modal1Visible) {
    //   this.modal1Visible = modal1Visible;
    // },
  },
};
</script>

<style scoped>
#components-form-demo-validate-other .dropbox {
  height: 180px;
  line-height: 1.5;
}
</style>