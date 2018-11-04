package process.scheduling;

import java.util.ArrayList;
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
		String alg = "";
		while(!alg.equals("FCFS")&&!alg.equals("SJF")&&!alg.equals("SRT")&&!alg.equals("Priority")&&!alg.equals("RRf")&&!alg.equals("RRv"))
		{
			System.out.println("What algorithm to use?(FCFS, SJF, SRT, Priority, RRf, RRv)");
			alg = reader.nextLine();
		}
		switch(alg)
		{
			case "FCFS":
				//FCFS
				break;
			case "SJF":
				SJF(processes);
				break;
			case "SRT":
				//SRT
				break;
			case "Priority":
				//Priority
				break;
			case "RRf":
				//RRf
				break;
			case "RRv":
				//RRv
				break;
		}
	}
	
	private double[] FCFS(Process[] process)
	{
		//first come first serve - non-preemptive
		Process[] reorgProcess = new Process[process.length];
		
		double[] results = {0,0};
		double wt= 0;
		double tat= 0;
		
		int counter = 0;
		//arrival time searched
		int scounter = 0;
		
		while (counter<process.length)
		{
			for(int i=0;i<process.length;i++)
			{
				if (process[i].getArrivaltime()==scounter)
				{
					reorgProcess[counter]=process[i];
					counter++;
				}
			}
			scounter++;
		}
		
		for(int i= 0; i< process.length; i++)
		{	
			wt+= tat;
			tat+= process[i].getBursttime();
		}
		
		//wait time average
		results[0]= wt/process.length;
		//turn around time average
		results[1]= tat/process.length;
		
		return results;
	}
	
	private static double[] SJF(Process[] process)
	{
		//short job first - non-preemptive
		Process[] reorgProcess = new Process[process.length]; //for reorganizing by arrival time
		ArrayList<Process> tempList = new ArrayList<Process>(0);
		double[] results = {0,0}; //{wait time, turnaround time}
		int counter = 0; //reorganized array position counter
		int scounter = 0; //arrival time counter
		int bcounter = 0; //burst time counter
		int maxb = 0; //maximum burst time
		for(int i=0;i<process.length;i++) //getting maximum burst time
		{
			if(process[i].getBursttime()>maxb)
			{
				maxb=process[i].getBursttime();
			}
		}
		while (counter<process.length) //reorganizing by arrival and burst times
		{
			for(int i=0;i<process.length;i++) //creates temporary arraylist with all processes with arrival time = scounter
			{
				if (process[i].getArrivaltime()==scounter)
				{
					tempList.add(process[i]);
				}
			}
			scounter++;
			while(bcounter<=maxb) //sorts the temporary arraylist processes by burst time and puts them in the reorganized processes array
			{
				for(int i=0;i<tempList.size();i++)
				{
					if(tempList.get(i).getBursttime()==bcounter)
					{
						reorgProcess[counter]=tempList.get(i);
						counter++;
					}
				}
				bcounter++;
			}
			tempList.clear();
			bcounter=0;
		}
		int ct = 0;
		for(int i=0;i<reorgProcess.length;i++)
		{
			if(ct<=reorgProcess[i].getArrivaltime())
			{
				ct=reorgProcess[i].getArrivaltime()+reorgProcess[i].getBursttime();
				results[1]+=ct-reorgProcess[i].getArrivaltime();
				results[0]+=results[1]-reorgProcess[i].getBursttime();
			}
			else
			{
				ct=ct+reorgProcess[i].getBursttime();
				results[1]+=ct-reorgProcess[i].getArrivaltime();
				results[0]+=results[1]-reorgProcess[i].getBursttime();
			}
		}
		results[0]=results[0]/reorgProcess.length;
		results[1]=results[1]/reorgProcess.length;
		return results;
	}
	
	private static double[] SRT(Process[] process)
	{
		//shortest remaining time - preemptive
		double[] results = {0,0}; //{wait time, turnaround time}
		
		return results;
	}
	
	private double[] Priority(Process[] process)
	{
		//priority - preemptive
		Process[] reorgProcess = new Process[process.length];
		
		double[] results = {0,0};
		double wt= 0;
		double tat= 0;
		
		int counter = 0;
		//arrival time searched
		int scounter = 0;
		
		while (counter<process.length)
		{
			for(int i=0;i<process.length;i++)
			{
				if (process[i].getArrivaltime()==scounter)
				{
					reorgProcess[counter]=process[i];
					counter++;
				}
			}
			scounter++;
		}
		
		//scenario where arrival time is the same, so processes get sorted by priority
		if(scounter == 0)
		{
			int count = 0;
			//arrival time searched
			int scount = 0;
			
			while (count<process.length)
			{
				for(int i=0;i<process.length;i++)
				{
					if (process[i].getPriority()==scount)
					{
						reorgProcess[count]=process[i];
						count++;
					}
				}
				scount++;
			}
	
			for(int i= 0; i< process.length; i++)
			{	
				wt+= tat;
				tat+= process[i].getBursttime();
			}
		}
		//scenario where arrival times are different
		else
		{
			while()
			for(int i= 0; i< process.length; i++)
			{	
				wt+= tat;
				
				tat+= process[i].getBursttime();
			}
		}
		
		//wait time average
		results[0]= wt/process.length;
		
		//turn around time average
		results[1]= tat/process.length;

		
		return results;
	}
	
	private static double[] RRf(Process[] process)
	{
		//round robin (fixed)
		double[] results = {0,0}; //{wait time, turnaround time}
		
		return results;
	}
	
	private static double[] RRv(Process[] process)
	{
		//round robin (variable)
		double[] results = {0,0}; //{wait time, turnaround time}
		
		return results;
	}
}
