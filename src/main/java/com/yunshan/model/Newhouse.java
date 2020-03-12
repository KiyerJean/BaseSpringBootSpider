package com.yunshan.model;

public class Newhouse {
    private Integer id;

    private String name;

    private String address;

    private String longitude;

    private String latitude;

    private String imgList;

    private String msgList;

    private String msgList2;

    private String introduce;

    private String huxingList;

    private String photoList;

    private Long updateTime;

    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getImgList() {
        return imgList;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList == null ? null : imgList.trim();
    }

    public String getMsgList() {
        return msgList;
    }

    public void setMsgList(String msgList) {
        this.msgList = msgList == null ? null : msgList.trim();
    }

    public String getMsgList2() {
        return msgList2;
    }

    public void setMsgList2(String msgList2) {
        this.msgList2 = msgList2 == null ? null : msgList2.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getHuxingList() {
        return huxingList;
    }

    public void setHuxingList(String huxingList) {
        this.huxingList = huxingList == null ? null : huxingList.trim();
    }

    public String getPhotoList() {
        return photoList;
    }

    public void setPhotoList(String photoList) {
        this.photoList = photoList == null ? null : photoList.trim();
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", address=").append(address);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", imgList=").append(imgList);
        sb.append(", msgList=").append(msgList);
        sb.append(", msgList2=").append(msgList2);
        sb.append(", introduce=").append(introduce);
        sb.append(", huxingList=").append(huxingList);
        sb.append(", photoList=").append(photoList);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", version=").append(version);
        sb.append("]");
        return sb.toString();
    }
}