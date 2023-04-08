package oop.ica.e2;

import javax.swing.ImageIcon;
import oop.ica.ts.TSProduct;

/**
 * @author Gbemileke Ajiboye - C2479785
 */
public class TSProductASCAdapter extends TSProduct implements ASCStockItemInterface {

    /**
     * @var ImageIcon: Image icon of stock item
     */
    private ImageIcon imageIcon;

    /**
     * Class constructor
     *
     * @param num
     * @param make
     * @param mdl
     * @param clr
     * @param notes
     * @param price
     * @param stk
     */
    public TSProductASCAdapter(String num, String make, String mdl, String clr, String notes, double price, int stk) {
        super(num, make, mdl, clr, notes, price, stk);
        this.imageIcon = new ImageIcon("./photos/ts_pics/" + this.getProductCode() + ".jpg");
    }

    /**
     * Get the stock item product code
     *
     * @method getProductCode
     * @return String
     */
    @Override
    public String getProductCode() {
        return this.getSkuNumber();
    }

    /**
     * Get the stock item product title
     *
     * @method getproductTitle
     * @return String
     */
    @Override
    public String getproductTitle() {
        return this.getColour() + " "
                + this.getModel() + " "
                + this.getMake();
    }

    /**
     * Get the stock item product description
     *
     * @method getProductDescription
     * @return String
     */
    @Override
    public String getProductDescription() {
        return this.getNotes();
    }

    /**
     * Get the Pounds section of stock item unit price
     *
     * @method getUnitPricePounds
     * @return Integer
     */
    @Override
    public int getUnitPricePounds() {
        return Integer.parseInt(
                Double.toString(this.getPrice())
                        .split("\\.")[0]
        );
    }

    /**
     * Get the Pence section of stock item unit price
     *
     * @method getUnitPricePence
     * @return Integer
     */
    @Override
    public int getUnitPricePence() {
        return Integer.parseInt(
                Double.toString(this.getPrice())
                        .split("\\.")[1]
        );
    }

    /**
     * Get the full stock item unit price
     *
     * @method getUnitPriceFull
     * @return String
     */
    @Override
    public String getUnitPriceFull() {
        return "£" + this.getPrice();
    }

    /**
     * Get the quantity of stock item in stock
     *
     * @method getQuantityOnStock
     * @return Integer
     */
    @Override
    public int getQuantityOnStock() {
        return this.getStock();
    }

    /**
     * Get the image icon of stock item
     *
     * @method getImageIcon
     * @return ImageIcon
     */
    @Override
    public ImageIcon getImageIcon() {
        return this.imageIcon;
    }

    /**
     * Decrease the quantity on stock of stock item by one
     *
     * @method reduceQuantityOnStockByOne
     */
    @Override
    public void reduceQuantityOnStockByOne() {
        this.decreaseStock();
    }

    /**
     * Decrease the quantity on stock of stock item by a value X passed
     *
     * @param X
     * @method reduceQuantityOnStockByX
     */
    @Override
    public void reduceQuantityOnStockByX(int X) {
        this.setStock(this.getQuantityOnStock() - X);
    }

    /**
     * Increase the quantity on stock of stock item by one
     *
     * @method increaseQuantityOnStockByOne
     */
    @Override
    public void increaseQuantityOnStockByOne() {
        this.decreaseStock();
    }

    /**
     * Increase the quantity on stock of stock item by a value Y passed
     *
     * @param Y
     * @method increaseQuantityOnStockByY
     */
    @Override
    public void increaseQuantityOnStockByY(int Y) {
        this.setStock(this.getQuantityOnStock() + Y);
    }

    /**
     * Check if stock item is on stock
     *
     * @method isOnStock
     * @return Boolean
     */
    @Override
    public boolean isOnStock() {
        return this.getQuantityOnStock() > 0;
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
        return this.getQuantityOnStock() < 5;
    }
}
