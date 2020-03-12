package com.test.sptech.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.sptech.Adapter.ItemDataAdapter;
import com.test.sptech.Constant;
import com.test.sptech.Models.MobileDataUsageYearly;
import com.test.sptech.R;
import com.test.sptech.Utilities.GeneralUtil;
import com.test.sptech.Utilities.Remote.WebServices;
import com.test.sptech.Utilities.Remote.WsRequest;
import com.test.sptech.Utilities.Remote.WsResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DisplayDataListActivity extends AppCompatActivity implements WebServices.AsyncResponse{

    private static final String TAG = DisplayDataListActivity.class.getSimpleName();
    private RecyclerView rvYearlyList;
    private ProgressDialog pd;
    private List<MobileDataUsageYearly> yearlyList;
    private ItemDataAdapter mAdapter;

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

        yearlyList = new ArrayList<>();
        initiateWebService();
    }

    private void initiateWebService(){

        // initiate progress dialog
        pd = new ProgressDialog(this);
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.show();

        WsRequest wsParam = new WsRequest(Constant.GET_ALL_DATA, null);
        WebServices wsService = new WebServices(this);
        wsService.execute(wsParam);
    }

    private void setupViews(){

        mAdapter = new ItemDataAdapter(yearlyList);

        rvYearlyList.setAdapter(mAdapter);
        rvYearlyList.setLayoutManager(new LinearLayoutManager(rvYearlyList.getContext()));
        rvYearlyList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
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

                JSONArray recordsArr = jsonResponse.getJSONArray("records");

                String storedYear = "";
                BigDecimal storedDataVol = BigDecimal.ZERO;


                for(int i = 0; i < recordsArr.length(); i++){
                    JSONObject record = recordsArr.getJSONObject(i);
                    String mobileDataVol = record.getString(Constant.JSON_VOLUME_OF_MOBILE_DATA);
                    String quarter = record.getString(Constant.JSON_QUARTER);

//                    Log.d(TAG, "quarter: "+quarter);
//                    Log.d(TAG, "mobileDataVol: "+mobileDataVol);

                    String[] quarterArr = quarter.split("-");
                    if(storedYear.isEmpty()){

                        if(recordsArr.length() == 1){
                            yearlyList.add(new MobileDataUsageYearly(new BigDecimal(mobileDataVol), quarterArr[0]));
                        }else{
                            storedDataVol = new BigDecimal(mobileDataVol);
                            storedYear = quarterArr[0];
                        }
                    }else if(quarterArr[0].equals(storedYear)){
                        storedDataVol = storedDataVol.add(new BigDecimal(mobileDataVol));
                    }else{

                        yearlyList.add(new MobileDataUsageYearly(storedDataVol, storedYear));

                        if(i == recordsArr.length() - 1){
                            yearlyList.add(new MobileDataUsageYearly(new BigDecimal(mobileDataVol), quarterArr[0]));
                        }else{
                            storedDataVol = new BigDecimal(mobileDataVol);
                            storedYear = quarterArr[0];
                        }

                    }
                }

                mAdapter.notifyDataSetChanged();

            }
        }catch(Exception e){
            Log.e(TAG, GeneralUtil.getStackTrace(e) );
        }


    }



}
