package com.my.q.three.obj;

import java.util.ArrayList;
import java.util.List;

/**
 * Placeholder to hold class and methods
 *
 */
public class ClassMethods {

	private String className;
	private List<MethodParams> methodParamsList;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<MethodParams> getMethodParamsList() {
		return methodParamsList;
	}
	public void setMethodParamsList(List<MethodParams> methodParamsList) {
		this.methodParamsList = methodParamsList;
	}
	public void setMethodParams(MethodParams mp) {
		if(methodParamsList==null) {
			methodParamsList = new ArrayList<MethodParams>();
		}
		methodParamsList.add(mp);
	}
}
