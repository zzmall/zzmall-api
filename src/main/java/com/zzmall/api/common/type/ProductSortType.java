package com.zzmall.api.common.type;

import org.springframework.data.domain.Sort;

/**
 * @Author Connor
 * @Date 2019/05/19 16:52
 */
public enum ProductSortType {


    PRICE_DESC(Sort.Direction.DESC),
    PRICE_ASC(Sort.Direction.ASC),

    ;

    public Sort.Direction direction;

    ProductSortType() {

    }

    ProductSortType(Sort.Direction direction) {
        this.direction = direction;
    }

    private static ProductSortType fromString(String str) {
        return valueOf(str.toUpperCase());
    }

    public static Sort toSort(String str) {
        ProductSortType type = fromString(str);
        return Sort.by(type.direction, "price");
    }


}
