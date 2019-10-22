package com.smr.studios.tweeser;

public class Data {

    private String id;
    private String desc;
    private String picLink;
    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Data(String id, String desc, String picLink) {
        this.id = id;
        this.desc = desc;
        this.picLink = picLink;
    }


    public Data() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPicLink(String picLink) {
        this.picLink = picLink;
    }

    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public String getPicLink() {
        return picLink;
    }
}
