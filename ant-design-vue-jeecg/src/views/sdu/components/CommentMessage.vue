<template>
  <div id="commentMsg">
    <div v-if="isEmpty(commentList)" class="head-message">暂无留言内容</div>
    <div v-else class="head-message">留言内容</div>
    <comment
      @handleReply="handleReply"
      v-for="(item1, index) in commentList"
      :key="'parent-' + index"
      :comment="item1"
    >
      <!-- 二层留言 -->
      <template #childComment v-if="!isEmpty(item1.children)">
        <comment
          v-for="(item2, index) in item1.children"
          :key="'children-' + index"
          :comment="item2"
          @handleReply="handleReply"
        ></comment>
      </template>
    </comment>
  </div>
</template>

<script>
import Comment from './Comment'
export default {
  name: 'CommentMessage.vue',
  components:{
    Comment
  },
  // data(){
  //   return{
  //     commentList:[],
  //   }
  // },
  props:{
    commentList:{
      type:Array,
      default:[]
    }
  },
  methods:{
    isEmpty(ls){
      return ls.length === 0
    },
    handleReply(data) {
      this.$emit("handleReply",{
        msgId:data.msgId,
        msgUsername:data.msgUsername
      });
    },
    async  findMessage(){
      let a = this.scheme.id
      await this.$axios.get("sdu/comments/findAll",{
        params:{
          id:a
        }
      }).then(res=>{
        this.commentList = JSON.parse(JSON.stringify(res.data.result));
        console.log("213")
      })
    },
  },
  mounted(){
    console.log("this is commentMessage")
    console.log(this.commentList)
  }
}
</script>

<style scoped>
.head-message {
  font-size: 20px;
  text-align: center;
}
</style>