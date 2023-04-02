package oop.ica.e2;

import java.util.Formatter;
import javax.swing.ImageIcon;

/**
 * @author Gbemileke Ajiboye - C2479785
 */
public class ASCStockItem implements ASCStockItemInterface {

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
     * @var ImageIcon: Image icon of stock item
     */
    private ImageIcon imageIcon;

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
    public ASCStockItem(
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
        this.imageIcon = new ImageIcon("./images/asc_pics/" + this.productCode + ".jpg");
    }

    /**
     * Get the stock item product code
     *
     * @method getProductCode
     * @return String
     */
    @Override
    public String getProductCode() {
        return productCode;
    }

    /**
     * Get the stock item product title
     *
     * @method getproductTitle
     * @return String
     */
    @Override
    public String getproductTitle() {
        return productTitle;
    }

    /**
     * Get the stock item product description
     *
     * @method getProductDescription
     * @return String
     */
    @Override
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * Get the Pounds section of stock item unit price
     *
     * @method getUnitPricePounds
     * @return Integer
     */
    @Override
    public int getUnitPricePounds() {
        return unitPricePounds;
    }

    /**
     * Get the Pence section of stock item unit price
     *
     * @method getUnitPricePence
     * @return Integer
     */
    @Override
    public int getUnitPricePence() {
        return unitPricePence;
    }

    /**
     * Get the full stock item unit price
     *
     * @method getUnitPriceFull
     * @return String
     */
    @Override
    public String getUnitPriceFull() {
        return new Formatter().format("£%,.2f",
                Double.parseDouble(unitPricePounds + "." + unitPricePence)
        ).toString();
    }

    /**
     * Get the quantity of stock item in stock
     *
     * @method getQuantityOnStock
     * @return Integer
     */
    @Override
    public int getQuantityOnStock() {
        return quantityOnStock;
    }

    /**
     * Get the image icon of stock item
     *
     * @method getImageIcon
     * @return ImageIcon
     */
    @Override
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    /**
     * Decrease the quantity on stock of stock item by one
     *
     * @method reduceQuantityOnStockByOne
     */
    @Override
    public void reduceQuantityOnStockByOne() {
        this.reduceQuantityOnStockByX(1);
    }

    /**
     * Decrease the quantity on stock of stock item by a value X passed
     *
     * @method reduceQuantityOnStockByX
     */
    @Override
    public void reduceQuantityOnStockByX(int X) {
        this.quantityOnStock -= X;
    }

    /**
     * Increase the quantity on stock of stock item by one
     *
     * @method increaseQuantityOnStockByOne
     */
    @Override
    public void increaseQuantityOnStockByOne() {
        this.increaseQuantityOnStockByY(1);
    }

    /**
     * Increase the quantity on stock of stock item by a value Y passed
     *
     * @method increaseQuantityOnStockByY
     */
    @Override
    public void increaseQuantityOnStockByY(int Y) {
        this.quantityOnStock += Y;
    }

    /**
     * Check if stock item is on stock
     *
     * @method isOnStock
     * @return Boolean
     */
    @Override
    public boolean isOnStock() {
        return this.quantityOnStock > 0;
    }

    /**
     * Check if stock item is out of stock
     *
     * @method isOutOfStock
     * @return Boolean
     */
    @Override
    public boolean isOutOfStock() {
        return !this.isOnStock();
    }

    /**
     * Check if stock item is low on stock
     *
     * @method isLowOnStock
     * @return Boolean
     */
    @Override
    public boolean isLowOnStock() {
        return this.quantityOnStock < 5;
    }
}
