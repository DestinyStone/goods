<template>
  <div id="submit" class="modal">
    <div class="modal-dialog">
      <div class="modal-content">

        <div class="modal-body"><button @click="handlerClose" class="close" data-dismiss="modal"><span>&times;</span></button></div>
        <div class="modal-title"><h1 class="text-center" id="operatorTitle">{{id ? '编辑' : '新增'}}</h1></div>

        <!-- 注册表单 -->
        <div class="modal-body">
          <form action="#" onsubmit="return false;" method="post">
            <div class="form-group has-feedback" id="submit-name-div">
              <label for="submit-name">分类名称</label>
              <div class="input-group">
                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                <input id="submit-name" name="name" class="form-control" placeholder="请输入分类名称" maxlength="20" type="text">
              </div>
              <span style="color: red; display: none;" class="tips"></span> <span style="display: none;" class=" glyphicon glyphicon-remove form-control-feedback"></span>
              <span style="display: none;" class="glyphicon glyphicon-ok form-control-feedback"></span>
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
  export default {
    name: "GroupSubmit",
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
        let name = $("#submit-name").val();

        if (!name) {
          this.fail($("#submit-name-div"), '请输入分类名称')
          return;
        }

        let data = {};
        data.id =  this.id;
        data.groupName = name;

        if (data.id) {
          Group.update(data.id, data).then(() => {
            this.$message.success("操作成功");
            this.handlerClose();
          })
          return;
        }
        Group.save(data).then(() => {
          this.$message.success("操作成功");
          this.handlerClose();
        })

      },
      init() {
        $('#submit').css("display", "block");

        if (this.id) {
          Group.getById(this.id).then(res => {
            res = res.data.data;
            $("#submit-name").val(res.groupName);
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