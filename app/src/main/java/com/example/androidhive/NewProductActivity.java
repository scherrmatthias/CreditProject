package com.example.androidhive;


import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;

//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

public class NewProductActivity extends Activity {

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

    EditText inputFirstName;
    EditText inputLastName;
    EditText inputAge;
    EditText inputReasons;

    TextView longitudeField;
    TextView latitudeField;

    // url to create new product
    private static String url_create_product ="http://matthias-scherr.com/AndroidServer/android_connect/create_product.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);


        // Edit Text
        inputFirstName = (EditText) findViewById(R.id.first_name_id);
        inputLastName = (EditText) findViewById(R.id.last_name_id);
        inputAge = (EditText) findViewById(R.id.age_id);
        inputReasons = (EditText) findViewById(R.id.reasons_id);
        longitudeField = (TextView) findViewById(R.id.longitude_id);
        latitudeField = (TextView) findViewById(R.id.latitude_id);

        Intent intent = getIntent();

        String latitude = intent.getStringExtra("lat");
        String longitude = intent.getStringExtra("lng");
        longitudeField.setText(longitude);
        latitudeField.setText(latitude);
        inputFirstName.setText(intent.getStringExtra("first_name"));
        inputLastName.setText(intent.getStringExtra("last_name"));
        inputAge.setText(intent.getStringExtra("age"));
        inputReasons.setText(intent.getStringExtra("reasons"));



        Button btnGetLocation= (Button) findViewById(R.id.btnGetLocation);
        // Create button
        Button btnCreateProduct = (Button) findViewById(R.id.btnCreateProduct);


        btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(NewProductActivity.this, WhereAmIActivity.class);
                i.putExtra("first_name",inputFirstName.getText().toString());
                i.putExtra("last_name",inputLastName.getText().toString());
                i.putExtra("age",inputAge.getText().toString());
                i.putExtra("reasons",inputReasons.getText().toString());
                startActivity(i);
            }
        });


        // button click event
        btnCreateProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread

                new CreateNewProduct().execute();

            }
        });
    }


    /**
     * Background Async Task to Create new product
     * */
    class CreateNewProduct extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(NewProductActivity.this);
            pDialog.setMessage("Creating Product..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
        protected String doInBackground(String... args) {


            String latitude = latitudeField.getText().toString();
            String longitude = longitudeField.getText().toString();


            String first_name = inputFirstName.getText().toString();
            String last_name = inputLastName.getText().toString();
            String age = inputAge.getText().toString();
            String reasons = inputAge.getText().toString();



            // Building Parameters

            /* Old params
            List<Pair<String,String>> params = new ArrayList<>();
            params.add(new Pair("name", name));
            params.add(new Pair("price", price));
            params.add(new Pair("description", description));
            */

            HashMap<String, String> params = new HashMap<String, String>();
            params.put("first_name",first_name);
            params.put("last_name",last_name);
            params.put("age", age);
            params.put("reasons", reasons);
            params.put("longitude_1", longitude);
            params.put("latitude_1", latitude);


            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_product,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag

            //The following try-catch block works, but needs to be fully understood.
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product
                    Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
                    startActivity(i);

                    // closing this screen
                    finish();
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }




    }
}