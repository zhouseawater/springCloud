package com.seawater.mybatisPlus.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.seawater.mybatisPlus.entity.Userinfo;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhouhaishui
 * @since 2018-07-19
 */
public interface IUserinfoService extends IService<Userinfo> {

    Page<Map> queryMyItems(Page<Map> mapPage, Map requestParam);
}
