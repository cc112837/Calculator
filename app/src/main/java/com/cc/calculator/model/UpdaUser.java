package com.cc.calculator.model;

/**
 * 项目名称：Calculator
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/8/18 16:29
 * 修改人：Administrator
 * 修改时间：2016/8/18 16:29
 * 修改备注：
 */
public class UpdaUser {

    /**
     * status : 1
     * data : 密码修改成功
     */

    private String status;
    private String data;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getData() {
        return data;
    }
}
