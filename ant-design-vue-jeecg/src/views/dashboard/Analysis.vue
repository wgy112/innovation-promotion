<template>
  <div>
    <index-chart v-if="indexStyle==1"></index-chart>
    <index-bdc v-if="indexStyle==2"></index-bdc>
    <index-task v-if="indexStyle==3"></index-task>
<!--    <index-table v-if="indexStyle==0"></index-table>-->
    <index-card v-if="indexStyle==0"></index-card>
<!--    <div style="width: 100%;text-align: right;margin-top: 20px">-->
<!--      请选择首页样式：-->
<!--      <a-radio-group v-model="indexStyle">-->
<!--        <a-radio :value="1">统计图表</a-radio>-->
<!--        <a-radio :value="2">统计图表2</a-radio>-->
<!--        <a-radio :value="3">任务表格</a-radio>-->
<!--      </a-radio-group>-->
<!--    </div>-->
  </div>
</template>

<script>
  import IndexChart from './IndexChart'
  import IndexBdc from './IndexBdc'

  import IndexCard from '@views/dashboard/IndexCard'

  export default {
    name: "Analysis",
    components: {
      IndexCard,
      IndexChart,
      IndexBdc

    },
    data() {
      return {
        indexStyle:0
      }
    },
    created() {

    },
    methods: {

    }
  }
</script>