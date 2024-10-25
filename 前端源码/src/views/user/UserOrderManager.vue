<template>
  <div>
    <!-- 上半部分 -->
    <div class="panel panel-default">
      <div class="panel-heading">
        <font size="3"> <span class="glyphicon glyphicon-user"></span>
          订单管理
        </font>
      </div>
    </div>
    <!-- 下半部分 -->
    <div class="panel panel-default">
      <!-- 头部 -->
      <div class="panel-heading" style="height: 55px">
        <div class="pull-left">
          <font size="4" style="line-height: 35px">订单管理</font>
        </div>
        <div class="pull-right">
          <button class="btn btn-success" style="margin-left: 10px;" @click="onLoad">
            <span class="glyphicon glyphicon-refresh"></span>刷&nbsp;新
          </button>
        </div>
      </div>
      <!-- 主列表 -->
      <div class="panel-body" style="overflow: auto;">
        <table id="table" class="table table-striped" style="text-align: center;  min-width: 800px">
          <tbody>
            <tr>
              <td>订单编号</td>
              <td>总计</td>
              <td>收货地址</td>
              <td>状态</td>
              <td>创建时间</td>
              <td style="width: 100px;">查看详情</td>
            </tr>
            <tr v-for="item in data" :key="item.id">
              <td>{{item.orderId}}</td>
              <td>{{item.orderTotal}}</td>
              <td>{{item.address}}</td>
              <td>{{item.status}}</td>
              <td>{{item.createdTime}}</td>
              <td>
                <button @click="openDetail(item.orderId)" class="btn btn-info" type="submit">
                  <span class="glyphicon glyphicon-zoom-in"></span>
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <order-detail :id="currentId" v-if="showDetail" @close="() => {showDetail = false}"/>
  </div>
</template>

<script>
  import Order from "../../api/Order";
  import OrderDetail from "./order/OrderDetail";

  export default {
    name: "UserOrderManager",
    components: {OrderDetail},
    data() {
      return {
        data: [],
        currentId: null,
        showDetail: false,
      }
    },
    methods: {
      openDetail(id) {
        this.currentId = id;
        this.showDetail = true;
      },
      onLoad() {
        Order.list().then(res => {
          let data = res.data.data;
          this.data = data;

        })
      }
    },
    created() {
      this.onLoad();
    }
  }
</script>

<style scoped>

</style>
