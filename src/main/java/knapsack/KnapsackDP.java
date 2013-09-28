package main.java.knapsack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class KnapsackDP implements Knapsack {

    int size;
    int numberOfItems;
    List<Item> items;
    int[][] vals;
    int total = -1;
    NavigableSet<Item> selectedItems;

    public KnapsackDP() {
    }

    public KnapsackDP(String filePath) {
	getArray(filePath);
	numberOfItems = items.size();
	vals = new int[size + 1][numberOfItems + 1];
	for (int i = 0; i <= size; i++)
	    vals[i][0] = 0;

    }

    @Override
    public void getArray(String filePath) {
	items = new ArrayList<Item>();
	try {
	    FileReader fr = new FileReader(filePath);
	    @SuppressWarnings("resource")
	    BufferedReader br = new BufferedReader(fr);
	    String line = br.readLine();
	    int tmp = Integer.parseInt(line.trim().split("(\\s)+")[0]);
	    if (tmp <= 0)
		throw new Exception("Knapstack size should be positive!");
	    size = tmp;
	    int name = 0;
	    while ((line = br.readLine()) != null) {
		String[] split = line.trim().split("(\\s)+");
		int val = Integer.parseInt(split[0]);
		int weig = Integer.parseInt(split[1]);
		items.add(new Item(val, weig, ++name));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Override
    public int getTotalResult() {
	if (total < 0)
	    calculate();
	return total;
    }

    @Override
    public NavigableSet<Item> getSelectedItems() {
	if (selectedItems == null)
	    traceBack();
	return selectedItems;
    }

    @Override
    public String getSelectedNumbers() {
	if (selectedItems == null)
	    traceBack();
	StringBuilder sb = new StringBuilder("Result: ");
	Iterator<Item> li = selectedItems.iterator();
	System.out.println(selectedItems.size());
	while (li.hasNext()) {
	    sb.append(li.next().lbl);
	    if (li.hasNext())
		sb.append(", ");
	}
	return sb.toString();
    }

    @Override
    public void calculate() {
	for (int i = 1; i <= numberOfItems; i++) {
	    Item it = items.get(i - 1);
	    for (int j = 0; j <= size; j++) {
		if (it.weight > j)
		    vals[j][i] = vals[j][i - 1];
		else
		    vals[j][i] = getMax(vals[j][i - 1], vals[j - it.weight][i - 1] + it.value);
	    }
	}
	total = vals[size][numberOfItems];
    }

    private int getMax(int a, int b) {
	return a > b ? a : b;
    }

    private void traceBack() {
	if (selectedItems == null)
	    calculate();
	selectedItems = new TreeSet<Item>(new Comparator<Item>() {

	    @Override
	    public int compare(Item o1, Item o2) {
		if (o1.lbl < o2.lbl)
		    return -1;
		if (o1.lbl > o2.lbl)
		    return 1;
		return 0;
	    }
	});
	int i = numberOfItems;
	int j = size;
	while (vals[j][i] != 0) {
	    if (vals[j][i - 1] == vals[j][i]) {
		i--;
		continue;
	    }
	    selectedItems.add(items.get(i - 1));
	    i--;
	    j -= items.get(i).weight;
	}
    }
}
