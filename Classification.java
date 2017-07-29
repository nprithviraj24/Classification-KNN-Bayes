package classification;
import java.sql.Connection;
import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.Math.*;

/**
 *
 * @author Raj
 */
public class Classification {

    public static void main(String[] args) throws SQLException {
       System.out.println("Enter 1 for Bayes type Classification, anything but 1 for KNN classification:  ");
        Scanner input = new Scanner(System.in);
       int option = input.nextInt();
        
       if(option == 1){
        Bayes b = new Bayes();
       } else {
           
           System.out.println("\t\t\t K-Nearest Neighbour!");
           System.out.println("____________________________________________");
           System.out.print("\nEnter the number of neighbours the data can have: ");
           int k = input.nextInt();
           kNN n = new kNN(k);
           //System.out.println("Data you want to classify: ");
           
       }
      } 
    
}


