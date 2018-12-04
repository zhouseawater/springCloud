package com.seawater.mybatisPlus.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.seawater.mybatisPlus.entity.Userinfo;
import com.seawater.mybatisPlus.mapper.UserinfoMapper;
import com.seawater.mybatisPlus.service.db1.IUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author zhouhaishui
 * @since 2018-07-19
 */
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, Userinfo> implements IUserinfoService {

    /**
     * 多数据源方式调用示例：
     * 方式一：
     * 如果需要在这里调用数据库2或者其他数据源可以直接注入mapper调用
     * @Autowired
     * UserInfo2Mapper userInfo2Mapper;
     */

    @Autowired
    UserinfoMapper userinfoMapper;


    @Override
    public Page<Map> queryMyItems(Page<Map> mapPage, Map requestParam) {
        List<Map> myItems = userinfoMapper.queryMyItems(mapPage,requestParam);
        mapPage.setRecords(myItems);
        return mapPage;
    }

    @Override
    public List<Map> queryUserInfo(Map map) {
        return userinfoMapper.queryUserInfo(map);
    }

    /**
     * @DataSourceSwitch(DBTypeEnum.db2)
     * 方式二: 将sql写在baseMapper.xml
     * 或者其他文件里,置顶使用哪个数据源即可
     * @param map
     * @return
     */
    /*@Override
    public List<Map> queryUserInfo(Map map) {
        return userinfoMapper.queryUserInfo(map);
    }*/



}
