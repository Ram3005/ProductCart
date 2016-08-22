package com.ram.productcart;

import java.util.ArrayList;

/**
 * Created by Ram Mishra on 8/22/2016.
 */
/* This class contains the arraylist of products in the menu and in the cart....
    It also contains the methods for getting menu items detail and cart item details...
    It is a like a global class , so we can get details of menu items and cart items from anywhere by getting instance of this class...
*/


public  class Controller  {
    private static Controller instance;
    private Controller() {

    }
    public static synchronized Controller getInstance(){
        if(instance==null){
            instance=new Controller();
        }
        return instance;
    }




    // for menu
    private ArrayList<Integer> menuproductquantity=new ArrayList<Integer>();
    private ArrayList<ProductItem> menuproducts=new ArrayList<ProductItem>();


    // adding menu items
    public void setMenuproducts(ProductItem product){
        menuproducts.add(product);
    }
    //getting menu items at particular position
    public ProductItem getMenuproducts(int position)
    {
        return menuproducts.get(position);
    }
    //getting list of menu items
    public ArrayList<ProductItem> getMenu()
    {
        return menuproducts;
    }
    // setting quantity of menu items to 0 initially
    public void setMenuproductquantity()
    {    for(int i=0;i<menuproducts.size();i++)
        menuproductquantity.add(0);
    }
    //changing menu items quantity at any time
    public void changeMenuproductquantity(int index,int quantity)
    {
        menuproductquantity.set(index,quantity);
    }
    // getting array of quantity of items
    public ArrayList<Integer> getMenuproductquantity()
    {
        return menuproductquantity;
    }


    // for cart
    private ArrayList<CartProductItem> myproducts = new ArrayList<CartProductItem>();
    private ArrayList<Integer> productquantity=new ArrayList<Integer>();

    public CartProductItem getProducts(int pPosition){
        return myproducts.get(pPosition);
    }
    public void  setProducts(CartProductItem products){
        myproducts.add(products);
    }
    public void removeProduct(int index)
    {
        myproducts.remove(index);
    }

    public int  getProductArraylistsize(){
        return myproducts.size();
    }
    public boolean CheckProductInCart(CartProductItem aproduct){
        return myproducts.contains(aproduct);
    }

    public ArrayList<CartProductItem> getMyproducts() {
        return myproducts;
    }

    public int getProductquantity(int position)
    {
        return productquantity.get(position);
    }
    public void setProductquantity(int quantity)
    {
        productquantity.add(quantity);
    }
    public void removeProductquantity(int index)
    {
        productquantity.remove(index);
    }
    public void changeProductquantity(int index,int  quantity)
    {
        productquantity.set(index,quantity);
    }

    public ArrayList<Integer> getProductquantity()
    {
        return productquantity;
    }






}
