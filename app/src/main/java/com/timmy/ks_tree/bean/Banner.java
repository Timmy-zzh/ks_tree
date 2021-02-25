package com.timmy.ks_tree.bean;

public class Banner {

//    @Json(name = "desc") val desc: String,
//    @Json(name = "id") val id: Int,
//    @Json(name = "imagePath") val imagePath: String,
//    @Json(name = "isVisible") val isVisible: Int,
//    @Json(name = "order") val order: Int,
//    @Json(name = "title") val title: String,
//    @Json(name = "type") val type: Int,
//    @Json(name = "url") val url: String

    public String desc;
    public int id;
    public String imagePath;
    public int isVisible;
    public int order;
    public int type;
    public String title;
    public String url;

    @Override
    public String toString() {
        return "Banner{" +
                "desc='" + desc + '\'' +
                ", id=" + id +
                ", imagePath='" + imagePath + '\'' +
                ", isVisible=" + isVisible +
                ", order=" + order +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
