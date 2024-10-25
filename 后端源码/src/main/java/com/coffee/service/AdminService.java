package com.coffee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coffee.entity.pojo.Admin;


public interface AdminService extends IService<Admin> {
    Admin login(String account, String password);
}
