package com.study.pojo;

import javax.persistence.Column;
import javax.persistence.Id;

public class Users {
    @Id
    private String id;

    private String username;

    private String password;

    /**
     * 头像缩略图
     */
    @Column(name = "face_image")
    private String faceImage;

    @Column(name = "face_image_big")
    private String faceImageBig;

    private String nickname;

    private String qrcode;

    /**
     * 手机设备的id
     */
    private String cid;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取头像缩略图
     *
     * @return face_image - 头像缩略图
     */
    public String getFaceImage() {
        return faceImage;
    }

    /**
     * 设置头像缩略图
     *
     * @param faceImage 头像缩略图
     */
    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    /**
     * @return face_image_big
     */
    public String getFaceImageBig() {
        return faceImageBig;
    }

    /**
     * @param faceImageBig
     */
    public void setFaceImageBig(String faceImageBig) {
        this.faceImageBig = faceImageBig;
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return qrcode
     */
    public String getQrcode() {
        return qrcode;
    }

    /**
     * @param qrcode
     */
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    /**
     * 获取手机设备的id
     *
     * @return cid - 手机设备的id
     */
    public String getCid() {
        return cid;
    }

    /**
     * 设置手机设备的id
     *
     * @param cid 手机设备的id
     */
    public void setCid(String cid) {
        this.cid = cid;
    }
}