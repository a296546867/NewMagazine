package com.example.sky.Bean;

import org.litepal.crud.DataSupport;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/31 9:33
 * 修改人：meigong
 * 修改时间：2016/5/31 9:33
 * 修改备注：
 */
public class FormItem  extends DataSupport {
    private String Name; //配方子项名字,如 水  鸡蛋 等
    private String Value; //配方子项值
    private String ProductID; //产品ID

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }
}
