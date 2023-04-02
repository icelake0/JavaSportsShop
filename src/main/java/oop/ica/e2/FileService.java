package oop.ica.e2;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

/**
 * @author Gbemileke Ajiboye - C2479785
 */
public class FileService {

    /**
     * @constant String: The delimiter for stock item props in string
     */
    private static final String DELIMITER = ",";

    /**
     * @constant String: project relative file patch
     */
    private static final String RELATIVE_FILE_PATH = "src/main/java/oop/ica/e2/";

    /**
     * @constant String: Input data file name
     */
    private static final String INPUT_FILE_PATH = "AsherSportsConsortium3.csv";

    /**
     * @constant String: Output data file name
     */
    private static final String OUTPUT_FILE_PATH = "asc_output.txt";

    /**
     * @var ArrayList<ASCStockItemInterface>: An array list of all stock items
     */
    private ArrayList<ASCStockItemInterface> stockItems;

    /**
     * Class constructor
     *
     * @method FileService
     */
    private FileService() {
        this.stockItems = new ArrayList();
    }

    /**
     * Class constructor
     *
     * @param stockItems ArrayList
     * @method FileService
     */
    private FileService(ArrayList<ASCStockItemInterface> stockItems) {
        this.stockItems = stockItems;
    }

    /**
     * Make an instance of FileService
     *
     * @return FileService
     */
    public static FileService make() { 
        return new FileService();
    }

    /**
     * Make an instance of FileService with ArrayList<ASCStockItemInterface> passed
     *
     * @return FileService
     */
    public static FileService make(ArrayList<ASCStockItemInterface> stockItems) {
        return new FileService(stockItems);
    }

    /**
     * Load data from file
     *
     * @return ArrayList<StockItem> 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public ArrayList<ASCStockItemInterface> loadData() throws FileNotFoundException, IOException {
        String pathname = FileService.RELATIVE_FILE_PATH + FileService.INPUT_FILE_PATH;
        File inputFile = new File(pathname);

        if (!inputFile.exists() || !inputFile.isFile()) {
            throw new FileNotFoundException("Stock Items data file (" + FileService.INPUT_FILE_PATH + ") does not exist as a data file.");
        }

        Scanner fileScanner = new Scanner(inputFile);
        this.buildStockItemArrayList(fileScanner);
        fileScanner.close();

        if (this.stockItems.size() == 0) {
            throw new FileNotFoundException("Stock Items data file (" + FileService.INPUT_FILE_PATH + ") is empty.");
        }

        return this.stockItems;
    }

    /**
     * Build and array list of stock item out of string loaded from file
     *
     * @param fileScanner : Scanner
     */
    private void buildStockItemArrayList(Scanner fileScanner) {
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();
            if (!line.isEmpty()) {
                stockItems.add(this.createStockItemFromLine(line));
            }
        }
    }

    /**
     * Create a single ASCStockItemInterface instance from one line of of file string
     *
     * @param line: String
     * @return
     */
    private ASCStockItemInterface createStockItemFromLine(String line) {
        String[] lineArray = line.split(FileService.DELIMITER);
        String productCode = lineArray[0];
        String title = lineArray[1];
        String description = lineArray[2];
        int unitPricePounds = Integer.parseInt(lineArray[3]);
        int unitPricePence = Integer.parseInt(lineArray[4]);
        int quantityOnStock = Integer.parseInt(lineArray[5]);
        return new ASCStockItem(
                productCode, title,
                description, unitPricePounds,
                unitPricePence, quantityOnStock
        );
    }

    /**
     * Save An Array of stock items to output file
     *
     * @throws IOException
     */
    public void saveData() throws IOException {
        Path path = Paths.get(FileService.RELATIVE_FILE_PATH + FileService.OUTPUT_FILE_PATH);
        Files.deleteIfExists(path);
        BufferedOutputStream buffer = new BufferedOutputStream(
                Files.newOutputStream(path, CREATE, WRITE)
        );
        byte outputStringBytes[] = this.buildOutputString().getBytes();
        buffer.write(outputStringBytes, 0, outputStringBytes.length);
        buffer.close();
    }

    /**
     * Build a string of stock items from the instance array of stock items
     *
     * @return outputString: String
     */
    private String buildOutputString() {
        String outputString = "";
        for (int i = 0; i < this.stockItems.size(); i++) {
            outputString += this.formatStockItemString(
                    stockItems.get(i)
            );
        }
        return outputString;
    }

    /**
     * Create a single line string representation of a stock item from an
     * instance
     *
     * @param stockItem: ASCStockItem
     * @return String
     */
    private String formatStockItemString(ASCStockItemInterface stockItem) {
        return new Formatter().format("%s,%s,%s,%s,%s,%s\r%n",
                stockItem.getProductCode(), stockItem.getproductTitle(),
                stockItem.getProductDescription(), stockItem.getUnitPricePounds(),
                stockItem.getUnitPricePence(), stockItem.getQuantityOnStock()
        ).toString();
    }
}
