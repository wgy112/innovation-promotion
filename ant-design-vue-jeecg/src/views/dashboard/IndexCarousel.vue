<template>
  <div>
    <a-carousel  autoplay arrows>
    <div
      slot="prevArrow"
      slot-scope="props"
      class="custom-slick-arrow"
      style="left: 10px;zIndex: 1"
    >
      <a-icon type="left-circle" />
    </div>
    <div slot="nextArrow" slot-scope="props" class="custom-slick-arrow" style="right: 10px">
      <a-icon type="right-circle" />
    </div>

    <div v-for="item in imageBox" :key="item.id">
      <div >
              <div>
              <img :src="item.idView" alt="" style="width: 100%" >
              </div>
<!--        <h3 class="title">{{ item.title }}</h3>-->
      </div>
    </div>
  </a-carousel>
<!--    <span class="myset">-->
<!--      智能制造实验室-->
<!--    </span>-->
  </div>
</template>

<script>
export default {
  name: 'IndexCarousel',
  data() {
    return{
      imageBox: [
        { id: 0, idView: require('@assets/rotation/carousel02.jpg'), title: '' },
        { id: 1, idView: require('@assets/rotation/carousel01.jpg'), title: '' },
        { id: 2, idView: require('@assets/rotation/carousel05.jpg'), title: '' },
        { id: 3, idView: require('@assets/rotation/carousel04.jpg'), title: '' }
      ]


    }
  }
}
</script>


<style scoped>
/*.pic_item img {*/
/*  width: 100%;*/
/*  height: 500px;*/
/*  display: flex;*/
/*  justify-content: center;*/
/*  position: relative;*/


/*}*/
.ant-carousel >>> .slick-slide {
  text-align: center;
  height: 500px;
  width: 100%;
  line-height: 160px;
  background: #364d79;
  overflow: hidden;
  margin-top: 0;
}
.ant-carousel >>> .custom-slick-arrow {
  width: 25px;
  height: 25px;
  font-size: 25px;
  color: #fff;
  background-color: rgba(31, 45, 61, 0.11);
  opacity: 0.3;
}

.ant-carousel >>> .custom-slick-arrow:before {
  display: none;
}
.ant-carousel >>> .custom-slick-arrow:hover {
  opacity: 0.5;
}

.ant-carousel >>> .slick-slide h3 {
  color: #fff;
}

.myset{
  background-image:linear-gradient(to right, #000066 , #990000);
  -webkit-background-clip: text;
  color: transparent;
  font-size: 450%;
  font-weight: bold;
}
</style>