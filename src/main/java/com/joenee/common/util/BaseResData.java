package com.joenee.common.util;

public class BaseResData<T> implements java.io.Serializable{
	public boolean success = true;
	public String code;
	public String msg;
	private T data;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
