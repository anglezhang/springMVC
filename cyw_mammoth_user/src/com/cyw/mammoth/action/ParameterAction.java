package com.cyw.mammoth.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyw.mammoth.service.ParameterSvc;

@Controller
public class ParameterAction {
	@Autowired
	ParameterSvc paramSvc;
	@RequestMapping("/night/state.do")
	 public String getNightAuditState(){
		boolean s=paramSvc.getNightAuditState();
		System.out.println(s);
		return "";
	 }
	
	@RequestMapping("/hote/date.do")
	 public String GetHotelDate(){
		Date s=paramSvc.GetHotelDate();
		System.out.println(s);
		return "";
	 }
	@RequestMapping("/ip/state.do")
	 public String GetIPState(){
		boolean s=paramSvc.GetIPFlag();
		System.out.println(s);
		return "";
	 }
	@RequestMapping("/image/upload.do")	
	public String uploadImage(){
		String path = "d:/QQ.png";  
	    File file = new File(path);  
	    FileInputStream fis;
	    try  
	    {  
	        fis = new FileInputStream(file);  
	        paramSvc.uploadImage(fis); 
	    }  
	    catch (IOException e)  
	    {  
	        e.printStackTrace();  
	    } 
	    return "";
		
	}
	@RequestMapping("/image/input.do")	
	public String getImageByte(){
		//String sql="select fileimage from testImage where id=20";
		//String[] columnNames= new String[]{"fileimage"};
		InputStream in=null;
		in=paramSvc.getImageByte("Citizen","id_bmp","where Check_ID=40022");
		/*	OutputStream out=null;
			try {
				out=new FileOutputStream("d:/ffxx.png");
				int ch=0;
				while((ch=in.read())!=-1){
					out.write(ch);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					out.flush();  
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
		return "";
	}
}
