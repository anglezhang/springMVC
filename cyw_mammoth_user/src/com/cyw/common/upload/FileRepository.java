package com.cyw.common.upload;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

// TODO: Auto-generated Javadoc
/**
 * 本地文件存储.
 */
public class FileRepository implements ServletContextAware {
	
	/** The log. */
	private Logger log = LoggerFactory.getLogger(FileRepository.class);

	/**
	 * Store by ext.
	 *
	 * @param path the path
	 * @param ext the ext
	 * @param file the file
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String storeByExt(String path, String ext, MultipartFile file)
			throws IOException {
		String filename = UploadUtils.generateFilename(path, ext);
		File dest = new File(getRealPath(filename));
		dest = UploadUtils.getUniqueFile(dest);
		store(file, dest);
		return filename;
	}

	/**
	 * Store by filename.
	 *
	 * @param filename the filename
	 * @param file the file
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String storeByFilename(String filename, MultipartFile file)
			throws IOException {
		File dest = new File(getRealPath(filename));
		store(file, dest);
		return filename;
	}

	/**
	 * Store by ext.
	 *
	 * @param path the path
	 * @param ext the ext
	 * @param file the file
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String storeByExt(String path, String ext, File file)
			throws IOException {
		String filename = UploadUtils.generateFilename(path, ext);
		File dest = new File(getRealPath(filename));
		dest = UploadUtils.getUniqueFile(dest);
		store(file, dest);
		return filename;
	}

	/**
	 * Store by filename.
	 *
	 * @param filename the filename
	 * @param file the file
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String storeByFilename(String filename, File file)
			throws IOException {
		File dest = new File(getRealPath(filename));
		store(file, dest);
		return filename;
	}

	/**
	 * Store.
	 *
	 * @param file the file
	 * @param dest the dest
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void store(MultipartFile file, File dest) throws IOException {
		try {
			UploadUtils.checkDirAndCreate(dest.getParentFile());
			file.transferTo(dest);
		} catch (IOException e) {
			log.error("Transfer file error when upload file", e);
			throw e;
		}
	}

	/**
	 * Store.
	 *
	 * @param file the file
	 * @param dest the dest
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void store(File file, File dest) throws IOException {
		try {
			UploadUtils.checkDirAndCreate(dest.getParentFile());
			FileUtils.copyFile(file, dest);
		} catch (IOException e) {
			log.error("Transfer file error when upload file", e);
			throw e;
		}
	}

	/**
	 * Retrieve.
	 *
	 * @param name the name
	 * @return the file
	 */
	public File retrieve(String name) {
		return new File(ctx.getRealPath(name));
	}
	
	/**
	 * Gets the real path.
	 *
	 * @param name the name
	 * @return the real path
	 */
	private String getRealPath(String name){
		String realpath=ctx.getRealPath(name);
		if(realpath==null){
			realpath=ctx.getRealPath("/")+name;
		}
		return realpath;
	}

	/** The ctx. */
	private ServletContext ctx;

	/* (non-Javadoc)
	 * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	public void setServletContext(ServletContext servletContext) {
		this.ctx = servletContext;
	}
}
