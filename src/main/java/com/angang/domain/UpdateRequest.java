package com.angang.domain;

import lombok.Data;

/**
 * @author lvga
 * @Description
 * @date 2022/9/19 15:52
 */
@Data
public class UpdateRequest {
    private Long id;

    private String sayHello;

    private String yourName;
}
