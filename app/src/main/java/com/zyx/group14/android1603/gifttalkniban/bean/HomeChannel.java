package com.zyx.group14.android1603.gifttalkniban.bean;

import java.io.Serializable;

/**
 * Created by yixin on 2016/8/29.
 */
public class HomeChannel implements Serializable {

    /**
     * editable : false
     * id : 101
     * name : 精选
     */

    private boolean editable;
    private int id;
    private String name;

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
