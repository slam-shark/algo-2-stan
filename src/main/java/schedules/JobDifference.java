/*
 * JobDifference extends Job abstract class
 * This Jobs compared by order of the difference (weight - length) 
 */
package main.java.schedules;

public class JobDifference extends Job {

    public JobDifference(int w, int l) {
	super(w, l);
    }

    @Override
    public double getSignificance() {
	// returns the difference (weight - length)
	return (weight - length);
    }

    @Override
    public String getDescription() {
	return "difference (weight - length)";
    }

}
