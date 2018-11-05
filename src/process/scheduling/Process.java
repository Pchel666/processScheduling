package process.scheduling;

public class Process
{
	private int bursttime;
	private int arrivaltime;
	private int priority;
	private int waittime;
	private int turnaroundtime;
	
	public Process()
	{
		bursttime = 0;
		arrivaltime = 0;
		priority = 0;
		waittime = 0;
		turnaroundtime = 0;
	}
	
	public Process(int b, int a, int p, int w, int t)
	{
		bursttime = b;
		arrivaltime = a;
		priority = p;
		waittime = w;
		turnaroundtime = t;
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
	
	public void setWaittime(int w)
	{
		waittime = w;
	}
	
	public int getWaittime()
	{
		return waittime;
	}
	
	public void setTurnaroundtime(int t)
	{
		turnaroundtime = t;
	}
	
	public int getTurnaroundtime()
	{
		return turnaroundtime;
	}
}