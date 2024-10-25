package com.coffee.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coffee.common.api.R;
import com.coffee.common.exception.ServiceException;
import com.coffee.common.exception.UserExistException;
import com.coffee.common.utils.AuthUtil;
import com.coffee.common.utils.PageUtils;
import com.coffee.entity.pojo.User;
import com.coffee.entity.vo.ModifyUserFormBean;
import com.coffee.entity.vo.RegisterFormBean;
import com.coffee.entity.vo.UploadPassword;
import com.coffee.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.net.www.protocol.http.AuthenticationHeader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/save")
    public R save(@RequestBody RegisterFormBean formBean, HttpServletRequest request, HttpServletResponse response) {

        try {
            // 表单数据拷贝
            User user = BeanUtil.copyProperties(formBean, User.class);
            userService.register(user);
            return R.success("注册成功！！");
        } catch (UserExistException e) {
            return R.fail("用户名重复,请更换一个用户名！！");
        } catch (Exception e) {
            return R.fail("对不起，注册失败！！");
        }
    }

    @RequestMapping("/update/{id}")
    public R update(@PathVariable("id") Integer id, @RequestBody ModifyUserFormBean formBean) {
        User user = BeanUtil.copyProperties(formBean, User.class);
        user.setUserId(id);
        userService.updateById(user);

        return R.success("更新成功！！");
    }

    @RequestMapping("/update-info")
    public R update(@RequestBody ModifyUserFormBean formBean) {
        User user = BeanUtil.copyProperties(formBean, User.class);
        user.setUserId(AuthUtil.getUserId());
        userService.updateById(user);

        return R.success("更新成功！！");
    }

    @RequestMapping("/update-password")
    public R update(@RequestBody UploadPassword uploadPassword) {
        User user = userService.getById(AuthUtil.getUserId());
        if (!Objects.equals(user.getPassword(), uploadPassword.getCurrentPassword())) {
            throw new ServiceException("当前密码输入有误");
        }
        user.setPassword(uploadPassword.getNewPassword());
        userService.updateById(user);
        AuthUtil.clear();
        return R.success("更新成功！！");
    }

    @RequestMapping("/delete")
    public R delete(@RequestParam("id") Integer id) {
        userService.removeById(id);
        return R.success("操作成功！！");
    }

    @RequestMapping("/list")
    public R list(HttpServletRequest request, HttpServletResponse response) {
        List<User> list = userService.list();
        return R.data(list);
    }

    @RequestMapping("/id")
    public R id(@RequestParam("id") Integer id) {
        return R.data(userService.getById(id));
    }
}
