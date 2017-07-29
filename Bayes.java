/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Bayes {
     Connection c = null;
     
        Bayes() throws SQLException{
       
       try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost/jdbc","postgres","tiger");
        }catch(Exception e){
            System.out.println("Unable to load driver!");
            System.exit(0);
        }      
       execute();
      } 

    public void execute() throws SQLException{
        Statement sql, stmt1,stmt2 = c.createStatement();
//       ResultSet rs = sql.executeQuery("Select * from Bayes;");    
        stmt1 = c.createStatement();
        stmt2 = c.createStatement();
        //stmt3 = c.createStatement();
        double c3=0;
        double pc1,pc2;
        
        ResultSet rs = stmt1.executeQuery("SELECT * FROM Bayes;");
        
        while(rs.next())
        	c3++;
        
        ResultSet rs2= stmt2.executeQuery("SELECT * FROM Bayes where computer='y'");
        double c1=0;
       
        while(rs2.next())
        c1++; 
        
        //System.out.println("\n"+c1);
        //System.out.println("\n"+c3);
        
        pc1=c1/c3;
        pc2=1-pc1;
        
        ResultSet rsc1,rsc2;
        double rcc1=0,rcc2=0,rpc1=0,rpc2=0;
        rsc1=stmt2.executeQuery("SELECT * FROM Bayes WHERE age<35 AND computer='y';" );
        while(rsc1.next())
        	rcc1++; 
        rcc1=rcc1/c1;
        
        rsc2=stmt2.executeQuery( "SELECT * FROM Bayes WHERE age<35 AND computer='n';" );
        while(rsc2.next())
        	rcc2++; 
        rcc2=rcc2/(c3-c1);
        
        rsc1=stmt2.executeQuery( "SELECT * FROM Bayes WHERE income>='30000' AND computer='y';" );
        while(rsc1.next())
        	rpc1++; 
        rpc1=rpc1/c1;
        rsc2=stmt2.executeQuery( "SELECT * FROM Bayes WHERE income>='30000' AND computer='n';" );
        while(rsc2.next())
        	rpc2++; 
        rpc2=rpc2/(c3-c1);
        
        double hf,ha;
        hf=rcc1*rpc1;
        ha=rcc2*rpc2;
        //System.out.println("\n"+hf+" "+ha);
        
        if(hf>ha)
        	 System.out.println("\n The customer will most likely buy computer");
        else
        	 System.out.println("\n The customer most likely will NOT buy the computer");
         
        rs.close();
        stmt2.close();
        c.close();
    }
}
    

