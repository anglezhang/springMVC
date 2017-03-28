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
 * Standard {@link Converter} implementation that converts an incoming String
 * into a <code>java.sql.Date</code> object, optionally using a default value or
 * throwing a {@link ConversionException} if a conversion error occurs.
 * </p>
 * 
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2007/08/14 14:59:24 $
 * @since 1.3
 */

public class DateConverter implements Converter {

    public Object convert(Class type, Object value) {
        if (value == null) {
            return null;
        } else if (type == Timestamp.class) {
            return convertToDate(type, value, DateUtil.getDateTimePattern());
        } else if (type == Date.class) {
            return convertToDate(type, value, DateUtil.getDatePattern());
        }

        throw new ConversionException("Could not convert " + value.getClass().getName() + " to " + type.getName());
    }

    protected Object convertToDate(Class type, Object value, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        if (value instanceof String) {
            try {
                if (StringUtils.isEmpty(value.toString())) {
                    return null;
                }
                Date date = df.parse((String) value);
                if (type.equals(Timestamp.class)) {
                    return new Timestamp(date.getTime());
                }
                return date;
            } catch (Exception pe) {
                pe.printStackTrace();
                throw new ConversionException("Error converting String to Date");
            }
        } else if (value instanceof Timestamp) {
            try {
                Timestamp timestamp = (Timestamp) value;
                Date date = new Date(timestamp.getTime());
                return date;
            } catch (Exception pe) {
                pe.printStackTrace();
                throw new ConversionException("Error converting Timestamp to Date");
            }
        } else if (value instanceof java.sql.Date) {
            try {
                java.sql.Date sqlDate = (java.sql.Date) value;
                return new Date(sqlDate.getTime());
            } catch (Exception pe) {
                pe.printStackTrace();
                throw new ConversionException("Error converting String to Date");
            }
        } else if (value instanceof Date) {
            return value;
        }
        throw new ConversionException("Could not convert " + value.getClass().getName() + " to " + type.getName());
    }
}
