package com.example.roommanagementproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.GLES31;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SharedMemory;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.roommanagementproject.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;

    SharedPreferences sharedPreferences;
String url="http://androindian.com/apps/example_app/api.php";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        String phoneloged=sharedPreferences.getString("mobile",null);
        String passloged=       sharedPreferences.getString("pass",null);
        if(phoneloged!=null&&passloged!=null)
        {
            Intent intent=new Intent(MainActivity.this,ProfilePage.class);
            startActivity(intent);
        }
       else {

            binding.loginbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("mobile", binding.mobile.getText().toString().trim());
                    editor.putString("pass", binding.pass.getText().toString().trim());
                    editor.commit();


                    //1
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("mobile", binding.mobile.getText().toString().trim());
                        jsonObject.put("pswrd", binding.pass.getText().toString().trim());

                        jsonObject.put("baction", "login_user");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    LoginPage loginPage = new LoginPage();
                    loginPage.execute(jsonObject.toString());

                }
            });


            binding.registration.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, Register.class);
                    startActivity(intent);
                }
            });
        }

    }

    private class LoginPage extends AsyncTask<String,String,String> {
        ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Content Loading");
            progressDialog.setTitle("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... param) {

            JSONObject object=JsonFunctions.StoreData(url,param[0]);
            Log.i("Result",object.toString());
            return object.toString();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            try {

                JSONObject jsonObjectresponse=new JSONObject(s.toString());
                String result=jsonObjectresponse.getString("response");
                if(result.equalsIgnoreCase("success")){
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                   Intent intent =new Intent(MainActivity.this,ProfilePage.class);
                   startActivity(intent);

                }else if(result.equalsIgnoreCase("failed")){
                    Toast.makeText(MainActivity.this, "login failed", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }}
