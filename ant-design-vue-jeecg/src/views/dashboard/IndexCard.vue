<template>
  <div class="indexSet"   >

    <div :class="{zeroPage:true,testenter:testShow }" ref="testref">
      <page-layout :desc="description" :title=title>
      <div slot="extra" class="extra-img">
        <img :src="extraImage" />
      </div>
      <!-- keep-alive  -->
      <route-view ref="content"></route-view>
    </page-layout>

      <div> <IndexCarousel></IndexCarousel></div>
    </div>

    <div :class="{firstPage:true,testenter:testShow2 }" ref="test2ref">
      <AreaComponent></AreaComponent>
<!--      <a-row :gutter="24" style="margin-bottom: 40px">-->
<!--        <a-col :span="8">-->

<!--        </a-col>-->
<!--        <a-col :span="1">-->
<!--               <span>-->
<!--                 <a-icon type="appstore" style="color:#990000 ;font-size: 240%;margin-top: 7px;margin-left: 16px"/>-->
<!--               </span>-->
<!--        </a-col>-->
<!--        <a-col :span="10">-->
<!--               <span class="fontSet">-->
<!--                  企业动态信息-->
<!--               </span>-->
<!--        </a-col>-->

<!--      </a-row>-->


<!--      <a-row :gutter="24">-->
<!--        <a-col :span="10">-->
<!--          <div id="shandong" style="height: 200px;">-->
<!--          </div>-->
<!--        </a-col>-->
<!--        <a-col :span="10" style="height: 200px;">-->
<!--          <a-button type="primary" style="width: 30%;margin-right: 5%;height: 50px" :ghost="this.true1" @click="getAreaProject(1)"><div style="font-weight: bold">济南</div></a-button>-->
<!--          <a-button type="primary" style="width: 30%;margin-right: 5%;height: 50px;font-weight: bold" :ghost="this.true1" @click="getAreaProject(2)">青岛</a-button>-->
<!--          <a-button type="primary" style="width: 30%;height: 50px;font-weight: bold" :ghost="this.true1" @click="getAreaProject(3)">淄博</a-button>-->
<!--          <div style="height: 10%"></div>-->
<!--          <a-button type="primary" style="width: 30%;margin-right: 5%;height: 50px;font-weight: bold" :ghost="this.true1" @click="getAreaProject(4)">潍坊</a-button>-->
<!--          <a-button type="primary" style="width: 30%;margin-right: 5%;height: 50px;font-weight: bold" :ghost="this.true1" @click="getAreaProject(5)">烟台</a-button>-->
<!--          <a-button type="primary" style="width: 30%;;height: 50px;font-weight: bold" :ghost="this.true1" @click="getAreaProject(6)">威海</a-button>-->
<!--          <div style="height: 10%"></div>-->
<!--          <a-button type="primary" style="width: 48%;margin-right: 4%;height: 50px" @click="showCompanyList">企业列表</a-button>-->
<!--          <a-button type="primary" style="width: 48%;height: 50px" @click="showProjectList">创新列表</a-button>-->
<!--        </a-col>-->

<!--      </a-row>-->
    </div>

    <div  :class="{secondPage:true,testenter:testShow3 }" ref="test3ref" >
      <a-row :gutter="24">
        <a-col :span="8">
                <span>
                </span>
        </a-col>
        <a-col :span="1">
                <span>
                  <a-icon type="appstore" style="color:#990000 ;font-size: 240%;margin-top: 7px;margin-left: 16px"/>
                </span>
        </a-col>
        <a-col :span="10">
               <span class="fontSet">
                  通用方案分类
               </span>
        </a-col>
      </a-row>


      <a-row :gutter="24" style="margin-top: 40px" type="flex" justify="space-around">
        <a-col v-for="(item,index) in dataSource" :span="3" :key="index">
          <div hoverable style="width: 150px;height: 200px;text-align: center;" @click="openScheme(item.id)">
            <img
              slot="cover"
              alt="example"
              :src="item.avatar"
              style="height: 150px"
            />
            <a-card-meta>
              <template slot="description">
                <h3 style="font-weight: bold">{{ item.title }}</h3>
              </template>
            </a-card-meta>
          </div>

        </a-col>
      </a-row>
    </div>

    <div  :class="{thirdPage:true,testenter:testShow4 }" ref="test4ref" >
      <IndexDS></IndexDS>

    </div>
