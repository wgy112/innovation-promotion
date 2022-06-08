<template>
  <a-comment>
    <span
      slot="actions"
      key="comment-basic-reply-to"
      @click="handlReply(comment.id, comment.user)"
    >
      <a href="#my-textarea">回复</a>
    </span>
    <a slot="author" style="font-size: 15px">{{comment.user}}</a>
    <a
      v-if="comment.parentmsguser"
      slot="author"
      class="reply-to"
    >@{{comment.parentmsguser}}</a>
    <a-avatar slot="avatar" style="backgroundColor:#87d068" icon="user"/>
    <p slot="content">{{comment.content}}</p>
    <a-tooltip slot="datetime">
      <span>{{comment.date}}</span>
    </a-tooltip>
    <slot name="childComment"></slot>
  </a-comment>
</template>

<script>
export default {
  name: 'Comment',
  props: {
    comment: ""
  },
  methods: {
    handlReply(msgId, msgUsername) {
      this.$emit("handleReply", { msgId, msgUsername });
    }
  },
  mounted(){
    console.log("this is comment")
  }
}
</script>

<style scoped>
.reply-to {
  padding-left: 5px;
  color: #409eff;
  font-weight: 500;
  font-size: 15px;
}
</style>