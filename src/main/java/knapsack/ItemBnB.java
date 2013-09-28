package main.java.knapsack;

public class ItemBnB extends Item {

    double ratio;

    public ItemBnB(int value, int weight, int name) {
	super(value, weight, name);
	ratio = (double) value / weight;
    }

}
