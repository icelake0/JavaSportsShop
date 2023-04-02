package oop.ica.e2;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.AbstractTableModel;

/**
 * @author Gbemileke Ajiboye - C2479785
 */
public class ASCTableModel extends AbstractTableModel {

    /**
     * @var string array of JTable column names
     */
    private String[] columnNames;

    /**
     * @var object array JTable data
     */
    private Object[][] data;

    /**
     * Class constructor
     *
     * @param columnNames
     * @param dataList
     */
    public ASCTableModel(final String[] columnNames, final ArrayList<ASCStockItemInterface> dataList) {
        this.columnNames = Arrays.copyOf(columnNames, columnNames.length);
        this.data = new Object[dataList.size()][columnNames.length];
        this.populateDataWithStockItems(dataList);
    }

    /**
     * Populate the model data with stock items
     *
     * @param ArrayList<StockItem> dataList
     */
    private void populateDataWithStockItems(final ArrayList<ASCStockItemInterface> dataList) {
        for (int index = 0; index < dataList.size(); index++) {
            data[index] = this.getStockItemDataObject(dataList.get(index));
        }
    }

    /**
     * Get stock item data object for a stock item
     *
     * @param stockItem
     * @return Object[]
     */
    private Object[] getStockItemDataObject(ASCStockItemInterface stockItem) {
        return Arrays.copyOf(new Object[]{
            stockItem.getProductCode(), stockItem.getproductTitle(),
            stockItem.getProductDescription(), stockItem.getUnitPriceFull(),
            stockItem.getQuantityOnStock()
        }, columnNames.length);
    }

    /**
     * Get total number of rows in the JTable model
     *
     * @return int
     */
    @Override
    public int getRowCount() {
        return data.length;
    }

    /**
     * Get total number of columns in the JTable model
     *
     * @return
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Get the value at a row and column index of the JTable model
     *
     * @param rowIndex
     * @param columnIndex
     * @return Object
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    /**
     * Set the value at a row and column index of the JTable model
     *
     * @param value
     * @param rowIndex
     * @param columnIndex
     */
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = value;
        fireTableCellUpdated(rowIndex, columnIndex);
    }

}
