package com.ram.productcart;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Ram Mishra on 8/22/2016.
 */
/* This is the cart products listview holder which further gets associated with the cart_product_item.xml*/
public class CartProductListViewHolder {
    TextView CartProductPrice;
    TextView CartProductTotalPrice;
    TextView CartProductItem;
    ImageButton add;
    ImageButton remove;
    EditText Quantity;
}
