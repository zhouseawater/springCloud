package com.seawater.mybatisPlus.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.seawater.mybatisPlus.entity.Userinfo;
import com.seawater.mybatisPlus.service.IUserinfoService;
import com.seawater.mybatisPlus.utils.RequestParamsToMapUtils;
import com.seawater.mybatisPlus.utils.ResultVo;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhouhaishui
 * @since 2018-07-19
 */
@RestController
@RequestMapping("/mybatisPlus/userinfo")
public class UserinfoController {

    Logger logger = Logger.getLogger(UserinfoController.class);


    @Autowired
    IUserinfoService iUserinfoService;


    /**
     * 新增记录
     * localhost:50009/mybatisPlus/userinfo/addUser?username=zhou&age=22&description=qwertyuio
     *
     */
    @Transactional
    @PostMapping("/addUser")
    public ResultVo addUser(HttpServletRequest request, Userinfo user)throws Exception{
        ResultVo result = new ResultVo();
        result.setError_no(ResultVo.ErrorCode.SUCCESS);
        result.setError_info(ResultVo.ErrorMessage.SUCCESS);
        Map results = new HashMap();
        String successFlag = "0";

        try{

            /**入参校验请自己添加**/

            iUserinfoService.insert(user);//插入数据
            if(null != user.getId()){//插入成功后会返回id
                successFlag = "1";
            }
        }catch (Exception e){
            result.setError_no(ResultVo.ErrorCode.FAILURE);
            result.setError_info(ResultVo.ErrorMessage.FAILURE);
            logger.error("调用接口" + request.getRequestURI() + "出现异常,接口入参:"+ RequestParamsToMapUtils.getParameterStringMap(request)
                    +"错误信息" + e.toString());
        }

        results.put("success_flag",successFlag);
        result.setResults(results);
        return result;
    }


    /**
     * 更新
     * localhost:50009/mybatisPlus/userinfo/updateUser?id=1&age=30
     * @param request
     * @param updateUser
     * @return
     * @throws Exception
     */
    @Transactional
    @PostMapping("/updateUser")
    public ResultVo updateUser(HttpServletRequest request, Userinfo updateUser)throws Exception{
        ResultVo result = new ResultVo();
        result.setError_no(ResultVo.ErrorCode.SUCCESS);
        result.setError_info(ResultVo.ErrorMessage.SUCCESS);
        Map results = new HashMap();
        String successFlag = "0";

        try{

            /**入参校验请自己添加**/

            Userinfo update =  iUserinfoService.selectById(updateUser.getId());
            if(null != update){
                BeanUtils.copyProperties(updateUser,update);//需要修改的属性获取
                iUserinfoService.updateById(update);
                successFlag = "1";
            }

        }catch (Exception e){
            result.setError_no(ResultVo.ErrorCode.FAILURE);
            result.setError_info(ResultVo.ErrorMessage.FAILURE);
            logger.error("调用接口" + request.getRequestURI() + "出现异常,接口入参:"+ RequestParamsToMapUtils.getParameterStringMap(request)
                    +"错误信息" + e.toString());
        }

        results.put("success_flag",successFlag);
        result.setResults(results);
        return result;
    }




    /**
     * 单表，自定义列 分页
     * 需要分页数据的话 传递 size 每页大小，current 当前页码
     * localhost:50009/mybatisPlus/userinfo/selectUserByPage 默认查询10调数据
     * localhost:50009/mybatisPlus/userinfo/selectUserByPage?size=2&current=2 每页两条，当前第二页
     * @param request
     * @param page
     * @param user
     * @return
     * @throws Exception
     */
    @GetMapping("/selectUserByPage")
    public ResultVo selectUserByPage(HttpServletRequest request, Page page, Userinfo user) throws Exception{
        ResultVo result  = new ResultVo();
        result.setError_no(ResultVo.ErrorCode.SUCCESS);
        result.setError_info(ResultVo.ErrorMessage.SUCCESS);

        try{

            /**此格式的意思为传递建立的user对象传递了属性才创建where条件**/
            EntityWrapper<Userinfo> ew = new EntityWrapper<Userinfo>(user);
            /**设置需要查询的列名**/
            ew.setSqlSelect("id,username,age")
                    /**排序方式**/
                    .orderBy(false,"createtime",false);

            Page<Userinfo> postList = iUserinfoService.selectPage(page,ew);
            result.setResults(postList);

        }catch (Exception e){

            result.setError_no(ResultVo.ErrorCode.FAILURE);
            result.setError_info(ResultVo.ErrorMessage.FAILURE);
            logger.error("调用接口" + request.getRequestURI() + "出现异常,接口入参:"+ RequestParamsToMapUtils.getParameterStringMap(request)
                    +"错误信息"+e.toString());
        }

        return result;
    }


    /**
     * 关联表查询需要分页
     * localhost:50009/mybatisPlus/userinfo/queryMyItems?size=2&current=2 和上面的page一样请求
     * @param request
     * @param user
     * @param page
     * @return
     * @throws Exception
     */

    @GetMapping("/queryMyItems")
    public ResultVo queryMyItems(HttpServletRequest request,Userinfo user ,Page page) throws Exception{
        ResultVo result = new ResultVo();
        result.setError_no(ResultVo.ErrorCode.SUCCESS);
        result.setError_info(ResultVo.ErrorMessage.SUCCESS);
        try{

            /**入参自己校验**/

            Page<Map> mapPage = new Page<>(page.getCurrent(),page.getSize());
            Map requestParam = new HashMap<>();
            Page<Map> myItems= iUserinfoService.queryMyItems(mapPage,requestParam);
            result.setResults(myItems);

        }catch (Exception e){
            result.setError_no(ResultVo.ErrorCode.FAILURE);
            result.setError_info(ResultVo.ErrorMessage.FAILURE);
            logger.error("调用接口" + request.getRequestURI() + "出现异常,接口入参:"+ RequestParamsToMapUtils.getParameterStringMap(request)
                    +"错误信息"+ e.toString());
        }

        return result;
    }




}

