package com.test.sptech.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.sptech.Adapter.ItemDataWIthImageAdapter;
import com.test.sptech.Adapter.QuarterDetailsDialogAdapter;
import com.test.sptech.Constant;
import com.test.sptech.Models.MobileDataUsageYearly;
import com.test.sptech.Models.QuarterDataVol;
import com.test.sptech.R;
import com.test.sptech.Utilities.DialogQuarterDetails;
import com.test.sptech.Utilities.GeneralUtil;
import com.test.sptech.Utilities.Remote.WebServices;
import com.test.sptech.Utilities.Remote.WsRequest;
import com.test.sptech.Utilities.Remote.WsResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DisplayClickableImageActivity extends AppCompatActivity
        implements WebServices.AsyncResponse, ItemDataWIthImageAdapter.OnItemSelectListener{

    private static final String TAG = DisplayDataListActivity.class.getSimpleName();
    private RecyclerView rvYearlyWithImageList;
    private ProgressDialog pd;
    private List<MobileDataUsageYearly> yearlyList;
    private ItemDataWIthImageAdapter mAdapter;
    private DialogQuarterDetails mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_clickable_image);

        findViewsById();
        getData();
        setupViews();
    }

    private void findViewsById() {
        rvYearlyWithImageList = findViewById(R.id.rvYearlyWithImageList);
    }

    private void getData() {
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

    private void setupViews() {

        mAdapter = new ItemDataWIthImageAdapter(yearlyList, this);

        rvYearlyWithImageList.setAdapter(mAdapter);
        rvYearlyWithImageList.setLayoutManager(new LinearLayoutManager(rvYearlyWithImageList.getContext()));
        rvYearlyWithImageList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    @Override
    public void processFinish(WsResponse wsResponse) {
        if (pd.isShowing()){
            pd.dismiss();
        }

        try{
            if(wsResponse != null || !wsResponse.isStatus()){
                String output = wsResponse.getOutput();

                JSONObject json = new JSONObject(output);
                JSONObject jsonResponse = json.getJSONObject("result");

                JSONArray recordsArr = jsonResponse.getJSONArray("records");

                String storedYear = "";
                List<QuarterDataVol> quarterList = null;
                MobileDataUsageYearly mItem  = null;

                for(int i = 0; i < recordsArr.length(); i++){

                    JSONObject record = recordsArr.getJSONObject(i);
                    String mobileDataVol = record.getString(Constant.JSON_VOLUME_OF_MOBILE_DATA);
                    String quarter = record.getString(Constant.JSON_QUARTER);

                    String[] quarterArr = quarter.split("-");
                    String yearStr = quarterArr[0];
                    String quarterStr = quarterArr[1];

                    if(storedYear.isEmpty()){

                        mItem = new MobileDataUsageYearly(yearStr);
                        quarterList = new ArrayList<>();
                        quarterList.add(new QuarterDataVol(quarterStr, mobileDataVol));

                        if(recordsArr.length() == 1){

                            finaliseYearlyList(mItem, quarterList);
                        }else{
                            storedYear = yearStr;
                        }
                    }else if(storedYear.equals(yearStr)){
                        quarterList.add(new QuarterDataVol(quarterStr, mobileDataVol));

                    }else{

                        finaliseYearlyList(mItem, quarterList);

                        if(i == recordsArr.length() - 1){
                            mItem = new MobileDataUsageYearly(yearStr);
                            quarterList.add(new QuarterDataVol(quarterStr, mobileDataVol));

                            finaliseYearlyList(mItem, quarterList);
                        }else{
                            mItem = new MobileDataUsageYearly(yearStr);
                            quarterList = new ArrayList<>();
                            quarterList.add(new QuarterDataVol(quarterStr, mobileDataVol));

                            storedYear = yearStr;
                        }
                    }


                }

                // check for any quarter in a year that demonstrates a decrease in volume data
                for(MobileDataUsageYearly y: yearlyList){
                    BigDecimal storedDataVol = BigDecimal.ZERO;

                    for(QuarterDataVol q: y.getQuarterList()){

                        if(storedDataVol.compareTo(q.getVolumeOfMobileData()) == Constant.IS_GREATER_THAN){

                            q.setDecreaseInDataVolFlag(true);
                            y.setDecreaseInDataVol(true);

                            if(y.getAffectedQuarters().isEmpty()){
                                y.setAffectedQuarters(q.getQuarter());
                            }else{
                                String affectedQuarters = y.getAffectedQuarters();
                                y.setAffectedQuarters(affectedQuarters.concat(","+ q.getQuarter()));
                            }

                        }

                        storedDataVol = q.getVolumeOfMobileData();
                    }
                }

                mAdapter.notifyDataSetChanged();

            }
        }catch(Exception e){
            Log.e(TAG, GeneralUtil.getStackTrace(e) );
        }
    }

    private void finaliseYearlyList(MobileDataUsageYearly mItem, List<QuarterDataVol> quarterList){
        mItem.setQuarterList(quarterList);
        yearlyList.add(mItem);
    }

    @Override
    public void onDisplayItemSelected(List<QuarterDataVol> quarterDataVolList) {

        if(DialogQuarterDetails.checkNotShowing(mDialog) && !quarterDataVolList.isEmpty()){
            mDialog = new DialogQuarterDetails();
            mDialog.showDialog(this, new QuarterDetailsDialogAdapter(quarterDataVolList));
        }
    }
}
