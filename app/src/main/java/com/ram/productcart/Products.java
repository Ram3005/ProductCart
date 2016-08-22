package com.ram.productcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Products extends AppCompatActivity implements ProductAdapter.CustomButtonListener {
    // Items with details to add as products ...Use real data through JSON
    String[] arrItems={"Key Chain","Perfume","Dairy Milk","Deo","Mat","Mobile Cover","T Shirt","Laughing Buddha"};
    String[] prices={"150","80","50","100","120","140","110","130"};

    int lengt=arrItems.length;

    int Total;

    Button opencart;
    Controller a=Controller.getInstance();
    ListView listView;
    ProductAdapter listAdapter = null;
    EditText totalsum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        // adding menu items
        if(a.getMenu().size()==0) {
            for (int i = 0; i < lengt; i++) {
                ProductItem menuitem = new ProductItem(arrItems[i], prices[i]);
                a.setMenuproducts(menuitem);
            }
            a.setMenuproductquantity();
        }
        //setting product items listview and its adapter

        listView=(ListView) findViewById(R.id.product_list);
        listAdapter = new ProductAdapter(this,a.getMenu(),a.getMenuproductquantity());
        listView.setAdapter(listAdapter);
        listAdapter.setCustomButtonListener(this);
        opencart=(Button)findViewById(R.id.opencart);
        opencart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),OpenCart.class));
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        listAdapter = new ProductAdapter(this,a.getMenu(),a.getMenuproductquantity());
        listView.setAdapter(listAdapter);
        listAdapter.setCustomButtonListener(this);
        opencart=(Button)findViewById(R.id.opencart);
        opencart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),OpenCart.class));
            }
        });

        totalsum=(EditText)findViewById(R.id.Total) ;
        Total=0;

        for(int i=0;i<a.getProductArraylistsize();i++)
        {
            Total=Total+(Integer.parseInt(a.getProducts(i).getProductPrice())*a.getProductquantity(i));
        }
        totalsum.setText(Integer.toString(Total));

    }

    @Override
    public void onButtonClickListener(int position, EditText editText, int value) {
        int quantity = Integer.parseInt(editText.getText().toString());
        quantity = quantity  + 1*value;
        if(quantity<0)
            quantity=0;
        editText.setText(quantity+ "");


        totalsum=(EditText)findViewById(R.id.Total) ;
        Total=0;

        for(int i=0;i<a.getProductArraylistsize();i++)
        {
            Total=Total+(Integer.parseInt(a.getProducts(i).getProductPrice())*a.getProductquantity(i));
        }
        totalsum.setText(Integer.toString(Total));




    }

}
