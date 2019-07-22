package com.example.roommanagementproject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

class JsonFunctions {
    public  static JSONObject StoreData
            (String url,String Param){

        StringBuffer sb = null;

        JSONObject jsonObject=null;

        try {
            URL jurl=new URL(url);
            HttpURLConnection connection=
                    (HttpURLConnection) jurl.openConnection();

            connection.setRequestMethod("POST");
            connection.setConnectTimeout(10000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            //headers
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.connect();

            OutputStream mOutPut = new BufferedOutputStream(connection.getOutputStream());
            mOutPut.write(Param.getBytes());
            mOutPut.flush();

            InputStream is=new BufferedInputStream(connection.getInputStream());
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));
            sb=new StringBuffer();
            String line;
            while ((line=bufferedReader.readLine())!=null)
            {
                sb.append(line+"\n");
            }
            is.close();
            jsonObject=new JSONObject(sb.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;

    }
}
