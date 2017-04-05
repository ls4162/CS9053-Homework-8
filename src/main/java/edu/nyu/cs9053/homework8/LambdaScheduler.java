package edu.nyu.cs9053.homework8;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections; 

/**
 * User: Lei Su
 * Date: 4/2/17
 * Time: 11:40 AM
 */
class LambdaScheduler {
	
    /**
     * @param jobs a list of jobs to be scheduled
     * @return accepted a list of jobs accepted by the scheduler in execution order
     */
    public ArrayList<Job> scheduler(ArrayList<Job> jobs) {
        ArrayList<Job> accepted = new ArrayList<>();
        if (jobs == null || jobs.size() == 0) {
            return accepted;
        }

        sortByEndTime(jobs);
        findAcceptable(jobs, accepted);

        return accepted;
	}

    /**
     * sort jobs by end time in ascending order
     */
	private void sortByEndTime(ArrayList<Job> jobs) {
        Collections.sort(jobs, new
            Comparator<Job>()
            {
                public int compare(Job a, Job b)
                {
                    LocalDateTime endTimeA = a.getEnd();
                    LocalDateTime endTimeB = b.getEnd();
                    return endTimeA.compareTo(endTimeB);
                }
            });
    }
	
    /**
     * schedule max amount of jobs without overlapping
     * by searching the smallest start time no less than the previous end time
     */		
    private void findAcceptable(ArrayList<Job> jobs, ArrayList<Job> accepted) {
        int i = 0;
        LocalDateTime previousEndTime;
        do {				
            accepted.add(jobs.get(i));
            previousEndTime = jobs.get(i).getEnd();
            i++;
            while (i < jobs.size() && jobs.get(i).getStart().compareTo(previousEndTime) < 0) {
                i++;
            }
        } while (i < jobs.size());
    }
}