package com.kzw.leisure.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ExploreMultiItemBean implements MultiItemEntity {

    public static final int TYPE_TAG=1;
    public static final int TYPE_ITEM=2;

    public String tagName;
    public SiteSourceBean.ListDTO bean;
    private int itemType;

    public String getTagName() {
        return tagName;
    }

    public ExploreMultiItemBean setTagName(String tagName) {
        this.tagName = tagName;
        return this;
    }

    public SiteSourceBean.ListDTO getBean() {
        return bean;
    }

    public ExploreMultiItemBean setBean(SiteSourceBean.ListDTO bean) {
        this.bean = bean;
        return this;
    }

    public ExploreMultiItemBean setItemType(int itemType) {
        this.itemType = itemType;
        return this;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    @Override
    public String toString() {
        return "ExploreMultiItemBean{" +
                "tagName='" + tagName + '\'' +
                ", bean=" + bean +
                ", itemType=" + itemType +
                '}';
    }
}
