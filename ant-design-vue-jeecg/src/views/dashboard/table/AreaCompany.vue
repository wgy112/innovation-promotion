<template>
  <a-card :bordered="false" class="card-area">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :md="10" :sm="8">
            <a-form-item label="名称" :labelCol="{span: 5}" :wrapperCol="{span: 18, offset: 1}">
              <a-input v-model="queryParam.keyword" placeholder="请输入承担单位名称关键字"></a-input>
            </a-form-item>
          </a-col>
<!--          <a-col :md="10" :sm="12">-->
<!--            <a-form-item label="请选择想要查找的日期" :labelCol="{span: 5}" :wrapperCol="{span: 18, offset: 1}">-->
<!--              <j-date format="YYYY-MM-DD" style="width:45%" v-model="queryParam.startDate"-->
<!--                      :disabled-date="disabledStartDate" placeholder="请选择开始时间"></j-date>-->
<!--              <span style="width: 10px;">~</span>-->
<!--              <j-date format="YYYY-MM-DD" style="width:45%" v-model="queryParam.endDate"-->
<!--                      :disabled-date="disabledEndDate" placeholder="请选择结束时间"></j-date>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
          <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
            <a-col :md="6" :sm="24">
              <a-button type="primary" @click="searchOn">查询</a-button>
              <a-button style="margin-left: 8px" @click="searchRest">重置</a-button>
            </a-col>
          </span>
        </a-row>
        <a-row :gutter="24">
          <a-col :md="20" :sm="24">
            <a-checkbox-group v-model="checkedList" :options="plainOptions" @change="onChange" />
            <a-checkbox :indeterminate="indeterminate" :checked="checkAll" @change="onCheckAllChange">
              全选
            </a-checkbox>
            <br><br>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- table区域 -->
    <div>
      <a-table
        ref="table"
        size="middle"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="pagination"
        :loading="loading"
        @change="handleTableChange"
        rowKey='pid'>
        <template slot="action" slot-scope="text, record">
          <a @click="handleInfo(record)">查看详情</a>
        </template>
      </a-table>
    </div>
  </a-card>
</template>

<script>
import moment from 'moment'

export default {
  name: 'AreaCompany',
  data(){
    return{
      queryParam: {
        keyword: '',
        startDate: '',
        endDate: '',
        checkedList: []
      },
      loading: false,
      checkedList: ['济南高新区', '青岛高新区', '淄博高新区', '潍坊高新区', '烟台高新区', '威海高新区'],
      indeterminate: true,
      checkAll: true,
      plainOptions: [
        '济南高新区', '青岛高新区', '淄博高新区', '潍坊高新区', '烟台高新区', '威海高新区'
      ],
      columns: [
        // {
        //   title: '项目名称',
        //   dataIndex: 'pname',
        //   width: 200
        // },
        {
          title: '承担单位名称',
          dataIndex: 'cname',
          width: 150
        },
        {
          title: '所属高新区',
          dataIndex: 'parea',
          width: 50
        },
        // {
        //   title: '单位立项时间',
        //   dataIndex: 'pcreatetime',
        //   width: 50
        // },
        // {
        //   title: '操作',
        //   dataIndex: 'action',
        //   align: 'center',
        //   align: 'center',
        //   scopedSlots: { customRender: 'action' },
        //   width: 50
        // }
      ],
      dataSource: [],
      pagination: {
        current: 1,
        pageSize: 10,
        pageSizeOptions: ['10', '20', '30'],
        showTotal: (total, range) => {
          return range[0] + '-' + range[1] + ' 共' + total + '条'
        },
        showQuickJumper: true,
        showSizeChanger: true,
        total: 0
      }
    }
  },
  methods: {
    async getProjectList() {
      this.loading = true
      await this.$axios.get('area/project/findAllProject').then(res => {
        this.dataSource = res.data.result
      })
      this.loading = false
    },
    onChange(checkedList) {
      this.indeterminate = !!this.checkedList.length && this.checkedList.length < this.plainOptions.length
      this.checkAll = this.checkedList.length === this.plainOptions.length
    },
    onCheckAllChange(e) {
      Object.assign(this, {
        checkedList: e.target.checked ? this.plainOptions : [],
        indeterminate: false,
        checkAll: e.target.checked
      })
    },
    handleTableChange(pagination, filters, sorter) {
      this.pagination = pagination
    },
    handleInfo(record) {
      // this.$router.push({ path: 'AreaDetailList', query: { pId: record.pid } })
    },
    async searchOn() {
      this.queryParam.checkedList = this.checkedList
      this.loading = true
      await this.$axios.post('area/project/findCompanyListLike', this.queryParam).then(res => {
        this.dataSource = res.data.result
      })
      this.loading = false
    },
    searchRest() {
      this.queryParam = {
        keyword: '',
        startDate: '',
        endDate: '',
        checkedList: []
      }
      this.checkedList = ['济南高新区', '青岛高新区', '淄博高新区', '潍坊高新区', '烟台高新区', '威海高新区']
      this.getProjectList()
    },
    disabledStartDate(startValue) {
      const endValue = moment(this.queryParam.endDate, 'YYYY-MM-DD')
      if (!startValue || !endValue) {
        return false
      }
      return startValue.valueOf() > endValue.valueOf()
    },
    disabledEndDate(endValue) {
      const startValue = moment(this.queryParam.startDate, 'YYYY-MM-DD')
      if (!endValue || !startValue) {
        return false
      }
      return startValue.valueOf() >= endValue.valueOf()
    }
  },
  created() {
    this.getProjectList()
  }
}
</script>

<style scoped>

</style>