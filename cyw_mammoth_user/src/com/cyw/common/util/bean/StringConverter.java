/*
 * Copyright 2001-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cyw.common.util.bean;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;

import  com.cyw.common.util.DateUtil;

/**
 * <p>
 * Standard {@link Converter} implementation that converts an incoming String into a <code>java.lang.String</code>
 * object, optionally using a default value or throwing a {@link ConversionException} if a conversion error occurs.
 * </p>
 * 
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2007/08/14 14:59:24 $
 * @since 1.3
 */

public final class StringConverter implements Converter {

	// --------------------------------------------------------- Public Methods

	/**
	 * Convert the specified input object into an output object of the specified type.
	 * 
	 * @param type
	 *            Data type to which this value should be converted
	 * @param value
	 *            The input value to be converted
	 * 
	 * @exception ConversionException
	 *                if conversion cannot be performed successfully
	 */
	public Object convert(Class type, Object value) {
		if (value == null || StringUtils.isBlank((String) value)) {
			return null;
		} else if (value instanceof Date) {
			DateFormat df = new SimpleDateFormat(DateUtil.getDatePattern());
			if (value instanceof Timestamp) {
				df = new SimpleDateFormat(DateUtil.getDateTimePattern());
			}
			try {
				return df.format(value);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ConversionException("Error converting Date to String");
			}
		} else {
			return value.toString();
		}

	}
}
