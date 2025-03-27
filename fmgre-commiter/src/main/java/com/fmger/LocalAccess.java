package com.fmger;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.sun.jna.platform.win32.OaIdl.DATE;

public class LocalAccess {


	private static Gson gson = new GsonBuilder()
//			.registerTypeAdapter(AjaxResult.class, new DeserializerAjaxResult())
//			.registerTypeAdapter(DATE.class, new LocalAccess.DeserializerDate())
			.setDateFormat("yyy-MM-dd HH:mm:ss")
			.create();
	private static Map<Class, Object> map = new HashMap<Class, Object>();
	
	public static <T> T get(Class<T> t) {
		if(t.equals(Gson.class)) {
			return (T) gson;
		}
		return getObj(t);
	}
	
	private static <T> T getObj(Class<T> t) {
		Object o = map.get(t);
		if(o == null) {
			synchronized(map) {
				o = map.get(t);
				if(o != null) return (T)o;
				try {
					Constructor<T> m = t.getConstructor();
					o = m.newInstance();
				} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				}
			}
		}
		return (T)o;
	}
	
	static class DeserializerDate implements JsonDeserializer {

		@Override
		public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
