package com.example.androidhive; import java.util.HashMap;
import android.util.Log;

import java.io.IOException;
import okhttp3.*;
import com.google.gson.*;
public class PostExample {


    //If no constructor is stated, an automatic, parameter-free constructur is created.
        public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
        //MediaType.get() Designates the content to be in JSON format, encoded in the UTF-8 character encoding.

        final OkHttpClient client = new OkHttpClient();

        String post(String url, String json) throws IOException {

            Log.i("Tag","Schritt3.1");
            Gson g = new Gson();

            JsonObject jsonObject = (JsonObject) new JsonParser().parse(json);
            RequestBody formBody = new FormBody.Builder()
                    .add("first_name",jsonObject.get("first_name").getAsString())
                    .add("last_name", jsonObject.get("last_name").getAsString())
                    .add("age", jsonObject.get("age").getAsString())
                    .add("reasons",jsonObject.get("reasons").getAsString())
                    .add("longitude_1",jsonObject.get("longitude_1").getAsString())
                    .add("latitude_1",jsonObject.get("latitude_1").getAsString())
                    .build();
            Log.i("Tag","Schritt3.2");

            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
            Log.i("Tag","Schritt3.3");
            // Call call = client.newCall(request);
            // Response response = call.execute();

            //  assertThat(response.code(), equalTo(200));



       /* RequestBody body = RequestBody.create(json, JSON);
        //RequestBody.create() returns a new request body that transmits content.
        Request request = new Request.Builder()
                .url(url) //sets the URL target of this request, returns Request.Builder instance
                .post(body) //returns Request.Builder instance
                .build(); // returns a Request instance */

            Call call = client.newCall(request);
            Log.i("Tag","Schritt3.4");
            Response response = call.execute();
            Log.i("Tag","Schritt3.5");



            return response.body().string();

        }
}
