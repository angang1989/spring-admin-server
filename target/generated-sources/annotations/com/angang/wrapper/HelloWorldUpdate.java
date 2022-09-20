package com.angang.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static com.angang.helper.HelloWorldMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.angang.entity.HelloWorldEntity;
import com.angang.helper.HelloWorldSegment;
import java.util.Optional;

/**
 *
 * HelloWorldUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class HelloWorldUpdate extends BaseUpdate<HelloWorldEntity, HelloWorldUpdate, HelloWorldQuery> {
  public final HelloWorldSegment.UpdateSetter set = new HelloWorldSegment.UpdateSetter(this);

  public final HelloWorldSegment.UpdateWhere where = new HelloWorldSegment.UpdateWhere(this);

  public final HelloWorldSegment.UpdateOrderBy orderBy = new HelloWorldSegment.UpdateOrderBy(this);

  public HelloWorldUpdate() {
    this(true, null, null, null);
  }

  public HelloWorldUpdate(boolean defaults, IFragment table, StringSupplier alias,
      Parameters shared) {
    super(table, alias, HelloWorldEntity.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setUpdateDefault(this);
    }
  }

  @Override
  public HelloWorldSegment.UpdateWhere where() {
    return this.where;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static HelloWorldUpdate emptyUpdater() {
    return new HelloWorldUpdate(false, null, null, null);
  }

  public static HelloWorldUpdate emptyUpdater(StringSupplier table) {
    return new HelloWorldUpdate(false, fragment(table), null, null);
  }

  public static HelloWorldUpdate updater() {
    return new HelloWorldUpdate(true, null, null, null);
  }

  public static HelloWorldUpdate defaultUpdater() {
    return updater();
  }

  public static HelloWorldUpdate updater(StringSupplier table) {
    return new HelloWorldUpdate(true, fragment(table), null, null);
  }
}
