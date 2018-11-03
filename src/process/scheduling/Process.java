package process.scheduling;

public class Process
{
	private int bursttime;
	private int arrivaltime;
	private int priority;
	
	public Process()
	{
	}
	
	public Process(int b, int a, int p)
	{
		bursttime = b;
		arrivaltime = a;
		priority = p;
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