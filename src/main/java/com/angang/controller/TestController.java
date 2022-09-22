package com.angang.controller;

import cn.org.atool.fluent.mybatis.model.StdPagedList;
import com.angang.domain.*;
import com.angang.entity.HelloWorldEntity;
import com.angang.service.HelloWorldService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @author lvga
 * @Description
 * @date 2022/9/19 14:47
 */
@RestController
@Slf4j
public class TestController {
    @Resource
    private HelloWorldService helloWorldService;

    @GetMapping("/add")
    public Long add() {
        Random random = new Random();
        int r = random.nextInt(100);

        HelloWorldEntity entity = new HelloWorldEntity();
        entity.setSayHello("hello" + r);
        entity.setYourName("world" + r);

        helloWorldService.insert(entity);

        return entity.getId();
    }

    @PostMapping("/update")
    public String update(@RequestBody UpdateRequest request) {
        HelloWorldEntity entity = helloWorldService.selectById(request.getId());

        if(Objects.isNull(entity)) {
            return "failed";
        }

        HelloWorldEntity entityUpdate = new HelloWorldEntity();
        entityUpdate.setId(entity.getId());
        entityUpdate.setSayHello(request.getSayHello());
        entityUpdate.setYourName(request.getYourName());

        helloWorldService.update(entityUpdate);

        return "success";
    }

    @PostMapping("/getByConditionAll")
    public List<GetByConditionResponse> getByCondition(@RequestBody GetByConditionRequest request) {
        List<GetByConditionResponse> list;

        list = helloWorldService.getByCondition(request);

        return list;
    }

    @PostMapping("/getByConditionPage")
    public PageInfo<HelloWorldEntity> getByConditionPage(@RequestBody GetByConditionRequest request) {

        return helloWorldService.getByConditionEntityPage(request);
    }

    @PostMapping("/getByConditionFluentPage")
    public StdPagedList<GetByConditionResponse> getByConditionFluentPage(@RequestBody GetByConditionRequest request) {

        return helloWorldService.getByConditionFluentPage(request);
    }

    @GetMapping("/aggregate")
    public AggregateResponse aggregate() {
        return helloWorldService.aggregate();
    }

    @GetMapping("/groupBy")
    public List<GroupByResponse> groupBy() {
        return helloWorldService.groupBy();
    }
}
