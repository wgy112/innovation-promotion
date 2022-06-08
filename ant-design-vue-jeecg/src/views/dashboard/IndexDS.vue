<template>
  <div >
<!--    <div class="index-container-ty">-->
    <a-spin :spinning="loading">
      <a-row type="flex" justify="start" :gutter="3">
        <a-col :sm="24" :lg="11">
          <div>
            <div style="margin-top: 60px;margin-bottom: 20px">
              <a-row :gutter="24">
                <a-col :span="2">
                  <span>

                    <a-icon type="appstore" style="color:#990000 ;font-size: 240%;margin-top: 7px;margin-left: 10px"/>
                  </span>
                </a-col>
                <a-col :span="18">
                  <span class="fontSet">
                    最新需求
                  </span>
                </a-col>
                <a-col :span="2">

                  <a-button style="height:36px;border-color: #990000" @click="demandButton">
                     <span class="fontSetMore">
                      More +
                     </span>
                  </a-button>

                </a-col>
              </a-row>
            </div>
            <a-table
              ref="table1"
              size="middle"
              rowKey="id"
              :columns="columns1"
              :dataSource="dataSource1"
              :pagination="false"
              :customRow="click1">
              <template slot="ellipsisText" slot-scope="text">
                <j-ellipsis :value="text" :length="textMaxLength"></j-ellipsis>
              </template>
              <template slot="date" slot-scope="text,record">
                <div class="dateTableSet"> {{text}}</div>
              </template>
              <template slot="demandName" slot-scope="text,record">
                <div class="demandTableSet"> {{text}}</div>
              </template>
            </a-table>
          </div>
        </a-col>
        <a-col :sm="24" :lg="2">

        </a-col>

        <a-col :sm="24" :lg="11">
          <div style="margin-top: 60px;margin-bottom: 20px">
            <div style="margin-top: 60px;margin-bottom: 20px">
              <a-row :gutter="24">
                <a-col :span="2">
                  <span>
                    <a-icon type="appstore" style="color:#990000 ;font-size: 240%;margin-top: 7px;margin-left: 10px"/>
                  </span>
                </a-col>
                <a-col :span="18">
                  <span class="fontSet">
                    最新通用方案
                  </span>
                </a-col>
                <a-col :span="2">
                  <a-button style="height:36px;border-color: #990000" @click="solutionButton">
                     <span class="fontSetMore">
                      More +
                     </span>
                  </a-button>
                </a-col>
              </a-row>
            </div>

            <a-table
              ref="table2"
              size="middle"
              rowKey="id"
              :columns="columns2"
              :dataSource="dataSource2"
              :pagination="false"
              :customRow="click2">
              <template slot="ellipsisText" slot-scope="text">
                <j-ellipsis :value="text" :length="textMaxLength"></j-ellipsis>
              </template>
              <template slot="date" slot-scope="text,record">
                <div class="dateTableSet"> {{text}}</div>
              </template>
              <template slot="demandName" slot-scope="text,record">
                <div class="demandTableSet"> {{text}}</div>
              </template>
            </a-table>
          </div>
        </a-col>
      </a-row >

      <a-row type="flex" justify="start" :gutter="3">
        <a-col :sm="24" :lg="24">

          <div style="margin-top: 60px;margin-bottom: 20px">
              <a-row :gutter="24">
                <a-col :span="1">
                  <span>

                    <a-icon type="appstore" style="color:#990000 ;font-size: 240%;margin-top: 7px;margin-left: 10px"/>
                  </span>
                </a-col>
                <a-col :span="21">
                  <span class="fontSet">
                    最新活动
                  </span>
                </a-col>
                <a-col :span="2">

                  <a-button style="height:36px;border-color: #990000">
                     <span class="fontSetMore">
                      More +
                     </span>
                  </a-button>

                </a-col>
              </a-row>
            </div>
          <div>
            <a-col>

            </a-col>

          </div>



        </a-col>
        <a-col :sm="24" :lg="8">
          <a-card style="background-color:#F8F8F6;width: 80%;margin-left: 10%;padding: 0" :body-style="{padding:'0'}">
            <img src="../../assets/activity/shandongchuangxin.png" alt="" width="100%" height="100%">
            <div style="font-size: 120%;font-weight: bold;text-align: center">2022山东省创新方法大赛</div>
            <div style="font-size: 120%;font-weight: bold;text-align: left">主办单位:山东省科协、山东省科技厅</div>
            <div style="font-size: 120%;font-weight: bold;text-align: left">时间：2022</div>
            <a-button style="margin-left: 35%;margin-bottom: 2%;margin-top: 4%" @click="goActivity0"> 报名参加</a-button>
          </a-card>
        </a-col>
