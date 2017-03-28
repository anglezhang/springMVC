package com.zoomoor.jy.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**   
 * 类名称：ExportUtil   
 * 类描述：导出   
 * 创建人：liuweixing
 * 创建时间：2015-8-12 上午9:57:16   
 * 修改人：liuweixing
 * 修改时间：2015-8-12 上午9:57:16   
 * 修改备注：   
 * @version       
 */ 
public class ExportUtil {
	/**  
	 * @Title: ExportXls  
	 * @Description: 导出excel
	 * @param request
	 * @param response
	 * @param sheetName sheet名称
	 * @param fileName 文件名称 
	 * @param cells 表头名称
	 * @param rowlists 内容list
	 * @throws Exception
	 * @throws FileNotFoundException
	 * @throws IOException void 
	 * @throws  
	 */  
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public  static void ExportXls(HttpServletRequest request,HttpServletResponse response,String sheetName,String fileName,String[] cells,List<List<String>> rowlists) throws Exception, FileNotFoundException,
			IOException {
		//创建一个webwook 对应一个excel 文件
		HSSFWorkbook wb = new HSSFWorkbook();
		//在webwook中添加一个sheet，对应excel文件中的sheet
		HSSFSheet sheet= wb.createSheet(sheetName);
		//在sheet中添加表头第0行
		HSSFRow row=sheet.createRow(0);
		//创建单元格，并设置表头，设置表头居中
		HSSFCellStyle title_style=wb.createCellStyle();
		title_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//设置内容样式
		HSSFCellStyle content_style=wb.createCellStyle();
		content_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		if(cells!=null){
			for(int i=0;i<cells.length;i++){
				//设置标题的宽度自适应
				sheet.setColumnWidth(i, cells[i].getBytes().length*2*256);
				HSSFCell cell=row.createCell(i);
				cell.setCellStyle(title_style);
				HSSFRichTextString title_text= new HSSFRichTextString(cells[i]);
				//写入标题
				cell.setCellValue(title_text);
			}
		}
		for(int i=0;i<rowlists.size();i++){
			row =sheet.createRow(i+1);
			List<String>  list= rowlists.get(i);
			//写入内容
			for(int j=0;j<list.size();j++){
				HSSFCell cell=row.createCell(j);
				//设置内容样式
				cell.setCellStyle(content_style);
				HSSFRichTextString content_text= new HSSFRichTextString(list.get(j));
				cell.setCellValue(content_text);
				
			}
		}
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename="+new String(fileName.getBytes("UTF-8"),"iso8859-1")+".xls");
		OutputStream  ops=response.getOutputStream();
		wb.write(ops);
		ops.flush();
		ops.close();
	}
}
