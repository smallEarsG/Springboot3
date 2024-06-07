package org.example.h5.common;

import io.micrometer.common.util.StringUtils;
import lombok.Data;

import java.io.Serializable;

// 封装请求基类
@Data
public class BaseVO implements Serializable {

    private  Integer pageNum = 1;
    private  Integer pageSize = 20;

    private static  int maxPageSize = 1000;
    /**
     * 排序字段(驼峰字段)
     */
    private String fieldSort;
    /**
     * 排序类型
     * 正序 asc
     * 倒序 desc
     */
    private String orderSort;

    public String getFieldSort() {
        if(StringUtils.isNotBlank(fieldSort)){
//            return GuavaUtils.transformToDatabaseFieldLower(fieldSort);
        }
        return fieldSort;
    }
    public String getOrderSort() {
        if(StringUtils.isNotBlank(orderSort)){
            return orderSort.split("end")[0];
        }
        return orderSort;
    }
}
