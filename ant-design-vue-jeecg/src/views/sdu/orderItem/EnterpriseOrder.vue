<template>
  <a-card style="height: 800px">

    <div class="table-page-search-wrapper" style="margin-top: 20px">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :span="6">
            <a-form-item label="名称" >
              <a-input  placeholder="请输入需求名称"  v-model="searchName"></a-input>
            </a-form-item>
          </a-col>
          <a-col span="8">
               <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
                 <a-button type="primary"   @click="onSearch">查询</a-button>
                  <a-button type="primary"  icon="reload" style="margin-left: 8px" @click="reSetTable">重置</a-button>
               </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <div style="margin-top: 10px">
      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="orderId"
        :columns="columns_1"
        :dataSource="searchData"
        :pagination="pagination"
        :loading="loading"
        @change="handleTableChange"
      >
        <template slot="title" slot-scope="currentPageData">
          <div style="height: 20px">
            <h3 STYLE="font-weight: bold">企业创新解决方案订单（可根据日期进行排序）</h3>
          </div>
        </template>
        <template slot="action" slot-scope="text, record">
          <a @click="handleInfo(record)">查看详情</a>
        </template>
        <template slot="action_change" slot-scope="text, record">
          <a @click="changeInfo(record)">修改订单</a>
        </template>
        <template slot="action_delete" slot-scope="text, record">
          <a @click="deleteInfo(record)">删除订单</a>
        </template>

      </a-table>
    </div>

    <div class="centerModal" ref='centerModal'>
      <a-modal

        :visible="visible"
        :confirm-loading="confirmLoading"
        @ok="handleOk"
        @cancel="handleCancel"
        class="setModal"
        width="30%"
      >
        <template slot="title">
          {{title}}
        </template>
        <template slot="footer">
          <a-button key="back" @click="handleCancel">
            返回
          </a-button>
          <a-button key="submit" type="primary" :loading="loading" @click="handleOk" v-show="isShow">
            提交
          </a-button>
        </template>
<!--        <div v-bind:class="{formSet:isActive}">-->
          <div >
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

            <a-form-model-item ref="demandPhone"  prop="demandPhone" v-show="showPhone">
              <template slot="label">
                <span style="font-size: medium">
                  企业电话：
                </span>
              </template>
              <div>
                <a-input  v-model="form.demandPhone">{{form.demandPhone}}</a-input>
              </div>
            </a-form-model-item>

            <a-form-model-item ref="demandPhone"  prop="demandPhone"  v-show="!showPhone">
              <template slot="label">
                <span style="font-size: medium">
                  企业电话：
                </span>
              </template>
              <div style="font-weight: bold">
                {{form.demandPhone}}
              </div>
            </a-form-model-item>

            <a-divider style="margin: 8px" v-show="!showPrice"></a-divider>


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
                      供方联系方式：
                    </div>
                  </template>
                  <div>
                    <div    style="font-weight: bold">{{form.suserPhone}}</div>
                  </div>
                </a-form-model-item>
              </a-col>
            </a-row>

            <a-divider style="margin: 8px"></a-divider>


            <a-form-model-item ref="priceSingle"  prop="priceSingle" v-show="showPrice">
                  <template slot="label">
                  <span style="font-size: medium">
                    意向订价：
                  </span>
                  </template>
                  <span>
                  <a-input  placeholder="请输入价格" style="width:50%" v-model.number="form.priceSingle" ></a-input>
                  </span>
                  <span style="margin-left: 2%">
                  ￥
                </span>
            </a-form-model-item>


            <a-form-model-item ref="priceSingle"  prop="priceSingle" v-show="!showPrice">
                  <template slot="label">
                  <span style="font-size: medium">
                    意向订价：
                  </span>
                  </template>
                  <span>
                  <span style="font-weight: bold">{{form.priceSingle }}</span>
                  </span>
                  <span style="margin-left: 2%">
                  ￥
                </span>
            </a-form-model-item>


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

