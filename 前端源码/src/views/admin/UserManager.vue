<template>
  <div>
    <!-- 上半部分 -->
    <div class="panel panel-default">
      <div class="panel-heading">
        <font size="3"> <span class="glyphicon glyphicon-user"></span>
          管理用户
        </font>
      </div>
    </div>
    <!-- 下半部分 -->
    <div class="panel panel-default">
      <!-- 头部 -->
      <div class="panel-heading" style="height: 55px">
        <div class="pull-left">
          <font size="4" style="line-height: 35px">管理用户</font>
        </div>
        <div class="pull-right">
          <button class="btn btn-primary" data-toggle="modal"
                  @click="openSave">
            <span class="glyphicon glyphicon-plus"></span>新&nbsp;增
          </button>
          <button class="btn btn-success" style="margin-left: 10px;" @click="onLoad">
            <span class="glyphicon glyphicon-refresh"></span>刷&nbsp;新
          </button>
        </div>
      </div>
      <!-- 主列表 -->
      <div class="panel-body" style="overflow: auto">
        <table id="table" class="table table-striped" style="text-align: center">
          <tbody>
            <tr>
              <td>ID</td>
              <td>帐号</td>
              <td>密码</td>
              <td>手机号码</td>
              <td>邮箱</td>
              <td style="width: 50px;">编辑</td>
              <td style="width: 50px;">删除</td>
            </tr>
            <tr v-for="item in data" :key="item.userId">
              <td>{{item.userId}}</td>
              <td>{{item.account}}</td>
              <td>{{item.password}}</td>
              <td>{{item.tel}}</td>
              <td>{{item.email}}</td>
              <td>
                <button @click="openUpdate(item.userId)" class="btn btn-info" type="submit">
                  <span class="glyphicon glyphicon-pencil"></span>
                </button>
              </td>
              <td>
                <button @click="openDelete(item.userId)" class="btn btn-info" type="submit">
                  <span class="glyphicon glyphicon-remove"></span>
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <user-submit :id="currentId" v-if="showSubmit" @close="() => {showSubmit = false; this.onLoad()}"/>
    <common-delete :id="currentId" v-if="showDelete" @close="() => {showDelete = false}" @handler="handlerDelete"/>
  </div>
</template>

<script>
  import User from "../../api/User";
  import UserSubmit from "./user/UserSubmit";
  import CommonDelete from "../common/CommonDelete";

  export default {
    name: "UserManager",
    components: {CommonDelete, UserSubmit},
    data() {
      return {
        data: [],
        showSubmit: false,
        currentId: null,
        showDelete: false,
      }
    },
    methods: {
      openDelete(id) {
        this.currentId = id;
        this.showDelete = true;
      },
      handlerDelete() {
        User.delete(this.currentId).then(() => {
          this.$message.success("删除成功");
          this.showDelete = false;
          this.onLoad();
        })
      },
      openUpdate(id) {
        this.currentId = id;
        this.showSubmit = true;
      },
      openSave() {
        this.currentId = null;
        this.showSubmit = true;
      },
      onLoad() {
        User.list().then(res => {
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
