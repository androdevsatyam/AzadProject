package com.androdev.azadproject.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.androdev.azadproject.Adapter.DataAdapter;
import com.androdev.azadproject.Helpers;
import com.androdev.azadproject.Model.Data;
import com.androdev.azadproject.Model.TData;
import com.androdev.azadproject.R;
import com.androdev.azadproject.databinding.ActivityDashBoardBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DashBoard extends AppCompatActivity {

    ActivityDashBoardBinding dashBoardBinding;
    ProgressDialog progressDialog;
    ArrayList<Data> mainData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        dashBoardBinding= DataBindingUtil.setContentView(this,R.layout.activity_dash_board);

        progressDialog=new ProgressDialog(DashBoard.this);

        dashBoardBinding.data.setLayoutManager(new LinearLayoutManager(this));
        dashBoardBinding.data.setHasFixedSize(true);
        getData();



if(mainData.size()>0){
    DataAdapter adapter=new DataAdapter(this,mainData);
}

    }

    private void getData() {
        progressDialog.setMessage("Fetching Data");
        progressDialog.show();
        try {
            JSONObject obj = new JSONObject(Helpers.loadJSONFromAsset(DashBoard.this));
            JSONArray ourDataArray = obj.getJSONArray("sort");
            ArrayList<TData> data = new ArrayList<>();
            String mid;


            for (int i = 0; i < ourDataArray.length(); i++) {

                JSONObject mobject = ourDataArray.getJSONObject(i);
                mid = mobject.getString("Mid");

                data.add(new TData(mobject.getString("Tid"), mobject.getString("amount"), mobject.getString("narration")));

                for (int j = i; j < ourDataArray.length(); j++) {
                    JSONObject tobject = ourDataArray.getJSONObject(i);
                    if (mid.equalsIgnoreCase(tobject.getString("Mid"))) {
                        TData tData = new TData(mobject.getString("Tid"), mobject.getString("amount"), mobject.getString("narration"));
                        //by using if this restrict duplicate entry
                        if (!data.contains(tData))
                            data.add(tData);
                    }
                }
                mainData.add(new Data(mid, data));
            }
//            Collections.sort(mainData);
        } catch (JSONException e) {
            progressDialog.dismiss();
            e.printStackTrace();
        }
    }
}