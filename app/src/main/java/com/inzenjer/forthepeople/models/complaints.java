package com.inzenjer.forthepeople.models;

/**
 * Created by SUDHEESH on 11/21/2017.
 */

public class complaints {
    String name, complaintid, status, from, complaint_des;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComplaintid() {
        return complaintid;
    }

    public void setComplaintid(String complaintid) {
        this.complaintid = complaintid;
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

    public String getComplaint_des() {
        return complaint_des;
    }

    public void setComplaint_des(String complaint_des) {
        this.complaint_des = complaint_des;
    }

    public complaints(String name, String complaintid, String status, String from, String complaint_des) {

        this.name = name;
        this.complaintid = complaintid;
        this.status = status;
        this.from = from;
        this.complaint_des = complaint_des;
    }
}
