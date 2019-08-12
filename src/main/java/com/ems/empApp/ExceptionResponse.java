package com.ems.empApp;

import io.swagger.annotations.ApiModelProperty;

public class ExceptionResponse {

	private ResponseMetaData responseStatus;

	@ApiModelProperty(value="Response Status")
	public ResponseMetaData getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseMetaData responseStatus) {
		this.responseStatus = responseStatus;
	}
	
	
}
