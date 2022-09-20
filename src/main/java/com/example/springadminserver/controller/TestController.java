package com.example.springadminserver.controller;

import com.example.springadminserver.domain.GetByConditionRequest;
import com.example.springadminserver.entity.HelloWorldEntity;
import com.example.springadminserver.mapper.HelloWorldMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    HelloWorldMapper helloWorldMapper;

    @GetMapping("/test")
    public Long test() {
        Random random = new Random();
        int r = random.nextInt(100);

        HelloWorldEntity entity = new HelloWorldEntity();
        entity.setSayHello("hello" + r);
        entity.setYourName("world" + r);

        helloWorldMapper.insert(entity);

        return entity.getId();
    }

    @PostMapping("/getByCondition")
    public List<HelloWorldEntity> getByCondition(@RequestBody GetByConditionRequest request) {
        List<HelloWorldEntity> list;

        list = helloWorldMapper.listEntity(
            helloWorldMapper.query()
                .select
                .sayHello()
                .yourName()
                .end()
                .where
                .applyIf(!StringUtils.isEmpty(request.getSayHello()), e-> e.and.sayHello().like(request.getSayHello()))
                .applyIf(!StringUtils.isEmpty(request.getYourName()), e -> e.and.yourName().like(request.getYourName()))
                .end()
        );

        return list;
    }


}
