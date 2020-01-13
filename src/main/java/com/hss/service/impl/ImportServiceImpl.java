package com.hss.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hss.service.ImportService;
import com.hss.util.ReadExcel;

@Service
public class ImportServiceImpl implements ImportService{

	@Override
	public String readExcelFile(MultipartFile file) {
		String result = "";  
        //创建处理EXCEL的类  
        ReadExcel readExcel = new ReadExcel();  
        //解析excel，获取上传的事件单  
        List<Map<String, Object>> userList = readExcel.getExcelInfo(file);  
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,  
        for(Map<String, Object> user:userList){
           System.out.println(user);
        }
        if(userList != null && !userList.isEmpty()){  
            result = "上传成功";  
        }else{  
            result = "上传失败";  
        }  
        return result;  
    }
}