<!--        <a-col :sm="24" :lg="8">-->
<!--            <a-card style="background-color:#F8F8F6;width: 80%;margin-left: 10%;padding: 0" :body-style="{padding:'0'}">-->
<!--              <img src="../../assets/activity/activity_1.jpg" alt="" width="100%" height="100%">-->
<!--              <div style="font-size: 120%;font-weight: bold;text-align: center">PCon 全球产品创新大会.2021北京站</div>-->
<!--              <div style="font-size: 120%;font-weight: bold;text-align: left">主办单位：51CTO</div>-->
<!--              <div style="font-size: 120%;font-weight: bold;text-align: center">时间：   2022-04-09 09:00 - 2022-04-10 18:00</div>-->
<!--              <a-button style="margin-left: 35%;margin-bottom: 2%;margin-top: 4%" @click="goActivity3"> 报名参加</a-button>-->
<!--            </a-card>-->
<!--        </a-col>-->
        <a-col :sm="24" :lg="8">
          <a-card style="background-color:#F8F8F6;width: 80%;margin-left: 10%"  :body-style="{padding:'0'}"  >
            <img src="../../assets/activity/activity_2.jpg" alt="" width="100%" height="100%" >
            <div style="font-size: 120%;font-weight: bold;text-align: center">PCon 全球产品创新大会.2021北京站</div>
            <div style="font-size: 120%;font-weight: bold;text-align: left">主办单位：51CTO</div>
            <div style="font-size: 120%;font-weight: bold;text-align: center">时间：   2022-04-09 09:00 - 2022-04-10 18:00</div>
            <a-button style="margin-left: 35%;margin-bottom: 2%;margin-top: 4%" @click="goActivity2"> 报名参加</a-button>
          </a-card>
        </a-col>
        <a-col :sm="24" :lg="8">
          <a-card style="background-color: #F8F8F6;width: 80%;height:100%;margin-left: 10%" :body-style="{padding:'0'}">
            <img src="../../assets/activity/activity_3.jpg" alt="" width="100%" height="100%">
            <div style="font-size: 120%;font-weight: bold;text-align: center">PCon 全球产品创新大会.2021北京站</div>
            <div style="font-size: 120%;font-weight: bold;text-align: left">主办单位：51CTO</div>
            <div style="font-size: 120%;font-weight: bold;text-align: center">时间：   2022-04-09 09:00 - 2022-04-10 18:00</div>
            <a-button style="margin-left: 35%;margin-bottom: 2%;margin-top: 4%" @click="goActivity1">报名参加</a-button>
          </a-card>
        </a-col>


      </a-row>

      <a-row type="flex" justify="start" :gutter="3">
        <a-col :sm="24" :lg="24">

          <div style="margin-top: 60px;margin-bottom: 20px">
            <a-row :gutter="24">
              <a-col :span="1">
                  <span>
                    <a-icon type="appstore" style="color:#990000 ;font-size: 240%;margin-top: 7px;margin-left: 10px"/>
                  </span>
              </a-col>
              <a-col :span="21">
                  <span class="fontSet">
                    创新工具
                  </span>
              </a-col>

            </a-row>
          </div>

        </a-col>

        <a-col :sm="24" :lg="8" style="text-align: center">
          <a-card style="background-color:#f7f9fa;width: 80%;margin-left: 10%;padding: 0;border: none" :body-style="{padding:'0'}">
            <div style="height: 250px;display: table-cell;vertical-align: middle">
              <img src="../../assets/mindfalllogo.png" alt="" width="100%" style="">
            </div>
            <a style="font-size: 120%;font-weight: bold;" @click="gotoTool">生成创意方案</a>
            <div style="font-size: 120%;font-weight: bold;text-align: center">一款使用Triz理论的帮助工程师快速开发创新方案的创新工具</div>

          </a-card>
        </a-col>

        <a-col :sm="24" :lg="8">
          <a-card style="background-color: #f7f9fa;width: 80%;height:100%;margin-left: 10%" :body-style="{padding:'0'}">
            <img src="../../assets/activity/triz_mooc_2.webp" alt="" width="100%" height="100%">
            <a style="font-size: 120%;font-weight: bold;margin-left: 110px">Triz 创新课程学习 </a>
            <div>
              TRIZ意译为发明问题的解决理论。TRIZ理论成功地揭示了创造发明的内在规律和原理，着力于澄清和强调系统中存在的矛盾，其目标是完全解决矛盾，获得最终的理想解
              .......

            </div>

          </a-card>
        </a-col>


      </a-row>



    </a-spin>

  </div>
</template>

<script >
import noDataPng from '@/assets/nodata.png'
import JEllipsis from '@/components/jeecg/JEllipsis'
import Icon from  'ant-design-vue';

