package com.cyw.common.fck;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.LocaleResolver;

import com.cyw.common.web.springmvc.MessageResolver;

// TODO: Auto-generated Javadoc
/**
 * Provides access to localized messages (properties).
 * <p>
 * Localized messages are loaded for a particular locale from a HTTP request.
 * The locale is resolved by the current {@link LocaleResolver}
 * instance/singleton. If a locale or a bundle for a locale cannot be found,
 * default messages are used.
 * </p>
 * <p>
 * Note: Loaded messages are cached per locale, any subsequent call of the same
 * locale will be served by the cache instead of another resource bundle
 * retrieval.
 * </p>
 * 
 * @version $Id: LocalizedMessages.java,v 1.1 2011/12/06 01:36:06 administrator Exp $
 */
public class LocalizedMessages {
	
	/**
	 * Searches for the message with the specified key in this message list.
	 *
	 * @param request the request
	 * @param key the key
	 * @param args the args
	 * @return the message
	 * @see Properties#getProperty(String)
	 */
	private static String getMessage(HttpServletRequest request, String key,
			Object... args) {
		return MessageResolver.getMessage(request, key, args);
	}

	/**
	 * Returns localized <code>fck.editor.compatibleBrowser.yes</code> property.
	 *
	 * @param request the request
	 * @return the compatible browser yes
	 */
	public static String getCompatibleBrowserYes(HttpServletRequest request) {
		return getMessage(request, "fck.editor.compatibleBrowser.yes"); //$NON-NLS-1$
	}

	/**
	 *  Returns localized <code>fck.editor.compatibleBrowser.no</code> property.
	 *
	 * @param request the request
	 * @return the compatible browser no
	 */
	public static String getCompatibleBrowserNo(HttpServletRequest request) {
		return getMessage(request, "fck.editor.compatibleBrowser.no"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.fileUpload.enabled</code> property.
	 *
	 * @param request the request
	 * @return the file upload enabled
	 */
	public static String getFileUploadEnabled(HttpServletRequest request) {
		return getMessage(request, "fck.connector.fileUpload.enabled"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.fileUpload.disabled</code>
	 * property.
	 *
	 * @param request the request
	 * @return the file upload disabled
	 */
	public static String getFileUploadDisabled(HttpServletRequest request) {
		return getMessage(request, "fck.connector.fileUpload.disabled"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.file_renamed_warning</code>
	 * property.
	 *
	 * @param request the request
	 * @param newFileName            the new filename of the warning
	 * @return localized message with new filename
	 */
	public static String getFileRenamedWarning(HttpServletRequest request,
			String newFileName) {
		return getMessage(request,
				"fck.connector.fileUpload.file_renamed_warning", newFileName); //$NON-NLS-1$
	}

	/**
	 * Returns localized
	 * <code>fck.connector.fileUpload.invalid_file_type_specified</code>
	 * property.
	 *
	 * @param request the request
	 * @return the invalid file type specified
	 */
	public static String getInvalidFileTypeSpecified(HttpServletRequest request) {
		return getMessage(request,
				"fck.connector.fileUpload.invalid_file_type_specified"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.fileUpload.write_error</code>
	 * property.
	 *
	 * @param request the request
	 * @return the file upload write error
	 */
	public static String getFileUploadWriteError(HttpServletRequest request) {
		return getMessage(request, "fck.connector.fileUpload.write_error"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.getResources.enabled</code>
	 * property.
	 *
	 * @param request the request
	 * @return the gets the resources enabled
	 */
	public static String getGetResourcesEnabled(HttpServletRequest request) {
		return getMessage(request, "fck.connector.getResources.enabled"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.getResources.disabled</code>
	 * property.
	 *
	 * @param request the request
	 * @return the gets the resources disabled
	 */
	public static String getGetResourcesDisabled(HttpServletRequest request) {
		return getMessage(request, "fck.connector.getResources.disabled"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.getResources.read_error</code>
	 * property.
	 *
	 * @param request the request
	 * @return the gets the resources read error
	 */
	public static String getGetResourcesReadError(HttpServletRequest request) {
		return getMessage(request, "fck.connector.getResources.read_error"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.createFolder.enabled</code>
	 * property.
	 *
	 * @param request the request
	 * @return the creates the folder enabled
	 */
	public static String getCreateFolderEnabled(HttpServletRequest request) {
		return getMessage(request, "fck.connector.createFolder.enabled"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.createFolder.disabled</code>
	 * property.
	 *
	 * @param request the request
	 * @return the creates the folder disabled
	 */
	public static String getCreateFolderDisabled(HttpServletRequest request) {
		return getMessage(request, "fck.connector.createFolder.disabled"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.invalid_command_specified</code>
	 * property.
	 *
	 * @param request the request
	 * @return the invalid command specified
	 */
	public static String getInvalidCommandSpecified(HttpServletRequest request) {
		return getMessage(request, "fck.connector.invalid_command_specified"); //$NON-NLS-1$
	}

	/**
	 * Returns localized
	 * <code>fck.connector.createFolder.folder_already_exists_error</code>
	 * property.
	 *
	 * @param request the request
	 * @return the folder already exists error
	 */
	public static String getFolderAlreadyExistsError(HttpServletRequest request) {
		return getMessage(request,
				"fck.connector.createFolder.folder_already_exists_error"); //$NON-NLS-1$
	}

	/**
	 * Returns localized
	 * <code>fck.connector.createFolder.invalid_new_folder_name_specified</code>
	 * property.
	 *
	 * @param request the request
	 * @return the invalid new folder name specified
	 */
	public static String getInvalidNewFolderNameSpecified(
			HttpServletRequest request) {
		return getMessage(request,
				"fck.connector.createFolder.invalid_new_folder_name_specified"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.createFolder.write_error</code>
	 * property.
	 *
	 * @param request the request
	 * @return the creates the folder write error
	 */
	public static String getCreateFolderWriteError(HttpServletRequest request) {
		return getMessage(request, "fck.connector.createFolder.write_error"); //$NON-NLS-1$
	}

	/**
	 * Returns localized
	 * <code>fck.connector.invalid_resource_type_specified</code> property.
	 *
	 * @param request the request
	 * @return the invalid resouce type specified
	 */
	public static String getInvalidResouceTypeSpecified(
			HttpServletRequest request) {
		return getMessage(request,
				"fck.connector.invalid_resource_type_specified"); //$NON-NLS-1$
	}

	/**
	 * Returns localized
	 * <code>fck.connector.invalid_current_folder_specified</code> property.
	 *
	 * @param request the request
	 * @return the invalid current folder specified
	 */
	public static String getInvalidCurrentFolderSpecified(
			HttpServletRequest request) {
		return getMessage(request,
				"fck.connector.invalid_current_folder_specified"); //$NON-NLS-1$
	}

}
