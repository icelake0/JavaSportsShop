package oop.ica.e2;

import javax.swing.ImageIcon;
import oop.ica.ts.TSProduct;

/**
 * @author Gbemileke Ajiboye - C2479785
 */
public class TSProductASCAdapter implements ASCStockItemInterface {
    
    /**
     * @var tsProduct : The instance of TSProduct this class serves as rapper for
     */
    private TSProduct tsProduct;
    
    /**
     * @var ImageIcon: Image icon of stock item
     */
    private ImageIcon imageIcon;
    
    /**
     * Class constructor
     * 
     * @param tsProduct 
     */
    public void TSProductASCAdapter (TSProduct tsProduct)
    {
        this.tsProduct = tsProduct;
        this.imageIcon = new ImageIcon("./images/ts_pics/" + this.getProductCode() + ".jpg");
    }
    
    /**
     * Get the stock item product code
     *
     * @method getProductCode
     * @return String
     */
    @Override
    public String getProductCode(){
        return this.tsProduct.getSkuNumber();
    }

    /**
     * Get the stock item product title
     *
     * @method getproductTitle
     * @return String
     */
    @Override
    public String getproductTitle(){
        return this.tsProduct.getColour() + " "
                + this.tsProduct.getModel() + " "
                + this.tsProduct.getMake();
    }

    /**
     * Get the stock item product description
     *
     * @method getProductDescription
     * @return String
     */
    @Override
    public String getProductDescription(){
        return this.tsProduct.getNotes();
    }

    /**
     * Get the Pounds section of stock item unit price
     *
     * @method getUnitPricePounds
     * @return Integer
     */
    @Override
    public int getUnitPricePounds(){
        return Integer.parseInt(
            Double.toString(this.tsProduct.getPrice())
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
    public int getUnitPricePence(){
        return Integer.parseInt(
            Double.toString(this.tsProduct.getPrice())
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
    public String getUnitPriceFull(){
        return "£"+this.tsProduct.getPrice();
    }

    /**
     * Get the quantity of stock item in stock
     *
     * @method getQuantityOnStock
     * @return Integer
     */
    @Override
    public int getQuantityOnStock(){
        return this.tsProduct.getStock();
    }

    /**
     * Get the image icon of stock item
     *
     * @method getImageIcon
     * @return ImageIcon
     */
    @Override
    public ImageIcon getImageIcon(){
        return this.imageIcon;
    }

    /**
     * Decrease the quantity on stock of stock item by one
     *
     * @method reduceQuantityOnStockByOne
     */
    @Override
    public void reduceQuantityOnStockByOne(){
        this.tsProduct.decreaseStock();
    }

    /**
     * Decrease the quantity on stock of stock item by a value X passed
     *
     * @param X
     * @method reduceQuantityOnStockByX
     */
    @Override
    public void reduceQuantityOnStockByX(int X){
        this.tsProduct.setStock(this.getQuantityOnStock() - X);
    }

    /**
     * Increase the quantity on stock of stock item by one
     *
     * @method increaseQuantityOnStockByOne
     */
    @Override
    public void increaseQuantityOnStockByOne(){
        this.tsProduct.decreaseStock();
    }

    /**
     * Increase the quantity on stock of stock item by a value Y passed
     *
     * @param Y
     * @method increaseQuantityOnStockByY
     */
    @Override
    public void increaseQuantityOnStockByY(int Y){
        this.tsProduct.setStock(this.getQuantityOnStock() + Y);
    }

    /**
     * Check if stock item is on stock
     *
     * @method isOnStock
     * @return Boolean
     */
    @Override
    public boolean isOnStock(){
        return this.getQuantityOnStock() > 0;
    }

    /**
     * Check if stock item is out of stock
     *
     * @method isOutOfStock
     * @return Boolean
     */
    @Override
    public boolean isOutOfStock(){
        return !this.isOnStock();
    }

    /**
     * Check if stock item is low on stock
     *
     * @method isLowOnStock
     * @return Boolean
     */
    @Override
    public boolean isLowOnStock(){
        return this.getQuantityOnStock() < 5;
    }
}
