package com.hotjavi.Util;

import com.google.gson.reflect.TypeToken;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by ylei on 17-4-7.
 */
public class GsonHttpMessageConvertGenetic extends GsonHttpMessageConverter {

    @Override
    protected TypeToken<?> getTypeToken(Type type) {
        if (type instanceof ParameterizedType){
            ParameterizedType parameterizedType=(ParameterizedType)type;
            return TypeToken.getParameterized(((ParameterizedType) type).getRawType(),((ParameterizedType) type).getActualTypeArguments());
        }else if (type instanceof GenericArrayType){
            GenericArrayType arrayType=(GenericArrayType)type;
            return TypeToken.getArray(arrayType.getGenericComponentType());
        }
            return super.getTypeToken(type);

    }
}
