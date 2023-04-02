package oop.ica.e2;

import javax.swing.ImageIcon;

/**
 * @author Gbemileke Ajiboye - C2479785
 */
public interface ASCStockItemInterface {

    /**
     * Get the stock item product code
     *
     * @method getProductCode
     * @return String
     */
    public String getProductCode();

    /**
     * Get the stock item product title
     *
     * @method getproductTitle
     * @return String
     */
    public String getproductTitle();

    /**
     * Get the stock item product description
     *
     * @method getProductDescription
     * @return String
     */
    public String getProductDescription();

    /**
     * Get the Pounds section of stock item unit price
     *
     * @method getUnitPricePounds
     * @return Integer
     */
    public int getUnitPricePounds();

    /**
     * Get the Pence section of stock item unit price
     *
     * @method getUnitPricePence
     * @return Integer
     */
    public int getUnitPricePence();

    /**
     * Get the full stock item unit price
     *
     * @method getUnitPriceFull
     * @return String
     */
    public String getUnitPriceFull();

    /**
     * Get the quantity of stock item in stock
     *
     * @method getQuantityOnStock
     * @return Integer
     */
    public int getQuantityOnStock();

    /**
     * Get the image icon of stock item
     *
     * @method getImageIcon
     * @return ImageIcon
     */
    public ImageIcon getImageIcon();

    /**
     * Decrease the quantity on stock of stock item by one
     *
     * @method reduceQuantityOnStockByOne
     */
    public void reduceQuantityOnStockByOne();

    /**
     * Decrease the quantity on stock of stock item by a value X passed
     *
     * @param X
     * @method reduceQuantityOnStockByX
     */
    public void reduceQuantityOnStockByX(int X);

    /**
     * Increase the quantity on stock of stock item by one
     *
     * @method increaseQuantityOnStockByOne
     */
    public void increaseQuantityOnStockByOne();

    /**
     * Increase the quantity on stock of stock item by a value Y passed
     *
     * @param Y
     * @method increaseQuantityOnStockByY
     */
    public void increaseQuantityOnStockByY(int Y);

    /**
     * Check if stock item is on stock
     *
     * @method isOnStock
     * @return Boolean
     */
    public boolean isOnStock();

    /**
     * Check if stock item is out of stock
     *
     * @method isOutOfStock
     * @return Boolean
     */
    public boolean isOutOfStock();

    /**
     * Check if stock item is low on stock
     *
     * @method isLowOnStock
     * @return Boolean
     */
    public boolean isLowOnStock();
}
