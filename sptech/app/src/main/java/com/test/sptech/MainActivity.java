package com.test.sptech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.test.sptech.DisplayDataList.DisplayDataListActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsByIdAndSetListeners();
    }

    private void findViewsByIdAndSetListeners(){
        Button btnGotoDataList = findViewById(R.id.btnGotoDataList);
        Button btnGotoClickableImage = findViewById(R.id.btnGotoClickableImage);

        btnGotoDataList.setOnClickListener(this);
        btnGotoClickableImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnGotoDataList:
                Intent displayDataActivity = new Intent(getApplicationContext(), DisplayDataListActivity.class);
                startActivity(displayDataActivity);
                break;
            case R.id.btnGotoClickableImage:
                break;
        }
    }
}
