package com.coffee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coffee.entity.pojo.ProjectGroup;
import com.coffee.mapper.GroupMapper;
import com.coffee.service.GroupService;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, ProjectGroup> implements GroupService {
}
