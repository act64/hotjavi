package com.hotjavi.Util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by ylei on 17-4-7.
 */
public class GsonHttpMessageConvertGenetic extends GsonHttpMessageConverter {

    public GsonHttpMessageConvertGenetic(){
        super();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(
                        new TypeToken<HashMap<String, Object>>(){}.getType(),
                        new JsonDeserializer<HashMap<String, Object>>() {
                            public HashMap<String, Object> deserialize(
                                    JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context) throws JsonParseException {

                                HashMap<String, Object> treeMap = new HashMap<String, Object>();
                                JsonObject jsonObject = json.getAsJsonObject();
                                Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
                                for (Map.Entry<String, JsonElement> entry : entrySet) {
                                    treeMap.put(entry.getKey(), entry.getValue());
                                }
                                return treeMap;
                            }
                        }).create();
        setGson(gson);
    }

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
