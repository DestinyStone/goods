<template>
  <div>
    <!-- 上半部分 -->
    <div class="panel panel-default">
      <div class="panel-heading">
        <font size="3"> <span class="glyphicon glyphicon-user"></span>
          商品管理
        </font>
      </div>
    </div>
    <!-- 下半部分 -->
    <div class="panel panel-default">
      <!-- 头部 -->
      <div class="panel-heading" style="height: 55px">
        <div class="pull-left">
          <font size="4" style="line-height: 35px">商品管理</font>
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
      <div class="panel-body" style="overflow: auto;">
        <table id="table" class="table table-striped" style="text-align: center;  min-width: 800px">
          <tbody>
          <tr>
            <td>ID</td>
            <td>名称</td>
            <td>类型</td>
            <td>库存量</td>
            <td>单价</td>
            <td style="width: 100px;">编辑</td>
            <td style="width: 100px;">删除</td>
            <td style="width: 100px;">上传图片</td>
          </tr>
          <tr v-for="item in data" :key="item.id">
            <td>{{item.itemId}}</td>
            <td>{{item.name}}</td>
            <td>{{item.typeName}}</td>
            <td>{{item.stock}}</td>
            <td>{{item.price}}</td>
            <td>
              <button @click="openUpdate(item.itemId)" class="btn btn-info" type="submit">
                <span class="glyphicon glyphicon-pencil"></span>
              </button>
            </td>
            <td>
              <button @click="openDelete(item.itemId)" class="btn btn-info" type="submit">
                <span class="glyphicon glyphicon-remove"></span>
              </button>
            </td>
            <td>
              <button @click="openUpload(item.itemId)" class="btn btn-info" type="submit">
                <span class="glyphicon glyphicon-upload"></span>
              </button>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
    <item-submit :id="currentId" v-if="showSubmit" @close="() => {showSubmit = false; this.onLoad()}"/>
    <item-upload  :id="currentId" v-if="showUpload" @close="() => {showUpload = false; this.onLoad()}"/>
    <common-delete :id="currentId" v-if="showDelete" @close="() => {showDelete = false}" @handler="handlerDelete"/>
  </div>
</template>

<script>
  import CommonDelete from "../common/CommonDelete";
  import Item from "../../api/Item";
  import ItemSubmit from "./item/ItemSubmit";
  import ItemUpload from "./item/ItemUpload";

  export default {
    name: "ItemManager",
    components: {ItemUpload, ItemSubmit, CommonDelete},
    data() {
      return {
        data: [],
        showSubmit: false,
        currentId: null,
        showDelete: false,
        showUpload: false,
      }
    },
    methods: {
      openUpload(id) {
        this.currentId = id;
        this.showUpload = true;
      },
      openDelete(id) {
        this.currentId = id;
        this.showDelete = true;
      },
      handlerDelete() {
        Item.delete(this.currentId).then(() => {
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
        Item.list().then(res => {
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
