package com.example.springadminserver.service;

import com.example.springadminserver.domain.GetByConditionRequest;
import com.example.springadminserver.entity.HelloWorldEntity;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author lvga
 * @Description
 * @date 2022/9/20 11:05
 */
public interface HelloWorldService {
    List<HelloWorldEntity> getByCondition(GetByConditionRequest request);

    void insert(HelloWorldEntity entity);

    PageInfo<HelloWorldEntity> getByConditionPage(GetByConditionRequest request);
}
