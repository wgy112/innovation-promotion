<template>
  <a-card>
    <h2>{{this.str}}</h2>
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :md="12" :sm="8">
            <a-form-item label="名称" :labelCol="{span: 5}" :wrapperCol="{span: 18, offset: 1}">
              <a-input v-model="searchName"></a-input>
            </a-form-item>
          </a-col>
          <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
            <a-col :md="6" :sm="24">
              <a-button type="primary" @click="searchOn">搜索</a-button>
              <a-button style="margin-left: 8px" @click="searchRest">重置</a-button>
            </a-col>
          </span>
        </a-row>
      </a-form>
<!--      <div style="margin-left: 8px;margin-bottom: 10px">-->
<!--        &lt;!&ndash;          {{ `显示第${begin}至${end}项结果,共${sum}项` }}&ndash;&gt;-->
<!--        <a-input-search style='width: 300px;margin-left: 5%;margin-bottom: 10px;margin-top: 8px'-->
<!--                        placeholder="方案名称模糊搜索"-->
<!--                        enter-button-->
<!--                        v-model="searchName"-->
<!--                        @search="onSearch"-->
<!--        />-->
<!--      </div>-->
      <div class="boxx" style="width: 90%;margin-left: 5%;">
        <a-list :grid="{ gutter: 16}" :data-source="dataSource" item-layout="vertical" :pagination="pagination" :loading="loading">
          <a-list-item slot="renderItem" slot-scope="item, index">
            <a-card class="card" hoverable :title="item.name" size="small" @click="showDescription(item)">
              <!--          <h3>问题描述：{{item.description}}</h3>-->
              <a slot="extra" href="#">{{item.date}}</a>
              <a-card-meta :title="item.companyname" :description="item.description">
                <a-avatar
                  slot="avatar"
                  style="backgroundColor:#87d068" icon="user"
                  shape="square"
                  size="large"
                />
              </a-card-meta>
            </a-card>
          </a-list-item>
        </a-list>
      </div>
    </div>
  </a-card>

</template>

<script>
const data = [];
export default {
  name: 'SchemeView.vue',
  data(){
    return{
      dataSource: [],
      loading: false,
      data,
      searchName: '',
      pagination: {
        onChange: page => {
          console.log(page)
        },
        pageSize: 5
      },
      datalt: data,

    }
  },
  methods:{
    async getData(){
      let res = await this.$axios.get("sdu/solution/findSolutionByClassify",{
        params:{
          classify:this.classify
        }
      });
      this.dataSource =res.data.result;
      // this.data=res.data.result;
      // this.datalt =res.data.result;
    },
    async searchOn() {
      this.loading = true
      //console.log(this.searchName)
      await this.$axios.get('sdu/solution/findSolutionListLike', {
        params:{
          searchName:this.searchName,
          classify:this.classify
        }
      }).then(res => {
        this.dataSource = res.data.result
        //console.log(this.dataSource)
      })
      this.loading = false
    },
    searchRest(){
      this.searchName=''
      this.getData()
    },
    onSearch(){
      if (this.searchName && this.searchName !== '') {
        this.datalt = this.data.filter(
          (p) =>{
            return p.name.indexOf(this.searchName) !== -1
          }
        )
      } else {
        this.datalt = this.data
      }
    },
    showDescription(item){
      //console.log(item)
      //this.$bus.$emit("flag",item)
      this.$router.push({
        path:'/sdu/schemedetail',
        query:{
          solution:item
        }
      })
    },
  },
  created() {
    this.getData()
  },
  mounted() {
    console.log(this.str)
  },
  computed:{
    str(){
      return this.$route.query.str;
    },
    classify(){
      return this.$route.query.classify;
    }
  },
}
</script>

<style scoped>
.card{
  transiton:all .4s;
}
.card:hover{
  transform: scale(1.02);
  background-color: #f7f1f1;
}
</style>