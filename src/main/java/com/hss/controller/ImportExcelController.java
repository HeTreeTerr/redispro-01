package com.hss.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hss.service.ImportService;
import com.hss.service.UserExcelImportService;

@Controller
@RequestMapping("/excel")
public class ImportExcelController {

	@Autowired(required=true)
    private ImportService importService;
	
	@Autowired(required=true)
	private UserExcelImportService userExcelImport;
    
    //导入excel
    @RequestMapping(value = "/import", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> importExcel(@RequestParam(value="file",required = false) MultipartFile file,
    		HttpServletRequest request,HttpServletResponse response){
    	System.out.println("----------开始导入-----------");
    	Map<String, Object> map = new HashMap<String, Object>();
    	if(file != null) {
    		System.out.println("文件数据file："+file.toString());
            String result = importService.readExcelFile(file);  
            map.put("message", result);
    	}else {
    		System.out.println("空空如也");
    	}        
        return map;  
    }  
    
    //导入excel
    @RequestMapping(value = "/import1", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> userExcelImport(@RequestParam(value="file",required = false) MultipartFile file,
    		HttpServletRequest request,HttpServletResponse response){
    	System.out.println("----------开始导入-----------");
    	Map<String, Object> map = new HashMap<String, Object>();
    	if(file != null) {
    		System.out.println("文件数据file："+file.toString());
            String result = userExcelImport.handlerUserExcelImport(file);  
            map.put("message", result);
    	}else {
    		System.out.println("空空如也");
    	}        
        return map;  
    }  
}
