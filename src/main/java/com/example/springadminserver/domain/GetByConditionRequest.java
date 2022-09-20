package com.example.springadminserver.domain;

import lombok.Data;

/**
 * @author lvga
 * @Description
 * @date 2022/9/19 15:52
 */
@Data
public class GetByConditionRequest {
    private String sayHello;

    private String yourName;

    private int pageSize = 10;

    private int currentPage = 1;
}
