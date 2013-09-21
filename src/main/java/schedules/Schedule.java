package main.java.schedules;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Schedule {
    List<Job> jobs;
    List<Double> wct;
    double totalWCT = -1;

    public Schedule(String filePath, Job j) {
	jobs = getArray(filePath, j);
	Collections.sort(jobs);
	Collections.reverse(jobs);
    }

    /*
     * Counts weighted completion time for each job.
     */
    public void getWCT() {
	totalWCT = 0;
	int time = 0;
	for (Job j : jobs) {
	    time += j.length;
	    totalWCT += (time * j.weight);
	}
    }

    /*
     * Returns the sum of weighted completion times of the resulting schedule
     */
    public double getResult() {
	return totalWCT;
    }
    
    /*
    * Returns the formatted string of the sum of WCT
    */
   public String getFormattedResult() {
	return String.format("%.0f", totalWCT);
   }
    
    

    /*
     * Reads schedule from file. Format for 2+ line: {[i_weight] [i_length]}
     */
    private List<Job> getArray(String filePath, Job j) {
	List<Job> array = new ArrayList<Job>();
	try {
	    FileReader fr = new FileReader(filePath);
	    @SuppressWarnings("resource")
	    BufferedReader br = new BufferedReader(fr);
	    String line = br.readLine(); // no need in first line with # of jobs
	    while ((line = br.readLine()) != null) {
		String[] split = line.trim().split("(\\s)+");
		int w = Integer.parseInt(split[0]);
		int l = Integer.parseInt(split[1]);
		Job job =(Class.forName(j.getClass().getName())).asSubclass(Job.class).getConstructor(int.class,int.class).newInstance(w, l);
		array.add(job);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return array;
    }
}
