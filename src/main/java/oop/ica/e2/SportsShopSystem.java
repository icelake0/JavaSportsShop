package oop.ica.e2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Heshani Iddagoda - W9621281
 * @author Akshay Kumar - Q2078619
 * @author Gbemileke Ajiboye - C2479785
 */
public class SportsShopSystem {

    /**
     * @var ArrayList<StockItem>: An array list of all stock items
     */
    private static ArrayList<StockItem> stockItems;

    /**
     * The entry point of the program
     *
     * @param args
     * @throws java.io.IOException
     * @method main
     */
    public static void main(String[] args) {
        System.out.println("ASHER SPORTS CONSORTIUM");
        loadData();
        promptUser();
    }

    /**
     * Load stock data to main class
     *
     * @method loadData
     */
    public static void loadData() {
        try {
            stockItems = FileService.make().loadData();
        } catch (FileNotFoundException e) {
            handleLoadDataException(e);
        } catch (IOException e) {
            handleLoadDataException(e);
        }
    }

    /**
     * Handle stock item data load exception
     *
     * @param e Exception
     */
    private static void handleLoadDataException(Exception e) {
        System.out.println("\n!!!!! Error: " + e.getMessage() + " !!!!!\n");
        System.exit(0);
    }

    /**
     * Print Menu to user and collect user input.
     *
     * @method promptUser
     */
    public static void promptUser() {
        printMenu();
        handleChoice(getInput());
    }

    /**
     * Printing the main menus
     *
     * @method printMenu
     */
    private static void printMenu() {
        System.out.println("\nMAIN MENU : Please Select a menu opotions to continue");
        System.out.println("\t[1] View Items");
        System.out.println("\t[2] Buy Items");
        System.out.println("\t[3] Add Stocks");
        System.out.println("\t[0] Quit\n");
    }

    /**
     * Get the user input
     *
     * @method getInput
     * @return int
     */
    private static int getInput() {
        try {
            return new Scanner(System.in).nextInt();
        } catch (InputMismatchException ex) {
            return -1;
        }
    }

    /**
     * Handle the user input choice
     *
     * @method handleChoice
     * @param int choice
     */
    private static void handleChoice(int choice) {
        switch (choice) {
            case 0:
                handleExit();
                break;
            case 1:
                handleViewItems();
                break;
            case 2:
                handleBuyItems();
                break;
            case 3:
                handleAddStocks();
                break;
            default:
                handleInvalidInput();
        }
        promptUser();
    }

    /**
     * Handle the View Items user choice
     *
     * @method handleViewItems
     */
    private static void handleViewItems() {
        System.out.println("\nView Items");
        displayItems("Product");
    }

    /**
     * Handle the Buy Items user choice
     *
     * @method handleBuyItems
     */
    private static void handleBuyItems() {
        System.out.println("\nBuy Item\n");
        System.out.println("Stock Items");
        displayItems("Buy");
        System.out.println("Select item if to buy, or 0 to return to main menu");
        handleBuyItemsChoice(getInput());
    }

    /**
     * handle buy item selected choice
     *
     * @param itemChoice: Integer
     */
    private static void handleBuyItemsChoice(int itemChoice) {
        if (itemChoice == 0) {
            return;
        }

        if (validateItemChoice(itemChoice)) {
            handleInvalidStockItemSelectionForBuy();
            return;
        }

        int stockItemsArrayListIndex = itemChoice - 1;

        StockItem selectedStockItem = stockItems.get(stockItemsArrayListIndex);

        if (selectedStockItem.isOutOfStock()) {
            handleBuyItemsOutOfStock(selectedStockItem);
            return;
        }

        selectedStockItem.reduceQuantityOnStockByOne();

        System.out.println("Sale of " + selectedStockItem.getproductTitle() + " for £" + selectedStockItem.getUnitPricePounds() + "." + selectedStockItem.getUnitPricePence() + " confirmed. Quantity remaining: " + selectedStockItem.getQuantityOnStock());
    }

