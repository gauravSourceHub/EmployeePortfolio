package com.ems.empApp;

public interface IEmsProcessor {
	public EmsResponse doProcess(EmsRequest emsRequest) throws EmsException;
}
