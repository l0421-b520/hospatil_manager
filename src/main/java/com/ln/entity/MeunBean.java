package com.ln.entity;

import java.io.Serializable;

public class MeunBean implements Serializable {
    private Long id;

    private Long pid;

    private String mname;

    private String url;

    private String target;

    private String icon;

    private Integer isbutton;

    /**
     * 设置树状图回显
     */
    private boolean checked = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname == null ? null : mname.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getIsbutton() {
        return isbutton;
    }

    public void setIsbutton(Integer isbutton) {
        this.isbutton = isbutton;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}