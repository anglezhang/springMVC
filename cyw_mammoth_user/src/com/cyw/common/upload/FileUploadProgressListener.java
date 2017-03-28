package com.cyw.common.upload;

import java.text.NumberFormat;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving fileUploadProgress events.
 * The class that is interested in processing a fileUploadProgress
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addFileUploadProgressListener<code> method. When
 * the fileUploadProgress event occurs, that object's appropriate
 * method is invoked.
 *
 * @see FileUploadProgressEvent
 */
public class FileUploadProgressListener implements ProgressListener {
	
	/** The mega bytes. */
	private double megaBytes = -1;  
	
	/** The session. */
	private HttpSession session;  
	  
    /**
     * Instantiates a new file upload progress listener.
     *
     * @param session the session
     */
    public FileUploadProgressListener(HttpSession session) {  
        this.session = session;  
    }  
  
        //pBytesRead  已经上传的大小  
        //pContentLength   文件总大小  
    /* (non-Javadoc)
         * @see org.apache.commons.fileupload.ProgressListener#update(long, long, int)
         */
        public void update(long pBytesRead, long pContentLength, int pItems) {  
        double mBytes = pBytesRead / 1000000;  
        double total=pContentLength/1000000;  
           if (megaBytes == mBytes) {  
               return;  
           }  
//           System.out.println("total====>"+total);  
//           System.out.println("mBytes====>"+mBytes);  
           megaBytes = mBytes;  
//           System.out.println("megaBytes====>"+megaBytes);  
//           System.out.println("We are currently reading item " + pItems);  
           if (pContentLength == -1) {  
//               System.out.println("So far, " + pBytesRead + " bytes have been read.");  
           } else {  
//               System.out.println("So far, " + pBytesRead + " of " + pContentLength+ " bytes have been read.");  
              double read=(mBytes/total);  
              NumberFormat nf=NumberFormat.getPercentInstance();  
//              System.out.println("read===>"+nf.format(read));//生成读取的百分比 并放入session中  
              session.setAttribute("progress", nf.format(read));  
           }  
    }  
}
