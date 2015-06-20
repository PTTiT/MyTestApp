package com.ttp.example.pycotestapp.object;

import com.google.gson.annotations.SerializedName;

public class GetNewsResponseObject {
	@SerializedName("responseData")
	private ResponseData responseData;

	@SerializedName("responseDetails")
	private String responseDetails;

	@SerializedName("responseStatus")
	private int responseStatus;

	public ResponseData getResponseData() {
		return responseData;
	}

	public void setResponseData(ResponseData responseData) {
		this.responseData = responseData;
	}

	public String getResponseDetails() {
		return responseDetails;
	}

	public void setResponseDetails(String responseDetails) {
		this.responseDetails = responseDetails;
	}

	public int getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(int responseStatus) {
		this.responseStatus = responseStatus;
	}

}
