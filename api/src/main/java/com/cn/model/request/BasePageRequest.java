package com.cn.model.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * @author LinChen
 */
@Getter
@Setter
public class BasePageRequest implements Serializable {
    private Integer pageNumber;
    private Integer pageSize;

    public <T> T getPageRequest(BiFunction<Integer, Integer, T> function) {
        return function.apply(Optional.ofNullable(this.pageNumber).orElse(1) - 1,
                Optional.ofNullable(this.pageSize).orElse(10));
    }
}
