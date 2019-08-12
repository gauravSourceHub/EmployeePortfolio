package com.ems.empApp;

import java.util.Map;

public class EmsRequest<T> {

	private Map<Object, Object> params;

	private T input;

	public Map<Object, Object> getParams() {
		return params;
	}

	public void setParams(Map<Object, Object> params) {
		this.params = params;
	}

	public T getInput() {
		return input;
	}

	public void setInput(T input) {
		this.input = input;
	}

}
