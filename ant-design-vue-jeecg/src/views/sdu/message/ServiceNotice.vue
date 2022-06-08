<template>
  <a-card :bordered="false"  style="height:800px">
    <div class="table-page-search-wrapper" >
      <a-form layout="inline" >
        <a-row :gutter="24">
          <a-col :span="6">
            <a-form-item label="标题">
              <a-input placeholder="请输入需求名称进行模糊搜索" v-model="searchName"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary"  icon="search" @click="onSearch">查询</a-button>
              <a-button type="primary"  icon="reload" style="margin-left: 8px">重置</a-button>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!--    <div class="table-operator">-->
    <!--      <a-button type="primary"  icon="book">全部标注已读</a-button>-->
    <!--    </div>-->

    <a-table
      ref="table"
      size="default"
      bordered
      rowKey="id"
      :columns="columns"
      :dataSource="dataSource"
      :pagination="pagination"
      :loading="loading"
      @change="handleTableChange">
      <span slot="action" slot-scope="text, record">
        <a @click="showAnnouncement(record)">查看</a>
      </span>
      <span slot="deleteState" slot-scope="text, record">
        <a @click="deleteState(record)">删除</a>
      </span>
      <template slot="readstate" slot-scope="text, record">
        <div>{{getReadState(text,record)}}</div>
      </template>
    </a-table>


  </a-card>
</template>

<script>

import moment from 'dayjs'

