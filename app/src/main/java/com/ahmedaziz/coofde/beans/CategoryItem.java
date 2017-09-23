package com.ahmedaziz.coofde.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Ahmed Aziz on 9/22/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryItem {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDrawable() {
        return drawable;
    }

    public void setDrawable(String drawable) {
        this.drawable = drawable;
    }

    private String drawable;
}
