package com.inzenjer.forthepeople.models;

/**
 * Created by SUDHEESH on 11/25/2017.
 */

public class suggetions {
    String name, suggetionid, status, from, suggetion_des;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuggetionid() {
        return suggetionid;
    }

    public void setSuggetionid(String suggetionid) {
        this.suggetionid = suggetionid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSuggetion_des() {
        return suggetion_des;
    }

    public void setSuggetion_des(String suggetion_des) {
        this.suggetion_des = suggetion_des;
    }

    public suggetions(String name, String suggetionid, String status, String from, String suggetion_des) {
        this.name = name;
        this.suggetionid = suggetionid;
        this.status = status;
        this.from = from;
        this.suggetion_des = suggetion_des;
    }
}
