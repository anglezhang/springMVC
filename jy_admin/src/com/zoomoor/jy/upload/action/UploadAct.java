package com.zoomoor.jy.upload.action;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zoomoor.common.util.DateUtils;
import com.zoomoor.common.web.ResponseUtils;

@SuppressWarnings("unchecked")
@Controller
public class UploadAct {
	
	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        
        String fileUrl = null;
        String filePath = "/upload/";
        HttpSession session = request.getSession();
        String realPath = session.getServletContext().getRealPath("/upload");
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
        	// 获取MulipartFile对象
        	MultipartFile file = entity.getValue();
            // 上传文件名
            String origName = file.getOriginalFilename();
            String ext = FilenameUtils.getExtension(origName).toLowerCase(Locale.ENGLISH);
            
            String filename = DateUtils.getCurrentTime()+ "." + ext;
            String allPath = request.getScheme()+"://"+request.getServerName();
        	if(request.getServerPort()!=80){
        		allPath+=":"+request.getServerPort();
        	}
            // 加上访问地址
            fileUrl =allPath+request.getContextPath() + filePath + filename;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, filename));
        }

		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		json.put("url", fileUrl);
		
		ResponseUtils.renderJson(response, json.toJSONString());
	}
}
