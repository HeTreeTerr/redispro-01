package com.hss.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ReadUserExcel {
	
	// 总行数
    private int totalRows = 0;
    // 总条数
    private int totalCells = 0;
    
    /**
     * 读取Excel里面客户的信息
     * 
     * @param wb
     * @return
     */
    public List<Map<String, Object>> readExcelValue(Workbook wb) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            // 循环Excel的列
            Map<String, Object> map = new HashMap<String, Object>();
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                        // 如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String name = String.valueOf(cell.getNumericCellValue());
                            map.put("name", name.substring(0, name.length() - 2 > 0 ? name.length() - 2 : 1));// 名称
                        } else {
                            map.put("name", cell.getStringCellValue());// 名称
                        }
                    } else if (c == 1) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String sex = String.valueOf(cell.getNumericCellValue());
                            map.put("sex",sex.substring(0, sex.length() - 2 > 0 ? sex.length() - 2 : 1));// 性别
                        } else {
                            map.put("sex",cell.getStringCellValue());// 性别
                        }
                    } else if (c == 2) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String age = String.valueOf(cell.getNumericCellValue());
                            map.put("age", age.substring(0, age.length() - 2 > 0 ? age.length() - 2 : 1));// 年龄
                        } else {
                            map.put("age", cell.getStringCellValue());// 年龄
                        }
                    }
                }
            }
            // 添加到list
            userList.add(map);
        }
        return userList;
    }

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalCells() {
		return totalCells;
	}

	public void setTotalCells(int totalCells) {
		this.totalCells = totalCells;
	}
    
    
}
