package process.scheduling;

public class Process
{
	public int pid;
	public int bursttime;
	public int arrivaltime;
	public int priority;
	
	public Process()
	{
	}
	
	public Process(int pi, int b, int a, int p)
	{
		pid = pi;
		bursttime = b;
		arrivaltime = a;
		priority = p;
	}
	
	public void setPid(int pi)
	{
		pid = pi;
	}
	
	public int getPid()
	{
		return pid;
	}
	
	public void setBursttime(int b)
	{
		bursttime = b;
	}
	
	public int getBursttime()
	{
		return bursttime;
	}
	
	public void setArrivaltime(int a)
	{
		arrivaltime = a;
	}
	
	public int getArrivaltime()
	{
		return arrivaltime;
	}
	
	public void setPriority(int p)
	{
		priority = p;
	}
	
	public int getPriority()
	{
		return priority;
	}
}