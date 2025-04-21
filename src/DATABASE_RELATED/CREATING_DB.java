package DATABASE_RELATED;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CREATING_DB {
	private static final String driver ="com.mysql.cj.jdbc.Driver";
	private static final String url ="jdbc:mysql://localhost:3306/";
	private static final String username ="root";
	private static final String password ="root";
	private static PreparedStatement pmst;
	private static Connection conn;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Scanner sc = new Scanner(System.in);
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("enter database name: ");
			String sql = "Create database "+sc.nextLine();
			//String sql = "drop database "+sc.nextLine();
			//String sql = "show databases;";
			pmst = conn.prepareStatement(sql);
			int i=pmst.executeUpdate();
			
			/*read database
			ResultSet rs = pmst.executeQuery();
			System.out.println("Databases on the server:");
            while (rs.next()) {
                String dbName = rs.getString(1); // Get the database name from the first column
                System.out.println(dbName);
            }*/

			if(i>0) {
				System.out.println("DataBase successfully created");
			}
			else {
				System.out.println("DataBase not created");
			}
			pmst.close();
			conn.close();
			sc.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

	}
	private static void getByid() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
			String sql = "Select * from " + sc.next() + "where order id =?";
			pmst= conn.prepareStatement(sql);
			System.out.println("enter order id ");
			pmst.setLong(1, sc.nextLong());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private static void getAll() {
		// TODO Auto-generated method stub
		try {
			Scanner sc = new Scanner(System.in);
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
			String sql = "Select * from " + sc.next();
			
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("login_id"));
				System.out.println("name "+rs.getString("login_password"));
				System.out.println("email "+rs.getString("login_email"));
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	

}
