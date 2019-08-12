package com.ems.empApp;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class EmsException extends Exception implements ExceptionMapper<EmsException> {

	protected int statusCode;
	protected String devMessage;
	protected String clientMessage;

	public EmsException() {

	}

	public EmsException(int statusCode, String devMessage, String clientMessage) {
		this.statusCode = statusCode;
		this.devMessage = devMessage;
		this.clientMessage = clientMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getDevMessage() {
		return devMessage;
	}

	public void setDevMessage(String devMessage) {
		this.devMessage = devMessage;
	}

	public String getClientMessage() {
		return clientMessage;
	}

	public void setClientMessage(String clientMessage) {
		this.clientMessage = clientMessage;
	}

	@Override
	public Response toResponse(EmsException exception) {
		// TODO Auto-generated method stub
		return null;
	}

}
