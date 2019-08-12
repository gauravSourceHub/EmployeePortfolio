package com.ems.empApp;

import io.swagger.annotations.ApiModelProperty;

public class ResponseMetaData {

	private long responseTime;
	private int statusCode;
	private String statusDescription;
	private ErrorMessage errorMessage;
	
	@ApiModelProperty(value="Response Time")
	public long getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}
	
	@ApiModelProperty(value="Status Code")
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	@ApiModelProperty(value="Status Description")
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	
	@ApiModelProperty(value="Error Message")
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
