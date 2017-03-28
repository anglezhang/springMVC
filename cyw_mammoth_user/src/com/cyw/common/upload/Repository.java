package com.cyw.common.upload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// TODO: Auto-generated Javadoc
/**
 * 文件存储接口.
 */
public interface Repository {
	
	/**
	 * Store by ext.
	 *
	 * @param ext the ext
	 * @param in the in
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String storeByExt(String ext, InputStream in) throws IOException;

	/**
	 * Store by name.
	 *
	 * @param name the name
	 * @param in the in
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String storeByName(String name, InputStream in) throws IOException;

	/**
	 * Retrieve.
	 *
	 * @param name the name
	 * @param out the out
	 * @return true, if successful
	 */
	public boolean retrieve(String name, OutputStream out);
}
