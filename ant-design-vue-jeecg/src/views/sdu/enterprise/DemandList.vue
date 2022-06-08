<template>
  <a-card class="setCard">
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
              <a-button type="primary"  icon="reload" style="margin-left: 8px" @click="reSetTable">重置</a-button>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>



    <div>
      <a-table
        ref="table"
        size="default"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="processData"
        :scroll="{ y: 240 }"
        :pagination=false
        :loading="loading"
        v-show="hide_process"
        @change="handleTableChange">
        <template slot="title" slot-scope="currentPageData">
          <div style="height: 10px">
          <h3 style="font-weight: bold">已有响应方案需求列表</h3>
          </div>
        </template>
<!--        <template #footer>-->
<!--          <div style="height: 1%">-->

<!--          </div>-->
<!--        </template>-->
        <span slot="action" slot-scope="text, record">
          <a @click="showAnnouncement(record)">查看详情</a>
        </span>
        <span slot="demandChange" slot-scope="text, record">
          <a @click="changeAnnouncement(record)">修改需求</a>
        </span>
        <span slot="action2" slot-scope="text, record">
          <a @click="showSolution(record)">方案列表</a>
        </span>
        <span slot="delete" slot-scope="text, record">
          <a-popconfirm
            title="确定要删除该需求?"
            ok-text="确定"
            cancel-text="取消"
            @confirm="processConfirm(record)"
            @cancel="cancel"
          >
            <a>删除</a>
          </a-popconfirm>
        </span>
      </a-table>
    </div>
<!--    <pagination v-show="total>0" :total="total" @page="pages" @pageSize="pageSizes" :currents="form.current" :pageSize="form.size" @pagination="getList" />-->

    <div style="margin-top: 10px">
      <a-table
        ref="table"
        size="default"
        bordered
        rowKey="id"
        :columns="columns2"
        :dataSource="unFinishData"
        :scroll="{ y: 240 }"
        :pagination=false
        :loading="loading"
        v-show="hide_unfinish"
        @change="handleTableChange">
        <template slot="title" slot-scope="currentPageData">
          <div style="height: 10px">
            <h3 STYLE="font-weight: bold">正在征集解决方案需求列表</h3>
          </div>
        </template>
<!--        <template #footer>-->
<!--          <div style="height: 1px">-->

<!--          </div>-->
<!--        </template>-->
        <span slot="action" slot-scope="text, record">
          <a @click="showAnnouncement(record)">查看详情</a>
        </span>
        <span slot="demandChange" slot-scope="text, record">
          <a @click="changeAnnouncement(record)">修改需求</a>
        </span>
        <span slot="delete" slot-scope="text, record">
          <a-popconfirm
            title="确定要删除该需求?"
            ok-text="确定"
            cancel-text="取消"
            @confirm="unFinishConfirm(record)"
            @cancel="cancel"
          >
            <a>删除</a>
          </a-popconfirm>
        </span>
      </a-table>
    </div>

    <div style="margin-top: 10px">
      <a-table
        ref="table"
        size="default"
        bordered
        rowKey="id"
        :columns="columns3"
        :dataSource="finishData"
        :scroll="{ y: 240 }"
        :pagination=false
        :loading="loading"
        v-show="hide_finish"
        @change="handleTableChange">
        <template slot="title" slot-scope="currentPageData">
          <div style="height:10px">
            <h3 style="font-weight: bold">已采纳解决方案需求列表</h3>
          </div>

        </template>
        <template #footer>
          <div style="height: 1px">

          </div>
        </template>
        <span slot="action" slot-scope="text, record">
          <a @click="showAnnouncement(record)">查看详情</a>
        </span>
        <span slot="demandChange" slot-scope="text, record">
          <a @click="changeAnnouncement(record)">修改需求</a>
        </span>
        <span slot="action2" slot-scope="text, record">
          <a @click="showSolution(record)">已完成方案</a>
        </span>
        <span slot="delete" slot-scope="text, record">
          <a-popconfirm
            title="确定要删除该需求?"
            ok-text="确定"
            cancel-text="取消"
            @confirm="finishConfirm(record)"
            @cancel="cancel"
          >
            <a>删除</a>
          </a-popconfirm>
        </span>
      </a-table>

    </div>

    <div>
      <a-table
        ref="table"
        size="default"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="allSelectData"
        :pagination="pagination"
        :loading="loading"
        v-show="hide_select"
        @change="handleTableChange">
        <template slot="title" slot-scope="currentPageData">
          <h3 style="font-weight: bold">搜索需求列表</h3>
        </template>
        <span slot="action" slot-scope="text, record">
          <a @click="showAnnouncement(record)">查看详情</a>
        </span>
        <span slot="demandChange" slot-scope="text, record">
          <a @click="changeAnnouncement(record)">修改需求</a>
        </span>
        <span slot="action2" slot-scope="text, record">
          <a @click="showSolution(record)">方案列表</a>
        </span>
        <span slot="delete" slot-scope="text, record">
          <a-popconfirm
            title="确定要删除该需求?"
            ok-text="确定"
            cancel-text="取消"
            @confirm="processConfirm(record)"
            @cancel="cancel"
          >
            <a>删除</a>
          </a-popconfirm>
        </span>
      </a-table>
    </div>
  </a-card>
