package e_Commerce_Website;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;


public class ProductDAO 
{

    public void insertProductWithImage() 
	{
        Scanner sc = new Scanner(System.in);

        try (Connection con = DBConnection.getConnection())
	 {

            String sql = "INSERT INTO estore(id, name, description, price, image) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql);


            System.out.println("Enter product id:");
            int id = Integer.parseInt(sc.nextLine());

            System.out.println("Enter product name:");
            String name = sc.nextLine();

            System.out.println("Enter product description:");
            String description = sc.nextLine();

            System.out.println("Enter product price:");
            double price = Double.parseDouble(sc.nextLine());

            System.out.println("Enter the image file path:");
            String filePath = sc.nextLine();


            stm.setInt(1, id);
            stm.setString(2, name);
            stm.setString(3, description);
            stm.setDouble(4, price);


            File file = new File(filePath);
            try (FileInputStream fis = new FileInputStream(file))
 	    {
                stm.setBinaryStream(5, fis, (int) file.length());
                
     
                int rowsInserted = stm.executeUpdate();
                if (rowsInserted > 0) 
		{
                    System.out.println("Product with image stored in the database successfully.");
                }
            } 
	    catch (Exception e)
            {
                System.out.println("Error reading the image file: " + e.getMessage());
            }

            
            stm.close();
           }
           catch (Exception e) 
           {
            System.out.println("An error occurred: " + e.getMessage());

           } 
	   finally 
	   {
            sc.close(); // Always close the scanner
        }
    }
}
