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
	}
}