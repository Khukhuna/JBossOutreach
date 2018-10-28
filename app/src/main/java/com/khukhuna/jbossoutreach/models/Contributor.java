package com.khukhuna.jbossoutreach.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contributor {
    @SerializedName("login")
    @Expose
    private String name;

    @SerializedName("avatar_url")
    @Expose
    private String image_url;

    @SerializedName("contributions")
    @Expose
    private int contribution;


    public String getName() {
        return name;
    }

    public String getImageurl() {
        return image_url;
    }

    public int getContribution() {
        return contribution;
    }
}
