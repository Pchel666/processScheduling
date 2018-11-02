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
	
	private int[] FCFS()
	{
		//first come first serve - non-preemptive
	}
	
	private int[] SJF()
	{
		//short job first - non-preemptive
	}
	
	private int[] SRT()
	{
		//shortest remaining time - preemptive
	}
	
	private int[] Priority()
	{
		//priority - preemptive
	}
	
	private int[] RRf()
	{
		//round robin (fixed)
	}
	
	private int[] RRv()
	{
		//round robin (variable)
	}
}