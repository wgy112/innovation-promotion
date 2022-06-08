<template>
  <page-layout :title="this.dataSource.pname" :logo="extraImage">

    <detail-list slot="headerContent" size="small" :col="1" class="detail-layout">
      <h3><detail-list-item term="公司名称">{{ this.dataSource.cname }}</detail-list-item></h3>
    </detail-list>
    <detail-list slot="headerContent" size="small" :col="2" class="detail-layout">
<!--      <detail-list-item term="验收时间"> </detail-list-item>-->
<!--      <detail-list-item term="项目验收情况">{{ this.dataSource.dstatus }}</detail-list-item>-->
      <detail-list-item term="项目的进展情况">{{ this.dataSource.dprogress }}</detail-list-item>
      <detail-list-item term="报表年份">{{ this.dataSource.dyear }}年{{ this.dataSource.dmonth }}报表</detail-list-item>
    </detail-list>
    <a-row slot="extra" class="status-list">
      <a-col :xs="24" :sm="24">
        <div class="text">项目验收情况</div>
        <div class="heading">{{ this.dataSource.dstatus }}</div>
      </a-col>
    </a-row>

    <a-card style="margin-top: 24px" :bordered="false" title="项目详情">
<!--      <detail-list>-->
<!--        <detail-list-item term="用户姓名">付晓晓</detail-list-item>-->
<!--        <detail-list-item term="会员卡号">32943898021309809423</detail-list-item>-->
<!--        <detail-list-item term="身份证">3321944288191034921</detail-list-item>-->
<!--        <detail-list-item term="联系方式">18112345678</detail-list-item>-->
<!--        <detail-list-item term="联系地址">浙江省杭州市西湖区黄姑山路工专路交叉路口</detail-list-item>-->
<!--      </detail-list>-->
<!--      <detail-list title="信息组">-->
<!--        <detail-list-item term="某某数据">725</detail-list-item>-->
<!--        <detail-list-item term="该数据更新时间">2018-08-08</detail-list-item>-->
<!--        <detail-list-item>&nbsp;</detail-list-item>-->
<!--        <detail-list-item term="某某数据">725</detail-list-item>-->
<!--        <detail-list-item term="该数据更新时间">2018-08-08</detail-list-item>-->
<!--        <detail-list-item>&nbsp;</detail-list-item>-->
<!--      </detail-list>-->
      <a-card type="inner">
        <detail-list size="small" :col="1">
          <detail-list-item term="项目完成目标">
            {{this.dataSource.dtarget}}
          </detail-list-item>
          <detail-list-item term="项目人员构成">
            {{this.dataSource.dbuild}}
          </detail-list-item>
          <detail-list-item term="项目目前进展">
            {{this.dataSource.dprogressSupplement}}
          </detail-list-item>
        </detail-list>
<!--        <a-divider style="margin: 16px 0" />-->
<!--        <detail-list title="组名称" size="small" :col="1">-->
<!--          <detail-list-item term="学名"> Citrullus lanatus (Thunb.) Matsum. et Nakai一年生蔓生藤本；茎、枝粗壮，具明显的棱。卷须较粗..-->
<!--          </detail-list-item>-->
<!--        </detail-list>-->
        <a-divider style="margin: 16px 0" />
        <detail-list title="" size="small" :col="2">
          <detail-list-item term="填写人">{{this.dataSource.dpeople}}</detail-list-item>
          <detail-list-item term="联系方式">{{this.dataSource.dcontacts}}</detail-list-item>
        </detail-list>
      </a-card>
    </a-card>
  </page-layout>
</template>

<script>

import PageLayout from '../../../components/page/PageLayout'
import DetailList from '../../../components/tools/DetailList'

const DetailListItem = DetailList.Item

export default {
  name: 'AreaDetail',
  components: {

    PageLayout,
    DetailList,
    DetailListItem
  },
  data() {
    return {
      dataSource:{
      },
      extraImage:require('../../../assets/profile.png'),
      loading:false,
    }
  },
  methods:{
    async getAreaDetai() {
      this.loading = true
      await this.$axios.get('area/d/findAreaDetailBydid', { params: { dId: this.$route.query.dId } }).then(res => {
        this.dataSource = res.data.result
        console.log(this.dataSource)
      })
      this.loading = false
    }
  },
  mounted() {
    this.getAreaDetai()
  }
}
</script>

<style lang="less" scoped>

.detail-layout {
  margin-left: 44px;
}

.text {
  color: rgba(0, 0, 0, .45);
}

.heading {
  color: rgba(0, 0, 0, .85);
  font-size: 20px;
}

.no-data {
  color: rgba(0, 0, 0, .25);
  text-align: center;
  line-height: 64px;
  font-size: 16px;

  i {
    font-size: 24px;
    margin-right: 16px;
    position: relative;
    top: 3px;
  }
}

.mobile {
  .detail-layout {
    margin-left: unset;
  }

  .text {

  }

  .status-list {
    text-align: left;
  }
}
</style>