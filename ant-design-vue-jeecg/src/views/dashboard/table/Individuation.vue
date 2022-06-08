<template>
  <a-card :bordered="false" class="card-area">
    <h2>个性化定制提升创新方法群</h2>
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :md="12" :sm="8">
            <a-form-item label="名称" :labelCol="{span: 5}" :wrapperCol="{span: 18, offset: 1}">
              <a-input></a-input>
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
      <div class="boxx" style="width: 90%;margin-left: 5%;">
        <a-list :grid="{ gutter: 16}" :data-source="dataSource" item-layout="vertical" :pagination="pagination">
          <a-list-item slot="renderItem" slot-scope="item, index">
            <a-card class="card" hoverable :title="item.name" size="small" @click="showDescription(item)">
              <!--          <h3>问题描述：{{item.description}}</h3>-->
              <a slot="extra" href="#">{{ item.date }}</a>
              <a-card-meta :title="item.user" :description="item.description">
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
const data = []
export default {
  name: 'Individuation',
  data() {
    return {
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
      datalt: data
    }

  },
  methods: {
    async searchOn() {
      // this.queryParam.checkedList = this.checkedList
      this.loading = true
      // await this.$axios.post('area/project/findProjectListLike', this.queryParam).then(res => {
      //   this.dataSource = res.data.result
      // })
      this.loading = false
    },
    searchRest() {
      // this.queryParam = {
      //   keyword: '',
      //   startDate: '',
      //   endDate: '',
      //   checkedList: []
      // }
      // this.getProjectList()
    },
    async getData(){
      this.loading = true
      await this.$axios.get('sdu/solution/findSolutionByClassify', { params: { classify: 2 } }).then(res => {
        this.dataSource = res.data.result
      })
      this.loading = false
    },
    showDescription(item){
      //console.log(item)
      //this.$bus.$emit("flag",item)
      this.$router.push({
        path:'/sdu/schemedetail',
        query:{
          scheme:item
        }
      })
    },
  },
  created() {
    this.getData()
  },
}
</script>

<style scoped>
.card {
  transiton: all .4s;
}

.card:hover {
  transform: scale(1.02);
  background-color: #f7f1f1;
}
</style>