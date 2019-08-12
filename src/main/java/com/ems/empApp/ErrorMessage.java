package com.ems.empApp;

import io.swagger.annotations.ApiModelProperty;

public class ErrorMessage {

	String devMessage;
	String clientMessage;
	
	public ErrorMessage(String devMessage, String clientMessage) {
		this.devMessage = devMessage;
		this.clientMessage = clientMessage;
	}
	
	@ApiModelProperty(value="Dev Message")
	public String getDevMessage() {
		return devMessage;
	}
	public void setDevMessage(String devMessage) {
		this.devMessage = devMessage;
	}
	
	@ApiModelProperty(value="Client Message")
	public String getClientMessage() {
		return clientMessage;
	}
	public void setClientMessage(String clientMessage) {
		this.clientMessage = clientMessage;
	}
	
	
}
