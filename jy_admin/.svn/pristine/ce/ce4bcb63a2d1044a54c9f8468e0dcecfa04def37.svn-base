package com.zoomoor.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Tom
 */
public class MyFileUtils {
	
	public static List<File> getFiles(File folder){
		List<File>files=new ArrayList<File>();
		iterateFolder(folder, files);
		return files;
	}
	
	private static void iterateFolder(File folder,List<File>files)  {
		  File flist[] = folder.listFiles();
		  files.add(folder);
		  if (flist == null || flist.length == 0) {
			  files.add(folder) ;
		  }else{
			  for (File f : flist) {
			      if (f.isDirectory()) {
			           iterateFolder(f,files);
			      } else {
			          files.add(f) ;
			      }
			 }
		 }
	}

	public static String getValueByPropertyName(String propertiesFileNameWithoutPostfix,String propertyName) {
        String s="";
        ResourceBundle bundel = ResourceBundle.getBundle(propertiesFileNameWithoutPostfix);
        s=bundel.getString(propertyName);
        return s;
    }
}
