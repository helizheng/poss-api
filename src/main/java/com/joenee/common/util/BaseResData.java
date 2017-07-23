package com.joenee.common.util;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author sylar_
 *
 */
public class BaseResData<T> implements java.io.Serializable{
	public boolean success = true;
	public String msg;
	private T data;
	private Date systemTime; //系统时间，用处：前台根据系统时间和帖子时间计算时间显示
	public BaseResData(){
		
	}
	public BaseResData(T data){
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData(){
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	

	public Date getSystemTime() {
		this.systemTime = new Date();
		return systemTime;
	}
	public void setSystemTime(Date systemTime) {
		this.systemTime = systemTime;
	}
	/**
	 * 将当前对象中各个属性转成map
	 * @throws
	 * @throws Exception
	 */
	public Map<String,String> toMap() throws Exception{
		Map<String,String> fields = new HashMap<String,String>();
		Field[] origFields = this.getClass().getDeclaredFields();
		String fieldName = null;
		Object fieldValue = null;
		for (int i = 0; i < origFields.length; i++) {
			fieldName = origFields[i].getName();
			fieldValue = origFields[i].get(this);
			if (fieldValue == null) {
				continue;
			}
			if(fieldValue instanceof BaseResData ){
				Map<String,String> mapF = ((BaseResData)fieldValue).toMap();
				fields.putAll(mapF);
			}else{
				fields.put(fieldName, fieldValue.toString());
			}
		}
		return fields;
	}
}
