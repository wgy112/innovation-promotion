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
      <div style="margin-top: 40px">
        <a-table
          ref="table"
          size="middle"
          bordered
          rowKey="id"
          :columns="columns_2"
          :dataSource="searchData"
          :pagination="pagination"
          :loading="loading"
          @change="handleTableChange"
          >
          <template slot="action" slot-scope="text, record">
            <a @click="handleInfo(record)">查看详情</a>
          </template>
<!--          <template slot="description" slot-scope="text, record">-->
<!--            <div v-html="description"></div>-->
<!--          </template>-->
<!--          <template slot="status" slot-scope="text" style="color: #f5222d">-->
<!--            {{text}}-->
<!--          </template>-->
        </a-table>
      </div>
  </a-card>
</template>

<script>


const columns_2 = [
  {
    title: '方案名字',
    //dateIndex  指的是每一个key的值
    dataIndex: 'name',
  },
  // {
  //   title: "方案详细",
  //   dataIndex:"description",
  //   ellipsis: true,
  //   scopedSlots: { customRender: 'description' },
  // },
  // {
  //   title: '方案效果',
  //   dataIndex: 'applyeffect',
  //   ellipsis: true,
  // },
  {
    title: '方案来源',
    dataIndex: 'user',
  },
  {
    title: '创建日期',
    dataIndex: 'date',
    defaultSortOrder:'descend',
    sorter: (a, b) =>{return a.date>b.date?1:-1},
    width:'20%'

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
    dataIndex: 'status',
    scopedSlots: { customRender: 'status' },
    customRender:function (text) {
      if(text=='1'){
        return "未查看";
      }else if(text=="2"){
        return "已查看";
      } else if(text=="3"){
        return "已采用";
      } else {
        return text;
      }
    }
  },
];


export default {
  name: 'SchemeScan',
  data() {
    return {
      demandItem:[],
      data:[],
      solutionData:[],
      // columns_1,
      columns_2,
      username: '',
      date:'',
      classify:'',
      id:'',
      pagination: {
        current: 1,
        pageSize: 10,
        pageSizeOptions: ['10', '20', '30'],
        showTotal: (total, range) => {
          return range[0] + '-' + range[1] + ' 共' + total + '条'
        }
      },
      visible: false,
      loading: false,
      writer:"作者：",
      database:[],
      description:'',
      confirmLoading: false,
      searchName:'',
      searchData:[],
      completeSolution:[]
    };
  },
  methods: {

    //  跳转详情页
    handleInfo(record){
      if(record.status===1){
        record.status=2}
      console.log("详情页的数据"+record);
     this.$axios.put('sdu/solutiondemand/editById',record).then(res=>{

       this.$router.push({
         path:"/sdu/SchemeDetail",
         query:{
           solution:record
         }
       })
      }).catch((err)=>{
        console.log(err)
      })




    },

    //返回上一级
    forwardPath(){
      // this.$router.push({
      //   path:'/sdu/enterprise/DemandList'
      // })
      this.$router.go(-1)
      this.$forceUpdate()
    },

    //获取需求数据
    async getDataNode() {
      // console.log(this.$route.query.id)
      await this.$axios.get('sdu/creative/findById', {
        params: {
          id: this.$route.query.id
        }
      }).then(res => {
        // console.log(res.data.result)
        this.data = res.data.result
      })
    },
    //  处理翻页
    handleTableChange(pagination, filters, sorter) {
      this.pagination = pagination
    },

    // 获取的是解决方案的数据
    async getSchemeData() {
      // console.log(this.$route.query.id)
      this.demandItem=JSON.parse(this.$route.query.demandItem,)
      await this.$axios.get('sdu/solutiondemand/findSolutionById', {
        params: {
          demandId: this.demandItem.id
        }
      }).then(res => {
        // console.log(res.data.result)
        console.log("这次的接受数据为"+this.demandItem.id)
        this.solutionData = res.data.result
        this.searchData=res.data.result
        console.log(this.solutionData)
      })
    },
    //  处理  搜索
    onSearch(){
      this.searchData=this.solutionData
      if (this.searchName && this.searchName !== '') {
        this.searchData = this.searchData.filter(
          (p) =>{
            return p.name.indexOf(this.searchName) !== -1
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

    // getJudgeData(){
    //   this.completeSolution=this.$route.query.completeSolution
    //   if(JSON.stringify(this.completeSolution)!==undefined){
    //
    //     this.solutionData = this.completeSolution
    //     this.searchData= this.completeSolution
    //     // console.log((this.searchData))
    //   }else{
    //     this.getSchemeData()
    //   }
    // }

  },
  //   重置搜索框

  created() {
    this.getDataNode()
    this.getSchemeData()
  }
}
</script>

<style scoped>
.ant-table-tbody > tr:hover:not(.ant-table-expanded-row):not(.ant-table-row-selected) > td {
  background: #e6f7ff;
}
</style>

<!--  <div  style="width:70%;margin: 0 auto">-->
<!--    <a-list :grid="{column:2}" item-layout="vertical" size="small" :pagination="pagination" :data-source="database">-->
<!--      <a-list-item slot="renderItem" key="item.title" slot-scope="item, index">-->
<!--        <a-card hoverable  class="demandCard">-->
<!--          <a-row>-->
<!--            <a-col span="16">-->
<!--              <a-list-item-meta :description="writer+item.user">-->
<!--                &lt;!&ndash;            <a slot="title" >{{ item.name }}</a>&ndash;&gt;-->
<!--                <a-avatar style="backgroundColor:#87d068" shape="square" size="large" slot="avatar" icon="user" :src="item.avatar" />-->
<!--                <a slot="title" >{{ item.name }}</a>-->
<!--              </a-list-item-meta>-->
<!--              <a-list-item-meta :description="myJudge(item.classify)" class="classifySet"></a-list-item-meta>-->
<!--              <a-list-item-meta :description="myCut(item.description)" >-->

<!--              </a-list-item-meta>-->

<!--              &lt;!&ndash;              {{ item.classify }}&ndash;&gt;-->
<!--            </a-col>-->
<!--            <a-col span="8">-->
<!--              <img-->
<!--                width="100%"-->
<!--                height="60%"-->
<!--                alt="logo"-->
<!--                src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png"-->
<!--                style="float: right "-->
<!--              />-->
<!--            </a-col>-->
<!--          </a-row>-->
<!--          <template slot="actions" >-->
<!--            <span>-->
<!--              <a-button @click="solutionScan(item.demandid)">-->
<!--                <span key="demandScan" >-->
<!--                <a-icon type="message"  />-->
<!--                  &lt;!&ndash;                 鼠标移动手势&ndash;&gt;-->
<!--                  <span style="cursor: auto">方案详情</span>-->
<!--                </span>-->
<!--              </a-button>-->
<!--            </span>-->
<!--            <span key="viewdetail" >-->
<!--              <a-button  type="link" @click="forwardPath">-->
<!--                <a-icon type="book-o"  />-->
<!--                <span style="cursor: auto">返回需求</span>-->
<!--              </a-button>-->
<!--            </span>-->
<!--          </template>-->
<!--        </a-card>-->
<!--      </a-list-item>-->
<!--    </a-list>-->
<!--  </div>-->