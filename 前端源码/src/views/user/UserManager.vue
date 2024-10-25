<template>
  <div>
    <div style="height: 80px;">
      <header-view/>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-md-2">
          <div class="panel panel-default">
            <div class="panel-heading">
              <ul class="nav nav-pills nav-stacked">
                <li class="disabled">
                  <a href="#">
                    <font size="3" style="font-weight: bold;">
                      <span class="glyphicon glyphicon-th-large"></span>管理中心
                    </font>
                  </a>
                </li>
              </ul>
            </div>
            <div class="panel-body">
              <ul id="item-urls" class="nav nav-pills nav-stacked">
                <li :class="{'active' : active === 'manager-order'}">
                  <a style="cursor: pointer" @click="() => {active = 'manager-order'}">
                    <span class="glyphicon glyphicon-th-large"></span> 订单管理
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <!-- 右侧主体 -->
        <div class="col-md-10">
          <user-order-manager v-if="active === 'manager-order'"/>
          <info-manager v-if="active === 'manager-info'"/>
          <password-manager  v-if="active === 'manager-password'"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import HeaderView from "../common/HeaderView";
  import UserOrderManager from "./UserOrderManager";
  import InfoManager from "./InfoManager";
  import PasswordManager from "./PasswordManager";

  export default {
    name: "UserManager",
    components: {PasswordManager, InfoManager, UserOrderManager, HeaderView},
    data() {
      return {
        active: "manager-order",
      }
    }
  }
</script>

<style scoped>

</style>
