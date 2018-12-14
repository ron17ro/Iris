package com.helpdesk.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum RequestType {
	Software(1), Hardware(2);
	
	private static final Map<Integer,RequestType> lookup  = new HashMap<Integer,RequestType>();

	static {
	    for(RequestType w : EnumSet.allOf(RequestType.class))
	         lookup.put(w.getCode(), w);
	}
	
	private int code;
	
	private RequestType(int code) {
	    this.code = code;
	}
	
	public int getCode() { return code; }
	
	public static RequestType get(int code) { 
	    return lookup.get(code); 
	}
}
