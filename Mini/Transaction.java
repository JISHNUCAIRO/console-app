import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class Transaction {
    public static void main(String[] gtgs)throws ClassNotFoundException,SQLException,InterruptedException
    {
        Scanner sc = new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection x=DriverManager.getConnection("jdbc:mysql://localhost/ticket","root","jishnu321");
        String s3="select name from user";
        PreparedStatement p3=x.prepareStatement(s3);
        ResultSet rs1=p3.executeQuery();
        String name ="";
        while(rs1.next())
          name =rs1.getString(1);
        String s="select price from transaction";
        PreparedStatement p=x.prepareStatement(s);
        ResultSet rs=p.executeQuery();
        int c=1;
        rs.next();
        int price = rs.getInt(1);
        while(rs.next())
         c++;    
        
              System.out.println("Welcome "+name+"!!");
              Thread.sleep(1500);
              System.out.println("There are total of "+c+" tickets available");
              Thread.sleep(1500);
              System.out.println("How many tickets do you need?");
              int n = sc.nextInt();
              while(true)
              {
                  Thread.sleep(1000);
                  if(n>0)
                  break;
                  System.out.println("Invalid number of tickets!!!");
                  Thread.sleep(1000);
                  System.out.println("Please enter a valid no of tickets needed.");
                  n = sc.nextInt();
              }
              while(true)
              { 
                  if(n>c)
                  {
                    Thread.sleep(1000);
                    System.out.println("Sorry,we don't have enough tickets available!!");
                    Thread.sleep(1000);
                  System.out.println("Please enter no of tickets less than "+c+"!!!");
                  n=sc.nextInt();
                   
                 }
                else
                 break;
             }
              int total = price*n;
              Thread.sleep(2000);
              System.out.println("The total price for "+n+" tickets is "+total);
              Thread.sleep(1500);
              System.out.print("Please pay the amount:");
              Thread.sleep(1500);
              while(true)
              {
                  int tot = sc.nextInt();
                  int dum=tot;
                  if(dum==total){
                      Thread.sleep(1500);
                      System.out.println("Thanks for booking the tickets!!");
                      break;}
                      else if(tot>total ||tot>Integer.MAX_VALUE){
                          System.out.println("You have entered invalid amount!!!");
                          Thread.sleep(1500);
                          System.out.println("Please pay the correct amount!!");}
                          else {
                          Thread.sleep(1500);
                          dum=total-tot;
                          total=dum;
                  System.out.println("Please pay the remaining amount "+dum);}
              }  
              String s1 = "delete from Transaction where transaction_id=?";
              PreparedStatement p1=x.prepareStatement(s1);
              while(n>0){
                     p1.setInt(1,c);
                     c--;
                     n--;
                     p1.executeUpdate();
                 }
                 sc.close();
    }
}
