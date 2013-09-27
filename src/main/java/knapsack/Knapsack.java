package main.java.knapsack;

import java.util.Deque;

public interface Knapsack {

    void getArray(String filePath);
    public int getTotalResult();
    public Deque<Item> getSelectedItems();
    public void calculate();
    public String getSelectedNumbers();
}