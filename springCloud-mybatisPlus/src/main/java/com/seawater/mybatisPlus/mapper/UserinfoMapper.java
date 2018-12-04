package com.seawater.mybatisPlus.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.seawater.mybatisPlus.entity.Userinfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhouhaishui
 * @since 2018-07-19
 */
public interface UserinfoMapper extends BaseMapper<Userinfo> {

    List<Map> queryMyItems(Page<Map> mapPage, Map requestParam);

    List<Map> queryUserInfo(Map map);
}
