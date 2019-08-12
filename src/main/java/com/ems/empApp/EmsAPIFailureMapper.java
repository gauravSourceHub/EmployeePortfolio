package com.ems.empApp;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EmsAPIFailureMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		ExceptionResponse excepRes = new ExceptionResponse();
		ResponseMetaData resMetaData = new ResponseMetaData();

		EmsException emsException = new EmsException();
		if (exception instanceof EmsException) {
			emsException = (EmsException) exception;
		}

		ErrorMessage errorMess = new ErrorMessage(emsException.getDevMessage(), emsException.getClientMessage());
		resMetaData.setErrorMessage(errorMess);
		resMetaData.setStatusCode(emsException.getStatusCode());
		resMetaData.setStatusDescription("Failed");
		excepRes.setResponseStatus(resMetaData);
		if (emsException.getStatusCode() == Response.Status.BAD_REQUEST.getStatusCode()) {
			return Response.status(Response.Status.BAD_REQUEST).entity(excepRes).type(MediaType.APPLICATION_JSON_TYPE)
					.build();
		} else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(excepRes)
					.type(MediaType.APPLICATION_JSON_TYPE).build();
		}
	}
}
