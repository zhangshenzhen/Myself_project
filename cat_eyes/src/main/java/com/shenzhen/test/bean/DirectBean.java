package com.shenzhen.test.bean;

public class DirectBean {


    public DirectBean(String movieName, String direct, String pict_uri) {
        this.movieName = movieName;
        this.direct = direct;
        this.pict_uri = pict_uri;
    }

    private String movieName;
    private String direct;
    private String pict_uri;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public String getPict_uri() {
        return pict_uri;
    }

    public void setPict_uri(String pict_uri) {
        this.pict_uri = pict_uri;
    }






}
