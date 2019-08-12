package com.ems.empApp;

public class EmsResponse<T> {

	private  ResponseMetaData responseMetaData;
	private T responseData;

	public T getResponseData() {
		return responseData;
	}

	public void setResponseData(T responseData) {
		this.responseData = responseData;
	}

	public ResponseMetaData getResponseMetaData() {
		return responseMetaData;
	}

	public void setResponseMetaData(ResponseMetaData responseMetaData) {
		this.responseMetaData = responseMetaData;
	}
	
	
}
