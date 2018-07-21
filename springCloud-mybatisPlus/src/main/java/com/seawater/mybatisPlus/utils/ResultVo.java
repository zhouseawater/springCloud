package com.seawater.mybatisPlus.utils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * Created by zhouhaishui on 2018/7/2.
 */
public class ResultVo implements Serializable {
    /**
     * 返回客户端统一格式，包括状态码，提示信息，以及业务数据
     */
    private static final long serialVersionUID = 1L;
    //状态码
    private int error_no;
    //必要的提示信息
    private String error_info;
    //业务数据
    private Object results;
    //结果集数据key
    private String dsName;

    public static class ErrorCode {
        public static final int SUCCESS = 0;
        public static final int FAILURE = 999;
    }

    public static class ErrorMessage {
        public static final String SUCCESS = "接口调用成功";
        public static final String FAILURE = "接口调用失败,请刷新";
    }

    public ResultVo() {
    }

    public ResultVo(int error_no, String error_info, Page page){
        this.error_no = error_no;
        this.error_info = error_info;
        this.results = page;
        if(StringUtils.isEmpty(getDsName())){
            this.dsName = "results";
        }
    }

    public ResultVo(int error_no, String error_info, Object data){
        this.error_no = error_no;
        this.error_info = error_info;
        this.results = data;
        if(StringUtils.isEmpty(getDsName())){
            this.dsName = "results";
        }
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public int getError_no() {
        return error_no;
    }

    public void setError_no(int error_no) {
        this.error_no = error_no;
    }

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }

    public Object getResults() {
        return results;
    }

    public void setResults(Object data) {
        this.results = data;
        if(StringUtils.isEmpty(getDsName())){
            this.dsName = "results";
        }
    }

    public void setResults(Page page) {
        this.results = page;
        if(StringUtils.isEmpty(getDsName())){
            this.dsName = "results";
        }
    }

    public String toString(){
        if(null == this.results){
            this.setResults(new Object());
        }
        return JSON.toJSONString(this);
    }
}
