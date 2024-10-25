<template>
  <div id="submit" class="modal">
    <div class="modal-dialog">
      <div class="modal-content">

        <div class="modal-body"><button class="close" data-dismiss="modal"><span>&times;</span></button></div>
        <div class="modal-title"><h1 class="text-center" id="operatorTitle">上传图片</h1></div>

        <!-- 注册表单 -->
        <div class="modal-body">
          <form action="#" onsubmit="return false;" method="post">
            <div class="form-group has-feedback">
              <label for="item-itemId">ID</label>
              <div class="input-group">
                <span class="input-group-addon"><span class="glyphicon glyphicon-leaf"></span></span>
                <input :value="id" id="item-itemId" disabled name="itemId" class="form-control" readonly="readonly" type="text">
              </div>
            </div>

            <div class="form-group has-feedback" id="item-image-url-div">
              <label for="item-image-url">图片</label>
              <div class="input-group">
                <span class="input-group-addon"><span class="glyphicon glyphicon-barcode"></span></span>
                <input style="line-height: 30px; margin-left: 10px;" id="item-image-url" name="imageUrl" class="file" data-show-preview="false" type="file" data-show-upload="false" data-allowed-file-extensions='["jpg","png","gif","bmp"]'>
              </div>
            </div>

            <div class="text-right">
              <button class="btn btn-primary" type="button" @click="submitHandler">提&nbsp;&nbsp;交</button>
              <button class="btn btn-danger" type="button" @click.stop="handlerClose" style="margin-left: 10px;">取&nbsp;&nbsp;消</button>
            </div>

          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import $ from "jquery"
  import Item from "../../../api/Item";
  export default {
    name: "ItemUpload",
    props: {
      id: {
        type: Number,
      }
    },
    data() {
      return {

      }
    },
    methods: {
      // 成功函数
      success(obj) {
        obj.removeClass('has-error').addClass('has-success');
        obj.find('.tips').hide();
        obj.find('.glyphicon-ok').show();
        obj.find('.glyphicon-remove').hide();
      },
      // 失败函数
      fail(obj, msg) {
        obj.removeClass('has-success').addClass('has-error');
        obj.find('.glyphicon-remove').show();
        obj.find('.glyphicon-ok').hide();
        obj.find('.tips').text(msg).show();
      },

      submitHandler() {
        let file = document.getElementById('item-image-url').files[0];

        if (!file) {
          this.fail($("#item-image-url-div"), '图片不能为空');
          return;
        }

        let data = new FormData();
        data.append("file", file);

        Item.upload(this.id, data).then(() => {
          this.$message.success("操作成功");
          this.handlerClose();
        })

      },
      init() {
        $('#submit').css("display", "block");

      },
      handlerClose() {
        $('#submit').css("display", "none");
        this.$emit("close");
      }
    },
    mounted() {
      this.init();
    }
  }
</script>

<style scoped>

</style>