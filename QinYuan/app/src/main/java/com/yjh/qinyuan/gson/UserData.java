package com.yjh.qinyuan.gson;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import java.io.Serializable;

public class UserData extends SugarRecord<UserData> implements Serializable {
    @SerializedName("DotPhone1") String phone1;
    @SerializedName("DotPhone2") String phone2;
    @SerializedName("DotUName") String name;
    @SerializedName("DotUser") String username;
    @SerializedName("DotType") int userType;

    public UserData() {
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
