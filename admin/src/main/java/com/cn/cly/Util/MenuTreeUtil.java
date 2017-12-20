package com.cn.cly.Util;

import com.cn.cly.entity.Permission;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by chen on 2017/6/28.
 * 将菜单转为树形结构
 */
public class MenuTreeUtil {
    static List<Permission> resultList;

    /**
     * 根据条件将菜单转成Tree对象
     */
    public static Set<Tree> makeTreeList(List<Permission> originMenus){
        Set<Tree> trees = new HashSet<>();
        Tree tree1;
        for(Permission permission:originMenus){
            if("menu".equals(permission.getResourceType())){
                tree1 = new Tree(permission.getId(),permission.getName(),permission.getParentId(),permission.getUrl(),false);
                trees.add(tree1);
            }
        }
        return eachTree(trees);
    }

    public static Set<Tree> makeTreeList(List<Permission> originMenus,List<Permission> roleMenus){
        Set<Tree> trees = new HashSet<>();
        Tree tree1;
        boolean contained;
        for(Permission sysPermission:originMenus){
            contained=false;
            for(Permission rolePermission:roleMenus){
               if(sysPermission.getId()==rolePermission.getId()){
                   contained=true;
                   break;
               }
            }
            if(contained){
                tree1 = new Tree(sysPermission.getId(),sysPermission.getName(),sysPermission.getParentId(),sysPermission.getUrl(),true);
            }else {
                tree1 = new Tree(sysPermission.getId(),sysPermission.getName(),sysPermission.getParentId(),sysPermission.getUrl(),false);
            }
            trees.add(tree1);
        }
        return eachTree(trees);
    }

    /**
     * 将已转成Tree对象的list进行转换成树状
     */
    public static Set<Tree> eachTree(Set<Tree> trees){
        Set<Tree> rootTrees = new HashSet<>();
        for (Tree tree : trees) {
            if(tree.getParentId() == 0){
                rootTrees.add(tree);
            }
            for (Tree t : trees) {
                if(t.getParentId() == tree.getId()){
                    if(tree.getChildren() == null){
                        List<Tree> myChildrens = new ArrayList<Tree>();
                        myChildrens.add(t);
                        tree.setChildren(myChildrens);
                    }else{
                        tree.getChildren().add(t);
                    }
                }
            }
        }
        return rootTrees;
    }

    /**
     * 将菜单根据树状排序
     */
    public static List<Permission> treeOrderList(List<Permission> sysPermissions){
        resultList = new ArrayList<>();
        sortList(sysPermissions,0);
        return resultList;
    }

    public static void sortList(List<Permission> list,long id){
        for(Permission permission:list){
            if(permission.getParentId() == id){
                resultList.add(permission);
                sortList(list,permission.getId());
            }
        }
    }
}
