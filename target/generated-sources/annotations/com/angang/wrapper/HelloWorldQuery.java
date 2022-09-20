package com.angang.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static cn.org.atool.fluent.mybatis.utility.StrConstant.EMPTY;
import static com.angang.helper.HelloWorldMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.BracketFrag;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.angang.entity.HelloWorldEntity;
import com.angang.helper.HelloWorldSegment;
import java.util.Optional;

/**
 *
 * HelloWorldQuery: 查询构造
 *
 * @author powered by FluentMybatis
 */
public class HelloWorldQuery extends BaseQuery<HelloWorldEntity, HelloWorldQuery> {
  /**
   * 指定查询字段, 默认无需设置
   */
  public final HelloWorldSegment.Selector select = new HelloWorldSegment.Selector(this);

  /**
   * 分组：GROUP BY 字段, ...
   * 例: groupBy('id', 'name')
   */
  public final HelloWorldSegment.GroupBy groupBy = new HelloWorldSegment.GroupBy(this);

  /**
   * 分组条件设置 having...
   */
  public final HelloWorldSegment.Having having = new HelloWorldSegment.Having(this);

  /**
   * 排序设置 order by ...
   */
  public final HelloWorldSegment.QueryOrderBy orderBy = new HelloWorldSegment.QueryOrderBy(this);

  /**
   * 查询条件 where ...
   */
  public final HelloWorldSegment.QueryWhere where = new HelloWorldSegment.QueryWhere(this);

  public HelloWorldQuery() {
    this(true, null, () -> null, null);
  }

  public HelloWorldQuery(String alias) {
    this(true, null, () -> alias, null);
  }

  public HelloWorldQuery(boolean defaults, IFragment table, String alias, Parameters shared) {
    this(defaults, table, () -> alias, shared);
  }

  public HelloWorldQuery(boolean defaults, IFragment table, StringSupplier alias,
      Parameters shared) {
    super(table, alias, HelloWorldEntity.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setQueryDefault(this);
    }
  }

  @Override
  public HelloWorldSegment.QueryWhere where() {
    return this.where;
  }

  @Override
  public HelloWorldSegment.QueryOrderBy orderBy() {
    return this.orderBy;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static HelloWorldQuery emptyQuery() {
    return new HelloWorldQuery(false, null, () -> null, null);
  }

  public static HelloWorldQuery emptyQuery(String alias) {
    return new HelloWorldQuery(false, null, () -> alias, null);
  }

  public static HelloWorldQuery emptyQuery(StringSupplier table) {
    return new HelloWorldQuery(false, fragment(table), () -> null, null);
  }

  public static HelloWorldQuery query() {
    return new HelloWorldQuery();
  }

  public static HelloWorldQuery defaultQuery() {
    return query();
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  public static HelloWorldQuery query(String alias) {
    return new HelloWorldQuery(alias);
  }

  public static HelloWorldQuery query(StringSupplier table) {
    assertNotNull("table", table);
    return new HelloWorldQuery(true, fragment(table), () -> null, null);
  }

  public static HelloWorldQuery query(StringSupplier table, String alias) {
    assertNotNull("table", table);
    return new HelloWorldQuery(true, fragment(table), () -> alias, null);
  }

  /**
   * select * from (select query) alias
   * @param query 子查询
   * @param alias 子查询别名
   */
  public static HelloWorldQuery query(IQuery query, String alias) {
    assertNotNull("query", query);
    return new HelloWorldQuery(true, BracketFrag.set(query), () -> alias, null);
  }
}
