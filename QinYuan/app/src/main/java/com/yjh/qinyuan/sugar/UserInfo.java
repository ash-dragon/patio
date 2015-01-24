package com.yjh.qinyuan.sugar;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.List;

public class UserInfo extends SugarRecord<UserInfo> implements Serializable {
    @SerializedName("data") UserData userData;
    @SerializedName("key") String key;
    @SerializedName("msg") boolean isSuccess;

    public UserInfo() {
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public void saveData(UserInfo userInfo) {
        List<UserInfo> userInfos = UserInfo.find(UserInfo.class, "key = ?", new String[]{userInfo.getKey()});

        for (UserInfo info : userInfos) {
            info.delete();
        }

        userInfo.getUserData().save();
        userInfo.save();
    }
}
