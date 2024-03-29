package com.app.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.app.model.Admin;





public class HealperAdminProfile {

	
	
    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();
        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }
    }
    
    
    public static List<Admin> convertExcelToListOfAdmins(InputStream is) {
        List<Admin> list = new ArrayList<>();
        try {
        		XSSFWorkbook workbook = new XSSFWorkbook(is);
        		XSSFSheet sheet = workbook.getSheet("Sheet1");
        		int rowNumber = 0;
        		Iterator<Row> iterator = sheet.iterator();
        		while (iterator.hasNext()) {
                Row row = iterator.next();
                if (rowNumber == 0) 
                {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();
                int sid = 0;
                Admin am = new Admin();

                while (cells.hasNext()) 
                {
                		Cell cell = cells.next();
                		switch(sid)
                		{
                		case 0:
                			am.setAdminId((int)cell.getNumericCellValue()); 
                			break;
                		
                		case 1:
                			am.setAdminName(cell.getStringCellValue());
                			break;
                			
                		case 2:
                			am.setPassword(cell.getStringCellValue());
                			break;
                		
                		case 3:
                			am.setRole(cell.getStringCellValue());
                			break;       
                               	
                		case 4:
                			am.setEmail(cell.getStringCellValue());
                			break;  
                			
                		default:
                			break;
                		}
                		sid++;
                }
                list.add(am);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    } 
    
    
}
