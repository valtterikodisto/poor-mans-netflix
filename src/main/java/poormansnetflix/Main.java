package poormansnetflix;

import java.io.IOException;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        
        Dotenv dotenv;
        try {
            dotenv = Dotenv.load();
        } catch (Exception ex) {
            dotenv = Dotenv.configure()
                    .directory("../")
                    .load();
        } 
        
        Interface cli = new Interface(dotenv.get("OMDB_APIKEY"));
        cli.start();
    }
    
}
