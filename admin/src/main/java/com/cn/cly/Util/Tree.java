package com.cn.cly.Util;

import java.util.List;

/**
 * Created by chen on 2017/6/28.
 */
public class Tree {
    private long id;

    private String name;

    private long parentId;

    private String menuUrl;

    private boolean checked;

    private List<Tree> children;

    public Tree(long id, String name, long parentId, String url, boolean checked) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.menuUrl = url;
        this.checked = checked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Tree [id=" + id + ", name=" + name + ", parentId=" + parentId
                + ", childrens=" + children + "]";
    }

}
