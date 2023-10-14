import java.util.ArrayList;

/**
   A vending machine.
*/
public class VendingMachine
{  
   private ArrayList<Product> products;
   public CoinSet coins;
   public CoinSet currentCoins;

   /**
      Constructs a VendingMachine object.
   */
   public VendingMachine()
   { 
      products = new ArrayList<Product>();
      coins = new CoinSet();
      currentCoins = new CoinSet();
   }
   
   /**
      Gets the type of products in the vending machine.
      @return an array of products sold in this machine.
   */
   public Product[] getProductTypes()
   { 
      ArrayList<Product> types = new ArrayList<Product>();
      
      for (Product p : products)
         if (!types.contains(p)) types.add(p);

      Product[] r = new Product[types.size()];
      for (int i = 0; i < types.size(); i++)
         r[i] = types.get(i);
      return r;
   }

   /**
      Adds the coin to the vending machine.
      @param c the coin to add
   */
   public void addCoin(Coin c)
   { 
      currentCoins.addCoin(c);
   }

   /**
      Buys a product from the vending machine.
      @param p the product to buy
   */
   public void buyProduct(Product p)
   { 
      for (int i = 0; i < products.size(); i++)
      { 
         Product prod = products.get(i);
         if (prod.equals(p))
         {  
            double payment = currentCoins.getValue();
            if (p.getPrice() <= payment)
            {  
               products.remove(i);
               coins.addCoins(currentCoins);
               currentCoins.removeAllCoins();
               return;
            }
            else
            { 
               throw new VendingException("Insufficient money");
            }
         }
      }
      throw new VendingException("No such product");
   }

   /**
      Adds a product to the vending machine.
      @param p the product to add
      @param quantity the quantity
   */
   public void addProduct(Product p, int quantity)
   {
      for (int i = 0; i < quantity; i++)
         products.add(p);
   }
   
   /**
      Removes the money from the vending machine.
      @return the amount of money removed
   */
   public double removeMoney()
   {  
      double r = coins.getValue();
      coins.removeAllCoins();
      return r;
   }
}
