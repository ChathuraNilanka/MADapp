package com.example.chathura.plan;

/**
 * Created by Chathura on 9/21/2018.
 */

public class ModelPlan {
    private int plan_id;
    private String title;
    private String num_days;
    private String start_date;
    private String end_date;
    private String trans_type;
    private String num_person;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getNum_days() {
        return num_days;
    }

    public void setNum_days(String num_days) {
        this.num_days = num_days;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getTrans_type() {
        return trans_type;
    }

    public void setTrans_type(String trans_type) {
        this.trans_type = trans_type;
    }

    public String getNum_person() {
        return num_person;
    }

    public void setNum_person(String num_person) {
        this.num_person = num_person;
    }
}
