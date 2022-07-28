package com.androdev.azadproject.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.androdev.azadproject.Adapter.DataAdapter;
import com.androdev.azadproject.Helpers;
import com.androdev.azadproject.Model.Data;
import com.androdev.azadproject.R;
import com.androdev.azadproject.databinding.ActivityDashBoardBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class DashBoard extends AppCompatActivity {

    ActivityDashBoardBinding dashBoardBinding;
    ProgressDialog progressDialog;
    ArrayList<String> mids = new ArrayList<>();
    public ArrayList<Data> mainData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        dashBoardBinding = DataBindingUtil.setContentView(this, R.layout.activity_dash_board);

        progressDialog = new ProgressDialog(DashBoard.this);

        dashBoardBinding.data.setLayoutManager(new LinearLayoutManager(this));
        dashBoardBinding.data.setHasFixedSize(true);

        getData();

        if (mainData.size() > 0) {
            progressDialog.dismiss();
            DataAdapter adapter = new DataAdapter(this, mainData);
            dashBoardBinding.data.setAdapter(adapter);
        } else {
            progressDialog.dismiss();
            Toast.makeText(DashBoard.this, "Data Not Found..", Toast.LENGTH_SHORT).show();
        }

}

    private void getData() {
        progressDialog.setMessage("Fetching Data");
        progressDialog.show();
        try {
            JSONObject obj = new JSONObject(Helpers.loadJSONFromAsset(DashBoard.this));
            JSONArray ourDataArray = obj.getJSONArray("sort");
            String mid;

            for (int i = 0; i < ourDataArray.length(); i++) {

                JSONObject mobject = ourDataArray.getJSONObject(i);
                mid = mobject.getString("Mid");
                ArrayList<Data.TData> tData = new ArrayList<>();

                if (!mids.contains(mid)) {
                    for (int j = i; j < ourDataArray.length(); j++) {
                        JSONObject tobject = ourDataArray.getJSONObject(j);
                        if (mid.equalsIgnoreCase(tobject.getString("Mid"))) {
                            Data.TData tiddata = new Data.TData(mobject.getString("Tid"), mobject.getString(
                                    "amount"), mobject.getString("narration"));
                            //by using if this restrict duplicate entry
                            if (!tData.contains(tiddata))
                                tData.add(tiddata);
                        }
                    }
                    Collections.sort(tData, (o1, o2) -> o1.getTid().compareTo(o2.getTid()));
                    Data dataModel = new Data(mid, tData);
                    mainData.add(dataModel);
                    mids.add(mid);
                }
            }
        } catch (JSONException e) {
            progressDialog.dismiss();
            e.printStackTrace();
        }
    }
}