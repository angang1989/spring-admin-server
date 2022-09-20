package com.angang.service.impl;

import cn.org.atool.fluent.mybatis.model.StdPagedList;
import com.angang.domain.GetByConditionRequest;
import com.angang.domain.GetByConditionResponse;
import com.angang.entity.HelloWorldEntity;
import com.angang.mapper.HelloWorldMapper;
import com.angang.service.HelloWorldService;
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
    public List<GetByConditionResponse> getByCondition(GetByConditionRequest request) {

        // TODO listPoJos方法 在跟pageHelper一起使用时 返回的pageInfo没有分页信息
        // 原因是在fluent mybatis把查询结果转为POJO时 会丢失pageHelper的分页信息 返回的list不是继承的page
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
    public List<HelloWorldEntity> getByConditionBean(GetByConditionRequest request) {
        List<HelloWorldEntity> list = helloWorldMapper.listEntity(
            helloWorldMapper.query()
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
    public PageInfo<HelloWorldEntity> getByConditionEntityPage(GetByConditionRequest request) {
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
//        List<GetByConditionResponse> helloWorldEntityList = this.getByCondition(request);
//        PageInfo<GetByConditionResponse> pageInfo = new PageInfo<>(helloWorldEntityList);

        List<HelloWorldEntity> helloWorldEntities = this.getByConditionBean(request);
        PageInfo<HelloWorldEntity> pageInfo = new PageInfo<>(helloWorldEntities);

        return pageInfo;
    }

    @Override
    public StdPagedList<GetByConditionResponse> getByConditionFluentPage(GetByConditionRequest request) {
        int pageStart = (request.getCurrentPage()-1)*request.getPageSize();
        int pageEnd = request.getPageSize();

        StdPagedList<GetByConditionResponse> page = helloWorldMapper.stdPagedPoJo(GetByConditionResponse.class,
            helloWorldMapper.query()
                .select
                .sayHello("sayHello")
                .yourName("yourName")
                .end()
                .where
                .applyIf(!StringUtils.isEmpty(request.getSayHello()), e-> e.and.sayHello().like(request.getSayHello()))
                .applyIf(!StringUtils.isEmpty(request.getYourName()), e -> e.and.yourName().like(request.getYourName()))
                .end()
                .limit(pageStart, pageEnd)
        );

        return page;
    }
}
