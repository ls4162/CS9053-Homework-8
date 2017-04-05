package edu.nyu.cs9053.homework8;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
// In this case someone would pay more money to have their job run first. 
// Make an extension to your work that instead of optimizing for the number of jobs, 
// you now maximize for the total value 
// (i.e. each job now as an associated cost or weight and you're optimizing for the largest cost).


class LamdaWeightedScheduler {
	public ArrayList<Job> weightedScheduler(ArrayList<Job> jobs) {
		// corner case
		ArrayList<Job> largestProfits = new ArrayList<>();
		if (jobs == null || jobs.size() == 0) {
			return largestProfits;
		}

		sortByProfitPerSecond(jobs);
		findLargestProfits(jobs, largestProfits);

		return largestProfits;
	}

	// sort jobs by profit in descending order
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

	// fixed end time, find earliest later start time	
	private void findLargestProfits(ArrayList<Job> jobs, ArrayList<Job> largestProfits) {
		int i = 0;
		LocalDateTime previousEndTime;
		do {				// use iterator?
			largestProfits.add(jobs.get(i));
			previousEndTime = jobs.get(i).getEnd();
			i++;
			while (i < jobs.size() && jobs.get(i).getStart().compareTo(previousEndTime) < 0) {
				i++;
			}
		} while (i < jobs.size());
	}
}