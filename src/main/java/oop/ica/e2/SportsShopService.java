package oop.ica.e2;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @author Gbemileke Ajiboye - C2479785
 */
public class SportsShopService {

    /**
     * @var SportsShopGUI sportsShopGUI instance of SportsShopGUI being served
     */
    private SportsShopGUI sportsShopGUI;

    /**
     * @var ArrayList<ASCStockItemInterface>: An array list of all stock items
     */
    private ArrayList<ASCStockItemInterface> stockItems = new ArrayList();

    /**
     * Class constructor
     * 
     * @param sportsShopGUI 
     */
    public SportsShopService(SportsShopGUI sportsShopGUI) {
        this.sportsShopGUI = sportsShopGUI;
    }

    /**
     * Get the instance stock item
     * 
     * @return ArrayList<ASCStockItemInterface>
     */
    public ArrayList<ASCStockItemInterface> getStockItems() {
        return this.stockItems;
    }

    /**
     * Handle click event for buy button
     *
     * @param int selectedRow
     */
    public void handleBuyButtonActionPerformed(int selectedRow) {
        if (!this.validateSelectedItem(selectedRow)) {
            this.showNoItemSelectedError();
            return;
        }

        ASCStockItemInterface selectedStockItem = stockItems.get(selectedRow);

        if (selectedStockItem.isOutOfStock()) {
            this.showOutOfStockError();
            return;
        }

        selectedStockItem.reduceQuantityOnStockByOne();

        this.sportsShopGUI.updateStockItemTable(selectedStockItem, selectedRow);

        this.showBuyConfirmationMessage(selectedStockItem, 1);
    }

    /**
     * Handle click event for add button
     * 
     * @param selectedRow
     */
    public void handleAddButtonActionPerformed(int selectedRow) {
        if (!this.validateSelectedItem(selectedRow)) {
            this.showNoItemSelectedError();
            return;
        }

        ASCStockItemInterface selectedStockItem = stockItems.get(selectedRow);

        selectedStockItem.increaseQuantityOnStockByOne();

        this.sportsShopGUI.updateStockItemTable(selectedStockItem, selectedRow);

        this.showAddConfirmationMessage(selectedStockItem, 1);
    }

    /**
     * Handle application start event
     */
    public void handleFormWindowOpened() {
        this.loadData();
    }

    /**
     * Handle system close event
     */
    public void handleQuitButtonActionPerformed() {
        this.saveData();
        System.exit(0);
    }

    /**
     * Handle stock item table mouse pressed event
     * 
     * @param selectedRow
     * @param photoLabel
     * @param itemLabel
     */
    public void handleASCStockItemMousePressed(int selectedRow, JLabel photoLabel, JLabel itemLabel) {
        if (this.validateSelectedItem(selectedRow)) {
            ASCStockItemInterface selectedStockItem = stockItems.get(selectedRow);
            photoLabel.setIcon(selectedStockItem.getImageIcon());
            itemLabel.setText(selectedStockItem.getproductTitle());
            this.showLowStockWarningMessage(selectedStockItem);
        }
    }

    /**
     * Handle click event for buyX button
     * 
     * @param selectedRow
     */
    public void handleBuyXButtonActionPerformed(int selectedRow) {
        if (!this.validateSelectedItem(selectedRow)) {
            this.showNoItemSelectedError();
            return;
        }

        ASCStockItemInterface selectedStockItem = stockItems.get(selectedRow);

        if (selectedStockItem.isOutOfStock()) {
            this.showOutOfStockError();
            return;
        }

        int selectedValue = this.getBuyQuantityFromUser(selectedStockItem);

        if (selectedValue > 0) {
            selectedStockItem.reduceQuantityOnStockByX(selectedValue);
            this.sportsShopGUI.updateStockItemTable(selectedStockItem, selectedRow);
            this.showBuyConfirmationMessage(selectedStockItem, selectedValue);
        }
    }

    /**
     * Handle click event for addY button
     * 
     * @param selectedRow
     */
    public void handleAddYButtonActionPerformed(int selectedRow) {
        if (!this.validateSelectedItem(selectedRow)) {
            this.showNoItemSelectedError();
            return;
        }

        ASCStockItemInterface selectedStockItem = stockItems.get(selectedRow);

        int inputValue = this.getAddQuantityFromUser(selectedStockItem);

        if (inputValue > 0) {
            selectedStockItem.increaseQuantityOnStockByY(inputValue);
            this.sportsShopGUI.updateStockItemTable(selectedStockItem, selectedRow);
            this.showAddConfirmationMessage(selectedStockItem, inputValue);
        }
    }

    /**
     * Load stock data to main class
     *
     * @method loadData
     */
    private void loadData() {
        try {
            this.stockItems = FileService.make().loadData();
        } catch (FileNotFoundException e) {
            this.handleLoadDataException(e);
        } catch (IOException e) {
            this.handleLoadDataException(e);
        }
    }

    /**
     * Handle stock item data load exception
     *
     * @param e Exception
     */
    private void handleLoadDataException(Exception e) {
        this.showError(e.getMessage(), "File loading Error");
        System.err.println(e);
        System.exit(0);
    }

