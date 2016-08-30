package com.cc.calculator.model;

import java.util.List;

/**
 * 项目名称：Calculator
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/8/29 9:26
 * 修改人：Administrator
 * 修改时间：2016/8/29 9:26
 * 修改备注：
 */
public class NewsList {


    /**
     * title : 楼顶抽油烟机突失火 太原消防化险为夷
     * image : http://localhost:8080/mhealth/upload/upload\160829023305634.png
     * articleId : 9
     */

    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String title;
        private String image;
        private int articleId;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setArticleId(int articleId) {
            this.articleId = articleId;
        }

        public String getTitle() {
            return title;
        }

        public String getImage() {
            return image;
        }

        public int getArticleId() {
            return articleId;
        }
    }
}
