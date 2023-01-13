import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductManagement {

    static ArrayList<Product> products = new ArrayList<>();
    static ArrayList<Wholesaler> wholesalers= new ArrayList<Wholesaler>();
    static ArrayList<Retailer> retailers = new ArrayList<Retailer>();

    Map<Integer, Product> w_products = new HashMap<>();

    public static void main(String[] args) {

        try {
            Scanner sc1 = new Scanner(new File("/Users/archanadevi/IdeaProjects/Day1MainTask/src/products.csv"));
            Scanner sc2 = new Scanner(new File("/Users/archanadevi/IdeaProjects/Day1MainTask/src/wholesalers.csv"));
            Scanner sc3 = new Scanner(new File("/Users/archanadevi/IdeaProjects/Day1MainTask/src/retailers.csv"));

            sc1.useDelimiter("\n");
            while (sc1.hasNext()) {
                String[] prod_data = sc1.next().split(",", 3);
                products.add(new Product(Integer.parseInt(prod_data[0]), prod_data[1], Integer.parseInt(prod_data[2])));
            }
            sc1.close();

            viewProducts();

            sc2.useDelimiter("\n");
            while (sc2.hasNext()) {
                String[] wholesalers_data = sc2.next().split(",", 3);
                wholesalers.add(new Wholesaler(Integer.parseInt(wholesalers_data[0]), wholesalers_data[1]));
            }
            sc2.close();

            viewWholesalers();

            sc3.useDelimiter("\n");
            while (sc3.hasNext()) {
                String[] retailers_data = sc3.next().split(",", 3);
                retailers.add(new Retailer(Integer.parseInt(retailers_data[0]), retailers_data[1]));
            }

            viewRetailers();

            System.out.println("Allocate");
            allocateToWholesaler(1, 1, 5);
            viewWholesalers();

        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    static void viewProducts() {
        System.out.println("--------------------------");
        System.out.println("Products: ");
        System.out.println("ID  Name  Stock");
        for (Product p : products) {
            System.out.print(p.id + " " + p.name + " " + p.stock + "\n");
        }
        System.out.println();
    }

    static void viewWholesalers() {
        System.out.println("--------------------------");
        System.out.println("Wholesalers: ");
        System.out.println("ID  Name\t\t    Stock");
        for (Wholesaler w : wholesalers) {
            System.out.print("\n" + w.id + " " + w.name + " ");
            if(w.w_products!=null) {
                for(int i=0; i<w.w_products.size(); i++)
                    System.out.print(w.w_products.get(i).id + " " + w.w_products.get(i).name + " " + w.w_products.get(i).stock);
            }
            else {
                System.out.println((Product) null);
            }
        }
        System.out.println();
        viewProducts();
    }

    static void viewRetailers() {
        System.out.println("--------------------------");
        System.out.println("Retailers: ");
        System.out.println("ID  Name\t\t    Stock");
        for (Retailer r : retailers) {
            System.out.print(r.id + " " + r.name + " ");
            if(r.r_products!=null) {
                for(int i=0; i<r.r_products.size(); i++)
                    System.out.print(r.r_products.get(i).id + " " + r.r_products.get(i).name + " " + r.r_products.get(i).stock);
            }
            else {
                System.out.println((Product) null);
            }
        }
        System.out.println();
    }

    static void allocateToWholesaler (int w_id, int p_id, int stock) {

        for(Wholesaler wholesaler : wholesalers) {
            int index = wholesalers.indexOf(wholesaler);
            System.out.println("index - " + index);
            if(wholesaler.id == w_id) {
                System.out.println("id" + wholesaler.id);
                for(Product product : products) {

                    int p_index = wholesalers.indexOf(product);

                    System.out.println("p_index " + p_index + " " + product.id + product.name);

                    if(product.id == p_id) {
                        System.out.println("pid" + product.id);
                        if(product.stock >= stock) {
                            System.out.println("pstock " + product.stock);
                            System.out.println("stock " + stock);

                            wholesalers.get(index).w_products.get("w_products").setStock(stock);

                            System.out.println("stock in jdsh " + wholesalers.get(index).w_products.get("w_products").id);

                            wholesalers.get(index).w_products.put("w_products", product);
                        }
                    }
                }
            }
            for(Map.Entry w_prod : wholesaler.w_products.entrySet()) {
                System.out.println(w_prod.getValue());
            }
        }
        viewWholesalers();
    }

}

class Product {
    int id;
    String name;
    int stock=0;

    Product(int id, String name, int stock) {
        this.id=id;
        this.name=name;
        setStock(stock);
    }
    void setStock(int stock) {
        this.stock=stock;
    }
}

class Wholesaler{
    int id;
    String name;
    Map<String, Product> w_products = new HashMap<String, Product>();

    Wholesaler(int id, String name) {
        this.id=id;
        this.name=name;
//        this.w_products.get(2).
    }

    Wholesaler() {

    }
}

class Retailer {
    int id;
    String name;
    ArrayList<Product> r_products = new ArrayList<Product>();

    Retailer(int id, String name) {
        this.id=id;
        this.name=name;
        this.r_products=null;
    }
}
