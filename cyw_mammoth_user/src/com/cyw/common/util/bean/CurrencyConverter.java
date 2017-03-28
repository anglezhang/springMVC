package com.cyw.common.util.bean;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is converts a Double to a double-digit String (and vise-versa) by BeanUtils when copying properties.
 * Registered for use in BaseAction.
 * 
 * <p>
 * <a href="CurrencyConverter.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class CurrencyConverter implements Converter {
    /** Log日志 */
    private static final Logger logger = LoggerFactory.getLogger(CurrencyConverter.class);

	protected final DecimalFormat formatter = new DecimalFormat("###,###.00");

	/**
	 * Convert a String to a Double and a Double to a String
	 * 
	 * @param type
	 *            the class type to output
	 * @param value
	 *            the object to convert
	 * @return object the converted object (Double or String)
	 */
	public final Object convert(final Class type, final Object value) {
		// for a null value, return null
		if (value == null) {
			return null;
		} else {
			if (value instanceof String) {
				if (logger.isDebugEnabled()) {
					logger.debug("value (" + value + ") instance of String");
				}

				try {
					if (StringUtils.isBlank(String.valueOf(value))) {
						return null;
					}

					if (logger.isDebugEnabled()) {
						logger.debug("converting '" + value + "' to a decimal");
					}

					// formatter.setDecimalSeparatorAlwaysShown(true);
					Number num = formatter.parse(String.valueOf(value));

					return new BigDecimal(num.doubleValue());
				} catch (ParseException pe) {
					pe.printStackTrace();
				}
			} else if (value instanceof Double) {
				if (logger.isDebugEnabled()) {
					logger.debug("value (" + value + ") instance of Double");
					logger.debug("returning double: " + formatter.format(value));
				}

				return formatter.format(value);
			}
		}

		throw new ConversionException("Could not convert " + value + " to " + type.getName() + "!");
	}
}
