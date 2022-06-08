<template>
  <div class="homeset">
    <a-row>
      <a-col :span="24" class="block">

        <!--        gutter 值应为左右的内边距之和-->
        <span >
            <h2 class="demonstration">
              <strong>创新引导辅助工具软件</strong>
            </h2>
        </span>
        <!--        需要注意的是一定要定义ref="carousel"属性，下面会根据该属性获取到该dom元素并调用走马灯提供的setActiveItem方法进行item的切换-->
        <!--        type  自然是走马灯的类型-->
        <!--          ref 属性被用来给dom 属性或者子组件注册引用信息，会根据父组件的$ref对象进行注册-->
        <!--          只要想在vue 中直接操作dom 元素，就必须使用ref属性进行注册-->
        <a-carousel height="450px" ref="carousel"   autoplay>
          <div v-for="item in imageBox" :key="item.id">
            <div class="pic_item">
              <span >
              <img :src="item.idView" alt=""  class="image">
              </span>
              <h3 class="title">{{item.title}}</h3>
            </div>
          </div>
        </a-carousel>
      </a-col>
    </a-row>
    <a-row>
      <a-col>
        <div class="mytitle">
          <!--            strong  标签可以使字体变粗-->
          <h3 ><strong>创新引导辅助工具软件</strong></h3>
        </div>
      </a-col>
    </a-row>
    <!--          第三部分介绍-->
    <a-row>
      <a-col>
        <div class="myintro">
          <h2 class="fontset">

            创新引导辅助工具软件旨在为企业创新提供指导，贯穿大数据爬取、创意引导、解决方案推荐、众智众创的创新过程。
            包括创新诊断咨询工具模块、创新需求分析工具模块、
            创新过程智能引导工具模块、创新方法智能匹配工具模块和反馈评价工具模块。
          </h2>
        </div>
      </a-col>
    </a-row>

    <a-row :gutter="24"  type="flex" justify="center" align="bottom">

      <!--       el-col 的offset属性，设置某个el-col元素左侧空多少分的位置，默认为零-->
      <!--      如果其offset和span之和大于24，则该行剩余元素移到下一行，同时这个元素只显示一部分-->
      <!--        中间的一系列参数是响应式栅格数-->
      <a-col :span="6">
        <div class="grid-content bg-purple">

          <div class="circle1"></div>
          <div class="circle2"></div>
          <div class="picture1">
            <img :src="img1"   alt="">
          </div>
<!--          <h3  class="firstparam">需求挖掘和行为模式分析系统</h3>-->
          <h3  class="firstparam">创新诊断咨询</h3>
          <h3 class="introduction">为有企业创新启发的用户提供一种工具用于寻找企业创新方案</h3>
<!--          <h3 class="introduction">通过创新思维推荐词云以及相关专利，完成创意引导</h3>-->
<!--          <button class="buttonset" >创意引导</button>-->
        </div>
      </a-col>
      <a-col :span="6">
        <div class="grid-content bg-purple ">
          <div class="circle1"></div>
          <div class="circle2"></div>
          <div class="picture1">
            <img :src="img2"   alt="">
          </div>
          <h3 class="firstparam">创新需求分析</h3>
<!--          <h3 class="firstparam">模式创新智能推荐系统</h3>-->
          <h3 class="introduction">使用多元的数据融合展示与分析方式，在大数据的环境下实现企业的创新需求分析</h3>
<!--          <h3 class="introduction">使用TRIZ矛盾分析方法，推荐发明原理，提供解决方案</h3>-->
<!--          <button  class="buttonset" >方案推荐</button>-->
        </div>
      </a-col>
      <a-col :span="6">
        <div class="grid-content bg-purple thirdpart">
          <div class="circle1"></div>
          <div class="circle2"></div>
          <div class="picture1">
            <img :src="img3"   alt="">
          </div>
<!--          <h3 class="firstparam">大数据智慧跨界支撑系统</h3>-->
          <h3 class="firstparam">创新过程智能引导</h3>
          <h3 class="introduction">通过创新思维推荐词云以及相关专利，完成创意引导</h3>
<!--          <h3 class="introduction">提供工作流式爬虫方法，支撑起整个平台的数据来源</h3>-->
<!--          <button class="buttonset">数据支撑</button>-->
        </div>
      </a-col>
      <a-col :span="6">
        <div class="grid-content bg-purple fourthpart">
          <div class="circle1"></div>
          <div class="circle2"></div>
          <div class="picture1">
            <img :src="img4"   alt="">
          </div>
          <h3 class="firstparam">创新方法智能匹配</h3>
<!--          <h3 class="firstparam">社会化众智众创反馈评价系统</h3>-->
<!--          <h3 class="introduction">创意、解决方案孵化交流社区，交流碰撞</h3>-->
          <h3 class="introduction">使用多样化分析方法，推荐发明原理，提供解决方案</h3>
<!--          <button class="buttonset">创客交流</button>-->
        </div>
      </a-col>
    </a-row>


  </div>
</template>


