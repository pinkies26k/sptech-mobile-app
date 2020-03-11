package com.test.sptech.DisplayDataList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.test.sptech.Constant;
import com.test.sptech.Models.MobileDataUsageYearly;
import com.test.sptech.R;
import com.test.sptech.Utilities.GeneralUtil;
import com.test.sptech.Utilities.Remote.WebServices;
import com.test.sptech.Utilities.Remote.WsRequest;
import com.test.sptech.Utilities.Remote.WsResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DisplayDataListActivity extends AppCompatActivity implements WebServices.AsyncResponse{

    private static final String TAG = DisplayDataListActivity.class.getSimpleName();
    private RecyclerView rvYearlyList;
    private ProgressDialog pd;
    private List<MobileDataUsageYearly> yearlyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data_list);

        findViewsById();
        getData();
        setupViews();
    }

    private void findViewsById(){

        rvYearlyList = findViewById(R.id.rvYearlyList);

    }

    private void getData(){
//        new JsonTask().execute("https://data.gov.sg/api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f");

        yearlyList = new ArrayList<>();
        initiateWebService();
    }

    private void initiateWebService(){

        // initiate progress dialog
        pd = new ProgressDialog(DisplayDataListActivity.this);
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.show();

        WsRequest wsParam = new WsRequest(Constant.GET_ALL_DATA, null);
        WebServices wsService = new WebServices(this);
        wsService.execute(wsParam);
    }

    private void setupViews(){

    }

    @Override
    public void processFinish(WsResponse wsResponse) {
        if (pd.isShowing()){
            pd.dismiss();
        }

        try{
            if(wsResponse != null || !wsResponse.isStatus()){
                String output = wsResponse.getOutput();

//                Log.d(TAG, "processFinish: output:: "+ output);

                JSONObject json = new JSONObject(output);
                JSONObject jsonResponse = json.getJSONObject("result");
                String resource_id = jsonResponse.getString("resource_id");

                JSONArray recordsArr = jsonResponse.getJSONArray("records");

                String currentYear = "", storedYear = "";
                for(int i = 0; i < recordsArr.length(); i++){
                    JSONObject record = recordsArr.getJSONObject(i);
                    String mobileDataVol = record.getString(Constant.JSON_VOLUME_OF_MOBILE_DATA);
                    String quarter = record.getString(Constant.JSON_QUARTER);

                    Log.d(TAG, "quarter: "+quarter);
                    Log.d(TAG, "mobileDataVol: "+mobileDataVol);

                    String[] quarterArr = quarter.split("-");
                    if(storedYear.isEmpty()){
                        yearlyList.add(new MobileDataUsageYearly(mobileDataVol, quarterArr[0]));
                        storedYear = quarterArr[0];
                    }else if(quarterArr[0].equals(storedYear)){

                    }
                }


            }
        }catch(Exception e){
            Log.e(TAG, GeneralUtil.getStackTrace(e) );
        }


    }



}
