import java.io.*;
import java.net.*;
import java.util.zip.CRC32;
/**
 *
 * @author rivas1
 */
public class Ex2Client 
{
    public static void main ( String[] args ) throws IOException
    {
        try
        {
            Socket socket = new Socket ("codebank.xyz", 38102);
            String[] first = new String[100];
            String[] second = new String[100];
            String[] cat = new String[100];
            byte[] cat2 = new byte[100];
            int[] cat3 = new int[100];
            CRC32 error = new CRC32();
            int errorCode = -1;
            
            if (socket.isConnected())
                System.out.println("Connected to server.");
            
            // Read from Server
            InputStream IS = socket.getInputStream();
            for ( int i = 0; i < 100; i++ )
            {
                first[i] = Integer.toHexString( IS.read() );
                second[i] = Integer.toHexString( IS.read() );
            }
            // Concatenate
            for ( int i = 0; i < 100; i++ )
            {
                cat[i] = first[i] + second[i];
                System.out.println("cat["+i+"]: " + cat[i]);
                // convert to int after
                cat3[i] = Integer.parseInt( cat[i], 16 );
                // convert to byte after
                cat2[i] = (byte) cat3[i];
            }
           
            // Checksum
            error.update( cat2 );
            System.out.println("Calculated CRC32: " + error.getValue() );
            errorCode = (int) error.getValue();
        
            // Send to server
            PrintStream PS = new PrintStream( socket.getOutputStream() );
            PS.println(errorCode);
            
            // Print server's response
            System.out.println("Server: " + IS.read() );
            
            // Close the connection
            socket.close();
            System.out.println("Disconnected.");
        }
        
        catch ( IOException e )
        {   
            e.printStackTrace();
        }
    }
}
