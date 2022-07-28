package com.androdev.azadproject.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Patterns;
import android.widget.Toast;

import com.androdev.azadproject.Connection.Connection;
import com.androdev.azadproject.Helpers;
import com.androdev.azadproject.Model.LoginModel;
import com.androdev.azadproject.R;
import com.androdev.azadproject.databinding.ActivityMainBinding;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainAct;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainAct = DataBindingUtil.setContentView(this, R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);

        mainAct.signin.setOnClickListener(v -> {
            if (verifyDetails()) {
                dialog.setMessage("Logging in...");
                dialog.show();
                try {
                    Call<ResponseBody> requestLogin = Connection.getCon().login("login.php", new LoginModel(mainAct.email.getText().toString()
                            , mainAct.pass.getText().toString()));
                    requestLogin.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            dialog.dismiss();

                            /**
                             * Api not Running so that's why implementing Long Click */
                            startActivity(new Intent(MainActivity.this, DashBoard.class));
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Something goes wrong.!", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    dialog.dismiss();
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "Server Error try with Long Press", Toast.LENGTH_SHORT).show());
                }
            }
        });

        mainAct.signin.setOnLongClickListener(v -> {
            if (verifyDetails()) {
                dialog.setMessage("Logging in...");
                dialog.show();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        if (mainAct.email.getText() != null && mainAct.pass.getText() != null)
                            if (mainAct.email.getText().toString().equals("info@optium.com") && mainAct.pass.getText().toString().equals("Optium@112233"))
                                startActivity(new Intent(MainActivity.this, DashBoard.class));
                            else
                                Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }, 1200);
            }
            return true;
        });
    }

    private boolean verifyDetails() {
        if (!Patterns.EMAIL_ADDRESS.matcher(mainAct.email.getText().toString()).matches()) {
            mainAct.email.setError("Invalid Email");
            mainAct.email.requestFocus();
            return false;
        } else if (!Helpers.getPassPatern().matcher(mainAct.pass.getText().toString()).matches()) {
            mainAct.pass.setError("Invalid Password");
            mainAct.pass.requestFocus();
            return false;
        } else
            return true;
    }
}