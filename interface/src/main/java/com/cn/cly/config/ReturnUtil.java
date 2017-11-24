package com.cn.cly.config;

import org.springframework.ui.ModelMap;

/**
 * JSON统一返回数据格式
 */
public class ReturnUtil {

    public static ModelMap Success(Object obj) {
        ModelMap mp = new ModelMap();
        mp.put("status", 200);
        mp.put("state", "success");
        mp.put("msg", "操作成功");
        mp.put("data", obj);
        return mp;
    }

    public static ModelMap Success(String msg, Object obj) {
        ModelMap map = new ModelMap();
        map.put("status", 200);
        map.put("state", "success");
        map.put("msg", msg);
        map.put("data", obj);
        return map;
    }

    public static ModelMap Error(int status) {
        ModelMap map = new ModelMap();
        map.put("status", status);
        map.put("state", "error");
        map.put("msg", "操作失败");
        return map;
    }

    public static ModelMap Error(int status,String msg) {
        ModelMap mp = new ModelMap();
        mp.put("status", status);
        mp.put("state", "error");
        mp.put("msg", msg);
        return mp;
    }
}
