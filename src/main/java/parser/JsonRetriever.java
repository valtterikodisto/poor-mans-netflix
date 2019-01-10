package parser;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.HttpURLConnection;

public class JsonRetriever {
    
    public JsonObject readFromUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        
        HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
        httpcon.addRequestProperty("User-Agent", "Mozilla/5.0");
        Scanner scanner = new Scanner(httpcon.getInputStream());
        String  jsonString = "";
        while (scanner.hasNext()) {
            jsonString += scanner.nextLine();
        }
        scanner.close();
        return new JsonParser().parse(jsonString).getAsJsonObject();
    }
    
}
