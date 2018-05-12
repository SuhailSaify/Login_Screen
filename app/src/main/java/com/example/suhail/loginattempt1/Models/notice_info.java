package com.example.suhail.loginattempt1.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.URI;

/**
 * Created by Suhail on 10/20/2017.
 */

public class notice_info {

    @SerializedName("notice")
    @Expose
    private String notice;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("imageurl")
    @Expose                              //in case of pdf, +screenshot
    private String imageurl;


    @SerializedName("pdf_url")
    @Expose
    private String pdf_url;


    public String getPdf_url() {
        return pdf_url;
    }

    public void setPdf_url(String pdf_url) {
        this.pdf_url = pdf_url;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public String getDate() {
        return date;
    }

    public String getNotice() {return notice;}

}
