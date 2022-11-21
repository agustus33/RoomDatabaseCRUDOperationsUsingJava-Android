package com.android.roomdb.recyclerview;

public class RecycleModal {
    private int uid;
    private  String name;
    private String email;
    Boolean isChecked;


    public RecycleModal(int uid, String name, String email, boolean isChecked) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.isChecked = isChecked ;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSelected(boolean isChecked) {

        this.isChecked = isChecked;
    }

    public boolean isSelected() {
        return isChecked;
    }
}
