<template>
  <div>
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example-navbar-collapse">
            <span class="sr-only">切换导航</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span> <span class="icon-bar"></span>
          </button>
        </div>

        <div class="collapse navbar-collapse" id="example-navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active">
              <a href="/">
                <span class="glyphicon glyphicon-home"></span>主页</a>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <span class="glyphicon glyphicon-th-list"></span> 分类 <b class="caret"></b>
              </a>
              <ul class="dropdown-menu" id="search-type">
                <li class="divider"></li>
                <li><a href="/#/search">全部</a></li>
                <li class='divider'></li>
                <li v-for="item in groupData" :key="item.id">
                  <a :href="`/#/search?type=${item.id}`">{{item.groupName}}</a>
                </li>
                <li class="divider"></li>
              </ul>
            </li>
          </ul>

          <ul id="login-show" :style="{display: role?'flex':'none'}" style="justify-content: end; padding-right: 10px" class="nav navbar-nav navbar-right">
            <li>
              <a href="#"><span class="text-primary" id="welcome">{{'欢迎回来: ' + auth.account}}</span></a>
            </li>
            <li class="user-show" :style="{display: role === 'user'?'block':'none'}" style="display: none">
              <a href="/#/shopping"><span class="glyphicon glyphicon-shopping-cart"></span> 购物车</a>
            </li>
            <li class="user-show" :style="{display: role === 'user'?'block':'none'}">
              <a href="/#/user"><span class="glyphicon glyphicon-user"></span> 个人中心</a>
            </li>
            <li class="admin-show" :style="{display: role === 'admin'?'block':'none'}">
              <a href="/#/admin"><span class="glyphicon glyphicon-user"></span> 管理中心</a>
            </li>
            <li>
              <button type="submit" class="btn btn-danger navbar-btn" @click="loginOut()">
                <span class="glyphicon glyphicon-log-out"></span> 退出
              </button>
            </li>
          </ul>

          <ul id="no-login-show" :style="{display: role?'none':'flex'}" style="justify-content: end; padding-right: 10px" class="nav navbar-nav navbar-right">
            <li>
              <button  @click="() => {showLogin = true}" type="submit" class="btn btn-info navbar-btn" href="">
                <span class="glyphicon glyphicon-log-in"></span>&nbsp;登录
              </button>
            </li>
            <!-- 形成简单间隔 -->
            <li>&nbsp;&nbsp;&nbsp;</li>
            <li>
              <button @click="() => {showRegister = true}" class="btn btn-success navbar-btn">
                <span class="glyphicon glyphicon-user"></span>&nbsp;注册用户
              </button>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <login-view  v-if="showLogin" @close="() => {showLogin = false}"/>
    <user-register v-if="showRegister" @close="() => {showRegister = false}"/>
  </div>
</template>

<script>
  import Group from "../../api/Group";
  import Auth from "../../api/Auth";
  import UserRegister from "../user/component/UserRegister";
  import LoginView from "./LoginView";

  export default {
     name: "HeaderView",
    components: {LoginView, UserRegister},
    data() {
      return {
        groupData: [],
        type: null,
        role: null,
        showLogin: false,
        showRegister: false,
        auth: {},
      }
    },
    methods: {
      getGroupData() {
        Group.list().then(res => {
          this.groupData = res.data.data;
        })
      },
      current() {
        Auth.current().then(res => {
          let data = res.data.data;
          if (!data) {
            this.role = null;
            if (this.$route.query.showLogin === 'true') {
              setTimeout(() => {
                this.showLogin = true;
              }, 300);
            }
            return;
          }
          this.role = data.role;
          this.auth = data;
        })
      },
      loginOut() {
        Auth.loginOut().then(() => {
          this.$message.success("退出成功");
          setTimeout(() => {
            window.location.href = "/";
            window.location.reload();
          }, 300);
        })
      }
    },
    created() {
      this.getGroupData();
      this.current();
    },
  }
</script>

<style scoped>

</style>