    /**
     * Get buy quantity input from user using a selection input dialog
     *
     * @param selectedStockItem
     * @param action
     * @return int
     */
    private int getAddQuantityFromUser(ASCStockItemInterface selectedStockItem) {
        int inputValue = -1;

        try {
            String inputString = JOptionPane.showInputDialog(this.sportsShopGUI,
                    "Please select the quantity you wish to add of: \n'" + selectedStockItem.getproductTitle() + "'",
                    "Quantity to purchase",
                    JOptionPane.QUESTION_MESSAGE,
                    this.getInputDialogImageIcon(selectedStockItem),
                    null, null
            ).toString();
            inputValue = Integer.parseInt(inputString);
        } catch (NumberFormatException e) {
            this.showError("Please input a whole number.", "Invalid input");
            return this.getAddQuantityFromUser(selectedStockItem);
        } catch (NullPointerException e) {
        }

        return inputValue;
    }

    /**
     * Get buy quantity input from user using a selection input dialog
     *
     * @param selectedStockItem
     * @param action
     * @return int
     */
    private int getBuyQuantityFromUser(ASCStockItemInterface selectedStockItem) {
        int selectedValue = -1;

        try {
            selectedValue = (int) JOptionPane.showInputDialog(this.sportsShopGUI,
                    "Please select the quantity you wish to buy of: \n'" + selectedStockItem.getproductTitle() + "'",
                    "Quantity to purchase",
                    JOptionPane.QUESTION_MESSAGE,
                    this.getInputDialogImageIcon(selectedStockItem),
                    this.getSelectionOptionsInputDialog(selectedStockItem),
                    1
            );
        } catch (NullPointerException e) {
        }

        return selectedValue;
    }

    /**
     * Get the selection options for input dialog
     *
     * @param stockItem
     * @return Object[]
     */
    private Object[] getSelectionOptionsInputDialog(ASCStockItemInterface stockItem) {
        return IntStream.rangeClosed(
                1, stockItem.getQuantityOnStock()
        ).boxed().collect(Collectors.toList()).toArray();
    }

    /**
     * Get an 100 X 100 size of the stock item image to be used for dialog
     *
     * @param stockItem
     * @return ImageIcon
     */
    private ImageIcon getInputDialogImageIcon(ASCStockItemInterface stockItem) {
        return new ImageIcon(
                stockItem.getImageIcon()
                        .getImage()
                        .getScaledInstance(100, 100, Image.SCALE_SMOOTH)
        );
    }

    /**
     * Show low stock warning message
     *
     * @param stockItem
     */
    private void showLowStockWarningMessage(ASCStockItemInterface stockItem) {
        String lowStockMessage = stockItem.isOutOfStock()
                ? "'%s' is out of stock" : "'%s' has only %d unit(s) of stock";
        if (stockItem.isLowOnStock()) {
            this.showWarningMessgae(
                    new Formatter().format(lowStockMessage,
                            stockItem.getproductTitle(), stockItem.getQuantityOnStock()
                    ).toString(), "Low Stock Warning"
            );
        }
    }

    /**
     * Show warning message passed
     *
     * @param message
     * @param title
     */
    private void showWarningMessgae(String message, String title) {
        JOptionPane.showMessageDialog(this.sportsShopGUI, message,
                title, JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Save data stock item to output file
     */
    private void saveData() {
        try {
            FileService.make(stockItems).saveData();
        } catch (IOException e) {
            this.showError("Error while saving output file", "Output File Error");
            System.err.println(e);
        }
    }

    /**
     * Validate that an and item in the table is selected
     *
     * @param selectedRow
     * @return int
     */
    private boolean validateSelectedItem(int selectedRow) {
        return selectedRow >= 0;
    }

    /**
     * Show and error message that no item is selected
     */
    private void showNoItemSelectedError() {
        this.showError("Please select an item from the table", "No Item Selected");
    }

    /**
     * Show and error message that item selected is out of stock
     */
    private void showOutOfStockError() {
        this.showError("The item you have selected is out of stock", "Out Of Stock");
    }

    /**
     * Show confirmation message for item bought
     *
     * @param stockItem
     * @param unitBought
     */
    private void showBuyConfirmationMessage(ASCStockItemInterface stockItem, int unitBought) {
        JOptionPane.showMessageDialog(this.sportsShopGUI,
                new Formatter().format("Item: %s%nPrice: %s%nUnit(s) bought: %d%nStock Remaining: %d",
                        stockItem.getproductTitle(), stockItem.getUnitPriceFull(),
                        unitBought, stockItem.getQuantityOnStock()
                ).toString(), "Confirmation of Sale", JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Show confirmation message for item added
     *
     * @param stockItem
     * @param unitAdded
     */
    private void showAddConfirmationMessage(ASCStockItemInterface stockItem, int unitAdded) {
        JOptionPane.showMessageDialog(this.sportsShopGUI,
                new Formatter().format("Item: %s%nUnit(s) added: %d%nNew stock quantity: %d",
                        stockItem.getproductTitle(), unitAdded, stockItem.getQuantityOnStock()
                ).toString(), "Confirmation of Added Stock", JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Show error message passed
     *
     * @param error
     * @param title
     */
    private void showError(String error, String title) {
        JOptionPane.showMessageDialog(this.sportsShopGUI,
                error, title, JOptionPane.ERROR_MESSAGE
        );
    }
}
