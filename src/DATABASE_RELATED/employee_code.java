package DATABASE_RELATED;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class employee_code {



	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String username = "root";
	private static final String password = "root";
	private static Connection conn;
	private static PreparedStatement pmst;
	public static void main(String[] args) {
		int choice;
		do {
			Scanner src = new Scanner(System.in);
			displayMenu();
			System.out.println("choose your choice:");
			
			choice = Integer.parseInt(src.next());
			switch (choice) {
			case 1:
				create_database();
				break;
			case 2:
				drop_database();
				break;
			case 3:
				create_table();
				break;
			case 4:
				data_insertion();
				break;
			case 5:
				delete_by_email();
				break;
			case 6:
				updata_data();
				break;
			case 7:
				getby_email();
				break;
			case 8:
				getAll();
				break;
			case 9:
				System.exit(0);
				break;
			case 10:
				show_db();
				break;
			case 11:
				login();
				break;
			default:
				System.out.println("Invalid");
			}
		} while (choice > 0);
	}

	private static void create_table() {
		// TODO Auto-generated method stub
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url = "jdbc:mysql://localhost:3306/" +src.next();
			conn = DriverManager.getConnection(url, username, password);
			//Create table 
			System.out.println("enter table name for creation: ");
			String sql = "CREATE TABLE "+src.next()+"(emp_id INT(10) PRIMARY KEY,emp_email VARCHAR(50),emp_password VARCHAR(8),emp_sal DOUBLE)";
			
			pmst = conn.prepareStatement(sql);
			pmst.executeUpdate(sql);
		
		conn.close();
		pmst.close();
	
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	}

	private static void login() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url = "jdbc:mysql://localhost:3306/"+src.next();
			conn = DriverManager.getConnection(url, username, password);
			String sql = "select * from employee where emp_id = ? and emp_password = ?";
			pmst = conn.prepareStatement(sql);	
			System.out.println("enter employee id:");
			pmst.setInt(1, src.nextInt());
			System.out.println("enter the employee password");
			pmst.setString(2, src.next());
			ResultSet rs = pmst.executeQuery();
			if (rs.next()) {
				System.out.println("login successfull...!");
			}
			else {
				System.out.println("Invalid order id or order pincode..!");
			}
			conn.close();
			pmst.close();
			src.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 */
	/**
	 * 
	 */
	private static void show_db() {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver);
			String url = "jdbc:mysql://localhost:3306/";
			conn = DriverManager.getConnection(url, username, password);
			
			
			String sql = "show databases";
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			System.out.println("existing databases are:");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			rs.close();
			conn.close();
			pmst.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void getAll() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url = "jdbc:mysql://localhost:3306/"+src.next();
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter table name:");
			String sql = "select * from "+ src.next();
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
				System.out.println("employee id :"+ rs.getInt("emp_id"));
				System.out.println("employee email :"+ rs.getString("emp_email"));
				System.out.println("employee password :"+ rs.getString("emp_password"));
				System.out.println("employee salary :"+ rs.getDouble("emp_sal"));
			
			}
			pmst.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void getby_email() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url = "jdbc:mysql://localhost:3306/"+ src.next();
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter table name:");
			String sql ="select * from "+src.next()+" where emp_id=?";
			pmst = conn.prepareStatement(sql);
			System.out.println("enter order id:");
			pmst.setInt(1,  src.nextInt());
			ResultSet rs = pmst.executeQuery();
			while(rs.next()) {
				System.out.println("employee id :"+ rs.getInt("emp_id"));
				System.out.println("employee email :"+ rs.getString("emp_email"));
				System.out.println("employee password :"+ rs.getString("emp_password"));
				System.out.println("employee salary :"+ rs.getDouble("emp_sal"));
			}
			conn.close();
			pmst.close();
			} catch (Exception e) {
				e.printStackTrace();
		    
		}

		
	}

	private static void updata_data() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url = "jdbc:mysql://localhost:3306/" + src.next();
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter table name");
			String sql = "update " + src.next() + " SET emp_sal = ? where emp_id = ? AND emp_email = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("enter new salary :");
			pmst.setDouble(1, src.nextDouble());
			System.out.println("enter employee id:");
			pmst.setInt(2, src.nextInt());
			System.out.println("enter employee email:");
			pmst.setString(3, src.next());
			
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("data updated");
			}
			else {
				System.out.println("data is not updated");
			}
			conn.close();
			pmst.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	private static void delete_by_email() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url = "jdbc:mysql://localhost:3306/" + src.next();
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter table name");
			String sql = "delete from " + src.next() + " where emp_id = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("enter employee id:");
			pmst.setInt(1, src.nextInt());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("data deleted");
			}
			else {
				System.out.println("data is not deleted");
			}
			conn.close();
			pmst.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	private static void data_insertion() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url = "jdbc:mysql://localhost:3306/" +src.next();
			conn = DriverManager.getConnection(url, username, password);
	
			System.out.println("enter table name to insert data: ");
			String sql1 ="insert into "+src.next()+"(emp_id ,emp_email ,emp_password,emp_sal) values(?,?,?,?)";
			pmst = conn.prepareStatement(sql1);
			System.out.println("enter the employee id:");
			pmst.setInt(1, src.nextInt());
			System.out.println("enter the employee email:");
			pmst.setString(2 ,src.next());
			System.out.println("enter the employee password");
			pmst.setString(3, src.next());
			System.out.println("enter the employee salary");
			pmst.setDouble(4, src.nextDouble());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("data is inserted");
			}
			else {
				System.out.println("data is not inserted");
			}
			conn.close();
			pmst.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static void drop_database() {
		try {
			Class.forName(driver);
			String url = "jdbc:mysql://localhost:3306/";
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter database name");
			Scanner src = new Scanner(System.in);
			String sql = "drop database " + src.next();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if (i == 0) {
				System.out.println("database droped");
			}
			else {
				System.out.println("database is not droped");
			}
			conn.close();
			pmst.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static void create_database() {
		try {
			Class.forName(driver);
			String url = "jdbc:mysql://localhost:3306/";
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter database name");
			Scanner src = new Scanner(System.in);
			String sql = "create database " + src.next();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("database created");
			}
			else {
				System.out.println("database is not created");
			}
			conn.close();
			pmst.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static void displayMenu() {
		System.out.println("\t1.create database");
		System.out.println("\t2.drop database");
		System.out.println("\t3.create table");
		System.out.println("\t4.data insertion");
		System.out.println("\t5.delete by email");
		System.out.println("\t6.updata data");
		System.out.println("\t7.getby email");
		System.out.println("\t8.getAll");
		System.out.println("\t9.Exit");
		System.out.println("\t10.show database");
		System.out.println("\t11.login");
		
	}

}