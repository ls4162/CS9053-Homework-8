package edu.nyu.cs9053.homework8;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

/**
 * User: Lei Su
 * Date: 4/2/17
 * Time: 11:40 AM
 */
class LamdaWeightedScheduler {
	
	/**
     * @param jobs a list of jobs to be scheduled
     * @return largestProfits a list of jobs accepted by the scheduler to make most profits in execution order
     */
	public ArrayList<Job> weightedScheduler(ArrayList<Job> jobs) {
		ArrayList<Job> largestProfits = new ArrayList<>();
		if (jobs == null || jobs.size() == 0) {
			return largestProfits;
		}

		sortByProfitPerSecond(jobs);
		findLargestProfits(jobs, largestProfits);

		return largestProfits;
	}

	/**
     * sort jobs by profit per second in descending order
     */
	private void sortByProfitPerSecond(ArrayList<Job> jobs) {
		Collections.sort(jobs, new
			Comparator<Job>()
			{
				public int compare(Job a, Job b)
				{
					long durationA = ChronoUnit.SECONDS.between(a.getStart(), a.getEnd());
					long durationB = ChronoUnit.SECONDS.between(b.getStart(), b.getEnd());
					double  profitA = a.getProfit();
					double profitB = b.getProfit();
					double profitPerSecondA = (double) (profitA / durationA);
					double profitPerSecondB = (double) (profitB / durationB);

					return profitPerSecondA > profitPerSecondB ? -1 : profitPerSecondA < profitPerSecondB ? 1 : 0; 
				}
			});
	}

	/**
     * schedule jobs to make most profits without overlapping
     * by searching the smallest start time no less than the previous end time
     */		
	private void findLargestProfits(ArrayList<Job> jobs, ArrayList<Job> largestProfits) {
		int i = 0;
		LocalDateTime previousEndTime;
		do {				
			largestProfits.add(jobs.get(i));
			previousEndTime = jobs.get(i).getEnd();
			i++;
			while (i < jobs.size() && jobs.get(i).getStart().compareTo(previousEndTime) < 0) {
				i++;
			}
		} while (i < jobs.size());
	}
}