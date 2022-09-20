package com.angang.service.impl;

import com.angang.domain.GetByConditionRequest;
import com.angang.domain.GetByConditionResponse;
import com.angang.entity.HelloWorldEntity;
import com.angang.mapper.HelloWorldMapper;
import com.angang.service.HelloWorldService;
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
    public List<GetByConditionResponse> getByCondition(GetByConditionRequest request) {
//        List<HelloWorldEntity> list = helloWorldMapper.listEntity(
//            helloWorldMapper.query()
//                .select
//                .sayHello("sayHello")
//                .yourName("yourName")
//                .end()
//                .where
//                .applyIf(!StringUtils.isEmpty(request.getSayHello()), e-> e.and.sayHello().like(request.getSayHello()))
//                .applyIf(!StringUtils.isEmpty(request.getYourName()), e -> e.and.yourName().like(request.getYourName()))
//                .end()
//        );

        List<GetByConditionResponse> list = helloWorldMapper.listPoJos(GetByConditionResponse.class,
            helloWorldMapper.query()
                .select
                .sayHello("sayHello")
                .yourName("yourName")
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
    public PageInfo<GetByConditionResponse> getByConditionPage(GetByConditionRequest request) {
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        List<GetByConditionResponse> helloWorldEntityList = this.getByCondition(request);
        PageInfo<GetByConditionResponse> pageInfo = new PageInfo<>(helloWorldEntityList);

        return pageInfo;
    }
}
