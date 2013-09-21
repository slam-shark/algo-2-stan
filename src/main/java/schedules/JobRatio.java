/*
 * JobDifference extends Job abstract class
 * This Jobs compared by order of the ratio (weight/length)
 */
package main.java.schedules;

public class JobRatio extends Job {

    public JobRatio(int w, int l) {
	super(w, l);
    }

    @Override
    public double getSignificance() {
	// returns the ratio (weight/length)
	return (1.0 * weight / length);
    }

    @Override
    public String getDescription() {
	return "ratio (weight/length)";
    }

}
