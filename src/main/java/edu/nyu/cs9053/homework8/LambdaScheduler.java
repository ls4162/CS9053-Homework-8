package edu.nyu.cs9053.homework8;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
// maximizes the number of jobs a single container can accept
// each Lambda job consists of the following: a starting time s until a final time t.
// Given a list of jobs you must accept a subset of the jobs, rejecting all others, so that the accepted jobs do not overlap in time.
// 


class LambdaScheduler {
	public ArrayList<Job> scheduler(ArrayList<Job> jobs) {
		// corner case
		ArrayList<Job> accepted = new ArrayList<>();
		if (jobs == null || jobs.size() == 0) {
			return accepted;
		}

		sortByEndTime(jobs);
		findAcceptable(jobs, accepted);

		return accepted;
	}

	// sort jobs by end time
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
		
		
	// fixed end time, find earliest later start time	
	private void findAcceptable(ArrayList<Job> jobs, ArrayList<Job> accepted) {
		int i = 0;
		LocalDateTime previousEndTime;
		do {				// use iterator?
			accepted.add(jobs.get(i));
			previousEndTime = jobs.get(i).getEnd();
			i++;
			while (i < jobs.size() && jobs.get(i).getStart().compareTo(previousEndTime) < 0) {
				i++;
			}
		} while (i < jobs.size());
	}

}