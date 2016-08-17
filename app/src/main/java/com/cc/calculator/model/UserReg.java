package com.cc.calculator.model;

/**
 * 项目名称：Calculator
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/8/17 10:43
 * 修改人：Administrator
 * 修改时间：2016/8/17 10:43
 * 修改备注：
 */
public class UserReg {

    /**
     * phone : 12345678902
     * status : 1
     * userId : 9
     * data : 登录成功
     */

    private String phone;
    private String status;
    private int userId;
    private String data;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }

    public int getUserId() {
        return userId;
    }

    public String getData() {
        return data;
    }
}
