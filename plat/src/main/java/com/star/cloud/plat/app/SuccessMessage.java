package com.star.cloud.plat.app;

public class SuccessMessage {
	
	private String msg;
	
	public SuccessMessage(String msg) {
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
		sb.append("{\"success\":true,\"msg\":");
		sb.append("\"");
		sb.append(msg);
		sb.append("\"}");
		return sb.toString();
	}

}
