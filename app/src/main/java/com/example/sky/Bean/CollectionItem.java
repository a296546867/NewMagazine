package com.example.sky.Bean;

/**
 * 项目名称：NewMagazine
 * 类描述：
 *
 * 收藏夹的Item对象实体
 *
 *
 * 创建人：sky
 * 创建时间：2016/5/8 0:53
 * 修改人：sky
 * 修改时间：2016/5/8 0:53
 * 修改备注：
 */
public class CollectionItem {


    private int    articleIMG;                 //文章封面
    private int    deleteIMG;                  //删除图标
    private String articleName;                //文本标题
    private String articleCollectionNUM;     //收藏量

    public CollectionItem(int articleIMG, int deleteIMG, String articleName, String articleCollectionNUM) {
        this.articleIMG = articleIMG;
        this.deleteIMG = deleteIMG;
        this.articleName = articleName;
        this.articleCollectionNUM = articleCollectionNUM;
    }

    public int getArticleIMG() {
        return articleIMG;
    }

    public int getDeleteIMG() {
        return deleteIMG;
    }

    public String getArticleName() {
        return articleName;
    }

    public String getArticleCollectionNUM() {
        return articleCollectionNUM;
    }

    public void setArticleIMG(int articleIMG) {
        this.articleIMG = articleIMG;
    }

    public void setDeleteIMG(int deleteIMG) {
        this.deleteIMG = deleteIMG;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public void setArticleCollectionNUM(String articleCollectionNUM) {
        this.articleCollectionNUM = articleCollectionNUM;
    }



}
