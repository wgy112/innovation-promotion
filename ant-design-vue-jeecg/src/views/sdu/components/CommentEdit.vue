<template>
  <div>
<!--    <a-list-->
<!--      v-if="comments.length"-->
<!--      :data-source="comments"-->
<!--      :header="`${comments.length} ${comments.length > 1 ? 'replies' : 'reply'}`"-->
<!--      item-layout="horizontal"-->
<!--    >-->
<!--      <a-list-item slot="renderItem" slot-scope="item, index">-->
<!--        <a-comment-->
<!--          :author="item.author"-->
<!--          :avatar="item.avatar"-->
<!--          :content="item.content"-->
<!--          :datetime="item.datetime"-->
<!--        />-->
<!--      </a-list-item>-->
<!--    </a-list>-->
    <a-comment id="comment-area">
      <a-avatar
        slot="avatar"
        style="backgroundColor:#87d068" icon="user"
      />
      <div slot="content">
        <a-form-item>
          <a-textarea id="my-textarea" :rows="4" :value="value" @change="handleChange" />
        </a-form-item>
        <a-form-item>
          <a-button html-type="submit" :loading="submitting" type="primary" @click="handleSubmit">
            添加评论
          </a-button>
        </a-form-item>
      </div>
    </a-comment>
  </div>
</template>

<script>
import moment from 'moment';
import { mapGetters } from 'vuex'
import datetime from '../../../components/jeecg/JVxeTable/jvxeTypes'
import store from '@/store/'
import {nanoid} from 'nanoid'

export default {
  name: 'CommentEdit',
  data() {
    return {
      comments: [],
      submitting: false,
      value: '',
      moment,
      item:{
        id:"",
        date:"",
        content:"",
        parentmsgid:"",
        user:"",
        schemeid:"",
        parentmsguser:""
      },
      commentNotice:{
        //commentid:"",
        comment:"",
        username:"",
        solutionid:"",
        date:"",
        solutionusername:"",
        parentmsguser:"",
      }
    };
  },
  props:{
    parentMsgId:"",
    replyMsgUsername:"",
    scheme:"",
  },
  watch: {
    replyMsgUsername() {
      document
        .querySelector("#my-textarea")
        .setAttribute("placeholder", "回复: " + "@" + this.replyMsgUsername);
      console.log(this.scheme)
    }
  },
  methods: {
    // handleSubmit() {
    //   if (!this.value) {
    //     return;
    //   }
    //
    //   this.submitting = true;
    //
    //   setTimeout(() => {
    //     this.submitting = false;
    //     this.comments = [
    //       {
    //         author: this.nickname,
    //         avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
    //         content: this.value,
    //         datetime: moment().fromNow(),
    //       },
    //       ...this.comments,
    //     ];
    //     this.value = '';
    //   }, 1000);
    // },
    async handleSubmit(){
      // var userId = store.getters.userInfo.id;
      // console.log(userId)
      if(!this.value){
        return;
      }
      //this.submitting = true;
      this.getData();
      this.item.content=this.value;
      this.item.parentmsgid=this.parentMsgId;
      console.log(this.scheme)
      this.item.schemeid=this.scheme.id;
      this.item.user = this.nickname;
      //this.item.user=this.$cookies.get("innovation_promotion_username");
      this.item.parentmsguser=this.replyMsgUsername
      this.commentNotice.parentmsguser=this.replyMsgUsername
      // this.item.id=nanoid()
      // this.commentNotice.commentid=this.item.id
      this.commentNotice.comment = this.value;
      await this.$axios.post("sdu/comments/add",this.item).then(res=>{
        this.submitting = false;
        this.value="";
        document
          .querySelector("#my-textarea")
          .setAttribute("placeholder", "");
        this.$emit('reload');
        console.log(this.item);
      })
      await this.handleCommentNotice()
    },

    async handleCommentNotice() {
      this.getData();
      this.commentNotice.username = this.$cookies.get("innovation_promotion_username");
      this.commentNotice.solutionid = this.scheme.id;
      this.commentNotice.solutionusername = this.scheme.user;
      await this.$axios.post("sdu/commentnotice/add", this.commentNotice).then(res => {
        console.log(this.commentNotice)
      })
    },

     handleChange(e) {
      this.value = e.target.value;
    },
    getData(){
      const date = new Date()
      const year = date.getFullYear() //月份从0~11，所以加一
      const dateArr = [date.getMonth() + 1, date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds()]
      for(let i=0; i<dateArr.length; i++){
        if (dateArr[i] >= 1 && dateArr[i] <= 9) {
          dateArr[i] = "0" + dateArr[i];
        }
      }
      const strDate = year + '-' + dateArr[0] + '-' + dateArr[1] + ' ' + dateArr[2] + ':' + dateArr[3] + ':' + dateArr[4]
      this.item.date=strDate
      //this.item.id=nanoid()
      this.commentNotice.date = strDate
    },
  },
  computed:{
    ...mapGetters(['nickname'])
  },
  mounted() {
    console.log(this.scheme)
  }
}
</script>

<style scoped>

</style>