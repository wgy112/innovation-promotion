<template>
  <div class='box'>
    <a-card class="boxx">
      <a-table
        :columns="columns"
        :data-source="dataSource"
        :pagination='{pageSize:pageSize}'
        rowKey='id'
        @change='handleTableChange'
        v-if="hasReset"
      >
        <template slot="title" slot-scope="currentPageData">
          <h2>针对需求解决办法</h2>
        </template>
        <template slot="view" slot-scope="text, record">
          <a @click="view(record)">查看</a>
        </template>
        <template slot="operation" slot-scope="text, record">
          <a @click="edit(record)">编辑</a>
          <a-divider type="vertical"/>
          <a-popconfirm
            title="确定删除该方案?"
            ok-text="删除"
            cancel-text="取消"
            @confirm="confirm(record)"
            @cancel="cancel"
          >
            <a href="#" @click="deletion(record)">删除</a>
          </a-popconfirm>
<!--          <a @click="deletion(record)">删除</a>-->
        </template>
      </a-table>
<!--      <h3 style="margin-top: -40px;">显示<a-input-number size="small" id="inputNumber" v-model="pageSize" :min="1" :max="10" @change="onChange" />项结果</h3>-->
    </a-card>
    <a-card class="boxx">
      <a-table
        :columns="columns"
        :data-source="dataSource2"
        :pagination='{pageSize:pageSize}'
        rowKey='id'
        @change='handleTableChange'
        v-if="hasReset2"
      >
        <template slot="title" slot-scope="currentPageData">
          <h2>通用创新方案</h2>
        </template>
        <template slot="view" slot-scope="text, record">
          <a @click="view2(record)">查看</a>
        </template>
        <template slot="operation" slot-scope="text, record">
          <a @click="edit2(record)">编辑</a>
          <a-divider type="vertical"/>
          <a-popconfirm
            title="确定删除该方案?"
            ok-text="删除"
            cancel-text="取消"
            @confirm="confirm2(record)"
            @cancel="cancel2"
          >
            <a href="#" @click="deletion(record)">删除</a>
          </a-popconfirm>
          <a-divider type="vertical"/>
          <a @click="popularize(record)">推广</a>
<!--          <a @click="deletion(record)">删除</a>-->
        </template>
      </a-table>
    </a-card>
  </div>
</template>

<script>
import store from '@/store/'
export default {
  name: 'MyPost',
  data(){
    return{
      hasReset:true,
      hasReset2:true,
      user:"",
      dataSource:[],
      dataSource2:[],
      searchName:"",
      hasSelected:true,
      loading: false,
      selectedRowKeys: [],
      columns:[
        {
          title: '标题',
          dataIndex: 'name',
          onFilter: (value, record) => record.name.indexOf(value) === 0,
          sorter: (a, b) => a.name < b.name,
          sortDirections: ['descend','ascend'],
        },
        // {
        //   title:'问题描述',
        //   dataIndex: 'description',
        //   defaultSortOrder: 'ascend',
        //   sorter: (a, b) => a.description > b.description,
        //   ellipsis: true,
        //   // sortDirections: ['descend','ascend'],
        // },
        // {
        //   title: '解决办法',
        //   dataIndex: 'solution',
        //   ellipsis: true,
        // },

        {
          title:'编辑时间',
          dataIndex: 'date',
          sorter:(a,b) => a.date > b.date,
          sortDirections: ['descend','ascend']
        },
        {
          title:'状态',
          dataIndex: 'status',
          customRender:function(text){
            if(text == '0'){
              return "已删除"
            }else if ( text == '1'){
              return "已发布"
            }else if ( text == '2'){//企业用户已查看服务商未查看
              return "✔用户已查看"
            }else if ( text == '3'){//企业用户已采用服务商未查看
              return "⭐已采用"
            }else if(text =='4'){
              return "用户已查看"
            }else if ( text == '3'){
              return "已采用"
            }
          }
        },
        {
          title: '查看',
          dataIndex: "view",
          key:"view",
          scopedSlots:{customRender:'view'},
        },
        {
          title: '操作',
          dataIndex: "operation",
          key:"operation",
          scopedSlots:{customRender:'operation'},
        }
      ],
      pageSize: 5,
    }
  },
  methods:{
    async popularize(record) {
      var json={};
      json.message = record.id;//message记录方案id
      json.senderId = store.getters.userInfo.id
      await this.$axios.post("sys/socketTest/sendAll",json).then(res=>{
        console.log(res)
      })
    },
    async getData(user){
      await this.$axios.get("sdu/solutiondemand/getSolutionByUser",{
        params:{
          user:user
        }
      }).then(res=>{
        this.dataSource=res.data.result
      })
    },
    async getData2(user){
      await this.$axios.get("sdu/solution/findSolutionByUser",{
        params:{
          user:user
        }
      }).then(res=>{
        this.dataSource2=res.data.result
        console.log(res)
      })
    },
    onSearch(){

    },
    showDescription2(record){

    },

    confirm2(record) {
      console.log(record);
      record.status = 0;
      this.hasReset2 = false
      this.$nextTick(async () => {
        await this.$axios.put("sdu/solution/deleteById", record).then(res => {
          this.$message.success('成功删除!')
          //this.$router.push("/dashboard/analysis")
          this.getData2(this.user)
          this.hasReset2 = true
        })
      })
    },
    cancel2(e) {
      console.log(e);
      this.$message.error('Click on No');
    },

    confirm(record){
      record.status = 0;
      this.hasReset = false
      this.$nextTick(async () => {
        await this.$axios.put("sdu/solutiondemand/deleteById", record).then(res => {
          this.$message.success('成功删除!')
          //this.$router.push("/dashboard/analysis")
          this.getData(this.user)
          this.hasReset = true
        })
      })
    },
    cancel(e) {
      console.log(e);
      this.$message.error('Click on No');
    },
    start() {
      //this.reload()
      this.loading = true;
      // ajax request after empty completing
      setTimeout(() => {
        this.loading = false;
        this.selectedRowKeys = [];
      }, 1000);
    },
    onSelectChange(selectedRowKeys) {
      console.log('selectedRowKeys changed: ', selectedRowKeys);
      this.selectedRowKeys = selectedRowKeys;
    },
    handleTableChange(pagination, filters, sorter){
      console.log(pagination,filters,sorter);
      const pager = { ...this.pagination };
      pager.current = pagination.current;
      this.pagination = pager;
      // this.fetch({
      //   results: pagination.pageSize,
      //   page: pagination.current,
      //   sortField: sorter.field,
      //   sortOrder: sorter.order,
      //   ...filters,
      // });
    },
    onChange(value) {
      console.log('changed', value);
    },
    edit(record){
      console.log(record)
      this.$router.push({
        path:'/sdu/solutionCedit',
        query:{
          solution:record
        }
      })
    },
    edit2(record){
      console.log(record)
      this.$router.push({
        path:'/sdu/solutionedit',
        query:{
          solution:record
        }
      })
    },
    view(record){
      console.log(record)
      this.$router.push({
        path:'/sdu/schemedetail',
        query:{
          solution:record
        }
      })
    },
    view2(record){
      this.$router.push({
        path:'/sdu/schemedetail',
        query:{
          solution:record
        }
      })
    },
    deletion(record){

    }
  },
  mounted(){
    this.user=this.$cookies.get("innovation_promotion_username")
    this.getData(this.user)
    this.getData2(this.user)
  },

}
</script>

<style scoped>
/*.boxx{*/
/*  width: 90%;*/
/*  margin-left: 5%;*/
/*}*/
</style>