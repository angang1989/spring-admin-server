package com.example.springadminserver.service.impl;

import com.example.springadminserver.domain.GetByConditionRequest;
import com.example.springadminserver.entity.HelloWorldEntity;
import com.example.springadminserver.mapper.HelloWorldMapper;
import com.example.springadminserver.service.HelloWorldService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lvga
 * @Description
 * @date 2022/9/20 11:06
 */
@Service
public class HelloWorldServiceImpl implements HelloWorldService {
    @Resource
    HelloWorldMapper helloWorldMapper;


    @Override
    public List<HelloWorldEntity> getByCondition(GetByConditionRequest request) {
        List<HelloWorldEntity> list = helloWorldMapper.listEntity(
            helloWorldMapper.query()
                .select
                .sayHello()
                .yourName()
                .end()
                .where
                .applyIf(!StringUtils.isEmpty(request.getSayHello()), e-> e.and.sayHello().like(request.getSayHello()))
                .applyIf(!StringUtils.isEmpty(request.getYourName()), e -> e.and.yourName().like(request.getYourName()))
                .end()
        );

        return list;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insert(HelloWorldEntity entity) {
        helloWorldMapper.insert(entity);
    }

    @Override
    public PageInfo<HelloWorldEntity> getByConditionPage(GetByConditionRequest request) {
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        List<HelloWorldEntity> helloWorldEntityList = this.getByCondition(request);
        PageInfo<HelloWorldEntity> pageInfo = new PageInfo<>(helloWorldEntityList);

        return pageInfo;
    }
}
