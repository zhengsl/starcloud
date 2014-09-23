package com.star.cloud.plat.app;

public class ErrorMessage {

	private String msg;
	
	public ErrorMessage(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String toJsonString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"success\":false,\"msg\":");
		sb.append("\"");
		sb.append(msg);
		sb.append("\"}");
		return sb.toString();
	}

}
