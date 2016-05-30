package com.example.sky.Bean;

import java.io.Serializable;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/30 9:58
 * 修改人：meigong
 * 修改时间：2016/5/30 9:58
 * 修改备注：
 */
public class EntityKeyValues implements Serializable {

    private String Key;
    private String Type;
    private String Value;

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
