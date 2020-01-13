package com.hss.util;

import java.util.ArrayList;  
import java.util.List;

import com.hss.bean.Node; 

public class TreeUtils {

	/* 
     * =============================================方法一=================================================================== 
     */  
      
    /** 
     * 获取树型结构数据（方法一） 
     * @param rootId 根节点id 
     * @param srcList 源list 
     * @return 
     */  
    public static Node buildTree(String rootId, List<Node> srcList){  
        Node rootNode=getNodeById(rootId, srcList);  
        List<Node> children=buildChildren(rootNode.getId(), srcList);  
        rootNode.setChildren(children);  
        return rootNode;  
    }  
      
    /** 
     * 根据父节点递归获取子节点（方法一） 
     * @param parentId 父节点id 
     * @param srcList 源list 
     * @return 
     */  
    public static List<Node> buildChildren(String parentId, List<Node> srcList){  
        //parentId对应的子节点集合  
        List<Node> children=new ArrayList<Node>();  
        for (Node node : srcList) {  
            if(node.getParentId().equals(parentId)){  
                //装载parentId对应的子节点，属于二级节点  
                children.add(node);  
            }  
        }  
        if(!children.isEmpty()){  
            //进一步递归获取子节点集合的子节点  
            for (Node nod : children) {  
                List<Node> childs=new ArrayList<Node>();  
                if(!nod.getIsLeaf()){  
                    //不是叶子节点，则该node应该还有子节点（不一定有）  
                    childs=buildChildren(nod.getId(), srcList);  
                }  
                nod.setChildren(childs);  
            }  
        }  
        return children;  
    }  
      
    /* 
     * =============================================方法二=================================================================== 
     */  
      
    /** 
     * 递归获取树型结构数据（方法二） 
     * @param parentId 父节点id 
     * @param srcList 源list 
     * @return 
     */  
    public static Node buildTreeNode(String parentId, List<Node> srcList){  
        //父节点  
        Node node=getNodeById(parentId, srcList);  
        List<Node> children=getChildrenNodeById(parentId, srcList);  
        if(children.isEmpty()){  
            node.setChildren(children);  
        }else{  
            List<Node> children2=new ArrayList<Node>();  
            for (Node temp : children) {  
                //递归  
                Node child=buildTreeNode(temp.getId(), srcList);  
                children2.add(child);  
            }  
            node.setChildren(children2);  
        }  
        return node;  
    }  
      
    /** 
     * 根据节点id获取子节点（方法二） 
     * @param parentId 
     * @param srcList 
     * @return 
     */  
    public static List<Node> getChildrenNodeById(String parentId, List<Node> srcList){  
        List<Node> children=new ArrayList<Node>();  
        for (Node node : srcList) {  
            if(node.getParentId().equals(parentId)){  
                //装载parentId对应的子节点  
                children.add(node);  
            }  
        }  
        return children;  
    }  
      
      
    /** 
     * 获取节点（公共） 
     * @param nodeId 根节点id 
     * @param srcList 源list 
     * @return 
     */  
    private static Node getNodeById(String nodeId, List<Node> srcList){  
        for (Node node : srcList) {  
            if(nodeId.equals(node.getId())){  
                return node;  
            }  
        }  
        return null;  
    }  
}
