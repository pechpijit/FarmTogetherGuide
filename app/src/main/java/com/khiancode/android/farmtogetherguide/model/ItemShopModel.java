package com.khiancode.android.farmtogetherguide.model;

public class ItemShopModel {

    String name;
    String pathImg;

    public ItemShopModel(String name, String pathImg) {
        this.name = name;
        this.pathImg = pathImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }
}
