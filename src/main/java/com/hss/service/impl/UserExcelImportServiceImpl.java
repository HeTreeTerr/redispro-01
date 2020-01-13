package com.hss.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hss.bean.UserExcel;
import com.hss.service.UserExcelImportService;
import com.hss.util.ReadExcel;
import com.hss.util.ReadExcelUtil;

@Service
public class UserExcelImportServiceImpl implements UserExcelImportService{

	@Override
	public String handlerUserExcelImport(MultipartFile file) {
		String result = "";  
        //创建处理EXCEL的类  
        ReadExcelUtil readExcelUtil = new ReadExcelUtil();  
        //解析excel，获取上传的事件单  
        List<Map<String, Object>> userList = readExcelUtil.getExcelInfo(file);
        List<UserExcel> userExcelList = new ArrayList<UserExcel>();
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,  
        for(Map<String, Object> user:userList){
           System.out.println(user);
           UserExcel userExcel = setValueToUserObject(user);  
           userExcelList.add(userExcel);
        }
        System.out.println("-----------封装入对象------------");
        System.out.println(userExcelList);
        if(userList != null && !userList.isEmpty()){  
            result = "上传成功";  
        }else{  
            result = "上传失败";  
        }  
        return result;  
	}
	
	private UserExcel setValueToUserObject(Map<String, Object> userDate) {
		UserExcel userExcel = new UserExcel();
		//写入姓名
		userExcel.setName(userDate.get("name").toString());
		//写入年龄
		userExcel.setAge(Integer.parseInt(String.valueOf(userDate.get("age"))));
		//写入性别
		String sex = userDate.get("sex").toString();
		//0男1女
		if("男".equals(sex)) {
			userExcel.setSex(0);
		}else if("女".equals(sex)){
			userExcel.setSex(1);
		}
		return userExcel;
	}
}
