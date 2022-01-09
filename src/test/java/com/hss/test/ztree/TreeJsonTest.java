package com.hss.test.ztree;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hss.bean.Node;
import com.hss.util.JsonFormatTool;
import com.hss.util.TreeUtils;

/**
 * ztree
 */
public class TreeJsonTest {

	@Test
	public void test1() {
		List<Node> nullList=new ArrayList<Node>();  
        //镇  
        Node znode1=new Node("1000", "徐桥镇", "100", "1,10,100,", nullList, true, 4, 1);  
        Node znode2=new Node("1001", "兴昌镇", "100", "1,10,100,", nullList, true, 4, 2);  
        Node znode3=new Node("1002", "天桥镇", "100", "1,10,100,", nullList, true, 4, 3);  
        //县  
        Node xnode1=new Node("100", "太湖县", "10", "1,10,", nullList, false, 3, 1);  
        Node xnode2=new Node("101", "宿松县", "10", "1,10,", nullList, false, 3, 2);  
        Node xnode3=new Node("102", "望江县", "10", "1,10,", nullList, false, 3, 3);  
        //市  
        Node snode1=new Node("10", "安庆市", "1", "1,", nullList, false, 2, 1);  
        Node snode2=new Node("11", "合肥市", "1", "1,", nullList, false, 2, 1);  
        Node snode3=new Node("12", "黄山市", "1", "1,", nullList, false, 2, 1);  
        //省  
        Node wnode1=new Node("1", "安徽省", "", "", nullList, false, 1, 1);  
        List<Node> list=new ArrayList<Node>();  
        list.add(wnode1);  
        list.add(xnode1);  
        list.add(xnode2);  
        list.add(xnode3);  
        list.add(snode1);  
        list.add(snode2);  
        list.add(snode3);  
        list.add(znode1);  
        list.add(znode2);  
        list.add(znode3);  
        //  
        Node node=TreeUtils.buildTree("1", list);//方法一  
        String result = node.toString();
//        System.out.println("结果:\n"+result);  
//      Node node2=TreeUtils.buildTreeNode("1", list);//方法二  
//      System.out.println("结果:\n"+node2.toString());  
        JsonFormatTool json = new JsonFormatTool();
        String jsonResult = json.formatJson(result);
        System.out.println(jsonResult);
	}
}
