package com.cc.calculator.model;

/**
 * 项目名称：Calculator
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/8/29 15:41
 * 修改人：Administrator
 * 修改时间：2016/8/29 15:41
 * 修改备注：
 */
public class NewDetail {

    /**
     * title : 的所发生的
     * articleId : 1
     * context : fadsfdjf
     */

    private String title;
    private int articleId;
    private String context;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTitle() {
        return title;
    }

    public int getArticleId() {
        return articleId;
    }

    public String getContext() {
        return context;
    }
}
