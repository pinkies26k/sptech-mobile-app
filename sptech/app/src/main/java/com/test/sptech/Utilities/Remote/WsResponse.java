package com.test.sptech.Utilities.Remote;

import org.json.JSONObject;

public class WsResponse {

    private boolean status;
    private String output;
    private String apiTag;
    private JSONObject resultObject;

    public WsResponse(String output, String apiTag){
        this.status = false;
        this.output = output;
        this.apiTag = apiTag;
    }

    public WsResponse(boolean status, String output, String apiTag) {
        this.status = status;
        this.output = output;
        this.apiTag = apiTag;
    }

    public WsResponse(boolean status, String apiTag, String output, JSONObject resultObject) {
        this.status = status;
        this.output = output;
        this.apiTag = apiTag;
        this.resultObject = resultObject;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getApiTag() {
        return apiTag;
    }

    public void setApiTag(String apiTag) {
        this.apiTag = apiTag;
    }

    public JSONObject getResultObject() {
        return resultObject;
    }

    public void setResultObject(JSONObject resultObject) {
        this.resultObject = resultObject;
    }
}
