package com.coffee.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coffee.common.api.R;
import com.coffee.common.utils.PageUtils;
import com.coffee.entity.pojo.ProjectGroup;
import com.coffee.entity.pojo.User;
import com.coffee.entity.vo.ModifyUserFormBean;
import com.coffee.service.GroupService;
import com.coffee.service.UserService;
import com.coffee.service.impl.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @RequestMapping("/list")
    @ResponseBody
    public R list() {
        List<ProjectGroup> list = groupService.list();
        return R.data(list);
    }

    @RequestMapping("/update/{id}")
    public R update(@PathVariable("id") Integer id,  @RequestBody ProjectGroup projectGroup) {
        projectGroup.setId(id);
        groupService.updateById(projectGroup);
        return R.success("操作成功");
    }

    @RequestMapping("/delete")
    public R userDelete(@RequestParam("id") Integer id) {
        groupService.removeById(id);
        return R.success("操作成功");
    }

    @RequestMapping("/save")
    public R userSave(@RequestBody ProjectGroup projectGroup) {
        projectGroup.setId(null);
        groupService.save(projectGroup);
        return R.success("操作成功");
    }

    @RequestMapping("/id")
    public R id(@RequestParam("id") Integer id) {
        return R.data(groupService.getById(id));
    }
}
