package com.test.sptech.Utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.sptech.Adapter.QuarterDetailsDialogAdapter;
import com.test.sptech.R;

public class DialogQuarterDetails {

    protected AlertDialog mAlertDialog;
    protected boolean isShowing = false; // true when the alert dialog generation method is called

    public void showDialog(Context context, final QuarterDetailsDialogAdapter adapter){
        isShowing = true;
        View dialogView = View.inflate(context, R.layout.layout_custom_dialog_quarter_details, null);
        Button btnOk = dialogView.findViewById(R.id.btnOk);
        RecyclerView rvContent = dialogView.findViewById(R.id.rvQuarterDetails);

        rvContent.setLayoutManager(new LinearLayoutManager(context));
        rvContent.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        rvContent.setHasFixedSize(true);
        rvContent.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);
        builder.setCancelable(false);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogQuarterDetails.this.dismiss();
            }
        });

        mAlertDialog = builder.create();
        mAlertDialog.show();
    }

    /**
     * To check if the dialog is not in the process of being shown or is not showing,
     * main use case is to prevent user from calling the #showDialog(...) method again
     * if user has called the method once and has not dismissed it with #dismiss()
     */
    public static boolean checkNotShowing(DialogQuarterDetails dialogQuarterDetails){
        return !(dialogQuarterDetails != null && dialogQuarterDetails.isShowing);
    }

    public void dismiss() {
        isShowing = false;

        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }
}
