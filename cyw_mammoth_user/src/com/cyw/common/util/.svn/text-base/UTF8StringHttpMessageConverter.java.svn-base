package com.cyw.common.util;



import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.FileCopyUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class UTF8StringHttpMessageConverter.
 */
public class UTF8StringHttpMessageConverter extends 
        AbstractHttpMessageConverter<String> { 
 
    /** The Constant DEFAULT_CHARSET. */
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8"); 
    
    /** The available charsets. */
    private final List<Charset> availableCharsets; 
 
    /**
     * Instantiates a new UT f8 string http message converter.
     */
    public UTF8StringHttpMessageConverter() { 
        this(DEFAULT_CHARSET); 
    } 
 
    /**
     * Instantiates a new UT f8 string http message converter.
     *
     * @param defaultCharset the default charset
     */
    public UTF8StringHttpMessageConverter(Charset defaultCharset) { 
        super(new MediaType("text", "plain", defaultCharset), MediaType.ALL); 
        this.availableCharsets = new ArrayList<Charset>(Charset 
                .availableCharsets().values()); 
    } 
 
    /* (non-Javadoc)
     * @see org.springframework.http.converter.AbstractHttpMessageConverter#supports(java.lang.Class)
     */
    @Override 
    protected boolean supports(Class<?> clazz) { 
        return String.class.equals(clazz); 
    } 
 
    /* (non-Javadoc)
     * @see org.springframework.http.converter.AbstractHttpMessageConverter#readInternal(java.lang.Class, org.springframework.http.HttpInputMessage)
     */
    @Override 
    protected String readInternal(Class<? extends String> clazz, 
            HttpInputMessage inputMessage) throws IOException, 
            HttpMessageNotReadableException { 
        MediaType contentType = inputMessage.getHeaders().getContentType(); 
        Charset charset = contentType.getCharSet() != null ? contentType 
                .getCharSet() : DEFAULT_CHARSET; 
        return FileCopyUtils.copyToString(new InputStreamReader(inputMessage 
                .getBody(), charset)); 
    } 
 
    /* (non-Javadoc)
     * @see org.springframework.http.converter.AbstractHttpMessageConverter#writeInternal(java.lang.Object, org.springframework.http.HttpOutputMessage)
     */
    @Override 
    protected void writeInternal(String t, HttpOutputMessage outputMessage) 
            throws IOException, HttpMessageNotWritableException { 
        MediaType contentType = outputMessage.getHeaders().getContentType(); 
        Charset charset = contentType.getCharSet() != null ? contentType 
                .getCharSet() : DEFAULT_CHARSET; 
        FileCopyUtils.copy(t, new OutputStreamWriter(outputMessage.getBody(), 
                charset)); 
    } 
 
    /**
     * Gets the accepted charsets.
     *
     * @return the accepted charsets
     */
    protected List<Charset> getAcceptedCharsets() { 
        return this.availableCharsets; 
    } 
     
    /* (non-Javadoc)
     * @see org.springframework.http.converter.AbstractHttpMessageConverter#getContentLength(java.lang.Object, org.springframework.http.MediaType)
     */
    @Override 
    protected Long getContentLength(String s, MediaType contentType) { 
        if (contentType != null && contentType.getCharSet() != null) { 
            Charset charset = contentType.getCharSet(); 
            try { 
                return (long) s.getBytes(charset.name()).length; 
            } catch (UnsupportedEncodingException ex) {                 
                throw new InternalError(ex.getMessage()); 
            } 
        } else { 
            return null; 
        } 
    } 
} 