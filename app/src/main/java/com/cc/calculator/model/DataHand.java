package com.cc.calculator.model;

import java.util.List;

/**
 * 项目名称：Calculator
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2017/1/22 13:52
 * 修改人：Administrator
 * 修改时间：2017/1/22 13:52
 * 修改备注：
 */

public class DataHand {

    public List<OneHand> data;

    public void setData(List<OneHand> data) {
        this.data = data;
    }

    public List<OneHand> getData() {
        return data;
    }

    public static class OneHand {

        public String title;
        public int image;

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<TwoHand> data;

        public void setData(List<TwoHand> data) {
            this.data = data;
        }

        public List<TwoHand> getData() {
            return data;
        }

        public static class TwoHand {
            public String twotitle;

            public String getTwotitle() {
                return twotitle;
            }

            public void setTwotitle(String twotitle) {
                this.twotitle = twotitle;
            }
        }


    }
}
