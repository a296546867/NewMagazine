package com.example.sky.Bean;

import java.io.Serializable;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/30 9:56
 * 修改人：meigong
 * 修改时间：2016/5/30 9:56
 * 修改备注：
 */
public class EntityKey implements Serializable {

    private String $id;
    private String EntitySetName;
    private String EntityContainerName;
    private EntityKeyValues EntityKeyValues;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getEntitySetName() {
        return EntitySetName;
    }

    public void setEntitySetName(String entitySetName) {
        EntitySetName = entitySetName;
    }

    public String getEntityContainerName() {
        return EntityContainerName;
    }

    public void setEntityContainerName(String entityContainerName) {
        EntityContainerName = entityContainerName;
    }

    public com.example.sky.Bean.EntityKeyValues getEntityKeyValues() {
        return EntityKeyValues;
    }

    public void setEntityKeyValues(com.example.sky.Bean.EntityKeyValues entityKeyValues) {
        EntityKeyValues = entityKeyValues;
    }
}
