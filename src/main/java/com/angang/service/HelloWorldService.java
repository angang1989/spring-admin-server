package com.angang.service;

import cn.org.atool.fluent.mybatis.model.StdPagedList;
import com.angang.domain.GetByConditionRequest;
import com.angang.domain.GetByConditionResponse;
import com.angang.entity.HelloWorldEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author lvga
 * @Description
 * @date 2022/9/20 11:05
 */
public interface HelloWorldService {
    List<GetByConditionResponse> getByCondition(GetByConditionRequest request);

    List<HelloWorldEntity> getByConditionBean(GetByConditionRequest request);

    void insert(HelloWorldEntity entity);

    PageInfo<HelloWorldEntity> getByConditionPage(GetByConditionRequest request);
}