    /**
     * Check that the selected stock item is valid
     *
     * @param itemChoice: Integer
     * @return Boolean
     */
    private static boolean validateItemChoice(int itemChoice) {
        return itemChoice < 1 || itemChoice > stockItems.size();
    }

    /**
     * Handle invalid stock item selection when buying item
     *
     * @method handleInvalidStockItemSelectionForBuy
     */
    private static void handleInvalidStockItemSelectionForBuy() {
        System.out.println("!!! Invalid stock item selected. please select a valid one, or 0 to return to main menu !!!");
        handleBuyItems();
    }

    /**
     * Handle out of stock error when buying item
     *
     * @method handleBuyItemsOutOfStock
     */
    private static void handleBuyItemsOutOfStock(StockItem selectedStockItem) {
        System.out.println("!!! " + selectedStockItem.getproductTitle() + " is out of stock. please select a valid one, or 0 to return to main menu !!!" + selectedStockItem.getQuantityOnStock());
        handleBuyItems();
    }

    /**
     * Handle the Add Stock user choice
     *
     * @method handleAddStocks
     */
    private static void handleAddStocks() {
        System.out.println("\nAdd Stock\n");
        System.out.println("Stock Items");
        displayItems("Add");
        System.out.println("Select item if to add, or 0 to return to main menu");
        handleAddStocksChoice(getInput());
    }

    /**
     * handle add stock selected choice
     *
     * @param itemChoice: Integer
     */
    private static void handleAddStocksChoice(int itemChoice) {
        if (itemChoice == 0) {
            return;
        }

        if (validateItemChoice(itemChoice)) {
            handleInvalidStockItemSelectionForAddStock();
            return;
        }

        int stockItemsArrayListIndex = itemChoice - 1;

        StockItem selectedStockItem = stockItems.get(stockItemsArrayListIndex);

        selectedStockItem.increaseQuantityOnStockByOne();

        System.out.println("New quantity for " + selectedStockItem.getproductTitle() + " is: " + selectedStockItem.getQuantityOnStock());
    }

    /**
     * Handle invalid stock item selection when adding stock
     *
     * @method handleInvalidStockItemSelectionForAddStock
     */
    private static void handleInvalidStockItemSelectionForAddStock() {
        System.out.println("!!! Invalid stock item selected. please select a valid one, or 0 to return to main menu !!!");
        handleAddStocks();
    }

    /**
     * Handle the Exit user choice
     *
     * @method handleExit
     */
    private static void handleExit() {
        saveData();
        System.out.println("\n**** Thank You for your vist ****");
        System.exit(0);
    }

    private static void saveData() {
        try {
            FileService.make(stockItems).saveData();
        } catch (IOException e) {
            System.out.println("\n!!!!! Error: " + e.getMessage() + " !!!!!\n");
        }
    }

    /**
     * Handle invalid user choice
     *
     * @method handleInvalidInput
     */
    private static void handleInvalidInput() {
        System.out.println("!!!! Please select a valid menu options !!!");
    }

    /**
     * Display all stock items in a table on console
     *
     * @param String action
     * @method displayItems
     */
    private static void displayItems(String action) {

        String tableFormater = "%-15s %-15s %-30s %-50s %-10s %-10s %n";
        System.out.printf(tableFormater, action + " Code", "Stock Code", "Title", "Description", "Price", "Quentity");
        for (int i = 0; i < stockItems.size(); i++) {
            StockItem stockItem = stockItems.get(i);
            int stockItemID = i + 1;
            System.out.printf(
                    tableFormater, stockItemID,
                    stockItem.getProductCode(), stockItem.getproductTitle(),
                    stockItem.getShortProductDescription(),
                    "£" + stockItem.getUnitPricePounds() + "." + stockItem.getUnitPricePence(),
                    stockItem.getQuantityOnStock());
        }
    }
}
