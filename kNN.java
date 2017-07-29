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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Prithvi Raj 14011M3510
 */
class kNN {
    int k;
     Connection c = null;
     List<Point> points = new ArrayList<Point>();
     List<Point> Neighbours = new ArrayList<Point>();
     
     
    kNN(int k) throws SQLException{
       
       try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost/jdbc","postgres","tiger");
        }catch(Exception e){
            System.out.println("Unable to load driver!");
            System.exit(0);
        }
        this.k = k;
        execute();
    }
    
    public void execute() throws SQLException{
        Scanner input = new Scanner(System.in);
        
      Statement sql  =  c.createStatement(); 
      String l;
      double x,y;
      int i,countA = 0, countB=0;
      
      ResultSet rs = sql.executeQuery("SELECT * from points;");
//      rs.last();
//      int rowCount = rs.getRow();
//      rs.beforeFirst();
//      String[] labels = new String[rowCount]
      while(rs.next()){
//          l = rs.getString(1);
//          x = rs.getDouble(2);
//          y = rs.getDouble(3);
//          Point k = new Point(x,y,l);
          points.add(new Point( rs.getDouble(2), rs.getDouble(3), rs.getString(1) ));
      }
      
        System.out.print("\t\t x co-ordinate: ");  
         x = input.nextDouble();
        System.out.print("\n\t\t y co-ordinate: ");
         y = input.nextDouble();
         
//         String[] Labels = new String[];
//         for(i=0; i<points.size(); i++){
//         }
       i=1;
        while(Neighbours.size() <= k || i<points.size()) {       
//            for(i=0;i<points.size();i++){
//                System.out.println("comparing x: "+x+" with radius ");
                if( points.get(i).x <= (x-i) && points.get(i).y <= (y-i) ){
                    Point pDist = points.get(i);
                    pDist.calcDistance(x, y, pDist);
                    Neighbours.add(pDist);
                } else if( points.get(i).x<= (x+i) && points.get(i).y <= (y+i) ){
                    Point pDist = points.get(i);
                    pDist.calcDistance(x, y, pDist);
                    Neighbours.add(pDist);
                }
//            }
            i++;
        }      
        Collections.sort(Neighbours, new Comparator<Point>(){
            @Override
            public int compare(Point o1, Point o2) {
                return Double.valueOf(o1.dist).compareTo(o2.dist);
                //To change body of generated methods, choose Tools | Templates.
            }
        
        });
//        System.out.println("neighbours size "+Neighbours.size());
        if(Neighbours.size() >= k){
            Neighbours.subList(k+1, Neighbours.size()).clear();
        }
            int occurrencesOfA = Collections.frequency(Neighbours, "A");
        System.out.println("");
        int occurrencesOfB = Collections.frequency(Neighbours, "B");
//         list.subList(from, to).clear();


        for(i=1; i<Neighbours.size(); i++){
            System.out.println("Nearest object "+i+" with class label "+Neighbours.get(i-1).label);           
        }
    }
}

class Point {

    String label;
    double x;
    double y;
    double dist;
    
    Point(double x, double y, String label){
        this.x = x;
        this.y = y;
        this.label = label;
//        calcDistance(x,y);
    }

    Point() {
         //To change body of generated methods, choose Tools | Templates.
    }
    
    public void calcDistance(double x, double y, Point p2){
//        System.out.println("Dist between ("+p2.x+","+p2.y+") and ("+ x+","+y+") is: " );
        this.dist = Math.pow((x-p2.x),2) + Math.pow((y-p2.y),2) ;  //calculated only for neighbours
        System.out.println("\n\nDist between ("+p2.x+","+p2.y+") and ("+ x+","+y+") is: "+this.dist+ "  \n");
    }

//    @Override
//    public int compare(Object o1, Object o2) {
//        Point p1 = (Point)o1;
//        Point p2 = (Point)o2;
//        
//        return Double.valueOf(p1.dist).compareTo(p2.dist);
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
