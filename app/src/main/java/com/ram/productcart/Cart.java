package com.ram.productcart;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class Cart extends Fragment {
    Controller c=Controller.getInstance();
    CartProductAdapter adapter;
    TextView subtotal;
    TextView total;
    ListView l;
    int Sub_total=0;
    int Total=0;
    int tax=0;
    int delivery_charge=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View v=getView();

        l=(ListView)v.findViewById(R.id.cart_products);


        adapter=new CartProductAdapter(this.getContext(),c.getMyproducts(),c.getProductquantity());
        l.setAdapter(adapter);
        setListViewHeightBasedOnChildren(l);
        // casting total and subtotal textviews
        subtotal=(TextView)v.findViewById(R.id.subtotal);
        total=(TextView)v.findViewById(R.id.total);
        //evaluating subtotal and total
        for(int i=0;i<c.getProductArraylistsize();i++)
        {
            Sub_total=Sub_total+(Integer.parseInt(c.getProducts(i).getProductPrice())*c.getProductquantity(i));
        }
        subtotal.setText(Integer.toString(Sub_total));
        Total=Sub_total+tax+delivery_charge;
        total.setText(Integer.toString(Total));
    }
/* This is a Cart Aapter */
    public class CartProductAdapter extends BaseAdapter {



        //public ArrayList<HashMap<String, String>> listQuantity;
        public ArrayList<Integer> q = new ArrayList<Integer>();

        ArrayList<CartProductItem> myproducts;

        private Context context;
        Controller c=Controller.getInstance();



        public CartProductAdapter (Context context, ArrayList<CartProductItem> myproducts,ArrayList<Integer> qq) {
            this.context = context;
            this.myproducts=myproducts;
            this.q=qq;
        }



        @Override
        public int getCount() {
            return myproducts.size();
        }

        @Override
        public CartProductItem getItem(int position) {
            return myproducts.get(position);
        }


        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View row;
            final CartProductListViewHolder listViewHolder;
            if(convertView == null)
            {
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = layoutInflater.inflate(R.layout.cart_product_item,parent,false);
                listViewHolder = new CartProductListViewHolder();
                listViewHolder.CartProductItem = (TextView) row.findViewById(R.id.CartProductItem);
                listViewHolder.CartProductPrice = (TextView) row.findViewById(R.id.CartProductPrice);
                listViewHolder.add= (ImageButton) row.findViewById(R.id.ib_add);
                listViewHolder.Quantity = (EditText) row.findViewById(R.id.quantity);
                listViewHolder.remove = (ImageButton) row.findViewById(R.id.ib_remove);
                listViewHolder.CartProductTotalPrice=(TextView)row.findViewById(R.id.CartProductTotalPrice);
                row.setTag(listViewHolder);
            }
            else
            {
                row=convertView;
                listViewHolder= (CartProductListViewHolder) row.getTag();
            }



            listViewHolder.CartProductPrice.setText(myproducts.get(position).getProductPrice());

            try{

                listViewHolder.Quantity.setText(q.get(position) +"");


            }catch(Exception e){
                e.printStackTrace();
            }
            listViewHolder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    q.set(position,q.get(position)+ 1);
                    c.changeProductquantity(position,q.get(position));
                    // changing quantity of menuitems
                    int p=0;
                    ProductItem product=new ProductItem(myproducts.get(position).getProductName(),myproducts.get(position).getProductPrice());
                    for(int i=0;i<c.getMenu().size();i++)
                    {
                        if(c.getMenuproducts(i).equals(product))
                        {
                            p=i;
                            c.changeMenuproductquantity(p,q.get(position));
                        }

                    }
                    refresh(c.getProductquantity());
                    //Totalling subtotal and total on click event
                    Sub_total=0;
                    for(int i=0;i<c.getProductArraylistsize();i++)
                    {
                        Sub_total=Sub_total+(Integer.parseInt(c.getProducts(i).getProductPrice())*c.getProductquantity(i));
                    }
                    subtotal.setText(Integer.toString(Sub_total));
                    Total=Sub_total+tax+delivery_charge;
                    total.setText(Integer.toString(Total));

                }
            });
            listViewHolder.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(q.get(position)>1)
                    {
                        q.set(position, q.get(position) - 1);
                        c.changeProductquantity(position, q.get(position));
                        int p=0;
                        ProductItem product=new ProductItem(myproducts.get(position).getProductName(),myproducts.get(position).getProductPrice());
                        for(int i=0;i<c.getMenu().size();i++)
                        {
                            if(c.getMenuproducts(i).equals(product))
                            {
                                p=i;
                                c.changeMenuproductquantity(p,q.get(position));

                            }
                        }
                        refresh(c.getProductquantity());
                    }
                    else
                    {

                        int p=0;
                        ProductItem product=new ProductItem(myproducts.get(position).getProductName(),myproducts.get(position).getProductPrice());
                        for(int i=0;i<c.getMenu().size();i++)
                        {
                            if(c.getMenuproducts(i).equals(product))
                            {
                                p=i;
                                c.changeMenuproductquantity(p,0);
                            }

                        }

                        c.removeProduct(position);
                        c.removeProductquantity(position);
                        refresh(c.getProductquantity());
                        setListViewHeightBasedOnChildren(l);

                    }

                    //Totalling subtotal and total on click event
                    Sub_total=0;
                    for(int i=0;i<c.getProductArraylistsize();i++)
                    {
                        Sub_total=Sub_total+(Integer.parseInt(c.getProducts(i).getProductPrice())*c.getProductquantity(i));
                    }
                    subtotal.setText(Integer.toString(Sub_total));
                    Total=Sub_total+tax+delivery_charge;
                    total.setText(Integer.toString(Total));


                }
            });

            listViewHolder.CartProductItem.setText(myproducts.get(position).getProductName());
            listViewHolder.CartProductTotalPrice.setText(Integer.toString(Integer.parseInt(myproducts.get(position).getProductPrice())*(q.get(position))));
            return row;
        }
    /* this method is used to refresh the adapter on clicking plus and minus button*/
        public void refresh(ArrayList<Integer> list)
        {

            q=list;


            adapter.notifyDataSetChanged();
        }


    }


/* This method is used to use the list view inside a scroll view and to make it scrollable*/
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
