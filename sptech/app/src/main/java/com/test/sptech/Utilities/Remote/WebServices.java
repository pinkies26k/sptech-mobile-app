package com.test.sptech.Utilities.Remote;

import android.os.AsyncTask;
import android.util.Log;

import com.test.sptech.Constant;
import com.test.sptech.Utilities.GeneralUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;

public class WebServices extends AsyncTask<WsRequest, Void, WsResponse> {

    private static final String TAG = WebServices.class.getSimpleName();

    private String mUrl = "";

    public AsyncResponse mDelegate = null;

    private String mCurrentOperation = "";

    public WebServices(AsyncResponse delegate){
        this.mDelegate = delegate;
    }

    @Override
    protected WsResponse doInBackground(WsRequest... wsParams) {

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try{

            WsRequest wsParam = wsParams[0];
            LinkedHashMap<String, String> paramHashMaps = wsParam.getParamHashMaps();


            switch (wsParam.getOperationName()){
                case Constant.GET_ALL_DATA:
                    mUrl = "https://data.gov.sg/api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f";
                    break;
            }

            URL url = new URL(mUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
//                Log.d("Response: ", "> " + line);

            }

            return new WsResponse(true, buffer.toString(), mCurrentOperation);

        }
        catch(Exception e){
            Log.e(TAG, GeneralUtil.getStackTrace(e));

            return new WsResponse("", mCurrentOperation);

        }finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                Log.e(TAG, "IOException: "+ GeneralUtil.getStackTrace(e));
            }
        }

    }

    @Override
    protected void onPostExecute(WsResponse wsResponse) {
        super.onPostExecute(wsResponse);
        mDelegate.processFinish(wsResponse);
    }

    public interface AsyncResponse {
        void processFinish(WsResponse wsResponse);
    }

}
