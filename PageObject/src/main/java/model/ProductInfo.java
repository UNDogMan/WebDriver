package model;

public class ProductInfo implements Comparable<ProductInfo>{
    public String name;
    public String count;
    public String price;
    public String size;

    public ProductInfo(String name, String count, String price, String size) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.size = size;
    }

    public int compareTo(ProductInfo object) {
        if (name.compareTo(object.name) == 0 &&
                count.compareTo(object.count) == 0 &&
                price.compareTo(object.price) == 0 &&
                size.compareTo(object.size) == 0)
            return 0;
        if (name.compareTo(object.name) > 0)
            return 1;
        if (count.compareTo(object.count) > 0)
            return 1;
        if (price.compareTo(object.price) > 0)
            return 1;
        if (size.compareTo(object.size) > 0)
            return 1;
        return -1;
    }
}