<!--          <a-form-model-->
<!--              ref="ruleForm"-->
<!--              :model="form"-->
<!--              :label-col="labelCol"-->
<!--              :wrapper-col="wrapperCol"-->
<!--              :rules="rules"-->
<!--              layout='vertical'-->
<!--          >-->
<!--              <a-row :gutter="24">-->
<!--                <a-col :span="12">-->
<!--                  <a-form-model-item ref="name" label="企业名称" prop="name">-->
<!--                    <div>-->
<!--                      <a-input  v-model="form.duserRealname" style="pointer-events: none"></a-input>-->
<!--                    </div>-->
<!--                  </a-form-model-item>-->
<!--                </a-col>-->
<!--                <a-col :span="12">-->
<!--                  <a-form-model-item ref="demandPhone" label="企业电话" prop="demandPhone">-->
<!--                    <div>-->
<!--                      <a-input  v-model="form.demandPhone"></a-input>-->
<!--                    </div>-->
<!--                  </a-form-model-item>-->
<!--                </a-col>-->
<!--              </a-row>-->
<!--              <a-row :gutter="24">-->
<!--                <a-col :span="24">-->
<!--                  <a-form-model-item ref="demandAddress" label="企业地址(如 山东省/济南市/历下区/山东大学软件学院)" prop="demandAddress" >-->
<!--                    <div>-->
<!--                      <a-input  placeholder="请输入企业地址" v-model="form.demandAddress"></a-input>-->
<!--                    </div>-->
<!--                  </a-form-model-item>-->

<!--                </a-col>-->
<!--              </a-row>-->

<!--              <a-form-model-item ref="demandName" label="企业需求名称" prop="demandName" v-show="hasDemand">-->
<!--                <div>-->
<!--                  <a-input  v-model="form.demandName" style="pointer-events: none"></a-input>-->
<!--                </div>-->
<!--              </a-form-model-item>-->
<!--              <a-form-model-item ref="solutionName" label="采用方案名称" prop="solutionName">-->
<!--                <div>-->

<!--                  <a-input  v-model="form.solutionName" style="pointer-events: none"></a-input>-->
<!--                </div>-->
<!--              </a-form-model-item>-->

<!--              <a-row :gutter="24">-->
<!--                <a-col :span="12">-->
<!--                  <a-form-model-item ref="orderCreate" label="方案公司/创新工程师" prop="orderCreate">-->
<!--                    <div>-->
<!--                      <a-input  v-model="form.suserRealname" style="pointer-events: none"></a-input>-->
<!--                    </div>-->
<!--                  </a-form-model-item>-->
<!--                </a-col>-->
<!--                <a-col :span="12">-->
<!--                  <a-form-model-item ref="OrderSuccess" label="联系电话" prop="OrderSuccess">-->
<!--                    <div>-->
<!--                      <a-input  placeholder="无" v-model="form.suserPhone" style="pointer-events: none"></a-input>-->
<!--                    </div>-->
<!--                  </a-form-model-item>-->
<!--                </a-col>-->
<!--              </a-row>-->
<!--              <a-row :gutter="24">-->
<!--                <a-col :span="12">-->
<!--                  <a-form-model-item ref="priceSingle" label="方案单价" prop="priceSingle">-->

<!--                    <span>-->
<!--                      <a-input  placeholder="请输入价格" style="width:50%" v-model.number="form.priceSingle" @change="getPriceAll()"></a-input>-->
<!--                    </span>-->
<!--                    <span style="margin-left: 5%">-->
<!--                      ￥-->
<!--                    </span>-->
<!--                  </a-form-model-item>-->
<!--                </a-col>-->
<!--                <a-col :span="12">-->
<!--                  <a-form-model-item ref="orderCount" label="订单数量" prop="orderCount">-->
<!--                    <div>-->
<!--                      <div>-->
<!--                        <a-button type="primary" @click="add" class="btn"><span style="font-weight: bold;color:#DCDCDC">+</span> </a-button>-->
<!--                        <a-input v-model.number="form.orderCount" style="width:40%" class="input" @change="getPriceAll"></a-input>-->
<!--                        <a-button type="primary" @click="sub" class="btn"><span style="font-weight: bold;color:#DCDCDC">-</span></a-button>-->
<!--                        <span style="margin-left: 5%">-->
<!--                            个-->
<!--                        </span>-->
<!--                      </div>-->
<!--                    </div>-->
<!--                  </a-form-model-item>-->
<!--                </a-col>-->
<!--              </a-row>-->
<!--              <a-row :gutter="24">-->
<!--                <a-col :span="12">-->
<!--                  <a-form-model-item ref="priceAll" label="订单总价" prop="priceAll">-->
<!--                    <span>-->
<!--                      <a-input  style="width: 50%;pointer-events: none" v-model.number="form.priceAll"  ></a-input>-->
<!--                    </span>-->
<!--                    <span style="margin-left: 5%">-->
<!--                      ￥-->
<!--                    </span>-->

