package com.ram.productcart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*This activity just opens the Cart fragment..You can also use the fragment transaction for opening the cart fragment by clicking the Submit Request Button in the Products xml layout
 I simply open an activity on clicking Submit Request which directly opens the Cart fragment... */

public class OpenCart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_cart);
        Cart c=(Cart)getSupportFragmentManager().findFragmentById(R.id.open_cart);
    }
}
