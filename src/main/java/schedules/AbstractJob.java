/*
 * Scheduling job
 * Provides implementation of Comparable's method: compareTo
 * Defines  getSignificance() method to compare different jobs
 */
package main.java.schedules;

public abstract class AbstractJob implements Comparable<AbstractJob> {
    final int weight;
    final int length;

    AbstractJob(int w, int l) {
	if (w <= 0)
	    throw new IllegalArgumentException("weight of job should be positive ");
	if (l <= 0)
	    throw new IllegalArgumentException("lenght of job should be positive ");
	weight = w;
	length = l;
    }

    public abstract double getSignificance();

    public abstract String getDescription();

    @Override
    public int compareTo(AbstractJob j) {
	double signThis = getSignificance();
	double signThat = j.getSignificance();
	if (signThis < signThat)
	    return -1;
	if (signThis > signThat)
	    return 1;
	if (weight < j.weight)
	    return -1;
	if (weight > j.weight)
	    return 1;
	return 0;
    }
}
