package com.cn.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LinChen
 */
@Getter
@Setter
public class BreadcrumbItem {
    private String name;
    private String url;

    public BreadcrumbItem(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public BreadcrumbItem(String name) {
        this.name = name;
        this.url = null;
    }
}
