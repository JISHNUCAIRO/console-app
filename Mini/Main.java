import java.util.Scanner;
import java.sql.*;
import java.util.regex.*;
public class Main {
    public static void main(String[] gst)throws ClassNotFoundException,SQLException,InterruptedException
    {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        String email = sc.nextLine();
       
         
        while(true)
        {
            if(!email.contains("@gmail.com"))
            {
                Thread.sleep(1500);
                System.out.println("Invalid Mail!!");
                Thread.sleep(1500);
                System.out.println("Please enter a valid email address");
                 email = sc.nextLine();
            }
            else 
            break;
        }
        User u = new User(name,email);
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection x2=DriverManager.getConnection("jdbc:mysql://localhost/ticket","root","jishnu321");
         String s2="select * from user order by user_id desc limit 1";
         PreparedStatement p2=x2.prepareStatement(s2);
         ResultSet r2=p2.executeQuery();
         r2.next();
         int n = r2.getInt(1)+1;
          String s3="insert into user value(?,?,?)";
         PreparedStatement p3=x2.prepareStatement(s3);
           p3.setInt(1,n);
           p3.setString(2,name);
           p3.setString(3,email);
           p3.executeUpdate();
    }
}
class User{
    private String name;
    private String email;
    
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
