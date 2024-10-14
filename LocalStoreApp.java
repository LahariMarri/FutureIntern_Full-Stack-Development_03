package e_Commerce_Website;

public class LocalStoreApp 
{
	public static void main(String[] args)
	
	{
		ProductDAO productDAO = new ProductDAO();
	
        productDAO.insertProductWithImage(); 
    }
}