export default {
  name: "MessageNotice",

  data () {
    return {

      searchName:'',
      description: '系统通告表管理页面',
      queryParam: {},
      columns: [
        {
          title: '消息类型',
          align: "center",
          dataIndex: 'messagetype',
          customRender: function (text) {
            if (text == '2') {
              return "需求订单消息";
            } else if (text == "1") {
              return "方案消息";
            } else if (text == "3") {
              return "通用订单消息";
            } else {
              return text;
            }
          }
        },
        // {
        //   title: '需求名称',
        //   align:"center",
        //   dataIndex: 'demandname'
        // },
        {
          title: '方案名称',
          align:"center",
          dataIndex: 'name',
          ellipsis:true,
        },
        // {
        //   title: '方案作者',
        //   align:"center",
        //   dataIndex: 'user'
        // },
        {
          title: '提交方案状态',
          align:"center",
          dataIndex: 'readstate',
          scopedSlots: { customRender: 'readstate' },
        },
        {
          title: '发布时间',
          align:"center",
          dataIndex: 'date',
          defaultSortOrder:'descend',
          sorter: (a, b) =>{return a.date>b.date?1:-1}
        },
        {
          title: '消息阅读状态',
          align:"center",
          dataIndex: 'servicestate',
          customRender: function (text) {
            if (text == '0') {
              return "已读";
            } else if (text == "1") {
              return "未读";
            } else {
              return text;
            }
          }
        },
        {
          title: '操作',
          dataIndex: 'action',
          align:"center",
          scopedSlots: { customRender: 'action' },
        },

        {
          title: '删除',
          dataIndex: 'deleteState',
          align:"center",
          scopedSlots: { customRender: 'deleteState' },
        },

      ],

      dataSource:[],
      loading:false,
      openPath:'',
      formData:'',
      dataBase:[],
      scheme:[],
      testSource:[],
      pagination: {
        current: 1,
        pageSize: 10,
        pageSizeOptions: ['10', '20', '30'],
        showTotal: (total, range) => {
          return range[0] + '-' + range[1] + ' 共' + total + '条'
        }
      },
      item:{
        name:'',
        description:'',

      },
      orderData:[],
    }
  },
  methods: {

    //  搜索方法：
    onSearch(){
      if (this.searchName && this.searchName !== '') {
        this.dataSource = this.dataBase.filter(
          (p) =>{
            return p.demandname.indexOf(this.searchName) !== -1
          }
        )
      } else {
        this.dataSource = this.dataBase
      }
    },

    async getMessage(){
      await this.$axios.get('sdu/messageNotice/messagePostByServiceUser',{
        params:{
          user:this.$cookies.get('innovation_promotion_username')
        }
      }).then(res=>{
        this.dataBase=res.data.result
        this.dataSource=res.data.result
        // console.log(this.dataSource)
      }).catch((err)=>{
        console.log(err)
      })

    },

    //   查看详情
    showAnnouncement(record){
      this.getAnnouncement(record)
      record.servicestate=0;

      //设置企业需求用户为可见这条消息

      record.orderstate=1
      // this.getOrderDate(record)
      // this.$axios.post('sdu/messageNotice/setReadState',record).then(res=>{
      //   console.log("更新状态成功")
      // })
      this.updateState(record)

    },

    //  根据方案名字查询整个方案的数据
     getAnnouncement(record){
      // console.log(record.name)
      if(record.messagetype==1){
         this.$axios.get('sdu/solutiondemand/findSolutionByName',{
        params:{
          name:record.name,
        }
      }).then(
        res=>{

        // this.scheme=JSON.parse(JSON.stringify(res.data.result))
        this.scheme=res.data.result

        // console.log(this.scheme[0])
        this.testSource=this.scheme[0]
        console.log(this.testSource)

          this.$router.push({
            path:'/sdu/SchemeDetail',
            query:{
              solution:this.scheme[0]
            }
          })


      }).catch((err)=>{
        console.log(err)
      })
      //    2  表示单一需求方案的消息通知
      }else if(record.messagetype==2)
      {
          this.$axios.get("sdu/enterpriseOrder/queryOrderByThreeName",{
          params:{
            duserName:record.demanduser,
            suserName:record.user,
            solutionName:record.name
          }
        }).then(res=>{
          this.orderData=res.data.result
          // console.log(this.orderData)
          this.$router.push({
            path:'/sdu/orderItem/ServiceOrder',
            query:{
              orderData:this.orderData
            }
          })
        })
      }else if(record.messagetype==3){
          this.$axios.get("sdu/enterpriseOrder/queryOrderByThreeName",{
          params:{
            duserName:record.demanduser,
            suserName:record.user,
            solutionName:record.name
          }
        }).then(res=>{
          this.orderData=res.data.result
          // console.log(this.orderData)
          this.$router.push({
            path:'/sdu/orderItem/ServiceOrder',
            query:{
              orderData:this.orderData
            }
          })
        })

      }
    },
     updateState(record){
      this.getOrderDate(record)
       this.$axios.post('sdu/messageNotice/setReadState',record
      ).then(res=>{
        console.log("发送成功")
      }).catch((err)=>{
        console.log(err)
      })

    },
    getOrderDate(record){
      const date = new Date()
      const year = date.getFullYear() //月份从0~11，所以加一
      const dateArr = [date.getMonth() + 1, date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds()]
      for(let i=0; i<dateArr.length; i++){
        if (dateArr[i] >= 1 && dateArr[i] <= 9) {
          dateArr[i] = "0" + dateArr[i];
        }
      }
      const strDate = year + '-' + dateArr[0] + '-' + dateArr[1] + ' ' + dateArr[2] + ':' + dateArr[3] + ':' + dateArr[4]
     record.date=strDate

    },

    //  删除消息
    async deleteState(record){
      record.servicestate=2;
      await this.$axios.post('sdu/messageNotice/setServiceState',record).then(res=>{
        console.log("删除成功")
        this.getMessage()
      }).catch((err)=>{
        console.log(err)
      })
    },
    // getCookie() {
    //   console.log(this.$cookies.keys())
    // },



    handleTableChange(pagination, filters, sorter) {
      this.pagination = pagination
    },

  // 阅读状态设置
    getReadState(text,record){
      if(text=='1'&& record.messagetype=='1'){
        return "用户未读";
      }else if(text=="0" && record.messagetype=='1'){
        return "用户已读";
      }else if( record.messagetype=='2'||record.messagetype=='3'){
        return "未确认订单";
      }
      else {
        return text;
      }
    }


  },
  created() {
    this.getMessage()
    // console.log(this.$cookies.keys)
  }
}
</script>
<style scoped>
.ant-card-body .table-operator{
  margin-bottom: 18px;
}
.anty-row-operator button{margin: 0 5px}
.ant-btn-danger{background-color: #ffffff}z

                                          .ant-modal-cust-warp{height: 100%}
.ant-modal-cust-warp .ant-modal-body{height:calc(100% - 110px) !important;overflow-y: auto}
.ant-modal-cust-warp .ant-modal-content{height:90% !important;overflow-y: hidden}
</style>