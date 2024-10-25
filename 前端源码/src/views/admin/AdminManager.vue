<template>
  <div>
    <div style="height: 80px;">
      <header-view/>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-md-2" id="menu-left">
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
                <li :class="{'active' : active === 'manager-item'}">
                  <a style="cursor: pointer" @click="() => {active = 'manager-item'}">
                    <span class="glyphicon glyphicon glyphicon-leaf"></span> 库存管理
                  </a>
                </li>
                <li :class="{'active' : active === 'manager-order'}">
                  <a style="cursor: pointer" @click="() => {active = 'manager-order'}">
                    <span class="glyphicon glyphicon-list-alt"></span> 订单管理
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <!-- 右侧主体 -->
        <div class="col-md-10">
          <user-manager v-if="active === 'manager-user'"/>
          <group-manager v-if="active === 'manager-group'"/>
          <item-manager v-if="active === 'manager-item'"/>
          <order-manager v-if="active === 'manager-order'"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import HeaderView from "../common/HeaderView";
  import UserManager from "./UserManager";
  import GroupManager from "./GroupManager";
  import ItemManager from "./ItemManager";
  import OrderManager from "./OrderManager";
  export default {
    name: "AdminManager",
    components: {OrderManager, ItemManager, GroupManager, UserManager, HeaderView},
    data() {
      return {
        active: "manager-item",
      }
    }
  }
</script>

<style scoped>
#menu-left {
  min-width: 185px;
}
</style>
