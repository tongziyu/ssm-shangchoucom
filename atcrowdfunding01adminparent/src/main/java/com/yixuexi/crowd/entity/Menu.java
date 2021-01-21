package com.yixuexi.crowd.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Menu {
    private Integer id;

    private Integer pid;

    private String name;

    private String url;

    private String icon;
    // 储存子节点的集合，初始化是为了防止空指针异常
    private List<Menu> children = new ArrayList<>();
    // 控制节点是否默认为打开状态，设置true默认打开
    private Boolean open = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id) &&
                Objects.equals(pid, menu.pid) &&
                Objects.equals(name, menu.name) &&
                Objects.equals(url, menu.url) &&
                Objects.equals(icon, menu.icon) &&
                Objects.equals(children, menu.children) &&
                Objects.equals(open, menu.open);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pid, name, url, icon, children, open);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                ", open=" + open +
                '}';
    }
}