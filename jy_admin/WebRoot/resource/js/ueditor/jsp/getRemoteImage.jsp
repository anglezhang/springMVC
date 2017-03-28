    <%@ page language="java" pageEncoding="utf-8"%>
    <%@ page import="java.io.*"%>
    <%@ page import="java.net.*"%>
    <%@ page import="java.util.*"%>
    <%@ page import="com.zoomoor.common.util.Uploader" %>
    <%@ page import="java.util.Calendar" %>
    
    
    <%
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	String url = request.getParameter("upfile");
    	String state = "远程图片抓取成功！";
    	Calendar calen = Calendar.getInstance();
    	String filePath = "upload";
    	String[] arr = url.split("ue_separate_ue");
    	String[] outSrc = new String[arr.length];
    	for(int i=0;i<arr.length;i++){
    		//保存文件路径
    		String str = request.getSession().getServletContext().getRealPath("/");
			File f = new File(str);
			String savePath = f+ "/"+filePath+"/"+calen.get(Calendar.YEAR)+"/"+(calen.get(Calendar.MONTH)+1)+"/"+calen.get(Calendar.DATE)+"/";
    		//格式验证
    		String type = getFileType(arr[i]);
			if(type.equals("")){
				state = "图片类型不正确！";
				continue;
			}
    		String saveName = Long.toString(new Date().getTime())+type;
    		//大小验证
    		HttpURLConnection.setFollowRedirects(false); 
		    HttpURLConnection   conn   = (HttpURLConnection) new URL(arr[i]).openConnection(); 
		    if(conn.getContentType().indexOf("image")==-1){
		    	state = "请求地址头不正确";
		    	continue;
		    }
		    if(conn.getResponseCode() != 200){
		    	state = "请求地址不存在！";
		    	continue;
		    }
            File dir = new File(savePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
    		File savetoFile = new File(savePath +"/"+ saveName);
    		outSrc[i]=filePath +"/"+calen.get(Calendar.YEAR)+"/"+(calen.get(Calendar.MONTH)+1)+"/"+calen.get(Calendar.DATE)+"/"+ saveName;
    		try {
    			InputStream is = conn.getInputStream();
    			OutputStream os = new FileOutputStream(savetoFile);
    			int b;
    			while ((b = is.read()) != -1) {
    				os.write(b);
    			}
    			os.close();
    			is.close();
    			// 这里处理 inputStream
    		} catch (Exception e) {
    			e.printStackTrace();
    			System.err.println("页面无法访问");
    		}
    	}
   	String outstr = "";
   	for(int i=0;i<outSrc.length;i++){
   		outstr+=outSrc[i]+"ue_separate_ue";
   	}
   	outstr = outstr.substring(0,outstr.lastIndexOf("ue_separate_ue"));
   	response.getWriter().print("{'url':'" + outstr + "','tip':'"+state+"','srcUrl':'" + url + "'}" );
    %>
    <%!
    public String getFileType(String fileName){
    	String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
    	Iterator<String> type = Arrays.asList(fileType).iterator();
    	while(type.hasNext()){
    		String t = type.next();
    		if(fileName.endsWith(t)){
    			return t;
    		}
    	}
    	return "";
    }
    %>
