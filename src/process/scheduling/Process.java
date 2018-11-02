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
	
	public void setPid(int p)
	{
		pid = p;
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
}