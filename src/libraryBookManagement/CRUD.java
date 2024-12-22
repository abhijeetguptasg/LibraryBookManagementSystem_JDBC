package libraryBookManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CRUD {

	public static void main(String[] args) throws SQLException {
		
		Scanner sc= new Scanner(System.in);
		
		while(true)
		{
			System.out.println("Press 1 to Enter Library.");
			System.out.println("Press 0 to exit.");
			
			int ip= sc.nextInt();
			
			if(ip==1)
			{
				System.out.println("1. Insert Book");
				System.out.println("2. Explore All Books");
				System.out.println("3. Update Book");
				System.out.println("4. Delete Book");
				
				int ip2= sc.nextInt();
				
				switch (ip2) {
				case 1: {
					
					System.out.println("Enter Book Id:-");
					int id= sc.nextInt();
					
					System.out.println("Enter Book Name :-");
					String name= sc.next();
					
					sc.nextLine();
					System.out.println("Enter Book Author :-");
					String author= sc.next();
					
					System.out.println("Enter Book Price :-");
					int price = sc.nextInt();
					
					System.out.println("Enter Book Type :-");
					String type= sc.next();
					
					insertBook(id, name, author, price, type);
					System.out.println();
					break;
				}
				
				case 2:
				{
					ResultSet rs=  readAllBooks();
					
					while(rs.next())
					{
						System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getInt(4)+"  "+rs.getString(5));
					}
					break;
				}
				
				case 3:
				{
					System.out.println("1. Update Book Name.");
					System.out.println("2. Update Book Author.");
					System.out.println("3. Update Book Price.");
					System.out.println("4. Update Book Type.");
					
					int ip3= sc.nextInt();
					
					switch (ip3) {
					 
					case 1: {
						
						System.out.println("Enter book Id to Update :-");
						int id= sc.nextInt();
						
						System.out.println("Enter a New Name :-");
						String name= sc.next();
						
						updateBookName(id, name);
						
						System.out.println();
						break;
					}
					
					case 2 :
					{
						System.out.println("Enter book Id to Update :-");
						int id= sc.nextInt();
						
						System.out.println("Enter a New Author :-");
						String author= sc.next();
						
						updateBookAuthor(id, author);
						
						System.out.println();
						break;
					}
					case 3:
					{
						System.out.println("Enter book Id to Update :-");
						int id= sc.nextInt();
						
						System.out.println("Enter a New Price :-");
						int price= sc.nextInt();
						
						updateBookPrice(id, price);
						System.out.println();
						break;
					}
					case 4:
					{
						System.out.println("Enter book Id to Update :-");
						int id= sc.nextInt();
						
						System.out.println("Enter a New Type :-");
						String type= sc.next();
						
						updateBookType(id, type);
						
						System.out.println();
						
						break;
					}
					default:
						System.out.println("Invalid Input !");
						System.out.println();
					}
					break;
				}
				case 4:
				{
					System.out.println("Enter a book Id to Delete :-");
					int id= sc.nextInt();
					
					deleteBook(id);
					System.out.println();
					
					break;
				}
				default:
					System.out.println("Enter a Valid Input.");
				}
			}
			else if(ip==0)
			{
				break;
			}
			
		}
		
		sc.close();
		
		
	}
	public static void insertBook(int id, String name, String author, int price, String type)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/shivani-jdbc","root","Tiger");
			
			PreparedStatement ps= con.prepareStatement("insert into library value(?,?,?,?,?)");
			
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, author);
			ps.setInt(4, price);
			ps.setString(5, type);
			
			ps.execute();
			
			System.out.println("Data Inserted!");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ResultSet readAllBooks()
	{
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shivani-jdbc","root","Tiger");
			
			Statement st= con.createStatement();
			
			ResultSet rs= st.executeQuery("select * from library");
			
			return rs;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void updateBookName(int id, String newName)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/shivani-jdbc","root","Tiger");
			
			PreparedStatement ps = con.prepareStatement("update library set name=? where bookId= ?");
			ps.setString(1, newName);
			ps.setInt(2, id);
			
			int result= ps.executeUpdate();
			if(result != 0)
			{
				System.out.println("Book Name Updated Updated!");
			}
			else
			{
				System.out.println("No Data available for this ID");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateBookAuthor(int id, String newAuthor)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/shivani-jdbc","root","Tiger");
			
			PreparedStatement ps = con.prepareStatement("update library set author=? where bookId= ?");
			ps.setString(1, newAuthor);
			ps.setInt(2, id);
			
			int result= ps.executeUpdate();
			if(result != 0)
			{
				System.out.println("Book Auhtor Updated!");
			}
			else
			{
				System.out.println("No Data available for this ID");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateBookPrice(int id, int price)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/shivani-jdbc","root","Tiger");
			
			PreparedStatement ps = con.prepareStatement("update library set price=? where bookId= ?");
			ps.setInt(1, price);
			ps.setInt(2, id);
			
			int result= ps.executeUpdate();
			if(result != 0)
			{
				System.out.println("Book Price Updated!");
			}
			else
			{
				System.out.println("No Data available for this ID");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateBookType(int id, String newType)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/shivani-jdbc","root","Tiger");
			
			PreparedStatement ps = con.prepareStatement("update library set type=? where bookId= ?");
			ps.setString(1, newType);
			ps.setInt(2, id);
			
			int result= ps.executeUpdate();
			if(result != 0)
			{
				System.out.println("Address Updated!");
			}
			else
			{
				System.out.println("No Data available for this ID");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteBook(int id)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/shivani-jdbc","root","Tiger");
			
			PreparedStatement ps = con.prepareStatement("delete from library where bookId=?");
			ps.setInt(1, id);
			
			int result= ps.executeUpdate();
			if(result != 0)
			{
				System.out.println("Book Deleted.");
			}
			else
			{
				System.out.println("No Data available for this ID");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
 