package presentation;
import DAO.*;
import model.Client;
import model.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class FileParser {

    Vector<Integer> vec = new Vector<Integer>();
    Vector<String> vec2 = new Vector<String>();

    /***
     * In aceasta metoda incercam sa citim informatiile din fiser si sa le transformam astefel incat sa le putem folosi
     * --tot in aceasta metoda sunt apelate toate operatiile (insert,delete,report,order)
     * --dupa ce este comparata informatia din fisier cu un anumit string se aleage efectuarea unei operatii
     */
    public void citireFisier(){
        int i=0;
        try{
            File Obj = new File("Comenzi.txt");
            Scanner myReader=new Scanner(Obj);
            InsertProduct insertProduct= new InsertProduct();
            InsertClient insertClient=new InsertClient();
            DeleteClient deleteClient=new DeleteClient();
            DeleteProduct deleteProduct=new DeleteProduct();
            ReportClient reportClient=new ReportClient();
            ReportProduct reportProduct= new ReportProduct();
            ReportOrder reportOrder=new ReportOrder();
            Order orderr =new Order();
            PDF afisarepdf = new PDF();
            OrderWithPrice orderWithPrice =new OrderWithPrice();
            Update update1= new Update();
            while(myReader.hasNextLine()){
                String data = myReader.nextLine ();

                String[] datasplit=data.split("[  : , ] ");
                for (String str :datasplit) {



                        vec2.add(str);
                        //System.out.println(vec2.get(i));
                        i++;

                }
            }
                for(int j=0; j< vec2.size();j++){
                  // System.out.println(vec2.get(j));
                    if(vec2.get(j).equals("Insert client")) {
                        Client client= new Client(0,vec2.get(j+1),vec2.get(j+2));
                       insertClient.insert(client);

                    }
                    if(vec2.get(j).equals("Report client")){
                       afisarepdf.crearePDF(reportClient.report());
                      // System.out.println( reportClient.report());
                    }
                    if(vec2.get(j).equals("Delete client")){
                        deleteClient.delete(vec2.get(j+1),vec2.get(j+2));
                    }
                    if(vec2.get(j).equals("Insert product")){
                        Product product= new Product(0,vec2.get(j+1),Integer.parseInt(vec2.get(j+2)),Float.parseFloat(vec2.get(j+3)));
                        insertProduct.insert(product);
                    }
                    if(vec2.get(j).equals("Report product")){
                       afisarepdf.crearePDF(reportProduct.report());
                         //   System.out.println(reportProduct.report());
                    }
                    if(vec2.get(j).equals("Delete Product")){
                         deleteProduct.delete(vec2.get(j+1));
                    }
                    if(vec2.get(j).equals("Order")){
                        int x;

                                if((orderr.findproductQuantity(vec2.get(j+2))-Integer.parseInt(vec2.get(j+3)))>=0){
                      x =(orderr.findproductQuantity(vec2.get(j+2))-Integer.parseInt(vec2.get(j+3)));}else{ x=0;}
                       orderr.order(orderr.findByclientName(vec2.get(j+1)),orderr.findByproductName(vec2.get(j+2)),Integer.parseInt(vec2.get(j+3)), vec2.get(j+1),vec2.get(j+2));
                     update1.update(x,orderr.findByproductName(vec2.get(j+2)));
                       orderr.orderWithPrice(orderr.findorderID(vec2.get(j+1)),orderr.findByclientName(vec2.get(j+1)),(Integer.parseInt(vec2.get(j+3)) * orderr.findPrice(vec2.get(j+2))));
                       afisarepdf.crearePDF((orderWithPrice.report()));
                      //  System.out.println(orderWithPrice.report());
                       // System.out.println( orderr.findproductQuantity(vec2.get(j+2)));
                }
                    if(vec2.get(j).equals("Report order")){
                        afisarepdf.crearePDF((reportOrder.report()));
                    //  System.out.println(reportOrder.report());
                    }

                }



            myReader.close();


        } catch (FileNotFoundException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
//
