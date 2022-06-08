<template>
  <div >
    <div>
      <a-input-search style='width: 300px;margin-left: 20%;margin-top: 20px;margin-bottom: 20px'
                      placeholder="方案名称模糊搜索"
                      enter-button
                      v-model="searchName"
                      @search="onSearch"
      />
    </div>
    <div class="classright">
      <a-list :grid="{column:2}" item-layout="vertical" size="small" :pagination="pagination" :data-source="datalt">
        <!--      <div slot="footer"><b>ant design vue</b> footer part</div>-->

        <a-list-item slot="renderItem" key="item.title" slot-scope="item, index">
          <a-card  hoverable class="hu">

            <img
              width="40%"
              height="30%"
              alt="logo"
              src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png"
              style="float: right"
            />
            <a-list-item-meta :description="item.user">
              <a slot="title" :href="item.href">{{ item.name }}</a>
              <a-avatar style="backgroundColor:#87d068" shape="square" size="large" slot="avatar" icon="user" :src="item.avatar" />
            </a-list-item-meta>
<!--            <div class="textarea">-->
<!--              {{ item.description }}-->
<!--            </div>-->
            <template slot="actions">
              <a-button @click="addscheme(item)" type="link">
                <span key="addscheme" >
                <a-icon type="message"  />
                <span style="cursor: auto">
                  添加方案
                </span>
                </span>
              </a-button>
              <span key="viewdetail" >
                <a-button @click="viewdetail(item)" type="link">
                  <a-icon type="book-o"  />
                  <span style="cursor: auto">
                    详细信息
                  </span>
                </a-button>
              </span>
            </template>
          </a-card>
        </a-list-item>
      </a-list>
    </div>
<!--    <div class="classright" style="width: 90%;margin-left: 5%;">-->
<!--      <a-list item-layout="vertical" size="small" :pagination="pagination" :data-source="datalt">-->
<!--        <a-list-item slot="renderItem" key="item.title" slot-scope="item, index">-->
<!--            <a slot="actions">查看详情</a>-->
<!--            <a slot="actions">添加评论</a>-->
<!--          <img-->
<!--            slot="extra"-->
<!--            width="272"-->
<!--            alt="logo"-->
<!--            src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png"-->
<!--          />-->
<!--          <a-list-item-meta>-->
<!--            <a slot="title" :href="item.href">{{ item.name }}</a>-->
<!--&lt;!&ndash;            <a-avatar slot="avatar" :src="item.avatar" />&ndash;&gt;-->
<!--          </a-list-item-meta>-->
<!--          {{ item.description }}-->
<!--        </a-list-item>-->
<!--      </a-list>-->
<!--    </div>-->


  </div>
</template>

<script>
const listData = [];
export default {
  name: 'DemandClassify',
  data(){
    return{
      searchName:"",
      datalt:listData,
      listData,
      pagination: {
        onChange: page => {
          console.log(page);
        },
        pageSize: 4,
      },
    }
  },
  methods:{
    onSearch(){
      if (this.searchName && this.searchName !== '') {
        this.datalt = this.listData.filter(
          (p) =>{
            return p.name.indexOf(this.searchName) !== -1
          }
        )
      } else {
        this.datalt = this.listData
      }
    },
    addscheme(item){
      console.log(item)
      this.$router.push({
        path:'/solutionclassify',
        query:{
          scheme:item,
          cid:this.cid
        }
      })
    },
    async searchSolutionNumber(id){
      let a =0
      await this.$axios.get("sdu/solutiondemand/searchnumberbyid",{
        params:{
          id:id
        }
      }).then(res=>{
        a=res.data
      });
      return a;
      console.log(a)
    },
    async getData(){
      await this.$axios.get("sdu/creative/findByCid",{
        params:{
          cid:this.cid
        }
      }).then(res=>{
        this.listData=res.data.result;
        this.datalt =res.data.result;
      });
    },
    async viewdetail(record){
      this.$router.push({
        path:'/sdu/enterprise/DemandScan2',
        query:{
          demandItem:record,
        }
      })
      // this.description = record.description
      // let solutionNumber = await this.searchSolutionNumber(record.id)
      // console.log(solutionNumber)
      // const h = this.$createElement;
      // this.$info({
      //   title: '详细信息',
      //   content: h('div', {}, [
      //     h('p', `${this.description}`),
      //     h('mark', `已有${solutionNumber}条解决方案`),
      //     h('p',`发布时间:${record.date}`)
      //   ]),
      //   onOk() {
      //   },
      // });
    },
    do(){
      this.getData()
    }
  },
  watch:{
    '$route.path':function(newVal,oldVal){
      if(newVal === '/demand/classify'){
        this.do()
      }
    },
  },
  mounted() {
    this.getData()
  },
  computed:{
    cid:function (){
         return this.$route.query.cid
    }
  },

}
</script>

<style scoped>
.hu{
  transiton:all .4s;
  /*cursor: pointer;*/
  border: 2px solid #afafaf;
  border-radius: 25px;
  padding: 20px;
  height: 298px;
  margin-top: 5px;
  margin-left: 8px;
}
.hu:hover{
  transform: scale(1.02);
  background-color: #f7f1f1;

}
.classright{
  width: 70%;
  margin: 0 auto;
}
.classleft{
  width: 28%;
  height:60px;
}
.listitem{
  /*height: 200px;*/
  /*margin-top: 50px;*/
}
.textarea{
  height: 60px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
}
.ant-col-5 {
  width: 20%;
}
</style>