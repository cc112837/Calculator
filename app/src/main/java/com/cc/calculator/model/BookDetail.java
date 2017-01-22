package com.cc.calculator.model;

/**
 * 项目名称：Calculator
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2017/1/22 11:35
 * 修改人：Administrator
 * 修改时间：2017/1/22 11:35
 * 修改备注：
 */

public class BookDetail {

    /**
     * status : 1
     * data : 存在相应的数据
     * dataDetails : 详情
     */

    private String status;
    private String data;
    private String dataDetails;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDataDetails() {
        return dataDetails;
    }

    public void setDataDetails(String dataDetails) {
        this.dataDetails = dataDetails;
    }
}
