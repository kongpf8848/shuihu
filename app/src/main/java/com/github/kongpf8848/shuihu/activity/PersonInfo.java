package com.github.kongpf8848.shuihu.activity;

import java.io.Serializable;

public class PersonInfo implements Serializable {
    private String name;
    private String nick;
    private String star;
    private String sort;
    private String desc;

    public PersonInfo(String name, String nick, String star, String sort, String desc, String image) {
        this.name = name;
        this.nick = nick;
        this.star = star;
        this.sort = sort;
        this.desc = desc;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
