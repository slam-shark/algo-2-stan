package main.java.knapsack;

import java.util.NavigableSet;

public interface Knapsack {

    void getArray(String filePath);

    /**
     * 
     * @return total value of items in Knapsack
     */
    public int getTotalResult();
    
    /**
     * 
     * @return list of numbers of selected items in ASC order
     */
    public NavigableSet<? extends Item> getSelectedItems();

    /**
     * explicit calculation
     */
    public void calculate();

    /**
     * 
     * @return list of numbers of selected items in ASC order
     */
    public String getSelectedNumbers();
}
