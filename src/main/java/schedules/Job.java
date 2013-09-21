/*
 * Scheduling job
 * Provides implementation of Comparable's method: compareTo
 * Defines  getSignificance() method to compare different jobs
 */
package main.java.schedules;

public abstract class Job implements Comparable<Job> {
    final int weight;
    final int length;

    Job(int w, int l) {
	weight = w;
	length = l;
    }

    public abstract double getSignificance();

    public abstract String getDescription();

    @Override
    public int compareTo(Job j) {
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
