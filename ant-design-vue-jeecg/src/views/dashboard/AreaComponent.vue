<template>
  <div>
    <a-row :gutter="24" style="margin-bottom: 40px">
      <a-col :span="8">
      </a-col>
      <a-col :span="1">
               <span>
                 <a-icon type="appstore" style="color:#990000 ;font-size: 240%;margin-top: 7px;margin-left: 16px"/>
               </span>
      </a-col>
      <a-col :span="10">
               <span class="fontSet">
                  企业动态信息
               </span>
      </a-col>
    </a-row>

    <a-row :gutter="24">
      <a-col :span="10">
        <a-col :span="24">
          <div id="shandong" style="height: 200px;"></div>
        </a-col>
        <a-col style="height:50px">


        </a-col>
        <a-col style="margin-top: 60px">
          <a-button type="primary" style="width: 48%;margin-right: 4%;height: 50px" @click="showCompanyList">企业列表</a-button>
          <a-button type="primary" style="width: 48%;height: 50px" @click="showProjectList">创新列表</a-button>
        </a-col>



      </a-col>

      <a-col :span="12">
        <div style="margin-top: 20px;font-family: 微软雅黑;font-size: 116%">
          &nbsp;&nbsp;&nbsp;&ensp;&ensp;山东半岛国家自主创新示范区是国务院批准的第十三家国家自主创新示范区，依托济南、青岛、淄博、潍坊、烟台、威海六个国家高新技术产业开发区，打造具有全球影响力的海洋科技创新中心，
          把山东半岛国家高新区建设成为转型升级引领区、创新创业生态区、体制机制创新试验区、开放创新先导区。
          2016年04月11日，国务院正式批复《关于支持山东半岛国家高新区建设国家自主创新示范区的请示》，同意设立山东半岛国家自主创新示范区。
        </div>
      </a-col>
    </a-row>


    <a-row :gutter="24" style="margin-top: 40px">
      <a-col :span="6">
        <div class="fontSetTwo" style="margin-left: 40%;margin-top: 25%">
          六<br>
          大<br>
          高<br>
          新<br>
<!--          示<br>-->
<!--          范<br>-->
          区
        </div>

      </a-col>
      <a-col :span="18">
        <a-col :span="8" >
          <div style="background-color: #F8F8F6;width: 80%">
            <img src="../../assets/area/jinan.jpg" alt="" style="width: 100%">
            <div style="text-align: left">
              地址：济南市历下区工业南路28号  <p></p>

              联系电话：0531-88880199
            </div>
          </div>
        </a-col>

        <a-col :span="8">
          <div style="background-color: #F8F8F6;width: 80%">
            <img src="../../assets/area/qingdao.jpg" alt="" style="width: 100%">
            <div style="text-align: left">
              地址：青岛市城阳区锦业路1座附近<br><p></p>



              联系电话：0532-82020885
            </div>
          </div>
        </a-col>

      <a-col :span="8">
        <div style="background-color: #F8F8F6;width: 80%">
          <img src="../../assets/area/zibo.jpg" alt="" style="width: 100%">
          <div style="text-align: left">
            地址：淄博市淄川区柳泉路109号 火炬大厦<br><p></p>



            联系电话：4009021361
          </div>
        </div>
      </a-col>
      <a-col :span="8" >
        <div style="background-color: #F8F8F6;width: 80%;margin-top: 20px">
          <img src="../../assets/area/weifang.jpg" alt="" style="width: 100%">
          <div style="text-align: left">
            地址：潍坊市潍城区高新技术产业开发区<br><p></p>



            联系电话：0536-8056122
          </div>
        </div>
      </a-col>

      <a-col :span="8">
        <div style="background-color: #F8F8F6;width: 80%;margin-top: 20px">
          <img src="../../assets/area/yantai.jpg" alt="" style="width: 100%">
          <div style="text-align: left">
            地址：中国烟台高新区科技大道创业大厦<br><p></p>



            联系电话：0535-6396111
          </div>
        </div>
      </a-col>

      <a-col :span="8">
        <div style="background-color: #F8F8F6;width: 80%;margin-top: 20px">
          <img src="../../assets/area/weihai.jpg" alt="" style="width: 100%">
          <div style="text-align: left">
            地址：威海市环翠区文化西路288号火炬大厦<br><p></p>


            联系电话：0631-5680711
          </div>
        </div>
      </a-col>
     </a-col>





    </a-row>
  </div>

</template>

<script>
import * as echarts from 'echarts';
export default {
  name: 'AreaComponent',
  components:{

  },
  data(){
    return{

    }

  },
  methods:{
    chartsInit(){
      let myChart = echarts.init(document.getElementById('shandong')) // 拿到一个实例
      echarts.registerMap('山东省', shandong, {})//引入地图文件
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
    showCompanyList(){
      this.$router.push("/sdu/areacompany")
    },
    showProjectList(){
      this.$router.push("/dashboard/table/areaproject")
    },

  },
  mounted() {
    this.chartsInit()
  }
}
</script>

<style scoped>
.fontSet{
  background-image:linear-gradient(to right, #000066 , #990000);
  -webkit-background-clip: text;
  color: transparent;
  font-size: 220%;
  font-weight: bold;

}
.fontSetTwo{
  background-image:linear-gradient(to bottom, #000066 , #990000);
  -webkit-background-clip: text;
  color: transparent;
  font-size: 220%;
  font-weight: bold;

}
</style>