<script>
  import ChartCard from '@/components/ChartCard'
  import ACol from "ant-design-vue/es/grid/Col"
  import ATooltip from "ant-design-vue/es/tooltip/Tooltip"
  import MiniArea from '@/components/chart/MiniArea'
  import MiniBar from '@/components/chart/MiniBar'
  import MiniProgress from '@/components/chart/MiniProgress'
  import RankList from '@/components/chart/RankList'
  import Bar from '@/components/chart/Bar'
  import LineChartMultid from '@/components/chart/LineChartMultid'
  import HeadInfo from '@/components/tools/HeadInfo.vue'


  import Trend from '@/components/Trend'
  import { getLoginfo,getVisitInfo } from '@/api/api'

  const rankList = []
  for (let i = 0; i < 7; i++) {
    rankList.push({
      name: '白鹭岛 ' + (i+1) + ' 号店',
      total: 1234.56 - i * 100
    })
  }
  const barData = []
  for (let i = 0; i < 12; i += 1) {
    barData.push({
      x: `${i + 1}月`,
      y: Math.floor(Math.random() * 1000) + 200
    })
  }
  export default {
    name: "IndexChart",
    components: {
      ATooltip,
      ACol,
      ChartCard,
      MiniArea,
      MiniBar,
      MiniProgress,
      RankList,
      Bar,
      Trend,
      LineChartMultid,
      HeadInfo
    },
    data() {
      return {

        loading: true,
        center: null,
        rankList,
        barData,
        loginfo:{},
        visitFields:['ip','visit'],
        visitInfo:[],
        indicator: <a-icon type="loading" style="font-size: 24px" spin />,
        imageBox:[
          // {id:0,idView:require("@assets/rotation/01.jpg"),title:"需求挖掘和行为模式分析系统"},
          // {id:1,idView:require("@assets/rotation/轮播2.jpg"),title:"模式创新智能推荐系统"},
          // {id:2,idView:require("@assets/rotation/轮播3.jpg"),title:"大数据智慧跨界支撑系统"},
          // {id:3,idView:require("@assets/rotation/轮播4.jpg"),title:"社会化众智众创反馈评价系统"},
          {id:0,idView:require("@assets/rotation/01.jpg"),title:"创新诊断咨询工具模块"},
          {id:1,idView:require("@assets/rotation/轮播2.jpg"),title:"创新需求分析工具模块"},
          {id:2,idView:require("@assets/rotation/轮播3.jpg"),title:"创新过程智能引导工具模块"},
          {id:3,idView:require("@assets/rotation/轮播4.jpg"),title:"创新方法智能匹配工具模块"},
        ],
        img1:require('@assets/rotation/图标1.png'),
        img2:require('@assets/rotation/图标2.png'),
        img3:require('@assets/rotation/图标3.png'),
        img4:require('@assets/rotation/图标4.png')



      }
    },
    created() {
      setTimeout(() => {
        this.loading = !this.loading
      }, 1000)
      this.initLogInfo();
    },
    methods: {
      initLogInfo () {
        getLoginfo(null).then((res)=>{
          if(res.success){
            Object.keys(res.result).forEach(key=>{
              res.result[key] =res.result[key]+""
            })
            this.loginfo = res.result;
          }
        })
        getVisitInfo().then(res=>{
          if(res.success){
             this.visitInfo = res.result;
           }
         })
      },
      // buttonClick(){
      //
      // }
    }
  }
</script>

<!--//1.scoped属性，是用来专门用于标签元素内部的，它是通过CSS的属性选择器实现的。-->
<!--//-->
<!--//2.lang属性，普通的style标签支持普通的样式，如果想要启用scss或less ,需要为style元素设置lang属性，用法如下-->
<style lang="less" scoped>





  .pic_item{
    position: relative;
    height: 100%;
    text-align: center;
  }
  .pic_item:hover{
    cursor: pointer;
  }
  .pic_item img{
    width: 100%;
    height: 500px;
    display: flex;
    justify-content: center;
    position:relative;



  }
  .pic_item h3{
    opacity:1;
    transform: translateX(0);
    -webkit-transform: translateX(0);

    position: absolute;
    text-align: center;
    left:40rem;
    top:2rem;
    font-weight: bold;
    font-size: 50px;
    font-family: Lato, Sans-serif;
    color:rgb(255,255,255) ;
    //bottom:5rem;

  }
  //  设置整个的背景颜色
  .homeset{
    background: white;
  }
  //设置走马灯上面的标题

  .demonstration{
    text-align: center;
    font-size: 32px;
    color: #A9A9A9;
  }
  .mytitle{
    text-align: center;
    font-size:30px;
    font-weight: 700;

  }
  .myintro{
    padding: 20px 300px;
    font-weight: 200;

  }
  .fontset{
    color: #A9A9A9;
    text-align: center;
  }

  //  第二行布局的样式设置
  // 四个卡片样式布局
  .ant-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .ant-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background:white;
    //background: #99a9bf;
  }
  .bg-purple {
    background-color: white;
    //background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  //调节模块的高度
  .grid-content {
    border-radius: 4px;
    min-height: 400px;

  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;

  }
  //.el-row mymodule{
  //  width:900px;
  //  margin:auto;
  .mymodule{
    width:70%;
    position: relative;
    left: 300px;
    font-size: 16px;



  }


  .circle1{
    width: 150px;
    height: 150px;
    border-radius: 75px;
    background-color: #A9A9A9;

    position: relative;
    left:80px;


  }
  .circle2{
    width: 142px;
    height: 142px;
    border-radius: 71px;
    background-color:white;
    position: absolute;
    top: 4px;
    left:96px;
  }

 //图标设置
  .picture1 img{
    position: absolute;
    left: 145px;
    top: 50px;

    width:50px;
    height:50px;
    z-index: 10;

  }

 .firstparam {

   font-weight: bold;
   font-size:20px;
   text-align: center;
    //margin-top: 100px;
   position:relative;
   top:40px;
   padding:0 10px;

  }
  .introduction{

    color:#A9A9A9;
    position: relative;
    //top:25px;
    top:50px;
    padding:0 40px;

  }
  .buttonset{
    height:60px;
    width:120px;
    font-size: 20px;
    font-weight: bold;
    position:relative;
    top:70px;
    left:90px;
    background-color: white;
    color:#808080;
  }
  .buttonset:hover{
    height:60px;
    width:120px;
    background-color:#21B6A8;
    color:white;

  }
</style>