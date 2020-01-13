package com.hss.bean;

import java.util.List;

//import com.alibaba.fastjson.JSONObject;

public class Node {

	/** 
     * id 
     */  
    private String id;  
    /** 
     * 名称 
     */  
    private String name;  
    /** 
     * 父节点 
     */  
    private String parentId;  
    /** 
     * 所有的父节点 
     */  
    private String parentIds;  
    /** 
     * 子节点 
     */  
    private List<Node> children;  
    /** 
     * 是否叶子节点 
     */  
    private Boolean isLeaf;  
    /** 
     * 级别 
     */  
    private Integer level;  
    /** 
     * 序号 
     */  
    private Integer sort;  
      
    public Node() {  
        super();  
    }  
  
    public Node(String id, String name, String parentId, String parentIds, List<Node> children, Boolean isLeaf,  
            Integer level, Integer sort) {  
        super();  
        this.id = id;  
        this.name = name;  
        this.parentId = parentId;  
        this.parentIds = parentIds;  
        this.children = children;  
        this.isLeaf = isLeaf;  
        this.level = level;  
        this.sort = sort;  
    }  
      
    public String getId() {  
        return id;  
    }  
    public void setId(String id) {  
        this.id = id;  
    }  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public String getParentId() {  
        return parentId;  
    }  
    public void setParentId(String parentId) {  
        this.parentId = parentId;  
    }  
    public String getParentIds() {  
        return parentIds;  
    }  
    public void setParentIds(String parentIds) {  
        this.parentIds = parentIds;  
    }  
    public List<Node> getChildren() {  
        return children;  
    }  
    public void setChildren(List<Node> children) {  
        this.children = children;  
    }  
    public Boolean getIsLeaf() {  
        return isLeaf;  
    }  
    public void setIsLeaf(Boolean isLeaf) {  
        this.isLeaf = isLeaf;  
    }  
    public Integer getLevel() {  
        return level;  
    }  
    public void setLevel(Integer level) {  
        this.level = level;  
    }  
    public Integer getSort() {  
        return sort;  
    }  
    public void setSort(Integer sort) {  
        this.sort = sort;  
    }  
  
//    @Override  
//    public String toString() {  
//        return JSONObject.toJSONString(this);  
//    }  
}