<!--                  </a-form-model-item>-->
<!--                </a-col>-->
<!--              </a-row>-->

<!--              <a-row :gutter="24">-->
<!--                <a-col :span="12">-->
<!--                  <a-form-model-item ref="orderCreate" label="订单创立时间" prop="orderCreate">-->
<!--                    <div>-->
<!--                      <a-input  v-model="form.orderCreate" style="pointer-events: none"></a-input>-->
<!--                    </div>-->
<!--                  </a-form-model-item>-->
<!--                </a-col>-->
<!--                <a-col :span="12">-->
<!--                  <a-form-model-item ref="OrderSuccess" label="订单确认时间" prop="OrderSuccess">-->
<!--                    <div>-->
<!--                      <a-input  v-model="form.orderSuccess" placeholder="未确认"  style="pointer-events: none"></a-input>-->
<!--                    </div>-->
<!--                  </a-form-model-item>-->
<!--                </a-col>-->
<!--              </a-row>-->
<!--        </a-form-model>-->
        </div>

      </a-modal>
    </div>
  </a-card>
</template>

<script>


const columns_1 = [
  // {
  //   title: '订单需求名称',
  //   dataIndex: 'demandName',
  // },
  {
    title: '订单种类',
    align:"center",
    dataIndex: 'solutionClassify',
    scopedSlots: { customRender: 'solutionClassify' },
    customRender:function (text) {
      if(text=='2'){
        return "通用方案订单";
      }else if(text=="1"){
        return "需求方案订单";
      }  else {
        return text;
      }
    }
  },

  {
    title: '订单方案名称',
    dataIndex: 'solutionName',
    ellipsis: true,

  },
  {
    title: '方案供方',
    dataIndex: 'suserRealname',
    ellipsis: true,
  },
  // {
  //   title: '采购方案数量',
  //   dataIndex: 'orderCount',
  // },
  {
    title: '意向订价(单位：元)',
    dataIndex: 'priceSingle',
  },
  {
    title: '创建日期',
    dataIndex: 'orderCreate',
    defaultSortOrder:'descend',
    sorter: (a, b) =>{return a.orderCreate>b.orderCreate?1:-1},
    // width:'10%'

  },
  {
    title: '操作',
    dataIndex: 'action',
    align: 'center',//设置列内容的对齐方式
    scopedSlots: { customRender: 'action' },

  },
  {
    title: '状态',
    align:"center",
    dataIndex: 'confirmState',
    scopedSlots: { customRender: 'confirmState' },
    customRender:function (text) {
      if(text=='1'){
        return "订单未确认";
      }else if(text=="2"){
        return "订单已确认";
      }  else {
        return text;
      }
    }
  },
  {
    title: '操作',
    dataIndex: 'action_change',
    align: 'center',//设置列内容的对齐方式
    scopedSlots: { customRender: 'action_change' },

  },
  {
    title: '操作',
    dataIndex: 'action_delete',
    align: 'center',//设置列内容的对齐方式
    scopedSlots: { customRender: 'action_delete' },

  },
];


