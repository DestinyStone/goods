<template>
  <div style="height: 100%;">
    <header-view/>
    <div id="home-bg" style="background-image: url('/images/index/background1.png')">
      <div id="carousel-example-generic" class="main carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
          <li data-target="#carousel-example-generic" data-slide-to="1"></li>
          <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
          <div class="item active">
            <img src="/images/index/img.png">
          </div>
          <div class="item">
            <img src="/images/index/img_1.png">
          </div>
          <div class="item">
            <img src="/images/index/img_2.png">
          </div>
        </div>

        <a class="carousel-control left" id="cl" href="#carousel-example-generic" role="button" data-slide="prev">
          <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        </a>
        <a class="carousel-control right" id="cr" href="#carousel-example-generic" role="button" data-slide="next">
          <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        </a>
      </div>
    </div>
    <div class="container items">
      <div class="row" id="top3" style="padding-top: 30px;">
        <div v-for="item in top3Data" :key="item.itemId" class="col-xs-4">
          <div style="display: flex; justify-content: center; flex-flow: column">
            <a :href="`/#/detail?id=${item.itemId}`" class="thumbnail">
              <img :src="item.imageUrl" class="img-circle"/>
              <div class="caption">
                <div class="text-muted"><br />{{item.name}}</div>
              </div>
            </a>
          </div>
        </div>
      </div>
    </div>

    <div id="bottom" style="background: url(/images/index/background3.png); position: absolute; bottom: 0; width: 100%; left: 0; right: 0">
      <div class="container">
        <div class="col-xs-2 "></div>
        <div class="col-xs-6 ">
          <br /> <br /> <font size="20"><strong>Coffee & Bar</strong></font>
          <br /> <font size="5"><strong>咖啡店点餐系统</strong></font> <br /> <br />
          <br />
        </div>
        <div class="col-xs-3 ">
          <br /> <br />
          <ul></ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import HeaderView from "../common/HeaderView";
  import Item from "../../api/Item";
  export default {
    name: "HomeView",
    components: {HeaderView},
    data() {
      return {
        top3Data: [],
      }
    },
    methods: {
      onLoad() {
        Item.top3().then(res => {
          this.top3Data = res.data.data;
        })
      }
    },
    created() {
      this.onLoad();
    }
  }
</script>

<style scoped>
  @media screen and (max-width:740px){
    #bottom {
      display: none;
    }
  }
  /*main区域居中*/
  .main {
    text-align: center;
    background-color: #fff;
    border-radius: 20px;
    height: 400px;
    margin: 60px auto;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
  }

  .items {
    text-align: center;
    margin-left: auto;
    margin-right: auto;
  }

  .items img {
    width: 150px;
  }

  /*去除轮播两边阴影*/
  #cl #cr {
    background-image: none;
  }

  .item img {
    width: 100%;
    height: 400px;
    overflow: hidden;
  }
  #home-bg {
    position:relative;
    width: 100%;
    height: 460px;
    background-size: 100% 460px;
    background-repeat: no-repeat;
  }
</style>
