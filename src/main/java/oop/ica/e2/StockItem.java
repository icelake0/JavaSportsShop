package oop.ica.e2;

/**
 *
 * @author Heshani Iddagoda - W9621281
 * @author Akshay Kumar - Q2078619
 * @author Gbemileke Ajiboye - C2479785
 */
public class StockItem {

    /**
     * @var String: The Stock item product code
     */
    private String productCode;

    /**
     * @var String: The Stock item product title
     */
    private String productTitle;

    /**
     * @var String: The Stock item product description
     */
    private String productDescription;

    /**
     * @var Integer: The Pounds section of stock item unit price
     */
    private int unitPricePounds;

    /**
     * @var Integer: The Pence section of stock item unit price
     */
    private int unitPricePence;

    /**
     * @var Integer: The quantity of stock item in stock
     */
    private int quantityOnStock;

    /**
     * Class constructor
     *
     * @param productCode: String
     * @param productTitle: String
     * @param productDescription: String
     * @param unitPricePounds: Integer
     * @param unitPricePence: Integer
     * @param quantityOnStock: Integer
     * @method StockItem
     *
     */
    public StockItem(
            String productCode,
            String productTitle,
            String productDescription,
            int unitPricePounds,
            int unitPricePence,
            int quantityOnStock) {
        this.productCode = productCode;
        this.productTitle = productTitle;
        this.productDescription = productDescription;
        this.unitPricePounds = unitPricePounds;
        this.unitPricePence = unitPricePence;
        this.quantityOnStock = quantityOnStock;
    }

    /**
     * Get the stock item product code
     *
     * @method getProductCode
     * @return String
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Get the stock item product title
     *
     * @method getproductTitle
     * @return String
     */
    public String getproductTitle() {
        return productTitle;
    }
    
    /**
     * Get the stock item short product title
     *
     * @method getShortProductTitle
     * @return String
     */
    public String getShortProductTitle() {
         if(productDescription.length() > 30){
            return productDescription.substring(27)+"...";
        }
        return productTitle;
    }


    /**
     * Get the stock item product description
     *
     * @method getProductDescription
     * @return String
     */
    public String getProductDescription() {
        return productDescription;
    }
    
    /**
     * Get the stock item short product description
     *
     * @method getShortProductDescription
     * @return String
     */
    public String getShortProductDescription() {
        if(productDescription.length() > 50){
            return productDescription.substring(47)+"...";
        }
        return productDescription;
    }

    /**
     * Get the Pounds section of stock item unit price
     *
     * @method getUnitPricePounds
     * @return Integer
     */
    public int getUnitPricePounds() {
        return unitPricePounds;
    }

    /**
     * Get the Pence section of stock item unit price
     *
     * @method getUnitPricePence
     * @return Integer
     */
    public int getUnitPricePence() {
        return unitPricePence;
    }

    /**
     * Get the quantity of stock item in stock
     *
     * @method getQuantityOnStock
     * @return Integer
     */
    public int getQuantityOnStock() {
        return quantityOnStock;
    }

    /**
     * Decrease the quantity on stock of stock item by one
     *
     * @method reduceQuantityOnStockByOne
     */
    public void reduceQuantityOnStockByOne() {
        this.quantityOnStock--;
    }

    /**
     * Increase the quantity on stock of stock item by one
     *
     * @method increaseQuantityOnStockByOne
     */
    public void increaseQuantityOnStockByOne() {
        this.quantityOnStock++;
    }

    /**
     * Check if stock item is on stock
     *
     * @method isOnStock
     * @return Boolean
     */
    public boolean isOnStock() {
        return this.quantityOnStock > 0;
    }

    /**
     * Check if stock item is out of stock
     *
     * @method isOutOfStock
     * @return Boolean
     */
    public boolean isOutOfStock() {
        return !this.isOnStock();
    }
}
