package com.angang.helper;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static com.angang.helper.HelloWorldMapping.*;

import cn.org.atool.fluent.mybatis.base.crud.IWrapper;
import cn.org.atool.fluent.mybatis.base.model.FieldMapping;
import cn.org.atool.fluent.mybatis.functions.IAggregate;
import cn.org.atool.fluent.mybatis.segment.GroupByBase;
import cn.org.atool.fluent.mybatis.segment.HavingBase;
import cn.org.atool.fluent.mybatis.segment.HavingOperator;
import cn.org.atool.fluent.mybatis.segment.OrderByApply;
import cn.org.atool.fluent.mybatis.segment.OrderByBase;
import cn.org.atool.fluent.mybatis.segment.SelectorBase;
import cn.org.atool.fluent.mybatis.segment.UpdateApply;
import cn.org.atool.fluent.mybatis.segment.UpdateBase;
import cn.org.atool.fluent.mybatis.segment.WhereBase;
import cn.org.atool.fluent.mybatis.segment.where.BooleanWhere;
import cn.org.atool.fluent.mybatis.segment.where.NumericWhere;
import cn.org.atool.fluent.mybatis.segment.where.ObjectWhere;
import cn.org.atool.fluent.mybatis.segment.where.StringWhere;
import com.angang.wrapper.HelloWorldQuery;
import com.angang.wrapper.HelloWorldUpdate;

/**
 *
 * HelloWorldSegment
 *
 * @author powered by FluentMybatis
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public interface HelloWorldSegment {
  interface ASegment<R> {
    R set(FieldMapping fieldMapping);

    default R id() {
      return this.set(id);
    }

    default R gmtCreated() {
      return this.set(gmtCreated);
    }

    default R gmtModified() {
      return this.set(gmtModified);
    }

    default R isDeleted() {
      return this.set(isDeleted);
    }

    default R sayHello() {
      return this.set(sayHello);
    }

    default R yourName() {
      return this.set(yourName);
    }
  }

  /**
   * select字段设置
   */
  final class Selector extends SelectorBase<Selector, HelloWorldQuery> implements ASegment<Selector> {
    public Selector(HelloWorldQuery query) {
      super(query);
    }

    protected Selector(Selector selector, IAggregate aggregate) {
      super(selector, aggregate);
    }

    @Override
    protected Selector aggregateSegment(IAggregate aggregate) {
      return new Selector(this, aggregate);
    }

    public Selector id(String _alias_) {
      return this.process(id, _alias_);
    }

    public Selector gmtCreated(String _alias_) {
      return this.process(gmtCreated, _alias_);
    }

    public Selector gmtModified(String _alias_) {
      return this.process(gmtModified, _alias_);
    }

    public Selector isDeleted(String _alias_) {
      return this.process(isDeleted, _alias_);
    }

    public Selector sayHello(String _alias_) {
      return this.process(sayHello, _alias_);
    }

    public Selector yourName(String _alias_) {
      return this.process(yourName, _alias_);
    }
  }

  /**
   * query/update where条件设置
   */
  abstract class EntityWhere<W extends WhereBase<W, U, HelloWorldQuery>, U extends IWrapper<?, U, HelloWorldQuery>> extends WhereBase<W, U, HelloWorldQuery> {
    public EntityWhere(U wrapper) {
      super(wrapper);
    }

    protected EntityWhere(U wrapper, W where) {
      super(wrapper, where);
    }

    public NumericWhere<W, HelloWorldQuery> id() {
      return this.set(id);
    }

    public ObjectWhere<W, HelloWorldQuery> gmtCreated() {
      return this.set(gmtCreated);
    }

    public ObjectWhere<W, HelloWorldQuery> gmtModified() {
      return this.set(gmtModified);
    }

    public BooleanWhere<W, HelloWorldQuery> isDeleted() {
      return this.set(isDeleted);
    }

    public StringWhere<W, HelloWorldQuery> sayHello() {
      return this.set(sayHello);
    }

    public StringWhere<W, HelloWorldQuery> yourName() {
      return this.set(yourName);
    }
  }

  /**
   * query where条件设置
   */
  class QueryWhere extends EntityWhere<QueryWhere, HelloWorldQuery> {
    public QueryWhere(HelloWorldQuery query) {
      super(query);
    }

    private QueryWhere(HelloWorldQuery query, QueryWhere where) {
      super(query, where);
    }

    @Override
    protected QueryWhere buildOr(QueryWhere and) {
      return new QueryWhere((HelloWorldQuery) this.wrapper, and);
    }
  }

  /**
   * update where条件设置
   */
  class UpdateWhere extends EntityWhere<UpdateWhere, HelloWorldUpdate> {
    public UpdateWhere(HelloWorldUpdate updater) {
      super(updater);
    }

    private UpdateWhere(HelloWorldUpdate updater, UpdateWhere where) {
      super(updater, where);
    }

    @Override
    protected UpdateWhere buildOr(UpdateWhere and) {
      return new UpdateWhere((HelloWorldUpdate) this.wrapper, and);
    }
  }

  /**
   * 分组设置
   */
  final class GroupBy extends GroupByBase<GroupBy, HelloWorldQuery> implements ASegment<GroupBy> {
    public GroupBy(HelloWorldQuery query) {
      super(query);
    }
  }

  /**
   * 分组Having条件设置
   */
  final class Having extends HavingBase<Having, HelloWorldQuery> implements ASegment<HavingOperator<Having>> {
    public Having(HelloWorldQuery query) {
      super(query);
    }

    protected Having(Having having, IAggregate aggregate) {
      super(having, aggregate);
    }

    @Override
    protected Having aggregateSegment(IAggregate aggregate) {
      return new Having(this, aggregate);
    }
  }

  /**
   * Query OrderBy设置
   */
  final class QueryOrderBy extends OrderByBase<QueryOrderBy, HelloWorldQuery> implements ASegment<OrderByApply<QueryOrderBy, HelloWorldQuery>> {
    public QueryOrderBy(HelloWorldQuery query) {
      super(query);
    }
  }

  /**
   * Update OrderBy设置
   */
  final class UpdateOrderBy extends OrderByBase<UpdateOrderBy, HelloWorldUpdate> implements ASegment<OrderByApply<UpdateOrderBy, HelloWorldUpdate>> {
    public UpdateOrderBy(HelloWorldUpdate updater) {
      super(updater);
    }
  }

  /**
   * Update set 设置
   */
  final class UpdateSetter extends UpdateBase<UpdateSetter, HelloWorldUpdate> implements ASegment<UpdateApply<UpdateSetter, HelloWorldUpdate>> {
    public UpdateSetter(HelloWorldUpdate updater) {
      super(updater);
    }
  }
}
