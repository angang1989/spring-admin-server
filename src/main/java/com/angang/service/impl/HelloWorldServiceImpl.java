package com.angang.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.org.atool.fluent.mybatis.model.StdPagedList;
import com.angang.dao.base.HelloWorldBaseDao;
import com.angang.domain.AggregateResponse;
import com.angang.domain.GetByConditionRequest;
import com.angang.domain.GetByConditionResponse;
import com.angang.domain.GroupByResponse;
import com.angang.entity.HelloWorldEntity;
import com.angang.service.HelloWorldService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author lvga
 * @Description
 * @date 2022/9/20 11:06
 */
@Service
public class HelloWorldServiceImpl extends HelloWorldBaseDao implements HelloWorldService {

    @Override
    public List<GetByConditionResponse> getByCondition(GetByConditionRequest request) {

        // TODO listPoJos方法 在跟pageHelper一起使用时 返回的pageInfo没有分页信息
        // 原因是在fluent mybatis把查询结果转为POJO时 会丢失pageHelper的分页信息 返回的list不是继承的page
        List<GetByConditionResponse> list = mapper.listPoJos(GetByConditionResponse.class,
            mapper.query()
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
        List<HelloWorldEntity> list = mapper.listEntity(
            mapper.query()
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
        mapper.insert(entity);
    }

    @Override
    public void update(HelloWorldEntity entity) {
        mapper.updateById(entity);
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

        StdPagedList<GetByConditionResponse> page = mapper.stdPagedPoJo(GetByConditionResponse.class,
            mapper.query()
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

    @Override
    public AggregateResponse aggregate() {
        Map<String, Object> resultMap;
        AggregateResponse response = new AggregateResponse();

        Optional<Map<String, Object>> resultOptional = mapper.findOneMap(
            mapper.query()
                .select
                .min.score("minScore")
                .max.score("maxScore")
                .sum.score("sumScore")
                .avg.score("avgScore")
                .end()
        );

        if(resultOptional.isPresent()) {
            resultMap = resultOptional.get();
            response.setMin((int)resultMap.get("minScore"));
            response.setMax((int)resultMap.get("maxScore"));
            response.setSum((BigDecimal) resultMap.get("sumScore"));
            response.setAvg((BigDecimal) resultMap.get("avgScore"));
        }

        return response;
    }

    @Override
    public List<GroupByResponse> groupBy() {
        List<GroupByResponse> resultList = new ArrayList<>();

        List<Map<String, Object>> listMap = mapper.listMaps(
            mapper.query()
                .select
                .yourName("yourName")
                .min.score("minScore")
                .max.score("maxScore")
                .sum.score("sumScore")
                .avg.score("avgScore")
                .end()
                .groupBy.yourName().end()
        );

        if(CollectionUtil.isNotEmpty(listMap)) {
            for (Map<String, Object> mapTmp : listMap) {
                GroupByResponse responseTmp = new GroupByResponse();
                responseTmp.setYourName((String)mapTmp.get("yourName"));
                responseTmp.setMin((int)mapTmp.get("minScore"));
                responseTmp.setMax((int)mapTmp.get("maxScore"));
                responseTmp.setSum((BigDecimal) mapTmp.get("sumScore"));
                responseTmp.setAvg((BigDecimal) mapTmp.get("avgScore"));

                resultList.add(responseTmp);
            }
        }

        return resultList;
    }
}
