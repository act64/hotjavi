package com.hotjavi.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/**
 * Created by ylei on 17-4-7.
 */
public class FastJsonHttpMessageConvertforGeneric extends FastJsonHttpMessageConverter4 {

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return reads(type,contextClass,inputMessage);
    }

    public<T extends Type,K> Object reads(final T type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream in = inputMessage.getBody();
        Class<K> aClass= (Class<K>) toClass(type);
        return JSON.parseObject(in, getFastJsonConfig().getCharset(), new TypeReference<K>(){}.getType(), getFastJsonConfig().getFeatures());
    }
    private static Class<?> toClass(Type o) {
        if (o instanceof GenericArrayType)
            return Array.newInstance(toClass(((GenericArrayType)o).getGenericComponentType()),
                    0)
                    .getClass();
        return (Class<?>)o;
    }

}
