package com.angang.mapper;

import static com.angang.helper.HelloWorldMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import cn.org.atool.fluent.mybatis.mapper.PrinterMapper;
import com.angang.entity.HelloWorldEntity;
import com.angang.wrapper.HelloWorldQuery;
import com.angang.wrapper.HelloWorldUpdate;
import java.util.List;
import java.util.function.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * HelloWorldMapper: Mapper接口
 *
 * @author powered by FluentMybatis
 */
@Mapper
@Component("fmHelloWorldMapper")
public interface HelloWorldMapper extends IWrapperMapper<HelloWorldEntity, HelloWorldQuery, HelloWorldUpdate>, IMapper<HelloWorldEntity> {
  @Override
  default IMapping mapping() {
    return MAPPING;
  }

  static List<String> print(int mode, Consumer<IWrapperMapper> simulators) {
    return PrinterMapper.print(mode, MAPPING, simulators);
  }
}
