package com.cyw.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class MyFileUtils.
 *
 * @author Tom
 */
public class MyFileUtils {
	
	/**
	 * Gets the files.
	 *
	 * @param folder the folder
	 * @return the files
	 */
	public static List<File> getFiles(File folder){
		List<File>files=new ArrayList<File>();
		iterateFolder(folder, files);
		return files;
	}
	
	/**
	 * Iterate folder.
	 *
	 * @param folder the folder
	 * @param files the files
	 */
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
}
