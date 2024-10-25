package com.coffee.controller;

import cn.hutool.core.bean.BeanUtil;
import com.coffee.common.api.R;
import com.coffee.common.exception.UserExistException;
import com.coffee.common.utils.AuthUtil;
import com.coffee.entity.pojo.Admin;
import com.coffee.entity.pojo.User;
import com.coffee.entity.vo.Auth;
import com.coffee.entity.vo.LoginFormBean;
import com.coffee.entity.vo.RegisterFormBean;
import com.coffee.service.AdminService;
import com.coffee.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/current")
    public R current() {
        try {
            Auth user = AuthUtil.getUser();
            return R.data(user);
        }catch (Exception e) {
            return R.data(null);
        }
    }

    @GetMapping("/loginOut")
    public R loginOut() {
        AuthUtil.clear();
        return R.success("操作成功");
    }

    @PostMapping("/login")
    public R login(@RequestBody LoginFormBean formBean) {
        AuthUtil.clear();

        // 登录操作
        boolean isLogin = false;
        if (formBean.getStatus().equals("user")) {
            isLogin = userLogin(formBean);
        } else if (formBean.getStatus().equals("admin")) {
            isLogin = adminLogin(formBean);
        }

        return isLogin ? R.success("登录成功") : R.fail("用户名或密码错误");
    }


    /**
     * 管理员登录
     */
    private boolean adminLogin(LoginFormBean formBean) {
        Admin admin = adminService.login(formBean.getAccount(), formBean.getPassword());

        if (admin != null) {
            Auth auth = new Auth();
            auth.setId(admin.getAdminId());
            auth.setAccount(formBean.getAccount());
            auth.setPassword(formBean.getPassword());
            auth.setRole(formBean.getStatus());
            AuthUtil.setToken(auth);
            return true;
        }

        return false;
    }

    /**
     * 用户登录
     */
    private boolean userLogin(LoginFormBean formBean) {
        User user = userService.login(formBean.getAccount(), formBean.getPassword());

        if (user != null) {
            Auth auth = new Auth();
            auth.setId(user.getUserId());
            auth.setAccount(formBean.getAccount());
            auth.setPassword(formBean.getPassword());
            auth.setRole(formBean.getStatus());
            AuthUtil.setToken(auth);
            return true;
        }

        return false;
    }

}
