package DATABASE_RELATED;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class insert_data {
	private static final String driver ="com.mysql.cj.jdbc.Driver";
	private static final String url ="jdbc:mysql://localhost:3306/dt_name";
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
			String sql = "CREATE TABLE login(login_id INT(10) PRIMARY KEY,login_email VARCHAR(50),login_password VARCHAR(8))";
			//String sql ="DROP TABLE login2";
			pmst = conn.prepareStatement(sql);
			pmst.executeUpdate(sql);
			
			String sql1 = "Insert into login(login_id,login_email,login_passwor) values(?,?,?)";
			
			pmst = conn.prepareStatement(sql1);
			System.out.println("enter a login id");
			pmst.setString(1,sc.next());
			System.out.println("enter a login email");
			pmst.setString(2,sc.next());
			System.out.println("enter a login password");
			pmst.setString(3,sc.next());
			int i=pmst.executeUpdate();
			if(i>0) {
				System.out.println("data successfully inserted in the table");
			}
			else {
				System.out.println("data not inserted in the  table");
			}
			pmst.close();
			conn.close();
			sc.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
