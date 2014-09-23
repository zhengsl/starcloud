package com.star.cloud.provision.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.star.cloud.provision.model.StarCloudProvisionException;

public class SaltRunHelper {

	private final String saltStackIp;
	private final String saltStackPort;

	private final String saltUserName;
	private final String saltPassword;

	private final String runUrl;
	
	private ISaltCallback defaultCallback;
	
	public SaltRunHelper(String saltStackIp, String saltStackPort,
			String saltUserName, String saltPassword, ISaltCallback callback) {
		this.saltStackIp = saltStackIp;
		this.saltStackPort = saltStackPort;
		this.saltUserName = saltUserName;
		this.saltPassword = saltPassword;
		this.runUrl = "http://" + this.saltStackIp + ":" + this.saltStackPort + "/run";
		this.defaultCallback = callback;
	}
	
	protected HttpPost newHttpPost(String target, String fun, String arg, String exprForm) {
		HttpPost post = new HttpPost(this.runUrl);
		post.addHeader("Accept", "application/json");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("client", "local"));
		nvps.add(new BasicNameValuePair("tgt", target));
		nvps.add(new BasicNameValuePair("fun", fun));
		nvps.add(new BasicNameValuePair("username", this.saltUserName));
		nvps.add(new BasicNameValuePair("password", this.saltPassword));
		nvps.add(new BasicNameValuePair("eauth", "pam"));
		if (arg != null)
			nvps.add(new BasicNameValuePair("arg", arg));
		if (exprForm != null)
			nvps.add(new BasicNameValuePair("expr_form", exprForm));
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e) {
			throw new StarCloudProvisionException(e);
		}
		return post;
	}
	
	protected Object runFunc(String target, String fun, String arg, String exprForm, ISaltCallback callback) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = newHttpPost(target, fun, arg, exprForm);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(post);
			String jsonResponse = EntityUtils.toString(response.getEntity());
			if (callback != null)
				return callback.afterResponse(jsonResponse);
			else
				return defaultCallback.afterResponse(jsonResponse);
		} catch (Exception e) {
			throw new StarCloudProvisionException(e);
		} finally {
			try {
				response.close();
				httpClient.close();
			} catch (IOException e) {
				throw new StarCloudProvisionException(e);
			}
		}
	}

}
