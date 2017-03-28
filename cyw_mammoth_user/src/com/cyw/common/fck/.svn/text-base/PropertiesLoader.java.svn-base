package com.cyw.common.fck;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * Manages FCKeditor.Java properties files.
 * <p>
 * It manages/loads the properties files in the following order:
 * <ol>
 * <li>the default properties as defined <a
 * href="http://java.fckeditor.net/properties.html">here</a>,
 * <li>the user-defined properties (<code>fckeditor.properties</code>) if
 * present.
 * </ol>
 * This means that user-defined properties <em>override</em> default ones. In
 * the backend it utilizes the regular {@link Properties} class.
 * </p>
 * <p>
 * Moreover, you can set properties programmatically too but make sure to
 * override them <em>before</em> the first call of that specific property.
 * 
 * @version $Id: PropertiesLoader.java,v 1.1 2011/12/06 01:36:06 administrator Exp $
 */
public class PropertiesLoader {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory
			.getLogger(PropertiesLoader.class);
	
	/** The Constant DEFAULT_FILENAME. */
	private static final String DEFAULT_FILENAME = "default.properties";
	
	/** The Constant LOCAL_PROPERTIES. */
	private static final String LOCAL_PROPERTIES = "/fckeditor.properties";
	
	/** The properties. */
	private static Properties properties = new Properties();

	static {

		// 1. load library defaults
		InputStream in = PropertiesLoader.class
				.getResourceAsStream(DEFAULT_FILENAME);

		if (in == null) {
			logger.error("{} not found", DEFAULT_FILENAME);
			throw new RuntimeException(DEFAULT_FILENAME + " not found");
		} else {
			if (!(in instanceof BufferedInputStream))
				in = new BufferedInputStream(in);

			try {
				properties.load(in);
				in.close();
				logger.debug("{} loaded", DEFAULT_FILENAME);
			} catch (Exception e) {
				logger.error("Error while processing {}", DEFAULT_FILENAME);
				throw new RuntimeException("Error while processing "
						+ DEFAULT_FILENAME, e);
			}
		}

		// 2. load user defaults if present
		InputStream in2 = PropertiesLoader.class
				.getResourceAsStream(LOCAL_PROPERTIES);

		if (in2 == null) {
			logger.info("{} not found", LOCAL_PROPERTIES);
		} else {

			if (!(in2 instanceof BufferedInputStream))
				in2 = new BufferedInputStream(in2);

			try {
				properties.load(in2);
				in2.close();
				logger.debug("{} loaded", LOCAL_PROPERTIES);
			} catch (Exception e) {
				logger.error("Error while processing {}", LOCAL_PROPERTIES);
				throw new RuntimeException("Error while processing "
						+ LOCAL_PROPERTIES, e);
			}

		}
	}

	/**
	 * Searches for the property with the specified key in this property list.
	 *
	 * @param key the key
	 * @return the property
	 * @see Properties#getProperty(String)
	 */
	public static String getProperty(final String key) {
		return properties.getProperty(key);
	}

	/**
	 * Sets the property with the specified key in this property list.
	 *
	 * @param key the key
	 * @param value the value
	 * @see Properties#setProperty(String, String)
	 */
	public static void setProperty(final String key, final String value) {
		properties.setProperty(key, value);
	}

	/**
	 * Returns <code>connector.resourceType.file.path</code> property
	 *
	 * @return the file resource type path
	 */
	public static String getFileResourceTypePath() {
		return properties.getProperty("connector.resourceType.file.path");
	}

	/**
	 * Returns <code>connector.resourceType.flash.path</code> property
	 *
	 * @return the flash resource type path
	 */
	public static String getFlashResourceTypePath() {
		return properties.getProperty("connector.resourceType.flash.path");
	}

	/**
	 * Returns <code>connector.resourceType.image.path</code> property
	 *
	 * @return the image resource type path
	 */
	public static String getImageResourceTypePath() {
		return properties.getProperty("connector.resourceType.image.path");
	}

	/**
	 * Returns <code>connector.resourceType.media.path</code> property
	 *
	 * @return the media resource type path
	 */
	public static String getMediaResourceTypePath() {
		return properties.getProperty("connector.resourceType.media.path");
	}

	/**
	 * Returns <code>connector.resourceType.file.extensions.allowed</code>
	 * property
	 *
	 * @return the file resource type allowed extensions
	 */
	public static String getFileResourceTypeAllowedExtensions() {
		return properties
				.getProperty("connector.resourceType.file.extensions.allowed");
	}

	/**
	 * Returns <code>connector.resourceType.file.extensions.denied</code>
	 * property
	 *
	 * @return the file resource type denied extensions
	 */
	public static String getFileResourceTypeDeniedExtensions() {
		return properties
				.getProperty("connector.resourceType.file.extensions.denied");
	}

	/**
	 * Returns <code>connector.resourceType.flash.extensions.allowed</code>
	 * property
	 *
	 * @return the flash resource type allowed extensions
	 */
	public static String getFlashResourceTypeAllowedExtensions() {
		return properties
				.getProperty("connector.resourceType.flash.extensions.allowed");
	}

	/**
	 * Returns <code>connector.resourceType.flash.extensions.denied</code>
	 * property
	 *
	 * @return the flash resource type denied extensions
	 */
	public static String getFlashResourceTypeDeniedExtensions() {
		return properties
				.getProperty("connector.resourceType.flash.extensions.denied");
	}

	/**
	 * Returns <code>connector.resourceType.image.extensions.allowed</code>
	 * property
	 *
	 * @return the image resource type allowed extensions
	 */
	public static String getImageResourceTypeAllowedExtensions() {
		return properties
				.getProperty("connector.resourceType.image.extensions.allowed");
	}

	/**
	 * Returns <code>connector.resourceType.image.extensions.denied</code>
	 * property
	 *
	 * @return the image resource type denied extensions
	 */
	public static String getImageResourceTypeDeniedExtensions() {
		return properties
				.getProperty("connector.resourceType.image.extensions.denied");
	}

	/**
	 * Returns <code>connector.resourceType.media.extensions.allowed</code>
	 * property
	 *
	 * @return the media resource type allowed extensions
	 */
	public static String getMediaResourceTypeAllowedExtensions() {
		return properties
				.getProperty("connector.resourceType.media.extensions.allowed");
	}

	/**
	 * Returns <code>connector.resourceType.media.extensions.denied</code>
	 * property
	 *
	 * @return the media resource type denied extensions
	 */
	public static String getMediaResourceTypeDeniedExtensions() {
		return properties
				.getProperty("connector.resourceType.media.extensions.denied");
	}

	/**
	 * Returns <code>connector.userFilesPath</code> property
	 *
	 * @return the user files path
	 */
	public static String getUserFilesPath() {
		return properties.getProperty("connector.userFilesPath");
	}

	/**
	 * Returns <code>connector.userFilesAbsolutePath</code> property
	 *
	 * @return the user files absolute path
	 */
	public static String getUserFilesAbsolutePath() {
		return properties.getProperty("connector.userFilesAbsolutePath");
	}
}
