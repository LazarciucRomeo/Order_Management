
package Start;

import DAO.DeleteClient;
import DAO.DeleteProduct;
import DAO.InsertClient;
import DAO.InsertProduct;
import model.Client;
import model.Product;
import presentation.*;

import java.io.IOException;


public class MainClass {

    public static void main(String[] args) throws IOException {
      FileParser file = new FileParser();
      PDF pdfq =new PDF();
      file.citireFisier();

    }

}
