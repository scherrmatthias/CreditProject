package com.example.androidhive; import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreenActivity extends Activity{

    //Attribute
    Button btnViewProducts;
    Button btnNewProduct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Methode der Elternklasse
        //Verbindung mit main_screen.xml wird durchgeführt
        setContentView(R.layout.main_screen);

        // Buttons
       // btnViewProducts = (Button) findViewById(R.id.btnViewProducts);
        btnNewProduct = (Button) findViewById(R.id.btnCreateProduct);

        // view products click event

        //setOnclickListener: Interface definition for a callback to be invoked when a view is clicked.
        // A new instance of View.OnClickListener is created
        //View.OnClicklistener has the public method onClick(View v)

/*        btnViewProducts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching All products Activity
                //An intent is an abstract description of an operation to be performed. It can bes used with
                Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
                //startActivity launches a new activty.

                startActivity(i);
                //The intent is used here to launch an activity

            }
        }); */

        // view products click event
        btnNewProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching create new product activity
                Intent i = new Intent(getApplicationContext(), NewProductActivity.class);
                startActivity(i);

            }
        });
    }
}