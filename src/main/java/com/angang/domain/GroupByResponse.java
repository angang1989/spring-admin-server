package com.angang.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lvga
 * @Description
 * @date 2022/9/22 15:44
 */
@Data
public class GroupByResponse {
    private String yourName;

    private BigDecimal avg;

    private BigDecimal sum;

    private int min;

    private int max;
}
