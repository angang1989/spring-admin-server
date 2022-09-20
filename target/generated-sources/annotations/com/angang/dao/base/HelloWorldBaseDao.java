package com.angang.dao.base;

import static com.angang.helper.HelloWorldMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import com.angang.entity.HelloWorldEntity;
import com.angang.mapper.HelloWorldMapper;
import com.angang.wrapper.HelloWorldQuery;
import com.angang.wrapper.HelloWorldUpdate;
import javax.annotation.Resource;

/**
 *
 * HelloWorldBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class HelloWorldBaseDao extends BaseDao<HelloWorldEntity, HelloWorldQuery, HelloWorldUpdate> {
  protected HelloWorldMapper mapper;

  @Override
  public HelloWorldMapper mapper() {
    return mapper;
  }

  @Override
  protected IDefaultGetter defaults() {
    return MAPPING;
  }

  @Override
  @Resource(
      name = "fmHelloWorldMapper"
  )
  public void setMapper(IMapper<HelloWorldEntity> mapper) {
    this.mapper = (HelloWorldMapper)mapper;
  }
}
