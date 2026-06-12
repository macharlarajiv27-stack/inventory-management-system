package com.rajiv;

import java.util.List;
import  java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        SessionFactory sf =new Configuration()
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Supplier.class)
                .addAnnotatedClass(InventoryDetail.class)
                .configure()
                .buildSessionFactory();
        Session session=sf.openSession() ;
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("\n===== INVENTORY TRACKER =====");
            System.out.println("1.  Add Product");
            System.out.println("2.  View Product");
            System.out.println("3.  Update Product");
            System.out.println("4.  Delete Product");
            System.out.println("5.  View All Products");
            System.out.println("6.  Supplier");
            System.out.println("7.  View Supplier with products");
            System.out.println("8.  Search Product");
            System.out.println("9.  Expensive Product");
            System.out.println("10. Product Summary");
            System.out.println("11. Exit");

            System.out.println("Enter Choice");
            int choice= sc.nextInt();

            switch (choice) {
                case 1:

                    Product p = new Product();
                    InventoryDetail iv = new InventoryDetail();
                    Supplier s = new Supplier();

                    sc.nextLine();
                    p.setInventoryDetail(iv);
                    System.out.println("Enter Product Name");
                    p.setName(sc.nextLine());
                    System.out.println("Enter Product Price");
                    p.setPrice(sc.nextDouble());
                    System.out.println("Enter Product Quantity");
                    p.setQuantity(sc.nextInt());

                    sc.nextLine();
                    System.out.println("Enter Warehouse location");
                    iv.setWarehouseLocation(sc.nextLine());
                    System.out.println("Enter Last update date");
                    iv.setLastUpdated(sc.nextLine());
                    sc.nextLine();


                    session.beginTransaction();
                    System.out.println("Enter Supplier Id");
                    int sid = sc.nextInt();

                    Supplier supplier = session.get(Supplier.class,sid);

                    p.setSupplier(supplier);

                    session.persist(p);

                    session.getTransaction().commit();

                    System.out.println("Product Added Successfully");

                    break;

                case 2:
                    int id = sc.nextInt();
                    Product p2 = session.get(Product.class, id);
                    if (p2 != null) {
                        System.out.println("ID: " + p2.getId());
                        System.out.println("Name: " + p2.getName());
                        System.out.println("Price: " + p2.getPrice());
                        System.out.println("Quantity: " + p2.getQuantity());
                    } else {
                        System.out.println("Product not found");
                    }
                    if (p2.getInventoryDetail() != null){
                        System.out.println("Warehouse: " +p2.getInventoryDetail().getWarehouseLocation());
                        System.out.println("LastUpdated: " +p2.getInventoryDetail().getLastUpdated());
                    }else {
                        System.out.println("Detail Not found");
                    }
                    if (p2.getSupplier() != null){
                        System.out.println("Supplier Name: "+p2.getSupplier().getName());
                    }
                    break;
                case 3:
                    System.out.println("Enter Product Id");
                    int uid = sc.nextInt();
                    Product p3 = session.get(Product.class, uid);

                    if (p3 != null) {
                        System.out.println("New Price");
                        double nprice = sc.nextDouble();
                        p3.setPrice(nprice);
                        session.beginTransaction();
                        session.merge(p3);
                        session.getTransaction().commit();
                        System.out.println("Updated Succesfully");
                    } else {
                        System.out.println("Product not found");
                    }
                    break;

                case 4:
                    System.out.println("Enter product id");
                    int pid = sc.nextInt();
                    Product p4 = session.get(Product.class, pid);
                    if (p4 != null) {
                        session.beginTransaction();
                        session.remove(p4);
                        session.getTransaction().commit();
                        System.out.println("Product Deleted");
                    } else {
                        System.out.println("Product not found");
                    }
                    break;

                case 5:
                    List<Product> product = session.createQuery("From Product", Product.class)
                            .getResultList();
                    for (Product p5 : product) {
                        System.out.println(
                                p5.getId() + " "
                                        + p5.getName() + " "
                                        + p5.getPrice() + " "
                                        + p5.getQuantity() + " "
                                        + p5.getInventoryDetail().getWarehouseLocation() + " "
                                        + p5.getInventoryDetail().getLastUpdated() + " "
                                        + p5.getSupplier().getName()
                        );
                    }
                    break;

                case 6:
                    Supplier sp = new Supplier();
                    sc.nextLine();
                    System.out.println("Enter supplier Name");
                    sp.setName(sc.nextLine());
                    session.beginTransaction();
                    session.persist(sp);
                    session.getTransaction().commit();
                    System.out.println("Supplier addded");
                    break;
                case 7:
                    System.out.println("Enter Supplier Id");
                    int sid1=sc.nextInt();
                    Supplier supplier1=session.get(Supplier.class,sid1);
                    if(supplier1 != null){
                        System.out.println(supplier1.getName());
                        System.out.println("Products:");
                        for (Product product1 :supplier1.getProduct()){
                            System.out.println(product1.getName());
                        }
                    }break;
                case 8:
                    sc.nextLine();
                    System.out.println("Product Name to search");
                    String pname=sc.nextLine();
                    List<Product> pro=session.createQuery("from Product where name =:pname", Product.class)
                            .setParameter("pname",pname)
                            .getResultList();
                    for(Product pro1:pro){
                        System.out.println(pro1.getId()+" "
                                           +pro1.getName()+" "
                                           +pro1.getPrice()+" "
                                           +pro1.getQuantity());
                    }break;
                case 9:
                    sc.nextLine();
                    System.out.println("Enter price");
                    double pri=sc.nextDouble();
                    List<Product> expensive=session.createQuery("from Product where price>:pri", Product.class)
                            .setParameter("pri",pri)
                            .getResultList();
                    for (Product pro3:expensive){
                        System.out.println(pro3.getName()+" "+pro3.getPrice());
                    }break;
                case 10:
                    List<Object []> summary=session.createQuery("select name, price from Product", Object[].class)
                            .getResultList();
                    for (Object []obj: summary){
                        System.out.println("Name: "+obj[0]+" "
                                           +"Price: "+obj[1] );
                    }break;
                case 11:
                    sf.close();
                    session.close();
                    System.out.println("Application closed");
                    System.exit(0);

            }
        }
    }
}