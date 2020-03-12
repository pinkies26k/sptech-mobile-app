package com.test.sptech.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.test.sptech.R;

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

        Intent intent = null;

        switch(v.getId()){


            case R.id.btnGotoDataList:
                intent = new Intent(getApplicationContext(), DisplayDataListActivity.class);

                break;
            case R.id.btnGotoClickableImage:
                intent = new Intent(getApplicationContext(), DisplayClickableImageActivity.class);
                break;

        }
        if(intent != null) startActivity(intent);
    }
}
