package process.scheduling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Scheduler 
{	
	public static void main(String[] args)
	{
		int counter = 0;
		int holder = 0;
		boolean arrtimes = false;
		boolean priorities = false;
		
		System.out.println("////////////////////////////////////////////////////////////////////////////////////////\nLab 4- Operating Systems CPU Scheduling Algorithms\n\nAlexander Popov & Steven Perez\n////////////////////////////////////////////////////////////////////////////////////////\n");
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
		System.out.println("\n");
		//getting all process information
		int pidcounter = 1;
		for (int i=0;i<processes.length;i++)
		{
			processes[i] = new Process();
			System.out.println("Inputting values for process " + pidcounter);
			pidcounter++;
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
			System.out.println("*****************************************");
		}
		//choosing algorithm

		String alg = "";
		reader.nextLine();
/*
 * PREVIOUS 
 * 
		while(!alg.equals("FCFS")&&!alg.equals("SJF")&&!alg.equals("SRT")&&!alg.equals("Priority")&&!alg.equals("RRf")&&!alg.equals("RRv"))
		{
			System.out.println("What algorithm to use?(FCFS, SJF, SRT, Priority, RRf, RRv)");
			alg = reader.nextLine();
		}
		double[] output= {0,0};
		switch(alg)
		{
			case "FCFS":
				output = FCFS(processes);
				break;
			case "SJF":
				output = SJF(processes);
				break;
			case "SRT":
				output = SRT(processes);
				break;
			case "Priority":
				output = Priority(processes);
				break;
			case "RRf":
				output = RRf(processes);
				break;
			case "RRv":
				output = RRv(processes);
				break;
		}
		System.out.println("Average wait time: " + output[0] + " Average turnaround time: " + output[1]);
*/	
		
//NEW
 		double tatlist[] = new double[5];			
		double tatcompare[] = new double[5];
		
		while(true&& !alg.equals("done"))
		{

			System.out.println("\nWhat algorithm to use?(FCFS, SJF, SRT, Priority, RRf, RRv)");
			alg = reader.nextLine();
		
			double output[] = new double[2];
			
			switch(alg)
			{
				case "FCFS":
					output = FCFS(processes);
					break;
				case "SJF":
					output = SJF(processes);
					break;
				case "SRT":
					output = SRT(processes);
					break;
				case "Priority":
					output = Priority(processes);
					break;
				case "RRf":
					output = RRf(processes);
					break;
				case "RRv":
					output = RRv(processes);
					break;
			}
			
			//TRYING TO MODIFY TO KEEP TRACK OF PAST TAT's IN ORDER TO DECIDE WHICH ALGORITHM IS BEST---THIS PART DOES NOT WORK YET (FROM HERE TO END OF MAIN)
			double wt= output[0];
			double tat= output[1];
			
			if(!alg.equals("done"))
			{
				System.out.println("Average wait time: " + wt + " Average turnaround time: " + tat+"\n");
			}

			int i = 0;	
			tatlist[i]= tat;
			tatcompare[i]= tat;
			i++;
			
			//TESTING
			//System.out.println(tatlist[i]);
			//System.out.println(tatcompare[i]);
		}
		
		if(alg.equals("done"))
		{	
			Arrays.sort(tatcompare);

			double bestTAT= tatcompare[0];
			
			//TESTING
			//System.out.println(bestTAT);
					
			int index=0;
			
			for(int j= 0; j< tatcompare.length; j++)
			{
				if(bestTAT == tatlist[j])
				{
					index=j;
					break;
				}
			}
			index= index+1;
			
			//TESTING
			//System.out.println(index);
			
			System.out.println("\n\nAlgorithm " + index + " is the most efficient");
		}
	}
	
	private static double[] FCFS(Process[] process)
	{
		//first come first serve - non-preemptive
		Process[] reorgProcess = new Process[process.length];
		
		double[] results = {0,0};
		
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
		int ct = 0;
		for(int i=0;i<reorgProcess.length;i++)
		{
			if(ct<=reorgProcess[i].getArrivaltime())
			{
				ct=reorgProcess[i].getArrivaltime()+reorgProcess[i].getBursttime();
				reorgProcess[i].setTurnaroundtime(ct-reorgProcess[i].getArrivaltime());
				reorgProcess[i].setWaittime(reorgProcess[i].getTurnaroundtime()-reorgProcess[i].getBursttime());
			}
			else
			{
				ct=ct+reorgProcess[i].getBursttime();
				reorgProcess[i].setTurnaroundtime(ct-reorgProcess[i].getArrivaltime());
				reorgProcess[i].setWaittime(reorgProcess[i].getTurnaroundtime()-reorgProcess[i].getBursttime());
			}
		}
		for(int i=0;i<reorgProcess.length;i++)
		{
			results[0]+=reorgProcess[i].getWaittime();
			results[1]+=reorgProcess[i].getTurnaroundtime();
		}
		
		//wait time average
		results[0]= results[0]/process.length;
		//turn around time average
		results[1]= results[1]/process.length;
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
				reorgProcess[i].setTurnaroundtime(ct-reorgProcess[i].getArrivaltime());
				reorgProcess[i].setWaittime(reorgProcess[i].getTurnaroundtime()-reorgProcess[i].getBursttime());
			}
			else
			{
				ct=ct+reorgProcess[i].getBursttime();
				reorgProcess[i].setTurnaroundtime(ct-reorgProcess[i].getArrivaltime());
				reorgProcess[i].setWaittime(reorgProcess[i].getTurnaroundtime()-reorgProcess[i].getBursttime());
			}
		}
		for(int i=0;i<reorgProcess.length;i++)
		{
			results[0]+=reorgProcess[i].getWaittime();
			results[1]+=reorgProcess[i].getTurnaroundtime();
		}
		//wait time average
		results[0]= results[0]/process.length;
		//turn around time average
		results[1]= results[1]/process.length;
		return results;
	}
	
	private static double[] SRT(Process[] process)
	{
		//shortest remaining time - preemptive
		Process[] reorgProcess = new Process[process.length];
		
		double[] results = {0,0}; //{wait time, turnaround time}
		
		//wait time average
		results[0]= results[0]/process.length;
				
		//turn around time average
		results[1]= results[1]/process.length;
		return results;
	}
	
	private static double[] Priority(Process[] process)
	{
		//priority - preemptive
		Process[] reorgProcess = new Process[process.length];
		
		double[] results = {0,0};
		
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
		
		if(scounter== 1)
		{
			int count=0;
			int scount=0;
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
			
			int ct = 0;
			for(int i=0;i<reorgProcess.length;i++)
			{
				if(ct<=reorgProcess[i].getArrivaltime())
				{
					ct=reorgProcess[i].getArrivaltime()+reorgProcess[i].getBursttime();
					reorgProcess[i].setTurnaroundtime(ct-reorgProcess[i].getArrivaltime());
					reorgProcess[i].setWaittime(reorgProcess[i].getTurnaroundtime()-reorgProcess[i].getBursttime());
				}
				else
				{
					ct=ct+reorgProcess[i].getBursttime();
					reorgProcess[i].setTurnaroundtime(ct-reorgProcess[i].getArrivaltime());
					reorgProcess[i].setWaittime(reorgProcess[i].getTurnaroundtime()-reorgProcess[i].getBursttime());
				}
			}
			
			for(int i=0;i<reorgProcess.length;i++)
			{
				results[0]+=reorgProcess[i].getWaittime();
				results[1]+=reorgProcess[i].getTurnaroundtime();
			}
		}
		
		//wait time average
		results[0]= results[0]/process.length;
		//turn around time average
		results[1]= results[1]/process.length;

		return results;
	}
	
	private static double[] RRf(Process[] process)
	{
		//round robin (fixed)
		Process[] reorgProcess = new Process[process.length];
		
		double[] results = {0,0}; //{wait time, turnaround time}
		
		//wait time average
		results[0]= results[0]/process.length;		
		//turn around time average
		results[1]= results[1]/process.length;
		
		return results;
	}
	
	private static double[] RRv(Process[] process)
	{
		//round robin (variable)
		Process[] reorgProcess = new Process[process.length];
		
		double[] results = {0,0}; //{wait time, turnaround time}
		
		//wait time average
		results[0]= results[0]/process.length;		
		//turn around time average
		results[1]= results[1]/process.length;
		
		return results;
	}
}
