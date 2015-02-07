package com.yjh.qinyuan.gson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Site implements Serializable {
    @SerializedName("CID") String cid;
    @SerializedName("CName") String cityName;
    @SerializedName("DotUName") String username;
    @SerializedName("DotPhone1") String phone1;
    @SerializedName("DotPhone2") String phone2;
    @SerializedName("DotProviceID") int provinceId;
    @SerializedName("DotCityID") int cityId;
    @SerializedName("DotProvice") String province;
    @SerializedName("DotCity") String city;
    @SerializedName("DotAddress") String address;
    @SerializedName("DotMemo") String memo;

    public String getCid() {
        return cid;
    }


    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return province + city + address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
