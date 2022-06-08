<template>
  <div>
    <a-popover
      trigger="click"
      placement="bottomRight"
      :autoAdjustOverflow="true"
      :arrowPointAtCenter="true"
      overlayClassName="header-notice-wrapper"
      @visibleChange="handleHoverChange"
      :overlayStyle="{ width: '300px', top: '50px' }">
      <template slot="content">
        <a-spin :spinning="loadding">
          <a-tabs>
            <a-tab-pane :tab="msg1Title" key="1">
              <a-list :data-source="dataSource" v-if="identify===true">
                <a-list-item slot="renderItem" slot-scope="item, index">
                  <a-list-item-meta title="你有新的评论" @click="toSolution(item)" :description="item.comment">
                    <a-avatar style="background-color: white" slot="avatar" src="https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png"/>
                  </a-list-item-meta>
                </a-list-item>
              </a-list>
              <a-list :data-source="dataSource" v-if="identify===false">
                <a-list-item slot="renderItem" slot-scope="item, index">
                  <a-list-item-meta title="你有新的评论" @click="toSolution2(item)" :description="item.comment">
                    <a-avatar style="background-color: white" slot="avatar" src="https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png"/>
                  </a-list-item-meta>
                </a-list-item>
              </a-list>
              <!--              <a-list-item>-->
              <!--                <a-list-item-meta title="你推荐的 IT大牛 已通过第三轮面试" description="一年前">-->
              <!--                  <a-avatar style="background-color: white" slot="avatar" src="https://gw.alipayobjects.com/zos/rmsportal/OKJXDXrmkNshAMvwtvhu.png"/>-->
              <!--                </a-list-item-meta>-->
              <!--              </a-list-item>-->
              <!--              <a-list-item>-->
              <!--                <a-list-item-meta title="这种模板可以区分多种通知类型" description="一年前">-->
              <!--                  <a-avatar style="background-color: white" slot="avatar" src="https://gw.alipayobjects.com/zos/rmsportal/kISTdvpyTAhtGxpovNWd.png"/>-->
              <!--                </a-list-item-meta>-->
              <!--              </a-list-item>-->

              <!--            <a-list>-->
              <!--              <a-list-item :key="index" v-for="(record, index) in announcement1">-->
              <!--                <div style="margin-left: 5%;width: 80%">-->
              <!--                  <p><a @click="showAnnouncement(record)">{{ record.titile }}</a></p>-->
              <!--                  <p style="color: rgba(0,0,0,.45);margin-bottom: 0px">{{ record.createTime }} 发布</p>-->
              <!--                </div>-->
              <!--                <div style="text-align: right">-->
              <!--                  <a-tag @click="showAnnouncement(record)" v-if="record.priority === 'L'" color="blue">一般消息</a-tag>-->
              <!--                  <a-tag @click="showAnnouncement(record)" v-if="record.priority === 'M'" color="orange">重要消息</a-tag>-->
              <!--                  <a-tag @click="showAnnouncement(record)" v-if="record.priority === 'H'" color="red">紧急消息</a-tag>-->
              <!--                </div>-->
              <!--              </a-list-item>-->
              <!--              <div style="margin-top: 5px;text-align: center">-->
              <!--                <a-button @click="toMyAnnouncement()" type="dashed" block>查看更多</a-button>-->

              <!--              </div>-->
              <!--            </a-list>-->
            </a-tab-pane>
            <a-tab-pane :tab="msg2Title" key="2">
              <a-list>
                <a-list-item :key="index" v-for="(record, index) in sduMessage">
                  <div style="margin-left: 5%;width: 80%">
                    <p><a @click="showReplyMessage(record)">{{ record.msgTxt }}</a></p>
                    <p style="color: rgba(0,0,0,.45);margin-bottom: 0px">来自{{ record.senderName }}的私信</p>
                  </div>
                  <!--                <div style="text-align: right">-->
                  <!--                  <a-tag @click="showAnnouncement(record)" v-if="record.priority === 'L'" color="blue">一般消息</a-tag>-->
                  <!--                  <a-tag @click="showAnnouncement(record)" v-if="record.priority === 'M'" color="orange">重要消息</a-tag>-->
                  <!--                  <a-tag @click="showAnnouncement(record)" v-if="record.priority === 'H'" color="red">紧急消息</a-tag>-->
                  <!--                </div>-->
                </a-list-item>
                <!--              <div style="margin-top: 5px;text-align: center">-->
                <!--                <a-button @click="toMyAnnouncement()" type="dashed" block>查看更多</a-button>-->
                <!--                <a-button @click="SendMsg()" type="dashed" block>发送信息</a-button>-->
                <!--              </div>-->
              </a-list>
            </a-tab-pane>
          </a-tabs>
        </a-spin>
      </template>
      <span @click="fetchNotice" class="header-notice">
      <a-badge :count="msgTotal">
        <a-icon style="font-size: 16px; padding: 4px" type="bell" />
      </a-badge>
    </span>
      <show-announcement ref="ShowAnnouncement" @ok="modalFormOk"></show-announcement>
      <dynamic-notice ref="showDynamNotice" :path="openPath" :formData="formData"/>
    </a-popover>
    <a-modal
      title="查看私信"
      :visible="replyMsgVisible"
      :confirm-loading="confirmLoading"
      @ok="handleOk"
      @cancel="handleCancel"
    >
      <a-avatar size="large" icon="user" style="backgroundColor:#87d068"/>
      <h2>{{this.replyRecord.senderName}}</h2>
      <p>{{this.replyRecord.msgTxt}}</p>
      <a-textarea placeholder="输入您的回复,点击确定发送给对方" :rows="4" v-model="ModalText"/>
      <a-alert v-if="alertVisible" message="请输入聊天内容,点击确定发送" banner />
    </a-modal>
  </div>
