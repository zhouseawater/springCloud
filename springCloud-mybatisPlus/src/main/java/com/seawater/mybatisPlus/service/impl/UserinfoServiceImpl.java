package com.seawater.mybatisPlus.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.seawater.mybatisPlus.entity.Userinfo;
import com.seawater.mybatisPlus.mapper.UserinfoMapper;
import com.seawater.mybatisPlus.service.IUserinfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhouhaishui
 * @since 2018-07-19
 */
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, Userinfo> implements IUserinfoService {

    @Override
    public Page<Map> queryMyItems(Page<Map> mapPage, Map requestParam) {
        List<Map> myItems = baseMapper.queryMyItems(mapPage,requestParam);
        mapPage.setRecords(myItems);
        return mapPage;
    }
}
