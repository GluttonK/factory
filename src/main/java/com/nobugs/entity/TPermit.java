package com.nobugs.entity;

public class TPermit {
    private Integer id;

    private Integer flag;

    private Integer status;

    private Integer parentId;

    private String permitName;

    private String permitDesc;

    private String permitPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPermitName() {
        return permitName;
    }

    public void setPermitName(String permitName) {
        this.permitName = permitName == null ? null : permitName.trim();
    }

    public String getPermitDesc() {
        return permitDesc;
    }

    public void setPermitDesc(String permitDesc) {
        this.permitDesc = permitDesc == null ? null : permitDesc.trim();
    }

    public String getPermitPath() {
        return permitPath;
    }

    public void setPermitPath(String permitPath) {
        this.permitPath = permitPath == null ? null : permitPath.trim();
    }
}