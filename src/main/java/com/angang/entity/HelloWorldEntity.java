package com.angang.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import lombok.Data;

import java.util.Date;

/**
 * @author lvga
 * @Description
 * @date 2022/9/19 14:42
 */
@Data
@FluentMybatis
public class HelloWorldEntity extends RichEntity {
    @TableId
    private Long id;

    private String sayHello;

    private String yourName;

    @TableField(insert = "now()")
    private Date gmtCreated;

    @TableField(insert = "now()", update = "now()")
    private Date gmtModified;

    @TableField(insert = "0")
    private Boolean isDeleted;
}