const tip_green = "rgba(0, 255, 0, 1)"
//1-3天
const tip_yellow = "rgba(255, 255, 0, 1)"
//超期
const tip_red = "rgba(255, 0, 0, 1)"
export default {
  name: "IndexDS",
  components:{
    Icon,
    JEllipsis,

  },
  data() {
    return {
      loading:false,
      textMaxLength:8,
      dataSource1:[],
      dataSource2:[],
      dataSource3:[],
      dataSource4:[],
      columns1: [
        {
          title:'时间',
          align:"left",
          dataIndex: 'date',
          scopedSlots: {customRender: "date"},
          colSpan:0,
          width:'30%',



        },
        {
          title:'需求名称',
          align:"left",
          dataIndex: 'name',
          scopedSlots: {customRender: "demandName"},
          colSpan:0,
          ellipsis:true,
        },

      ],
      columns2: [

        {
          title:'',
          align:"left",
          dataIndex: 'name',
          scopedSlots: {customRender: "demandName"},
          colSpan:0,
          ellipsis:true,
        },
        {
          title:'时间',
          align:"left",
          dataIndex: 'date',
          scopedSlots: {customRender: "date"},
          colSpan:0,
          width:'25%'

        },

      ],


    }
  },
  created() {
    this.mock();
  },
  mounted(){

  },
  methods: {
    gotoTool(){
      let url = 'http://182.92.155.101/login?MindFallTicket=' + this.$store.getters.token
      window.open(url, '_blank')
    },
    getTipColor(rd){
      let num = rd.restDay
      if(num<=0){
        return tip_red
      }else if(num>=1 && num<4){
        return tip_yellow
      }else if(num>=4){
        return tip_green
      }
    },

    demandButton(){
      this.$router.push({
        path:'sdu/DemandClassify'
      })

    },
    solutionButton(){
      this.$router.push({
        path:'sdu/DemandClassify'
      })

    },


    goActivity1(){
      window.open('http://www.mostsd.com/page/innovate/eventDetails.html?id=4b80a6f8cf8448738c3526063f0a793b&anvaid=7',"_blank")
    },
    goActivity2(){
      window.open('http://www.mostsd.com/page/innovate/eventDetails.html?id=e3244041b93a43309bc610c8f3d4db1a&anvaid=7',"_blank")
    },
    goActivity3(){
      window.open('http://www.mostsd.com/page/innovate/eventDetails.html?id=62beb803f7c84c19a0009388de44d2d0&anvaid=7',"_blank")
    },
    goTrizCourse(){
      window.open('https://www.icourse163.org/course/SUDA-1001753306?from=searchPage',"_blank")

    },
    goActivity0(){
      this.$router.push({
        path:'/sdu/Activity',
      });
    },
    goPage(){
      this.$message.success("请根据具体业务跳转页面")
      //this.$router.push({ path: '/comp/mytask' })
    },
    async mock() {
      await this.$axios.get("sdu/creative/findLatestCreative").then(res => {
        console.log(res)
        this.dataSource1 = res.data
      })
      //this.dataSource1 = tempSs1
      await this.$axios.get("sdu/solution/getLatestSolution").then(res => {
        console.log(res)
        this.dataSource2 = res.data.result
      })
      //this.dataSource2 = tempSs2
      //this.dataSource3 = tempSs1
      this.dataSource4 = []
      this.ifNullDataSource(this.dataSource4, '.tytable4')
    },

    ifNullDataSource(ds,tb){
      this.$nextTick(()=>{
        if(!ds || ds.length==0){
          var tmp = document.createElement('img');
          tmp.src=noDataPng
          tmp.width=300
          let tbclass=`${tb} .ant-table-placeholder`
          // document.querySelector(tbclass).innerHTML=""
          // document.querySelector(tbclass).appendChild(tmp)
        }
      })
    },
    handleData(){

    },

    click1(record){
      return {
        on: {
          click: () => {
            this.$router.push({
              path:'/sdu/enterprise/DemandScan2',
              query:{
                demandItem:record,
              }
            })
          }
        }
      }
    },
    click2(record){
      return{
        on:{
          click:()=>{
            this.$router.push({
              path:'/sdu/schemedetail',
              query:{
                solution:record,
              }
            })
          }
        }
      }
    },


  }
}
</script>

<style lang="less" scoped>
/*.my-index-table{height:270px}*/
/*.my-index-table table{font-size: 14px !important;}*/
//.ant-table-tbody > tr > td {
//  text-align: center;
//  font-size: 120%;
//  color:black;
//  font-family: "Microsoft YaHei","Hiragino Sans GB",Helvetica;
//}

.box5{
  height: 100%;
  width: 100%;
  /*//background-image: url("../../assets/triz.png");*/
}

.fontSet{
  background-image:linear-gradient(to right, #000066 , #990000);
  -webkit-background-clip: text;
  color: transparent;
  font-size: 220%;
  font-weight: bold;

}

.fontSetMore{
  background-image:linear-gradient(to right, #000066 , #990000);
  -webkit-background-clip: text;
  color: transparent;
  font-size: 120%;

}
.demandTableSet{
  font-size: 120%;
  font-weight: 400;
  //font-family: "Hiragino Sans GB";


  font-family: "Microsoft YaHei","Hiragino Sans GB",Helvetica;

}
.dateTableSet{
  font-size: small;
  color:#2eabff ;
}

</style>