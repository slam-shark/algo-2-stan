package main.java.knapstack;

import java.util.Deque;

public interface Knapstack {

    void getArray(String filePath);
    public int getTotalResult();
    public Deque<Item> getSelectedItems();
    public void calculate();
    public String getSelectedNumbers();
}
