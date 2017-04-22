import java.io.*;
import java.net.*;

public final class Ex2Client
{
	public static void main (String[] args) throws IOException
	{
		String serverOutput = "";
		String[] firstHalf = new String[50]; // first 2 bytes of concatenation
		String[] secondHalf = new String[50]; // second 2 bytes of concatenation

		try
		{
			Socket socket = new Socket ("codebank.xyz", 38102);
			if (socket.isConnected())
				System.out.println("Connected to server.");	

			System.out.println("Received bytes:");

			{
				// firstHalf[i] = BR.readLine();
				// secondHalf[i] = BR.readLine();
				// System.out.println("First 2 bytes: " + firstHalf[i] + "Second 2 bytes: " + secondHalf[i]); // debug
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
}