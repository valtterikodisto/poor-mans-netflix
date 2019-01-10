package poormansnetflix;

import java.io.IOException;
import java.util.Scanner;
import builder.ScriptBuilder;


public class Interface {
    
    private ScriptBuilder builder;

    public Interface(String apikey) {
        this.builder = new ScriptBuilder(apikey);
    }
    
    public void start() throws IOException, InterruptedException {
        
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Name of the TV series: ");
            String keyword = scanner.nextLine();

            System.out.print("Season: ");
            int season = Integer.parseInt(scanner.nextLine());

            System.out.print("Episode: ");
            int episode = Integer.parseInt(scanner.nextLine());

            int response = this.builder.build(keyword, season, episode);
            switch (response) {
                case 0: return;
                default: System.out.println("Could not find '" + keyword + "'");
            }
        }
    }
    
    
    
    
}
