package com.ram.productcart;

/**
 * Created by Ram Mishra on 8/22/2016.
 */
/*  This class is for the products in the menu */
public class ProductItem {
    private String productName;
    private String productPrice;
    public ProductItem(String productName,String productPrice){
        this.productName = productName;
        this.productPrice = productPrice;

    }

    public String getProductName(){
        return productName;
    }



    public String getProductPrice(){
        return productPrice;
    }

 // These two methods below are for comparing a new product with the existing product  in the arraylist that they are equal or not

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((productName == null) ? 0 : productName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ProductItem other = (ProductItem) obj;
        if (productName == null) {
            if (other.productName != null)
                return false;
        } else if (!productName.equals(other.productName))
            return false;
        return true;
    }
}
