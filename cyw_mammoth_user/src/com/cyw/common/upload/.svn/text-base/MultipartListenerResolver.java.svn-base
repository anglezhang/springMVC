package com.cyw.common.upload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

// TODO: Auto-generated Javadoc
/**
 * The Class MultipartListenerResolver.
 */
@SuppressWarnings({ "unchecked" })   
public class MultipartListenerResolver extends CommonsMultipartResolver {
	
	/* (non-Javadoc)
	 * @see org.springframework.web.multipart.commons.CommonsMultipartResolver#parseRequest(javax.servlet.http.HttpServletRequest)
	 */
	@Override  
    protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {  
        String encoding = determineEncoding(request);  
        FileUpload fileUpload = prepareFileUpload(encoding);  
        //设置监听器  
        fileUpload.setProgressListener(new FileUploadProgressListener(request.getSession()));  
        try {  
            List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);  
            return parseFileItems(fileItems, encoding);  
        } catch (FileUploadBase.SizeLimitExceededException ex) {  
            throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);  
        } catch (FileUploadException ex) {  
            throw new MultipartException("Could not parse multipart servlet request", ex);  
        }  
    }  
}
