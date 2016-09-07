package com.cc.calculator.model;

import java.util.List;

/**
 * 项目名称：Calculator
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/7 15:53
 * 修改人：Administrator
 * 修改时间：2016/9/7 15:53
 * 修改备注：
 */
public class ImgDow {
    /**
     * status : 1
     * data : [{"imagePath":"http://117.34.105.29:8209/mhealth/icon1473236449178_12"}]
     */

    private String status;
    /**
     * imagePath : http://117.34.105.29:8209/mhealth/icon1473236449178_12
     */

    private List<DataEntity> data;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String imagePath;

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getImagePath() {
            return imagePath;
        }
    }

    /**
     * status : 1
     * data : 图片下载成功
     * imagePath : upload/album/01/a002.jpg
     */


}
