package com.coffee.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coffee.common.api.R;
import com.coffee.common.utils.PageUtils;
import com.coffee.entity.pojo.Item;
import com.coffee.entity.pojo.ProjectGroup;
import com.coffee.service.GroupService;
import com.coffee.service.ItemService;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ResourceLoader resourceLoader;

    @RequestMapping("/upload/{id}")
    public R uploadImage(@PathVariable("id") Integer id,  MultipartFile file) {

        try {
            InputStream in = file.getInputStream();
            byte[] buffer= new byte[in.available()];
            in.read(buffer);

            FileUtil.writeBytes(buffer, System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes" + File.separator + "static" + File.separator + "images" + File.separator + file.getOriginalFilename());

            String path = "http://localhost:9999/images/" + file.getOriginalFilename();
            Item item = new Item();
            item.setItemId(id);
            item.setImageUrl(path);
            itemService.updateById(item);
            return R.success("");
        } catch (IOException e) {
            e.printStackTrace();
            return R.fail("上传错误");
        }
    }


    @RequestMapping("/list")
    public R list(Item item) {

        LambdaQueryWrapper<Item> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(item.getType()), Item::getType, item.getType());

        List<Item> list = itemService.list(wrapper);
        List<ProjectGroup> groups = groupService.list();
        for (Item record : list) {
            Map<Integer, ProjectGroup> projectGroupMap = groups.stream().collect(Collectors.toMap(ProjectGroup::getId, Function.identity()));
            ProjectGroup projectGroup = projectGroupMap.get(Integer.parseInt(record.getType()));
            record.setTypeName(projectGroup == null ? "" : projectGroup.getGroupName());
        }
        return R.data(list);
    }

    @RequestMapping("/update/{id}")
    public R update(@PathVariable("id") Integer id,  @RequestBody Item item) {
        item.setItemId(id);
        itemService.updateById(item);
        return R.success("操作成功");
    }

    @RequestMapping("/delete")
    public R delete(@RequestParam("id") Integer id) {
        itemService.removeById(id);
        return R.success("操作成功");
    }

    @RequestMapping("/save")
    public R save(@RequestBody Item item) {
        item.setItemId(null);
        itemService.save(item);
        return R.success("操作成功");
    }

    @RequestMapping("/top3")
    @ResponseBody
    public R top3() {
        List<Item> collect = itemService.list().stream().limit(3).collect(Collectors.toList());
        List<ProjectGroup> groups = groupService.list();
        for (Item record : collect) {
            Map<Integer, ProjectGroup> projectGroupMap = groups.stream().collect(Collectors.toMap(ProjectGroup::getId, Function.identity()));
            ProjectGroup projectGroup = projectGroupMap.get(Integer.parseInt(record.getType()));
            record.setTypeName(projectGroup == null ? "" : projectGroup.getGroupName());
        }
        return R.data(collect);
    }

    @RequestMapping("/id")
    public R id(@RequestParam("id") Integer id) {
        Item item = itemService.getById(id);
        ProjectGroup projectGroup = groupService.getById(item.getType());
        item.setTypeName(projectGroup == null ? "" : projectGroup.getGroupName());
        return R.data(item);
    }
}