<!--    <a-layout-footer></a-layout-footer>-->

  </div>

</template>

<script>

import * as echarts from 'echarts';
import RouteView from '@comp/layouts/RouteView'
import PageLayout from '@comp/page/PageLayout'
import LayoutFooter from '../../components/page/GlobalFooter'
import ShanDong from '../../../public/shandong.json'
import IndexCarousel from './IndexCarousel'
import IndexDS from './IndexDS'
import AreaComponent from './AreaComponent'

export default {
  name: 'IndexCard',
  components: {
    LayoutFooter,
    RouteView,
    PageLayout,
    IndexCarousel,
    IndexDS,
    AreaComponent,
  },
  data() {
    return {
      true1:true,
      title: '面向区域的智能化创新方法推广平台',
      description: '智能化的创新方法应用推广公共服务平台，实现创新资源聚集、创新方法服务化、创新服务智能化，支撑构建创新方法生态和创新方法应用推广。',
      extraImage: require('../../assets/index.png'),
      dataSource: [
        // {
        //   id: 1,
        //   title: '山东半岛自主创新示范区',
        //   avatar: require('../../assets/jzxz2.png'),
        //   content: '依托山东半岛“6+1”载体建设国家自主创新示范区改革。'
        // },
        {
          id: 1,
          title: '数字化管理',
          avatar: require('../../assets/sbsz.png'),
          content: '数字化管理'
        },
        {
          id: 2,
          title: '个性化定制',
          avatar: require('../../assets/zfgz.png'),
          content: ''
        },
        {
          id: 3,
          title: '网络化协同',
          avatar: require('../../assets/xtgz.png'),
          content: ''
        },
        {
          id: 4,
          title: '服务型制造',
          avatar: require('../../assets/cwpc.png'),
          content: ''
        },
        {
          id: 5,
          title: '中小企业创成',
          avatar: require('../../assets/xmgl.png'),
          content: ''
        },
        {
          id: 6,
          title: '技术发明创造',
          avatar: require('../../assets/hgsy.png'),
          content: ''
        }
      ],
      imageBox: [
        { id: 0, idView: require('@assets/rotation/carousel01.jpg'), title: '' },
        { id: 1, idView: require('@assets/rotation/carousel02.jpg'), title: '' },
        { id: 2, idView: require('@assets/rotation/carousel03.jpg'), title: '' },
        { id: 3, idView: require('@assets/rotation/carousel04.jpg'), title: '' }
      ],
      fadeInElements:[],
      testShow:false,
      testShow2:false,
      testShow3:false,
      testShow4:false,

    }
  },
  methods: {
    openScheme(cid) {
      switch (cid) {
        case 1:
          this.$router.push({ path: '/sdu/schemeview',query:{
              str:"数字化管理",
              classify:1
            } })
          break
        case 2:
          this.$router.push({ path: '/sdu/schemeview',query:{
              str:"个性化定制",
              classify:2
            } })
          break
        case 3:
          this.$router.push({ path: '/sdu/schemeview',query:{
              str:"网络化协同",
              classify:3
            } })
          break
        case 4:
          this.$router.push({ path: '/sdu/schemeview',query:{
              str:"服务型制造",
              classify:4
            } })
          break
        case 5:
          this.$router.push({ path: '/sdu/schemeview',query:{
              str:"中小企业创成",
              classify:5
            } })
          break
        case 6:
          this.$router.push({ path: '/sdu/schemeview',query:{
              str:"技术发明创造",
              classify:6
            } })
          break
      }
    },
    echartsInit(){
      let myChart = echarts.init(document.getElementById('shandong')) // 拿到一个实例
      echarts.registerMap('山东省', ShanDong, {})//引入地图文件
      //console.log(JSON)
      var option = {
        visualMap: {
          //left: 'center',
          //bottom: '1%',
          min: 5,
          max: 100,
          //orient: 'horizontal',
          text: ['', '示范区项目'],
          realtime: true,
          calculable: true,
          inRange: {
            color: ['#00FFFF', '#1E90FF', '#0000FF']
          }
        },
        series: [
          {
            type: 'map',
            mapType: '山东省',//地图名称
            roam: true,
            // emphasis: {
            //   label: {
            //     show: false
            //   }
            // },
             selectedMode: true,
            data:[
              {name:'济南市',value:72},
              {name:'青岛市',value: 45},
              {name:'淄博市',value: 48},
              {name:'潍坊市',value: 33},
              {name:'烟台市',value: 58},
              {name:'威海市',value: 59},
            ]
          }]
      }
      myChart.setOption(option)
    },
    getAreaProject(n){
      console.log(n)
    },
    showCompanyList(){
      this.$router.push("/sdu/areacompany")
    },
    showProjectList(){
      this.$router.push("/dashboard/table/areaproject")
    },

    // 循环判断是否要触发过渡
    handleScroll () {
      this.currentScroll = window.pageYOffset //表示当前滚动的位置

      if (this.currentScroll >= this.$refs.testref.offsetTop - 400) { //当前滚动位置到达testref的时候，显示div（100作为调整用）
        this.testShow = true;
      }
      if (this.currentScroll >= this.$refs.test2ref.offsetTop - 500) {
        this.testShow2 = true;
      }
      if (this.currentScroll >= this.$refs.test3ref.offsetTop - 500) {
        this.testShow3 = true;
      }
      if (this.currentScroll >= this.$refs.test4ref.offsetTop - 500) {
        this.testShow4 = true;
      }



    },




  },
  mounted() {
    this.echartsInit()

    window.addEventListener('scroll', this.handleScroll)

  }
}
</script>