</template>

<script>
  import { getAction,putAction } from '@/api/manage'
  import ShowAnnouncement from './ShowAnnouncement'
  import store from '@/store/'
  import DynamicNotice from './DynamicNotice'
  import { mapGetters } from 'vuex'
  export default {
    name: "HeaderNotice",
    components: {
      DynamicNotice,
      ShowAnnouncement,
    },
    data () {
      return {
        alertVisible:false,
        loadding: false,
        url:{
          listCementByUser:"/sys/annountCement/listByUser",
          editCementSend:"/sys/sysAnnouncementSend/editByAnntIdAndUserId",
          queryById:"/sys/annountCement/queryById",
        },
        hovered: false,
        announcement1:[],
        announcement2:[],
        msg1Count:"0",
        msg2Count:"0",
        msg1Title:"通知",
        msg2Title:"",
        stopTimer:false,
        websock: null,
        lockReconnect:false,
        heartCheck:null,
        formData:{},
        openPath:'',
        dataSource:[],
        identify:true,//true服务商false企业用户
        sduMessage:[],
        replyMsgVisible:false,
        confirmLoading: false,
        replyRecord:{},
        ModalText:"",
      }
    },
    computed:{
      msgTotal () {
        return parseInt(this.msg1Count)+parseInt(this.msg2Count);
      },
      ...mapGetters(['nickname'])
    },
    mounted() {
      this.loadData();
      //this.timerFun();
      this.initWebSocket();
     // this.heartCheckFun();
      this.identityRole()
      //this.getCommentNotice()
      //this.demandGetCommentNotice()
    },
    destroyed: function () { // 离开页面生命周期函数
      this.websocketOnclose();
    },
    methods: {
      timerFun() {
        this.stopTimer = false;
        let myTimer = setInterval(()=>{
          // 停止定时器
          if (this.stopTimer == true) {
            clearInterval(myTimer);
            return;
          }
          this.loadData()
        },6000)
      },
      loadData (){
        try {
          // 获取系统消息
          getAction(this.url.listCementByUser).then((res) => {
            if (res.success) {
              console.log(res)
              this.announcement1 = res.result.anntMsgList;
              this.msg1Count = res.result.anntMsgTotal;
              this.msg1Title = "通知";
              this.announcement2 = res.result.sysMsgList;
              this.msg2Count = res.result.sysMsgTotal;
              this.msg2Title = "系统消息";
            }
          }).catch(error => {
            console.log("系统消息通知异常",error);//这行打印permissionName is undefined
            this.stopTimer = true;
            console.log("清理timer");
          });
        } catch (err) {
          this.stopTimer = true;
          console.log("通知异常",err);
        }
      },
      fetchNotice () {
        if (this.loadding) {
          this.loadding = false
          return
        }
        this.loadding = true
        setTimeout(() => {
          this.loadding = false
        }, 200)
      },
      showDialogue(){

      },
      showAnnouncement(record){
        putAction(this.url.editCementSend,{anntId:record.id}).then((res)=>{
          if(res.success){
            this.loadData();
          }
        });
        this.hovered = false;
        if(record.openType==='component'){
          this.openPath = record.openPage;
          this.formData = {id:record.busId};
          this.$refs.showDynamNotice.detail(record.openPage);
        }else{
          this.$refs.ShowAnnouncement.detail(record);
        }
      },
      toMyAnnouncement(){
        this.$router.push({
          path: '/isps/userAnnouncement'
        });
      },
      modalFormOk(){
      },
      handleHoverChange (visible) {
        this.hovered = visible;
      },

      initWebSocket: function () {
        // WebSocket与普通的请求所用协议有所不同，ws等同于http，wss等同于https
        var userId = store.getters.userInfo.id
        var url = window._CONFIG['domianURL'].replace("https://","wss://").replace("http://","ws://")+"/websocket/"+userId;
        //console.log(url);
        this.websock = new WebSocket(url);
        this.websock.onopen = this.websocketOnopen;
        this.websock.onerror = this.websocketOnerror;
        this.websock.onmessage = this.websocketOnmessage;
        this.websock.onclose = this.websocketOnclose;
        // console.log(userId)
        // this.userid = userId
        this.getUnreadMsg();
      },

      websocketOnopen: function () {
        console.log("WebSocket连接成功");
        var message = {
          toid: "1479290974221492226",
          content: "I love programming"
        };
        //this.websock.send(JSON.stringify(message))
        //心跳检测重置
        //this.heartCheck.reset().start();
        //this.getCommentNotice()
      },
      websocketOnerror: function (e) {
        console.log("WebSocket连接发生错误");
        this.reconnect();
      },
      websocketOnmessage: function (e) {
        //console.log(e)
        this.openNotification(e.data)
        console.log("-----接收消息-------",e.data);
        var data = eval("(" + e.data + ")"); //解析对象
        if(data.cmd == "topic"){
            //系统通知
          this.loadData();
        }else if(data.cmd == "user"){
            //用户消息
          this.loadData();
        }
        //心跳检测重置
        //this.heartCheck.reset().start();
      },
      websocketOnclose: function (e) {
        console.log("connection closed (" + e + ")");
        if(e){
          console.log("connection closed (" + e.code + ")");
        }
        this.reconnect();
      },
      websocketSend(text) { // 数据发送
        try {
          this.websock.send(text);
        } catch (err) {
          console.log("send failed (" + err.code + ")");
        }
      },
      SendMsg(){
        // this.timeoutObj = setTimeout(function(){
        //   this.websocketSend("nihao");
        //   console.info("客户端发送心跳2");
        // },this.timeout)
        const message = {
          "fromid": '1499295785461276673',
          "toid": '1479290974221492226',
          "content": 'I love programming'
        }
        this.websocketSend(JSON.stringify(message));
        //this.websocketOnmessage("nihao")
      },
      openNotification (data) {
        var text = data.msgTxt;
        const key = `open${Date.now()}`;
        if (JSON.parse(data).msgId === "M0000"){// 推广方案
          this.$notification.open({
            message: '💡您收到一条推广方案',
            placement:'bottomRight',
            description: text,
            key,
            btn: (h)=>{
              return h('a-button', {
                props: {
                  type: 'primary',
                  size: 'small',
                },
                on: {
                  click: async () => {
                    //this.showDetail(key,data)
                    //console.log(JSON.parse(data))
                      await this.$axios.get("sdu/solution/findById", {
                        params: {
                          id: JSON.parse(data).msgTxt
                        }
                      }).then(res=>{
                          this.$router.push({
                            path: '/sdu/schemedetail' +
                              '',
                            query: {
                              solution:res.data.result
                            }
                          })
                        }
                      );
                  }
                }
              }, '查看详情')
            },
          });
        }
        if (JSON.parse(data).msgId === "M0001"){// 私信
          this.$notification.open({
            message: '💡您收到一条私信',
            placement:'bottomRight',
            description: text,
            key,
            btn: (h)=>{
              return h('a-button', {
                props: {
                  type: 'primary',
                  size: 'small',
                },
                on: {
                  click: async () => {
                    //this.showDetail(key,data)
                    //console.log(JSON.parse(data))
                    this.replyMsgVisible = true
                    this.replyRecord = JSON.parse(data)
                  }
                }
              }, '查看详情')
            },
          });
        }
      },

      reconnect() {
        var that = this;
        if(that.lockReconnect) return;
        that.lockReconnect = true;
        //没连接上会一直重连，设置延迟避免请求过多
        setTimeout(function () {
          console.info("尝试重连...");
          that.initWebSocket();
          that.lockReconnect = false;
        }, 5000);
      },
      heartCheckFun(){
        var that = this;
        //心跳检测,每20s心跳一次
        that.heartCheck = {
          timeout: 20000,
          timeoutObj: null,
          serverTimeoutObj: null,
          reset: function(){
            clearTimeout(this.timeoutObj);
            //clearTimeout(this.serverTimeoutObj);
            return this;
          },
          start: function(){
            var self = this;
            this.timeoutObj = setTimeout(function(){
              //这里发送一个心跳，后端收到后，返回一个心跳消息，
              //onmessage拿到返回的心跳就说明连接正常
              that.websocketSend("HeartBeat");
              console.info("客户端发送心跳");
              //self.serverTimeoutObj = setTimeout(function(){//如果超过一定时间还没重置，说明后端主动断开了
              //  that.websock.close();//如果onclose会执行reconnect，我们执行ws.close()就行了.如果直接执行reconnect 会触发onclose导致重连两次
              //}, self.timeout)
            }, this.timeout)
          }
        }
      },


      showDetail(key,data){
        this.$notification.close(key);
        var id = data.msgId;
        getAction(this.url.queryById,{id:id}).then((res) => {
          if (res.success) {
            var record = res.result;
            this.showAnnouncement(record);
          }
        })

      },
      async getCommentNotice() {
        //console.log(this.$cookies.get('innovation_promotion_username'))
        await this.$axios.get("sdu/commentnotice/getNoticebyName", {
          params: {
            name: this.$cookies.get('innovation_promotion_username')
          }
        }).then(res => {
          this.dataSource = res.data.result
          //console.log(this.dataSource)
          if(this.dataSource.length>0){
            this.$emit('changeflag',this.dataSource.length)
          }
        })
      },
      async demandGetCommentNotice() {
        //console.log(this.$cookies.get('innovation_promotion_username'))
        await this.$axios.get("sdu/commentnotice/demandGetNoticebyName", {
          params: {
            name: this.nickname
          }
        }).then(res => {
          this.dataSource = res.data.result
          console.log(this.dataSource)
          if(this.dataSource.length>0){
            this.$emit('changeflag',this.dataSource.length)
          }
        })
      },
      async toSolution(item) {
        await this.$axios.get("sdu/solutiondemand/findById", {
          params: {
            id: item.solutionid
          }
        }).then(res => {
          console.log(res.data.result[0])
          this.$router.push({
            path: '/sdu/schemedetailtwo',
            query: {
              solution: res.data.result[0]
            }
          })
          item.state=0
          //item.demandreadstate=0
          this.$nextTick(this.$axios.put("sdu/commentnotice/deleteById",item).then(res=>{
            this.getCommentNotice()
          }))
        })
      },
      async toSolution2(item) {
        await this.$axios.get("sdu/solutiondemand/findById", {
          params: {
            id: item.solutionid
          }
        }).then(res => {
          console.log(res.data.result[0])
          this.$router.push({
            path: '/sdu/schemedetailtwo',
            query: {
              solution: res.data.result[0]
            }
          })
          //item.state=0
          item.demandreadstate=0
          this.$nextTick(this.$axios.put("sdu/commentnotice/deleteById",item).then(res=>{
            this.demandGetCommentNotice()
          }))
        })
      },
      async identityRole(){
        const userid = store.getters.userInfo.id
        console.log(userid)
        await this.$axios.get("sys/role/getroleid",{
          params:{
            userid:userid
          }
        }).then(res=>{
          //console.log(res)
          //服务商
          if(res.data=== 1476359056458838000){
            //console.log("123")
            this.identify=true
            this.getCommentNotice()
          }else if(res.data===1473923867157840000){//企业用户
           // console.log("456")
            this.identify=false
            this.demandGetCommentNotice()
          }else {

          }
        })
      },
      async getUnreadMsg(){
        const userId = store.getters.userInfo.id
        var params = new URLSearchParams();
        params.append('userId', userId);
        await this.$axios.post("sys/socketTest/pullUnreadMessage",params).then(res=>{
          this.sduMessage=res.data.result
          console.log(this.sduMessage)
          for(var key in this.sduMessage){
            //console.log(this.sduMessage[key])
            this.$emit('changeflag',this.sduMessage.length)
            //this.openNotification(this.sduMessage[key])
          }
        })
      },
      showReplyMessage(record){
        this.replyMsgVisible=true
        this.replyRecord = record
      },
      handleOk(e) {
        if(this.ModalText != ""){
          //this.ModalText = 'The modal will be closed after two seconds';
          this.confirmLoading = true;
          let json = {};
          json.userId = this.replyRecord.senderId
          json.message = this.ModalText
          json.senderId = store.getters.userInfo.id
          json.senderName = this.nickname
          const date = new Date()
          const year = date.getFullYear() //月份从0~11，所以加一
          const dateArr = [date.getMonth() + 1, date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds()]
          for (let i = 0; i < dateArr.length; i++) {
            if (dateArr[i] >= 1 && dateArr[i] <= 9) {
              dateArr[i] = "0" + dateArr[i];
            }
          }
          json.date = year + '-' + dateArr[0] + '-' + dateArr[1] + ' ' + dateArr[2] + ':' + dateArr[3] + ':' + dateArr[4]
          this.$axios.post("sys/socketTest/sendUser", json).then(res => {
            this.confirmLoading = true;
            setTimeout(() => {
              this.replyMsgVisible = false;
              this.confirmLoading = false;
            }, 2000);
          })
          // setTimeout(() => {
          //   this.replyMsgVisible = false;
          //   this.confirmLoading = false;
          // }, 2000);
        }else {
          this.alertVisible = true
        }
      },
      handleCancel(e) {
        //console.log('Clicked cancel button');
        this.replyMsgVisible = false;
      },
    },

  }
</script>

<style lang="css">
  .header-notice-wrapper {
    top: 50px !important;
  }
</style>
<style lang="less" scoped>
  .header-notice{
    display: inline-block;
    transition: all 0.3s;

    span {
      vertical-align: initial;
    }
  }
</style>