package com.seawater.mybatisPlus.config;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.sql.Timestamp;

/**
 *
 */
public class MyMetaObjectHandler extends MetaObjectHandler {

    /**
     * 字段为空自动填充,如果要使填充生效,一定在在实体类对应的字段上设置fill = FieldFill.INSERT字段！
     */
    public void insertFill(MetaObject metaObject) {
        // 更多查看源码测试用例

        Object createTime = getFieldValByName("createTime", metaObject);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (createTime == null) {
            setFieldValByName("createTime", timestamp, metaObject);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新填充
        setFieldValByName("modifyTime", new Timestamp(System.currentTimeMillis()), metaObject);
    }

}
