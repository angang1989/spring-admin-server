package com.angang.helper;

import static cn.org.atool.fluent.mybatis.base.model.UniqueType.*;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;

import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.crud.IDefaultSetter;
import cn.org.atool.fluent.mybatis.base.entity.AMapping;
import cn.org.atool.fluent.mybatis.base.entity.TableId;
import cn.org.atool.fluent.mybatis.base.model.FieldMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.metadata.DbType;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.angang.entity.HelloWorldEntity;
import com.angang.mapper.HelloWorldMapper;
import com.angang.wrapper.HelloWorldQuery;
import com.angang.wrapper.HelloWorldUpdate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * HelloWorldMapping: Entity帮助类
 *
 * @author powered by FluentMybatis
 */
public class HelloWorldMapping extends AMapping<HelloWorldEntity, HelloWorldQuery, HelloWorldUpdate> {
  /**
   * 表名称
   */
  public static final String TABLE_NAME = "hello_world";

  /**
   * Entity名称
   */
  public static final String ENTITY_NAME = "HelloWorldEntity";

  /**
   * 实体属性 : 数据库字段 映射
   *  id : id
   */
  public static final FieldMapping<HelloWorldEntity> id = new FieldMapping<HelloWorldEntity>
  	("id", "id", PRIMARY_ID, null, null, Long.class, null)
  	.sg((e, v) -> e.setId((Long) v), HelloWorldEntity::getId);

  /**
   * 实体属性 : 数据库字段 映射
   *  gmtCreated : gmt_created
   */
  public static final FieldMapping<HelloWorldEntity> gmtCreated = new FieldMapping<HelloWorldEntity>
  	("gmtCreated", "gmt_created", null, "now()", null, Date.class, null)
  	.sg((e, v) -> e.setGmtCreated((Date) v), HelloWorldEntity::getGmtCreated);

  /**
   * 实体属性 : 数据库字段 映射
   *  gmtModified : gmt_modified
   */
  public static final FieldMapping<HelloWorldEntity> gmtModified = new FieldMapping<HelloWorldEntity>
  	("gmtModified", "gmt_modified", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setGmtModified((Date) v), HelloWorldEntity::getGmtModified);

  /**
   * 实体属性 : 数据库字段 映射
   *  isDeleted : is_deleted
   */
  public static final FieldMapping<HelloWorldEntity> isDeleted = new FieldMapping<HelloWorldEntity>
  	("isDeleted", "is_deleted", null, "0", null, Boolean.class, null)
  	.sg((e, v) -> e.setIsDeleted((Boolean) v), HelloWorldEntity::getIsDeleted);

  /**
   * 实体属性 : 数据库字段 映射
   *  sayHello : say_hello
   */
  public static final FieldMapping<HelloWorldEntity> sayHello = new FieldMapping<HelloWorldEntity>
  	("sayHello", "say_hello", null, null, null, String.class, null)
  	.sg((e, v) -> e.setSayHello((String) v), HelloWorldEntity::getSayHello);

  /**
   * 实体属性 : 数据库字段 映射
   *  yourName : your_name
   */
  public static final FieldMapping<HelloWorldEntity> yourName = new FieldMapping<HelloWorldEntity>
  	("yourName", "your_name", null, null, null, String.class, null)
  	.sg((e, v) -> e.setYourName((String) v), HelloWorldEntity::getYourName);

  public static final IDefaultSetter DEFAULT_SETTER = new IDefaultSetter(){};

  public static final List<FieldMapping> ALL_FIELD_MAPPING = Collections.unmodifiableList(Arrays
  	.asList(id, gmtCreated, gmtModified, isDeleted, sayHello, yourName));

  public static final HelloWorldMapping MAPPING = new HelloWorldMapping();

  protected HelloWorldMapping() {
    super(DbType.MYSQL);
    super.tableName = TABLE_NAME;
    super.tableId = new TableId("id", "id", true, "", false);
    super.uniqueFields.put(PRIMARY_ID, id);
    super.Ref_Keys.unmodified();
  }

  @Override
  public Class entityClass() {
    return HelloWorldEntity.class;
  }

  @Override
  public Class mapperClass() {
    return HelloWorldMapper.class;
  }

  @Override
  public <E extends IEntity> E newEntity() {
    return (E) new HelloWorldEntity();
  }

  @Override
  public final List<FieldMapping> allFields() {
    return ALL_FIELD_MAPPING;
  }

  @Override
  public IDefaultSetter defaultSetter() {
    return DEFAULT_SETTER;
  }

  @Override
  protected final HelloWorldQuery query(boolean defaults, StringSupplier table,
      StringSupplier alias, Parameters shared) {
    return new HelloWorldQuery(defaults, fragment(table), alias, shared);
  }

  @Override
  protected final HelloWorldUpdate updater(boolean defaults, StringSupplier table,
      StringSupplier alias, Parameters shared) {
    return new HelloWorldUpdate(defaults, fragment(table), alias, shared);
  }
}
