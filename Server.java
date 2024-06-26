import java.io.*;
import java.net.*;
class Server
{
	//class variables
	private Socket statergy1;
	private int statergy1_score;
	private Socket statergy2;
	private int statergy2_score;
    private ServerSocket server;

	int port;
	int totalrounds;

	//class constructors

	Server(int port,int totalrounds)
	{
		this.port = port;
		this.totalrounds = totalrounds;
	}
	Server()
	{
		port = 5000;
		totalrounds = 200;
	}

	//class fuctions

	void startServer()
	{
		try
		{
			server = new ServerSocket(port);
			statergy1 = server.accept();
			statergy2 = server.accept();
		}
		catch(IOException i)
		{
			System.out.println(i + "Could not connect...\nExiting...");
			System.exit(0);
		}
	}

	void calculateScore(String m1 ,String m2)
	{	
		if(m1=="c" && m2 == "d")
			statergy2_score += 5;
		else if(m1=="d" && m2 == "c")
			statergy1_score += 5;
		else if(m1=="c" && m2 == "c")
		{
			statergy1_score += 3;
			statergy2_score += 3;
		}
		else
		{
			statergy1_score += 1;
			statergy2_score += 1;
		}
	}

	void startGamePlay()
	{
		Messenger s1 = new Messenger(statergy1);
		Messenger s2 = new Messenger(statergy2);

		while(totalrounds>=0)
		{
			String m1 = s1.getMessage();
			String m2 = s2.getMessage();

			calculateScore(m1,m2);

			s1.sendMessage(m2);
			s2.sendMessage(m1);

			totalrounds--;
		}
	}
}
class Messenger extends Thread
{
	private Socket statergy;
	private String message;
	Messenger(Socket statergy)
	{
		this.statergy = statergy;
	}
	String getMessage()
	{
		run();
		return message;
	}
	void sendMessage(String message)
	{

	}
	public void run()
	{
		message = ;
	}
}