package com.cyw.common.util;

import java.io.IOException;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

// TODO: Auto-generated Javadoc
/**
 * This TypeAdapter unproxies Hibernate proxied objects, and serializes them
 * through the registered (or default) TypeAdapter of the base class.
 */
public class HibernateProxyTypeAdapter extends TypeAdapter<HibernateProxy> {

    /** The Constant FACTORY. */
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        @Override
        @SuppressWarnings("unchecked")
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            return (HibernateProxy.class.isAssignableFrom(type.getRawType()) ? (TypeAdapter<T>) new HibernateProxyTypeAdapter(gson) : null);
        }
    };
    
    /** The context. */
    private final Gson context;

    /**
     * Instantiates a new hibernate proxy type adapter.
     *
     * @param context the context
     */
    private HibernateProxyTypeAdapter(Gson context) {
        this.context = context;
    }

    /* (non-Javadoc)
     * @see com.google.gson.TypeAdapter#read(com.google.gson.stream.JsonReader)
     */
    @Override
    public HibernateProxy read(JsonReader in) throws IOException {
        throw new UnsupportedOperationException("Not supported");
    }

    /* (non-Javadoc)
     * @see com.google.gson.TypeAdapter#write(com.google.gson.stream.JsonWriter, java.lang.Object)
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void write(JsonWriter out, HibernateProxy value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        // Retrieve the original (not proxy) class
        Class<?> baseType = Hibernate.getClass(value);
        // Get the TypeAdapter of the original class, to delegate the serialization
        TypeAdapter delegate = context.getAdapter(TypeToken.get(baseType));
        // Get a filled instance of the original class
        Object unproxiedValue = ((HibernateProxy) value).getHibernateLazyInitializer()
                .getImplementation();
        // Serialize the value
        delegate.write(out, unproxiedValue);
    }
}

