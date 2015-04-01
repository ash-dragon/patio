package com.yjh.qinyuan.gson;

import com.google.gson.annotations.SerializedName;
import com.yjh.qinyuan.util.Constants;

import java.io.Serializable;
import java.util.ArrayList;

public class TownBranch implements Serializable {
    @SerializedName("NID") int nid;
    @SerializedName("PTName") String ptName;
    @SerializedName("CTName") String ctName;
    @SerializedName("NName") String nName;
    @SerializedName("PTID") int ptId;
    @SerializedName("CTID") int ctId;
    @SerializedName("DotUName") String username;
    @SerializedName("DotPhone1") String phone1;
    @SerializedName("DotPhone2") String phone2;
    @SerializedName("DotProviceID") int provinceId;
    @SerializedName("DotCityID") int cityId;
    @SerializedName("DotCountyID") int countryId;
    @SerializedName("DotTownID") int townId;
    @SerializedName("DotProvice") String province;
    @SerializedName("DotCity") String city;
    @SerializedName("DotCounty") String country;
    @SerializedName("DotTown") String town;
    @SerializedName("DotAddress") String address;
    @SerializedName("DotMemo") String memo;
    @SerializedName("imgurl1") String image1;
    @SerializedName("imgurl2") String image2;
    @SerializedName("imgurl3") String image3;
    @SerializedName("imgurl4") String image4;
    @SerializedName("imgurl5") String image5;
    @SerializedName("Longitude") double longitude;
    @SerializedName("Latitude") double latitude;

    public ArrayList<String> getUrls() {
        ArrayList<String> urls = new ArrayList<>();

        if (!image1.isEmpty() && !image1.trim().equals("")) {
            urls.add(image1);
        }
        if (!image2.isEmpty() && !image2.trim().equals("")) {
            urls.add(image2);
        }
        if (!image3.isEmpty() && !image3.trim().equals("")) {
            urls.add(image3);
        }
        if (!image4.isEmpty() && !image4.trim().equals("")) {
            urls.add(image4);
        }
        if (!image5.isEmpty() && !image5.trim().equals("")) {
            urls.add(image5);
        }

        return urls;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getPtName() {
        return ptName;
    }

    public void setPtName(String ptName) {
        this.ptName = ptName;
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName;
    }

    public String getnName() {
        return nName;
    }

    public void setnName(String nName) {
        this.nName = nName;
    }

    public int getPtId() {
        return ptId;
    }

    public void setPtId(int ptId) {
        this.ptId = ptId;
    }

    public int getCtId() {
        return ctId;
    }

    public void setCtId(int ctId) {
        this.ctId = ctId;
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

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAddress() {
        return province + city + country + town + address;
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

    public String getImage1() {
        return Constants.DOMAIN + image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getImage5() {
        return image5;
    }

    public void setImage5(String image5) {
        this.image5 = image5;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
