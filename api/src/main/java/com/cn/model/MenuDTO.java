package com.cn.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author LinChen
 */
@Getter
@Setter
public class MenuDTO implements Serializable {
    private Long id;
    private String name;
    private String path;
    private List<MenuDTO> children;
}
