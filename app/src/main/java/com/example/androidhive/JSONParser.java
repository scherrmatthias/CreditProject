package com.example.androidhive; import java.util.HashMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

/*import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient; */
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.util.Pair;


import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class JSONParser {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    // constructor
    public JSONParser() {

    }

    // function get json from url
    // by making HTTP POST or GET mehtod
        /*Old params
    public JSONObject makeHttpRequest(String url, String method,
                                      List<Pair<String, String>> params)
    */

    public JSONObject makeHttpRequest(String url, String method, HashMap<String, String> params)
    {

        // Making HTTP request
        try {
            Log.i("Tag","Schritt1");
            int i = 1;
            // check for request method
            if (method == "POST") {
                // request method is POST
                // defaultHttpClient
            /* DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent(); */

                // Start of okhttp3
                Log.i("Tag","Schritt2");

                PostExample example = new PostExample();
                Log.i("Tag","Schritt3");

                String json = "{\n" +
                        "\t\"first_name\": \"".concat(params.get("first_name")).concat("\",\n" +
                                "\t\"last_name\": \"").concat(params.get("last_name")).concat("\",\n" +
                                "\t\"age\": \"").concat(params.get("age")).concat("\",\n" +
                                "\t\"reasons\": \"").concat(params.get("reasons")).concat("\",\n" +
                                "\t\"longitude_1\": \"").concat(params.get("longitude_1")).concat("\",\n" +
                                "\t\"latitude_1\": \"").concat(params.get("latitude_1"))+"\"}";

                //    System.out.println(json);

                Log.i("Tag",json);
                Log.i("Tag","Schritt3");

                String response = example.post("http://matthias-scherr.com/AndroidServer/android_connect/create_product.php", json);
                Log.i("Tag","Schritt4");


               // System.out.println(response);
            }


        } catch (IOException ioException) {
            ioException.printStackTrace();
        } /*else if(method == "GET"){
                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jObj;

    }*/
        return null;
    }
}