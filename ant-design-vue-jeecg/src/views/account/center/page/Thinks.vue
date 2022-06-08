<template>
  <div class="app-list">
    <a-list
      :grid="{lg:1}"
      :dataSource="dataSource">
      <a-list-item slot="renderItem" slot-scope="item, index">
        <a-card :hoverable="true">
          <span><i class="el-icon-star-off"></i></span>
          <br/>
          <br/>
          <a-card-meta>
            <div style="margin-bottom: 3px" slot="title">{{ item.title }}</div>

<!--            <a-avatar class="card-avatar" slot='avatar' size="small"/>-->


            <div class="meta-cardInfo" slot="description">
              <!--              <div>-->
              <!--                <p>活跃用户</p>-->
              <p>
                <span>{{ item.detail }}</span>
              </p>
            </div>
          </a-card-meta>
          <template class="ant-card-actions" slot="actions">
          </template>
        </a-card>
      </a-list-item>
    </a-list>
  </div>
</template>

<script>
const dataSource = [{
  title:'网器应用平台',
  detail:'网器应用平台详细内容网器应用平台详细内容网器应用平台详细内容网器应用平台详细内容网器应用平台详细内容',
},
  {
    title: '智能制造工厂模型',
    detail: '智能制造工厂模型详细内容智能制造工厂模型详细内容智能制造工厂模型详细内容智能制造工厂模型详细内容智能制造工厂模型详细内容'
  }]
// for (let i = 0; i < 11; i++) {
//   dataSource.push({
//     title: 'Alipay',
//     avatar: 'https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png',
//     activeUser: 17,
//     newUser: 1700
//   })
// }


export default {
  name: "thinks",
  components: {},
  data() {
    return {
      dataSource,
    }
  }
}
</script>

<style lang="less" scoped>

.app-list {

  .meta-cardInfo {
    zoom: 1;
    margin-top: 16px;

    > div {
      position: relative;
      text-align: left;
      float: left;
      width: 50%;

      p {
        line-height: 32px;
        font-size: 24px;
        margin: 0;

        &:first-child {
          color: rgba(0, 0, 0, .45);
          font-size: 12px;
          line-height: 20px;
          margin-bottom: 4px;
        }
      }

    }
  }
}

</style>