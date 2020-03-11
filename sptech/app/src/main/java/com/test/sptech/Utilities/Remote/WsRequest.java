package com.test.sptech.Utilities.Remote;

import java.util.LinkedHashMap;

public class WsRequest {

    private String operationName;
    private LinkedHashMap<String, String> paramHashMaps;

    /**
     *
     * @param operationName         (the name of the web service API)
     * @param paramHashMaps         (return null if no other parameters are needed to pass to web service)
     */
    public WsRequest(String operationName, LinkedHashMap<String, String> paramHashMaps) {
        this.operationName = operationName;
        this.paramHashMaps = paramHashMaps;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public LinkedHashMap<String, String> getParamHashMaps() {
        return paramHashMaps;
    }

    public void setParamHashMaps(LinkedHashMap<String, String> paramHashMaps) {
        this.paramHashMaps = paramHashMaps;
    }
}
