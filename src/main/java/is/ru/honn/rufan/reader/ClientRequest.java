package is.ru.honn.rufan.reader;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Handles requests from clients to read from file
 * or url
 */
public class ClientRequest
{
    /**
     * Empty Constructor
     */
    public ClientRequest()
    {
    }

    /**
     * Gets content form url
     * @param url to get content from
     * @return String from url
     */
    public String getRequest(String url)
    {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        String result = response.readEntity(String.class);
        client.close();

        return result;
    }

    /**
     * Gets content from file
     * @param fileName name of file
     * @return content of file
     * @throws ReaderException
     */
    public String getFileContent(String fileName) throws ReaderException
    {
        StringBuilder content = new StringBuilder();
        try
        {
            java.io.FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null)
            {
                content.append(line);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex)
        {
              String msg = "Unable to open file '" + fileName + "'";
              throw new ReaderException(msg, ex);
        }
        catch(IOException ex)
        {
              String msg = "Error reading file '" + fileName + "'";
              throw new ReaderException(msg, ex);
        }
        return content.toString();
    }
}
