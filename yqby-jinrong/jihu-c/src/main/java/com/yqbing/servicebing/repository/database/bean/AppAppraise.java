package com.yqbing.servicebing.repository.database.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


@ApiModel(value = "app评论")
public class AppAppraise {
    private Integer id;
    @ApiModelProperty(value = "app名称", name = "appPack")
    private String appPack;
    @ApiModelProperty(value = "appid称", name = "appId")
    private Integer appId;
    @ApiModelProperty(value = "评论用户ID", name = "userId")
    private Integer userId;
    @ApiModelProperty(value = "评论用户头像", name = "userimg")
    private String userimg;
    @ApiModelProperty(value = "评论用户名称", name = "username")
    private String username;
    @ApiModelProperty(value = "评论创建时间", name = "createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @ApiModelProperty(value = "评论修改时间", name = "updateTime")
    private Date updateTime;
    @ApiModelProperty(value = "评论详情", name = "content")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppPack() {
        return appPack;
    }

    public void setAppPack(String appPack) {
        this.appPack = appPack == null ? null : appPack.trim();
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg == null ? null : userimg.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}