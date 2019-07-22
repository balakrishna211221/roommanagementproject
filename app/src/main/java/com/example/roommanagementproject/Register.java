package com.example.roommanagementproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roommanagementproject.databinding.ActivityRegisterBinding;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class Register extends AppCompatActivity {
ActivityRegisterBinding activityRegisterBinding;
String url="http://androindian.com/apps/example_app/api.php";


    TextView Loginredirect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       activityRegisterBinding= DataBindingUtil.setContentView(Register.this,R.layout.activity_register);

        //Loginredirect=findViewById(R.id.loginpageredirect);
        activityRegisterBinding.loginpageredirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Register.this,MainActivity.class);
                startActivity(intent);

            }
        });


        activityRegisterBinding.reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //jason object requet 1
                JSONObject  regreqJsonObject=new JSONObject();

                try {
                    regreqJsonObject.put("name",activityRegisterBinding.username.getText().toString().trim());
                    regreqJsonObject.put("mobile",activityRegisterBinding.phone.getText().toString().trim());
                    regreqJsonObject.put("email",activityRegisterBinding.email.getText().toString().trim());
                    regreqJsonObject.put("pswrd",activityRegisterBinding.password.getText().toString().trim());
                    regreqJsonObject.put("baction","register_user");
                    Toast.makeText(Register.this, "sending request", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //need to execute that jsonobject for that creating a inner class
                 RegPage regPage=new RegPage();
                  regPage.execute(regreqJsonObject.toString());

            }
        });





    }


    private class RegPage extends AsyncTask<String,String,String> {
       // String url = "http://androindian.com/apps/example_app/api.php";

        @Override
        protected String doInBackground(String... params) {


            JSONObject object=JsonFunctions.StoreData(url,params[0]);
            Log.i("Result",object.toString());
            return object.toString();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
             JSONObject jsonObject = new JSONObject(s.toString());
             String   respone = jsonObject.getString("response");

                if (respone.trim().equals("success")) {

                    String status = jsonObject.getString("user");
                    Toast.makeText(getApplicationContext(), "" + status, Toast.LENGTH_LONG).show();


                } else if (respone.trim().equals("failed")) {
                    String status1 = jsonObject.getString("user");
                    Toast.makeText(getApplicationContext(), "" + status1, Toast.LENGTH_LONG).show();


                } else if (respone.trim().equals("error")) {
                    String status2 = jsonObject.getString("user");
                    Toast.makeText(getApplicationContext(), "" + status2, Toast.LENGTH_LONG).show();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