<style lang="less" scoped>
.indexSet{
  //display: table;
  margin: 0;
  padding: 0;
  background-color: white;
  height: 3600px;


}
.card-avatar {
  width: 48px;
  height: 48px;
  border-radius: 48px;
}

.ant-card-actions {
  background: #f7f9fa;

  li {
    float: left;
    text-align: center;
    margin: 12px 0;
    color: rgba(0, 0, 0, 0.45);
    width: 50%;

    &:not(:last-child) {
      border-right: 1px solid #e8e8e8;
    }

    a {
      color: rgba(0, 0, 0, .45);
      line-height: 22px;
      display: inline-block;
      width: 100%;

      &:hover {
        color: #1890ff;
      }
    }
  }
}

.new-btn {
  background-color: #fff;
  border-radius: 2px;
  width: 100%;
  height: 186px;
}

.meta-content {
  position: relative;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  height: 64px;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}


.extra-img {
  margin-top: -60px;
  text-align: center;
  width: 195px;

  img {
    width: 100%;
  }
}

//.mobile {
//  .extra-img {
//    margin-top: 0;
//    text-align: center;
//    width: 96px;
//
//    img {
//      width: 100%;
//    }
//  }
//}

.ant-col-5 {
  width: 20%;
}
.pic_item h3 {
  opacity: 1;
  transform: translateX(0);
  -webkit-transform: translateX(0);

  position: absolute;
  text-align: center;
  left: 40rem;
  top: 2rem;
  font-weight: bold;
  font-size: 50px;
  font-family: Lato, Sans-serif;
  color: rgb(255, 255, 255);
  //bottom:5rem;

}
.radius{
  width: 100px;
  height: 100px;
  background-color: #e1e1e1;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.fontSet{
  background-image:linear-gradient(to right, #000066 , #990000);
  -webkit-background-clip: text;
  color: transparent;
  font-size: 220%;
  font-weight: bold;

}

.firstPage{
  width:70%;
  margin-left: 15%;
  background-color: white;
  margin-top: 100px;
  transform: translateY(10%); //滚动前的位置
  opacity: 0; //滚动前设置透明隐藏

}

.secondPage{
  width:70%;
  margin-top: 100px;
  margin-bottom: 50px;
  margin-left: 15%;
  transform: translateY(10%); //滚动前的位置
  opacity: 0; //滚动前设置透明隐藏
}

.thirdPage{
  width:70%;
  margin-left: 15%;
  transform: translateY(10%); //滚动前的位置
  opacity: 0; //滚动前设置透明隐藏
}
.testenter{
  transform: translateY(0%)!important;  //滚动后的位置
  opacity: 1!important;  //滚动后显现
  transition: all 200ms ease;
}


</style>