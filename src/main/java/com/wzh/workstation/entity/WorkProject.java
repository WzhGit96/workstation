package com.wzh.workstation.entity;

import java.util.Date;

public class WorkProject {
    private Long id;

    private Long uid;

    private String code;

    private String name;

    private String branchs;

    private String projectLink;

    private String pkgSeq;

    private String archeryScript;

    private Byte status;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Byte delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink == null ? null : projectLink.trim();
    }

    public String getPkgSeq() {
        return pkgSeq;
    }

    public void setPkgSeq(String pkgSeq) {
        this.pkgSeq = pkgSeq == null ? null : pkgSeq.trim();
    }

    public String getArcheryScript() {
        return archeryScript;
    }

    public void setArcheryScript(String archeryScript) {
        this.archeryScript = archeryScript == null ? null : archeryScript.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    public String getBranchs() {
        return branchs;
    }

    public void setBranchs(String branchs) {
        this.branchs = branchs;
    }
}