import store from "@/store/"
export default {
  name: 'EnterpriseOrder',
  data() {
    return {
      columns_1,
      solutionData:[],
      pagination: {
        current: 1,
        pageSize: 6,
        pageSizeOptions: ['6', '12', '18'],
        showTotal: (total, range) => {
          return range[0] + '-' + range[1] + ' 共' + total + '条'
        }
      },
      visible: false,
      loading: false,
      database:[],
      confirmLoading: false,
      searchName:'',
      searchData:[],
      count:1 ,
      min:0,
      rules: {
        // demandAddress: [{ required: true, message: '请输入地址', trigger: 'blur' }],
        priceSingle: [{ required: true, message: '请输入采购价格', trigger: 'blur' }],
        // orderCount: [{ required: true, message: '选择方案数量', trigger: 'blur' }],
        demandPhone: [{ required: true, message: '请输入联系人电话', trigger: 'blur' }],
      },
      labelCol: { span: 18 },
      wrapperCol: { span: 24 },
      form:[],
      // 鼠标是否能够点击的开关
      isActive:true,
      //  提交按钮的控制开关
      isShow:false,
      title:"订单详情查看",
      // 控制订单中的需求项的显示
      hasDemand:true,
      orderData:[],

      showPhone:true,
      showPrice:true,




    };

  },
  methods: {

    //  处理翻页
    handleTableChange(pagination, filters, sorter) {
      this.pagination = pagination
    },

    // 获取的是解决方案的数据
    async getOrderData() {
      // console.log(this.$route.query.id)
      // console.log("这次的接受数据为"+store.getters.userInfo.id)

      await this.$axios.get('sdu/enterpriseOrder/queryEnterpriseOrderByUser', {
        params: {
          duserName:store.getters.userInfo.username
        }
      }).then(res => {
        // console.log(res.data.result)
        // console.log("这次的接受数据为"+store.getters.userInfo.id)
        this.solutionData = res.data.result
        this.searchData=res.data.result
        // console.log(this.solutionData)
      })
    },
    //  处理  搜索
    onSearch(){
      this.searchData=this.solutionData
      if (this.searchName && this.searchName !== '') {
        this.searchData = this.searchData.filter(
          (p) =>{
            return p.solutionName.indexOf(this.searchName) !== -1
          }
        )
      }
      else{
        this.searchData=this.solutionData
      }
    },
    reSetTable(){
      this.searchName='';
      this.searchData=this.solutionData
    },

    add(){
      this.form.orderCount++;
      this.getPriceAll()
    },
    sub(){
      if(this.form.orderCount>0)
        this.form.orderCount--
      this.getPriceAll()
    },
    //  跳转详情页
    handleInfo(record){
      this.isShow=false;
      this.showPhone=false;
      this.showPrice=false;
      this.title="订单详情查看"
      this.form=record;
      // console.log(this.form)
      if(this.form.solutionClassify==1){
        // console.log("可以通行，娃回房间")
        this.hasDemand=true;
        this.showModal()
      } else{
        this.hasDemand=false;
        this.showModal();
      }

    },
    showModal() {
      this.visible = true;


    },
    handleOk(e){
      this.$refs.ruleForm.validate(async valid => {
        if (valid) {
          this.confirmLoading = true;
          this.$axios.post('sdu/enterpriseOrder/updateByOrderId', this.form).then(res => {
            this.visible = false;
            this.confirmLoading = false;
            this.$message.info('订单提交成功');

          })
        }})
    },

    //  表单验证
    resetForm() {
      this.$refs.ruleForm.resetFields();
    },
    //  对话框取消
    handleCancel(){
      this.visible = false;

    },
    //  获取总价
    getPriceAll(){
      if(this.form.orderCount!==''&&this.form.priceSingle!==''){
        this.form.priceAll=this.form.orderCount*this.form.priceSingle
      }else{
        this.form.priceAll=0;
      }
    },
    // 修改订单
    changeInfo(record){

       if(record.confirmState==2){
         this.$message.info("订单已经被确认，无法进行修改，请与卖方联系")

       }
       else{
       this.form=record;
       this.title="订单修改";
       this.isShow=true;
       this.showPrice=true;
       this.showPhone=true;
        if(record.solutionClassify==1){
          this.hasDemand=true;
          this.showModal()
        }
        else{
          this.hasDemand=false
          this.showModal();
        }
       }
    },

    //  删除订单
    async deleteInfo(record){
       console.log(record)
       record.orderDelete=0;
       await  this.$axios.post('sdu/enterpriseOrder/updateByOrderId',record).then(res=>{
         console.log("删除是成功")
         this.getOrderData()
       })
    },
    //   获取消息通知中的订单消息
    getNoticeOrder(){
      this.orderData=this.$route.query.orderData
      // console.log(this.orderData)
      if(JSON.stringify(this.orderData)!==undefined){

        this.solutionData = this.orderData
        this.searchData= this.orderData
        // console.log((this.searchData))
      }else{
        this.getOrderData()
      }
    }

  },
  //   重置搜索框

  created() {
    // this.getDataNode()

    this.getNoticeOrder()
  }
}
</script>

<style scoped>
.ant-table-tbody > tr:hover:not(.ant-table-expanded-row):not(.ant-table-row-selected) > td {
  background: #e6f7ff;
}
/deep/ .ant-modal-body{
  height:660px
}

/* 设置表单各项之间的间隔*/
.ant-form-item {
  margin-bottom: 8px;
}

.btn{
  width: 20%;
  background-color:#F5F5F5	;
  text-align: center;

}
.btn:hover{
  background-color: #A9A9A9;
}
.input{
  background-color: #fff;
  background-image: none;
  border-radius: 4px;
  border: 1px solid #dcdfe6;
  box-sizing: border-box;
  color: #606266;
  display: inline-block;
  font-size: inherit;
  text-align: center;

  margin:2px;
}

.formSet{
  pointer-events: none;
}

</style>

