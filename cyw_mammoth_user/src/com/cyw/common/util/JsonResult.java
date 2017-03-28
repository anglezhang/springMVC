package com.cyw.common.util;

import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

// TODO: Auto-generated Javadoc
/**
 * The Class JsonResult.
 *
 * @author holly
 */
public class JsonResult{
	
	/** The code. */
	private int code;
	
	/** The result. */
	private Object result;

	/**
	 * Instantiates a new json result.
	 */
	public JsonResult() {
		this(10, null);
	}

	/**
	 * Instantiates a new json result.
	 *
	 * @param code the code
	 * @param object the object
	 */
	public JsonResult(int code, Object object) {
		this.code = code;
		this.result = object;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
		return gsonBuilder.serializeNulls().create().toJson(this);
//		TargetStrategy ts = new TargetStrategy(JsonResult.class);  		
//		Gson gsonBuilder = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).serializeNulls().create();
//		gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
//		gsonBuilder.setExclusionStrategies(new SpecificClassExclusionStrategy(null, Object.class)).create();
//		return gsonBuilder.toJson(ts);
		//long st=System.currentTimeMillis();
//		String returnValue=JSONObject.toJSONString(this);
		//long se=System.currentTimeMillis();
		//System.out.println(se-st);
//		return returnValue;
//		new JsonGenerator().
//		return JSONObject.toString(String.valueOf(this.code), this.result);
//		GsonBuilder gsonBuilder = new GsonBuilder();
//		gsonBuilder.setDateFormat("yyyy-MM-dd");  
//		gsonBuilder.registerTypeAdapter(Timestamp.class, new JsonSerializer<Timestamp>(){//Timestamp时间格式需要带时分秒  
//		        @Override  
//		        public JsonElement serialize(Timestamp date, Type type,  
//		                JsonSerializationContext arg2) {  
//		                return new JsonPrimitive(DateUtils.getDate(date));
//		        }
//
//				});  
//		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");  
//		gsonBuilder.registerTypeAdapter(Timestamp.class,new TimestampTypeAdapter());  
//		gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
//		gsonBuilder.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//		Type type = new TypeToken<JsonResult>(){}.getType();
//		Gson gson=new GsonBuilder().registerTypeAdapter(ProjectListVO.class, new JsonSerializer<ProjectListVO>() {
//            @Override
//            public JsonElement serialize(ProjectListVO src, Type typeOfSrc,
//                    JsonSerializationContext context) {
//                JsonObject o=new JsonObject();
//                //o.addProperty("pid",  src.);
//                //o.addProperty("name", src.getName());
//                return o;
//            } 
//        }).create();
//		return gson.toJson(this);
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	public void setResult(Object result) {
		this.result = result;
	}

}
