package com.yjh.qinyuan.gson;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.List;

public class UserInfoModel extends SugarRecord<UserInfoModel> implements Serializable {
    @SerializedName("data") UserData userData;
    @SerializedName("key") String key;
    @SerializedName("msg") boolean isSuccess;

    public UserInfoModel() {
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

    public void saveData(UserInfoModel userInfoModel) {
        List<UserInfoModel> userInfoModels = UserInfoModel.listAll(UserInfoModel.class);

        for (UserInfoModel info : userInfoModels) {
            info.delete();
        }

        userInfoModel.getUserData().save();
        userInfoModel.save();
    }

    public static void clearData() {
        List<UserInfoModel> userInfoModels = UserInfoModel.listAll(UserInfoModel.class);

        for (UserInfoModel info : userInfoModels) {
            info.delete();
        }
    }
}
