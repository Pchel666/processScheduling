package process.scheduling;

import java.util.Scanner;

public class Scheduler 
{	
	public static void main(String[] args)
	{
		int counter = 0;
		int holder = 0;
		boolean arrtimes = false;
		boolean priorities = false;
		
		//setting array of processes
		Scanner reader = new Scanner(System.in);
		System.out.println("How many processes?");
		holder = reader.nextInt();
		Process[] processes = new Process[holder];
		
		//checking if arrival times will be given
		while(counter==0)
		{
			System.out.println("Are there arrival times?(1 for yes, 0 for no)");
			holder = reader.nextInt();
			if(holder==0)
			{
				arrtimes = false;
				counter=1;
			}
			if(holder==1)
			{
				arrtimes = true;
				counter=1;
			}
		}
		counter=0;
		
		//checking if priority values will be given
		while(counter==0)
		{
			System.out.println("Are there priority values?(1 for yes, 0 for no)");
			holder = reader.nextInt();
			if(holder==0)
			{
				priorities = false;
				counter=1;
			}
			if(holder==1)
			{
				priorities = true;
				counter=1;
			}
		}
		counter=0;
		
		//getting all process information
		for (int i=0;i<processes.length;i++)
		{
			processes[i] = new Process();
			System.out.println("Inputting values for process " + i);
			System.out.println("What is the burst time?");
			holder = reader.nextInt();
			processes[i].setBursttime(holder);
			if(arrtimes)
			{
				System.out.println("What is the arrival time?");
				holder = reader.nextInt();
				processes[i].setArrivaltime(holder);
			}
			if(priorities)
			{
				System.out.println("What is the priority value?");
				holder = reader.nextInt();
				processes[i].setPriority(holder);
			}
		}
		
	//choosing algorithm
	//TODO:choose algorithm, make method for each algorithm, make the chart
	}
	
	private double[] FCFS(Process[] process, int length)
	{
		//first come first serve - non-preemptive
		double[] results = {};
		double wt= 0;
		double tat= 0;
		
		for(int i= 0; i< length; i++)
		{	
			if(i>0)
			{
				wt+= tat;
			}
			
			tat+= process[i].getBursttime();
		}
		
		//wait time average
		results[0]= wt/length;
		
		//turn around time average
		results[1]= tat/length;
		
		return results;
	}
	
	private double[] SJF(Process[] process)
	{
		//short job first - non-preemptive
		double[] results = {0,0};
		double wt= 0;
		double tat= 0;
		int counter = 0;
		for(int i=0;i<process.length;i++)
		{
			if (process[i].getArrivaltime()>0)
			{
				counter=1;
			}
		}		
		return results;
	}
	
	private double[] SRT(Process[] process)
	{
		//shortest remaining time - preemptive
		double[] results = {0,0};
		
		return results;
	}
	
	private double[] Priority(Process[] process)
	{
		//priority - preemptive
		double[] results = {0,0};
		
		return results;
	}
	
	private double[] RRf(Process[] process)
	{
		//round robin (fixed)
		double[] results = {0,0};
		
		return results;
	}
	
	private double[] RRv(Process[] process)
	{
		//round robin (variable)
		double[] results = {0,0};
		
		return results;
	}
}