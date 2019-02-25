// Use Synchronize method to enable synchronization between multiple threads trying to access method at same time.
// Example that shows multiple threads
// can execute the same method but in
// synchronized way.
class Line
{

	// if multiple threads(trains) trying to access
	// this synchronized method on the same Object
	// but only one thread will be able
	// to execute it at a time.
	synchronized public void getLine()
	{
		for (int i = 0; i < 3; i++)
		{
			System.out.println(i);
			try
			{
				Thread.sleep(400);
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
	}
}

class Train extends Thread
{
	// Reference variable of type Line.
	Line line;

	Train(Line line)
	{
		this.line = line;
	}

	@Override
	public void run()
	{
		line.getLine();
	}
}

class SynchronizeMethodClass
{
	public static void main(String[] args)
	{
		Line obj = new Line();

		// we are creating two threads which share
		// same Object.
		Train train1 = new Train(obj);
		Train train2 = new Train(obj);

		// both threads start executing .
		train1.start();
		train2.start();
	}
}
