package com.ram.productcart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ram Mishra on 8/22/2016.
 */

/*This is the adapter for the listview of menu items i.e products shown to the user */
public class ProductAdapter extends BaseAdapter{
    static interface CustomButtonListener {
        public void onButtonClickListener(int position, EditText editText, int value);
    };


    public ArrayList<Integer> quantity = new ArrayList<Integer>();
    ArrayList<ProductItem> menu;




    private Context context;
    CustomButtonListener customButtonListener;
    Controller ct=Controller.getInstance();


    public ProductAdapter (Context context,ArrayList<ProductItem> menu, ArrayList<Integer> quantity) {
        this.context = context;
        this.menu=menu;
        this.quantity=quantity;
    }

    public void setCustomButtonListener(CustomButtonListener customButtonListner)
    {
        this.customButtonListener = customButtonListner;
    }

    @Override
    public int getCount() {
        return menu.size();
    }

    @Override
    public ProductItem getItem(int position) {
        return menu.get(position);
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row;
        final ProductListViewHolder listViewHolder;
        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.product_item,parent,false);
            listViewHolder = new ProductListViewHolder();
            listViewHolder.ProductItem = (TextView) row.findViewById(R.id.ProductItem);
            listViewHolder.ProductPrice = (TextView) row.findViewById(R.id.ProductPrice);
            listViewHolder.btnPlus = (ImageButton) row.findViewById(R.id.ib_add);
            listViewHolder.Quantity = (EditText) row.findViewById(R.id.quantity);
            listViewHolder.btnMinus = (ImageButton) row.findViewById(R.id.ib_remove);
            row.setTag(listViewHolder);
        }
        else
        {
            row=convertView;
            listViewHolder= (ProductListViewHolder) row.getTag();
        }



        listViewHolder.ProductPrice.setText(menu.get(position).getProductPrice());

        try{

            listViewHolder.Quantity.setText(quantity.get(position) +"");


        }catch(Exception e){
            e.printStackTrace();
        }

        listViewHolder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customButtonListener != null) {
                    quantity.set(position,quantity.get(position)+ 1);
                    // adding products in cart
                    CartProductItem product=null;
                    product=new CartProductItem(menu.get(position).getProductName(),menu.get(position).getProductPrice());
                    int p=0;
                    if(ct.CheckProductInCart(product))  // If product already exist in cart then clicking this button will increase its quantity in the cart
                    {
                        for(int i=0;i<ct.getProductArraylistsize();i++)
                        {
                            if(ct.getProducts(i).equals(product)) // check for that particular product
                            {
                                p=i;
                            }
                        }
                        ct.changeProductquantity(p,quantity.get(position));
                    }
                    else // else it adds the product in the cart
                    {
                        ct.setProducts(product);
                        ct.setProductquantity(quantity.get(position));
                    }
                    customButtonListener.onButtonClickListener(position, listViewHolder.Quantity, 1);





                }

            }
        });
        listViewHolder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customButtonListener != null) {
                    if(quantity.get(position)>0)
                        quantity.set(position, quantity.get(position) - 1);
                    CartProductItem product=null;
                    product=new CartProductItem(menu.get(position).getProductName(),menu.get(position).getProductPrice());
                    int p=0;
                    int r=0;
                    if(ct.CheckProductInCart(product))// if quantity of the product in the cart is greater than one then clicking this will decrease its quantity by one in the cart
                    {   if(quantity.get(position)>0)
                    {
                        for (int i = 0; i < ct.getProductArraylistsize(); i++)
                        {
                            if (ct.getProducts(i).equals(product)) // check for that particular product
                            {
                                p = i;
                            }
                        }
                        ct.changeProductquantity(p, quantity.get(position));
                    }
                    else // if quantity of product in the cart is one then it removes that product in the cart on clicking this
                    {
                        for (int i = 0; i < ct.getProductArraylistsize(); i++)
                        {
                            if (ct.getProducts(i).equals(product)) // check for that particular product
                            {
                                r = i;
                            }
                        }
                        ct.removeProduct(r);
                        ct.removeProductquantity(r);

                    }
                    }
                    customButtonListener.onButtonClickListener(position,listViewHolder.Quantity,-1);



                }
            }
        });
        listViewHolder.ProductItem.setText(menu.get(position).getProductName());
        return row;
    }
}