</template>
<script>

import store from '@/store/'
export default {
  name:'DemandList',
  data() {
    return {

      refresh:true,
      current:1,
      size:4,
      pagination: {
        onChange: page => {
          console.log(page);
        },
        pageSize: 4,
        size:'small'
      },
      //需求查看的对话框确认变量
      visible: false,
      loading: false,
      writer:"作者：",
      processData:[],
      finishData:[],
      unFinishData:[],
      selectProcessData:[],
      confirmLoading: false,
      data:[],
      date:'',
      allData:[],
      allSelectData:[],
      username:'',
      classify:'',
      id:'',
      searchName:"",
      title:'',
      inject:['reload'],
      columns: [
        {
          title: '需求名称',
          align:"center",
          dataIndex: 'name'
        },
        // {
        //   title: '需求描述',
        //   align:"center",
        //   ellipsis: true,
        //   dataIndex: 'description'
        // },

        {
          title: '需求分类',
          align:"center",
          dataIndex: 'classify',
          customRender:function (text) {
            if(text=='1'){
              return "数字化管理";
            }else if(text=="2"){
              return "个性化定制提升";
            } else if(text=="3"){
              return "网络化协同提升";
            } else if(text=="4"){
              return "服务型制造提升";
            } else if(text=="5"){
              return "中小企业创成";
            } else if(text=="6"){
              return "技术发明创造";
            } else {
              return text;
            }
          }
        },
        {
          title: '发布时间',
          align:"center",
          dataIndex: 'date',
          sort:true,
          defaultSortOrder:'descend',
          sorter: (a, b) =>{return a.date>b.date?1:-1}

        },
        {
          title: '操作',
          dataIndex: 'action',
          align:"center",
          scopedSlots: { customRender: 'action' },
        },{
          title: '操作',
          dataIndex: 'demandChange',
          align:"center",
          scopedSlots: { customRender: 'demandChange' },
        },
        {
          title: '操作',
          dataIndex: 'action2',
          align:"center",
          scopedSlots: { customRender: 'action2' },
        },
        {
          title: '操作',
          dataIndex: 'delete',
          align:"center",
          scopedSlots: { customRender: 'delete' },
        },
        ],
      columns2: [
        {
          title: '需求名称',
          align:"center",
          dataIndex: 'name'
        },
        // {
        //   title: '需求描述',
        //   align:"center",
        //   ellipsis: true,
        //   dataIndex: 'description'
        // },

       {
          title: '需求分类',
          align:"center",
          dataIndex: 'classify',
          customRender:function (text) {
            if(text=='1'){
              return "数字化管理";
            }else if(text=="2"){
              return "个性化定制提升";
            } else if(text=="3"){
              return "网络化协同提升";
            } else if(text=="4"){
              return "服务型制造提升";
            } else if(text=="5"){
              return "中小企业创成";
            } else if(text=="6"){
              return "技术发明创造";
            } else {
              return text;
            }
          }
        },
        {
          title: '发布时间',
          align:"center",
          dataIndex: 'date',
          sort:true,
          defaultSortOrder:'descend',
          sorter: (a, b) =>{return a.date>b.date?1:-1}
        },{
          title: '操作',
          dataIndex: 'action',
          align:"center",
          scopedSlots: { customRender: 'action' },
        },
        {
          title: '操作',
          dataIndex: 'demandChange',
          align:"center",
          scopedSlots: { customRender: 'demandChange' },
        },
        {
          title: '操作',
          dataIndex: 'delete',
          align:"center",
          scopedSlots: { customRender: 'delete' },
        },
        ],
      columns3: [
        {
          title: '需求名称',
          align:"center",
          dataIndex: 'name'
        },
        // {
        //   title: '需求描述',
        //   align:"center",
        //   ellipsis: true,
        //   dataIndex: 'description'
        // },
{
          title: '需求分类',
          align:"center",
          dataIndex: 'classify',
          customRender:function (text) {
            if(text=='1'){
              return "数字化管理";
            }else if(text=="2"){
              return "个性化定制提升";
            } else if(text=="3"){
              return "网络化协同提升";
            } else if(text=="4"){
              return "服务型制造提升";
            } else if(text=="5"){
              return "中小企业创成";
            } else if(text=="6"){
              return "技术发明创造";
            } else {
              return text;
            }
          }
        },

        {
          title: '发布时间',
          align:"center",
          dataIndex: 'date',
          sort:true,
          defaultSortOrder:'descend',
          sorter: (a, b) =>{return a.date>b.date?1:-1}

        },
        {
          title: '操作',
          dataIndex: 'action',
          align:"center",
          scopedSlots: { customRender: 'action' },
        },
        {
          title: '操作',
          dataIndex: 'action2',
          align:"center",
          scopedSlots: { customRender: 'action2' },
        },
        {
          title: '操作',
          dataIndex: 'delete',
          align:"center",
          scopedSlots: { customRender: 'delete' },
        },
      ],
      hide_process:true,
      hide_unfinish:true,
      hide_finish:true,
      hide_select:false,
      completeSolution:[],




    };

  },
  methods:{

    //  搜索需求名
    onSearch(){
      if (this.searchName && this.searchName !== '') {
        this.allSelectData = this.allData.filter(
          (p) =>{
            return p.name.indexOf(this.searchName) !== -1
          }
        );
        this.hide_process=false
        this.hide_finish=false
        this.hide_unfinish=false
        this.hide_select=true

      }
      else {
        this.allSelectData=this.allData
      }

    },

    //  重置表格
    reSetTable(){
      this.searchName='';

       this.hide_select=false;
       this.hide_unfinish=true;
       this.hide_finish=true;
       this.hide_process=true;



    },

    //  查询需求详情
    showAnnouncement(record){
      // console.log("详情"+record)
      this.$router.push({
        path:'/sdu/enterprise/DemandScan',
        query:{
          // demandItem:JSON.stringify(record)
          demandItem:record
        }
      })

    },
    //  修改需求
    changeAnnouncement(record){
      // console.log("详情"+record)
      this.$router.push({
        path:'/sdu/enterprise/DemandChange',
        query:{
          demandItem:record
        }
      })

    },

    //  展示方案详情页
    showSolution(record){
      this.refresh=false;
      this.$nextTick(()=>{
        this.refresh=true;
        // console.log(record)
        this.$router.push({
          path:'/sdu/enterprise/SchemeScan',
          query:{
            demandItem:JSON.stringify(record)
          }
        })
      })
    },

    // 分页、排序、筛选变化时触发
    handleTableChange(pagination, filters, sorter) {
      this.pagination = pagination
      // console.log("this.pagitation"+this.pagination)
    },

    getCookie(){
      // console.log("........"+this.$cookies.get('innovation_promotion_username'))
      return this.$cookies.get('innovation_promotion_username')
    },

    processConfirm(record) {
      // console.log(record);
      this.$message.success('删除成功');
      this.demandDeleteProgress(record)
    },
    unFinishConfirm(record){
      this.$message.success('删除成功');
      this.demandDeleteUnFinish(record)
    },
    finishConfirm(record){
      this.$message.success('删除成功');
      this.demandDeleteFinish(record)
    },
    cancel(e) {
      console.log(e);

    },

    //  删除需求
    async demandDeleteProgress(record){
      console.log(record.id)
      let params = new URLSearchParams()
      params.append('id',record.id)
      await this.$axios.post('sdu/creative/demandDelete',params).then(res=>{
        console.log("删除成功")
        this.getDataProgress()

      //   使页面强制刷新
      }).catch((err)=>{
        console.log(err)
      })

    },
    async demandDeleteUnFinish(record){
      console.log(record.id)
      let params = new URLSearchParams()
      params.append('id',record.id)
      await this.$axios.post('sdu/creative/demandDelete',params).then(res=>{
        console.log("删除成功")
        this.getDataUnFinish()

        //   使页面强制刷新
      }).catch((err)=>{
        console.log(err)
      })

    },
    async demandDeleteFinish(record){
      console.log(record.id)
      let params = new URLSearchParams()
      params.append('id',record.id)
      await this.$axios.post('sdu/creative/demandDelete',params).then(res=>{
        console.log("删除成功")
        this.getDataFinish()

        //   使页面强制刷新
      }).catch((err)=>{
        console.log(err)
      })

    },

    //  已完成的需求
    async getDataFinish(){
      await this.$axios.get('sdu/creative/findByUser',{
        params:{
          user:this.$cookies.get('innovation_promotion_username'),
          status:3
        }
      }).then(res=>{
        // console.log(res.data.result)
        this.finishData =res.data.result
      })
    },
    // 正在征集方案的需求(还没有相对应需求)
    async getDataUnFinish(){
      await this.$axios.get('sdu/creative/findByUser',{
        params:{
          user:this.$cookies.get('innovation_promotion_username'),
          status:1
        }
      }).then(res=>{
        // console.log(res.data.result)
        this.unFinishData =res.data.result
      })
    },
    //  已有相应方案的需求
    async getDataProgress(){
      await this.$axios.get('sdu/creative/findByUser',{
        params:{
          user:this.$cookies.get('innovation_promotion_username'),
          status:2
        }
      }).then(res=>{
        // console.log(res.data.result)
        this.processData =res.data.result
        this.selectProcessData=res.data.result
      })
    },

  //    查询本人的所有需求
    async getAllDemand(){
      await this.$axios.get('sdu/creative/findBySingleUser',{
        params:{
          user:this.$cookies.get('innovation_promotion_username'),
        }
      }).then(res=>{
        this.allData=res.data.result
        this.allSelectData=res.data.result
        console.log(this.allData)
      })
    },

    // async showCompleteSolution(record){
    //   console.log(record)
    //
    //   await this.$axios.get('sdu/solutiondemand/findSolutionByStatus',{
    //     params:{
    //       demandid:record.id,
    //
    //     }
    //   }).then(res=>{
    //     this.completeSolution=res.data.result
    //     console.log(this.completeSolution)
    //     this.refresh=false;
    //     this.$nextTick(()=>{
    //       this.refresh=true;
    //       // console.log(record)
    //       this.$router.push({
    //         path:'/sdu/enterprise/SchemeScan',
    //         query:{
    //           completeSolution:this.completeSolution
    //         }
    //       })
    //     })
    //   })
    // }
  },
  created() {
    console.log(store.getters.nickname)
    this.getCookie()
    setTimeout(()=>{
      this.getDataProgress()
    },200);
    setTimeout(()=>{
      this.getDataFinish()
    },400);
    setTimeout(()=>{
     this.getDataUnFinish()
    },600);
    this.getAllDemand()

  },
  watch:{
    $route (to, from) {
      this.$forceUpdate()
    }

  }

};
</script>

<style scoped>
.ant-pagination{
  padding:0px;
  margin-top: 50px;
}

</style>

