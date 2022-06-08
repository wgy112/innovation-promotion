<template>
  <div >

    <a-divider>
      <a-page-header
        style="border: 1px solid rgb(235, 237, 240)"
        title="需求类型"
      />
    </a-divider>

    <a-row :gutter="24">
      <a-col v-for="(item,index) in dataSource" :span="4" :key="index">
        <a-card :hoverable="true" @click="openScheme(item.id)">
          <a-card-meta >
            <div style="margin-bottom: 3px" slot="title">{{ item.title }}</div>
            <a-avatar class="card-avatar" slot="avatar" :src="item.avatar" size="large" />
            <div class="meta-content" slot="description">{{ item.content }}</div>
          </a-card-meta>
<!--          <template class="ant-card-actions" slot="actions">-->
<!--            <a @click="openScheme(item.id)">查看</a>-->
<!--          </template>-->
        </a-card>
      </a-col>
    </a-row>

<!--    <div class="card-list" ref="content">-->
<!--      <a-list-->
<!--        :grid="{gutter: 24,column:4}"-->
<!--        :dataSource="dataSource"-->
<!--      >-->
<!--        <a-list-item slot="renderItem" slot-scope="item, index">-->
<!--            <a-card :hoverable="true">-->
<!--              <a-card-meta>-->
<!--                <div style="margin-bottom: 3px" slot="title">{{ item.title }}</div>-->
<!--                <a-avatar class="card-avatar" slot="avatar" :src="item.avatar" size="large" />-->
<!--                <div class="meta-content" slot="description">{{ item.content }}</div>-->
<!--              </a-card-meta>-->
<!--              <template class="ant-card-actions" slot="actions">-->
<!--                <a @click="openScheme(item.id)">查看</a>-->
<!--              </template>-->
<!--            </a-card>-->
<!--          </a-list-item>-->

<!--      </a-list>-->
<!--    </div>-->

<!--    <a-upload-->
<!--      name="avatar"-->
<!--      list-type="picture-card"-->
<!--      class="avatar-uploader"-->
<!--      :show-upload-list="false"-->
<!--      action="https://www.mocky.io/v2/5cc8019d300000980a055e76"-->
<!--      :before-upload="beforeUpload"-->
<!--      @change="handleChange"-->
<!--    >-->
<!--      <img v-if="imageUrl" :src="imageUrl" alt="avatar" />-->
<!--      <div v-else>-->
<!--        <a-icon :type="loading ? 'loading' : 'plus'" />-->
<!--        <div class="ant-upload-text">-->
<!--          Upload-->
<!--        </div>-->
<!--      </div>-->
<!--    </a-upload>-->

  </div>
</template>

<script>
import RouteView from '@comp/layouts/RouteView'
import PageLayout from '@comp/page/PageLayout'

function getBase64(img, callback) {
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result));
  reader.readAsDataURL(img);
}

export default {
  name: 'IndexCard',
  components: {
    RouteView,
    PageLayout
  },
  data() {
    return {

      loading: false,
      imageUrl: '',

      title: '面向区域的智能化创新方法推广平台',
      description: '智能化的创新方法应用推广公共服务平台，实现创新资源聚集、创新方法服务化、创新服务智能化，支撑构建创新方法生态和创新方法应用推广。',
      //extraImage: require('../../assets/index.png'),
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
          avatar: require('../../assets/icon5.png'),
          content: ''
        },
        {
          id: 2,
          title: '个性化定制提升',
          avatar: require('../../assets/icon4.png'),
          content: ''
        },
        {
          id: 3,
          title: '网络化协同提升',
          avatar: require('../../assets/icon2.png'),
          content: ''
        },
        {
          id: 4,
          title: '服务型制造提升',
          avatar: require('../../assets/icon1.png'),
          content: ''
        },
        {
          id: 5,
          title: '中小企业创成',
          avatar: require('../../assets/icon3.png'),
          content: ''
        },
        {
          id: 6,
          title: '技术发明创造',
          avatar: require('../../assets/icon7.png'),
          content: ''
        }
      ],
      // imageBox: [
      //   { id: 0, idView: require('@assets/rotation/carousel01.jpg'), title: '' },
      //   { id: 1, idView: require('@assets/rotation/carousel02.jpg'), title: '' },
      //   { id: 2, idView: require('@assets/rotation/carousel03.jpg'), title: '' },
      //   { id: 3, idView: require('@assets/rotation/carousel04.jpg'), title: '' }
      // ]
    }
  },
  methods: {
    openScheme(cid) {

      this.$router.push({path:'demand/classify',query:{
        cid:cid
        }})

      // switch (cid) {
      //   case 1:
      //     this.$router.push({ path: 'table/AreaProject' })
      //     break
      //   case 2:
      //     this.$router.push({ path: 'table/Digital' })
      //     break
      //   case 3:
      //     this.$router.push({ path: 'table/Individuation' })
      //     break
      //   case 4:
      //     this.$router.push({ path: 'table/Griding' })
      //     break
      //   case 5:
      //     this.$router.push({ path: 'table/ServiceProduce' })
      //     break
      //   case 6:
      //     this.$router.push({ path: 'table/EnterpriseCreation' })
      //     break
      // }
    },

    handleChange(info) {
      if (info.file.status === 'uploading') {
        this.loading = true;
        return;
      }
      if (info.file.status === 'done') {
        // Get this url from response in real world.
        getBase64(info.file.originFileObj, imageUrl => {
          this.imageUrl = imageUrl;
          this.loading = false;
        });
      }
    },
    beforeUpload(file) {
      const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
      if (!isJpgOrPng) {
        this.$message.error('You can only upload JPG file!');
      }
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.$message.error('Image must smaller than 2MB!');
      }
      return isJpgOrPng && isLt2M;
    },

  }
}
</script>

<style lang="less" scoped>

.avatar-uploader > .ant-upload {
  width: 128px;
  height: 128px;
}
.ant-upload-select-picture-card i {
  font-size: 32px;
  color: #999;
}

.ant-upload-select-picture-card .ant-upload-text {
  margin-top: 8px;
  color: #666;
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

.mobile {
  .extra-img {
    margin-top: 0;
    text-align: center;
    width: 96px;

    img {
      width: 100%;
    }
  }
}


.pic_item {
  position: relative;
  height: 100%;
  text-align: center;
}

.pic_item:hover {
  cursor: pointer;
}

.pic_item img {
  width: 100%;
  height: 500px;
  display: flex;
  justify-content: center;
  position: relative;


}
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
</style>