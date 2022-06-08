<template>
  <a-card :bordered="false" class="card-area">
    <div>
      <a-table
        ref="table"
        size="middle"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="pagination"
        :loading="loading"
        @change="handleTableChange"
        rowKey='did'>
        <template slot="action" slot-scope="text, record">
          <a @click="handleInfo(record)">查看详情</a>
        </template>
      </a-table>
    </div>
  </a-card>
</template>

<script>
export default {
  name: 'AreaDetailList',
  data() {
    return {
      columns: [
        {
          title: '项目名称',
          dataIndex: 'pname',
          width: 200
        },
        {
          title: '承担单位名称',
          dataIndex: 'cname',
          width: 150
        },
        {
          title: '版本',
          dataIndex: 'dyear',
          width: 50
        },
        {
          title: '提交时间',
          dataIndex: 'dtijiaotime',
          width: 50
        },
        {
          title: '操作',
          dataIndex: 'action',
          align: 'center',
          scopedSlots: { customRender: 'action' },
          width: 50
        }
      ],
      dataSource: [],
      pagination: {
        current: 1,
        pageSize: 10,
        pageSizeOptions: ['10', '20', '30'],
        showTotal: (total, range) => {
          return range[0] + '-' + range[1] + ' 共' + total + '条'
        }
      },
      loading: false
    }
  },
  methods: {
    async getDetailList() {
      this.loading = true
      await this.$axios.get('area/detail/findDetailByPid', { params: { pId: this.$route.query.pId } }).then(res => {
        this.dataSource = res.data.result
      })
      this.loading = false
    },
    handleTableChange(pagination, filters, sorter) {
      this.pagination = pagination
    },
    handleInfo(record) {
      //console.log(record.did)
      this.$router.push({ path: 'AreaDetail' , query: { dId: record.did }})
    }
  },
  created() {
    this.getDetailList()
  }
}
</script>

<style scoped>

</style>