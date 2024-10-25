<template>
  <div style="height: 100%;">
    <header-view/>
    <div class="container" style="padding-top: 80px;">
      <div class="panel panel-default">
        <!-- 头部 -->
        <div class="panel-heading" style="height: 55px">
          <div class="pull-left">
            <font size="4" style="line-height: 35px">搜索结果</font>
          </div>
          <div class="pull-right">
            <button @click="onLoad" class="btn btn-success">
              <span class="glyphicon glyphicon-refresh"></span>刷&nbsp;新
            </button>
          </div>
        </div>
        <!-- 主列表 -->
        <div class="panel-body items">
          <div id="search-item-wrapper">
            <div v-for="item of data" :key="item.itemId" class="col-xs-4">
              <a :href="`/#/detail?id=${item.itemId}`" class="thumbnail">
                <img :src="item.imageUrl" class="img-circle">
                <div class="caption">
                  <div class="text-muted">
                    <br />{{item.name}}
                  </div>
                </div>
              </a>
            </div>
          </div>

          <div class="row"></div>

          <p>&nbsp;</p>
          <p>&nbsp;</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import HeaderView from "../common/HeaderView";
  import Item from "../../api/Item";
  export default {
    name: "ItemSearch",
    components: {HeaderView},
    data() {
      return {
        data: []
      }
    },
    watch:{
      $route(){
        this.onLoad();
      }
    },
    methods: {
      onLoad() {
        Item.list({type: this.$route.query.type}).then(res => {
          this.data = res.data.data;
        })
      }
    },
    created() {
      this.onLoad();
    }
  }
</script>

<style scoped>
  .items {
    text-align: center;
    margin-left: auto;
    margin-right: auto;
  }

  .items img {
    width: 150px;
  }
</style>