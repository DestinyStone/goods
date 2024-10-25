<template>
  <div id="submit" class="modal">
    <div class="modal-dialog">
      <div class="modal-content">

        <div class="modal-body"><button @click="handlerClose" class="close" data-dismiss="modal"><span>&times;</span></button></div>
        <div class="modal-title"><h1 class="text-center" id="operatorTitle">{{id ? '编辑' : '新增'}}</h1></div>

        <!-- 注册表单 -->
        <div class="modal-body">
          <form action="#" onsubmit="return false;" method="post">
            <div class="form-group has-feedback" id="item-name-div">
              <label for="item-name">名称</label>
              <div class="input-group"><span class="input-group-addon"><span class="glyphicon glyphicon-leaf"></span></span>
                <input id="item-name" name="name" class="form-control" type="text">
              </div>
            </div>

            <!-- 下拉表单 -->
            <div class="form-group has-feedback" id="item-type-div">
              <label for="item-type">类型</label>
              <div class="input-group">
                <span class="input-group-addon"><span class="glyphicon glyphicon-tasks"></span></span>
                <select class="form-control" name="type" id="item-type"></select>
              </div>
            </div>

            <div class="form-group has-feedback" id="item-stock-div">
              <label for="item-stock">库存数量</label>
              <div class="input-group">
                <span class="input-group-addon"><span class="glyphicon glyphicon-folder-close"></span></span>
                <input id="item-stock" name="stock" class="form-control" placeholder="请输入库存数量" maxlength="10" type="text">
              </div>
              <span style="color: red; display: none;" class="tips"></span> <span style="display: none;" class="glyphicon glyphicon-remove form-control-feedback"></span>
              <span style="display: none;" class="glyphicon glyphicon-ok form-control-feedback"></span>
            </div>

            <div class="form-group has-feedback" id="item-price-div">
              <label for="item-price">单价</label>
              <div class="input-group">
                <span class="input-group-addon"><span class="glyphicon glyphicon-usd"></span></span>
                <input id="item-price" name="price" class="form-control" placeholder="请输入单价" maxlength="10" type="text">
              </div>
              <span style="color: red; display: none;" class="tips"></span> <span style="display: none;" class="glyphicon glyphicon-remove form-control-feedback"></span>
              <span style="display: none;" class="glyphicon glyphicon-ok form-control-feedback"></span>
            </div>

            <div class="form-group has-feedback" id="item-description-div">
              <label for="item-description">描述</label>
              <div class="input-group">
                <span class="input-group-addon"><span class="glyphicon glyphicon-list-alt"></span></span>
                <textarea id="item-description" name="description" class="form-control" rows="3" type="text" placeholder="请输入该商品的相关描述" style="resize: none"></textarea>
              </div>
            </div>

            <div class="text-right">
              <button class="btn btn-primary" type="button" @click="submitHandler">提&nbsp;&nbsp;交</button>
              <button class="btn btn btn-warning orm-control" style="margin-left: 10px;" type="reset">重&nbsp;&nbsp;置</button>
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
  import Group from "../../../api/Group";
  import Item from "../../../api/Item";
  export default {
    name: "ItemSubmit",
    props: {
      id: {
        type: Number,
      }
    },
    data() {
      return {
        checkItemName: false,
        checkItemStock: false,
        checkItemPrice: false,
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
        if (this.checkItemName) {
          this.success($("#item-name-div"));
        } else {
          this.fail($("#item-name-div"));
        }

        if (this.checkItemStock) {
          this.success($("#item-stock-div"));
        } else {
          this.fail($("#item-stock-div"));
        }

        if (this.checkItemPrice) {
          this.success($("#item-price-div"));
        } else {
          this.fail($("#item-price-div"));
        }

        if (this.checkItemName && this.checkItemStock && this.checkItemPrice) {
          let data = {};
          data.itemId = this.id;
          data.name = $("#item-name").val();
          data.type = $("#item-type").val();
          data.stock = $("#item-stock").val();
          data.price = $("#item-price").val();
          data.description = $("#item-description").val();

          if (data.itemId) {
            Item.update(data.itemId, data).then(() => {
              this.$message.success("操作成功");
              this.handlerClose();
            })
            return;
          }
          Item.save(data).then(() => {
            this.$message.success("操作成功");
            this.handlerClose();
          })
        }
      },
      init() {
        $('#submit').css("display", "block");

        Group.list().then(res => {
          let data = res.data.data;
          let html = "";
          for(let item of data) {
            html += "<option value=" + item.id + ">" + item.groupName + "</option>"
          }
          document.getElementById("item-type").innerHTML = html;
        })

        $(document).ready(() => {
          $('#item-name-div').find('#item-name').change(() => {
            let value = $("#item-name").val();
            if (value == 0) {
              this.fail($("#item-name-div"), '餐点名不能为空');
              this.checkItemName = false;
            } else {
              this.success($("#item-name-div"));
              this.checkItemName = true;
            }
          });
        });

        let regPositiveInt = /^[1-9]\d*$/;
        $(document).ready(() => {
          $('#item-stock-div').find('#item-stock').change(() => {
            let value = $("#item-stock").val();
            if (regPositiveInt.test(value)) {
              this.success($("#item-stock-div"));
              this.checkItemStock = true;
            } else {
              this.fail($("#item-stock-div"), '库存数量必须为正整数');
              this.checkItemStock = false;
            }
          });
        });

        let regPositiveDbl = /^([1-9]+(\.\d+)?|0\.\d+)$/;
        $(document).ready(() => {
          $('#item-price-div').find('#item-price').change(() => {
            let value = $("#item-price").val();
            if (regPositiveDbl.test(value)) {
              this.success($("#item-price-div"));
              this.checkItemPrice = true;
            } else {
              this.fail($("#item-price-div"), '单价必须为正数');
              this.checkItemPrice = false;
            }
          });
        });

        if (this.id) {
          Item.getById(this.id).then(res => {
            res = res.data.data;
            $("#item-name").val(res.name);
            $("#item-type").val(res.type);
            $("#item-stock").val(res.stock);
            $("#item-price").val(res.price);
            $("#item-description").val(res.description);

            this.checkItemName = true;
            this.checkItemStock = true;
            this.checkItemPrice = true;
          })
        }
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