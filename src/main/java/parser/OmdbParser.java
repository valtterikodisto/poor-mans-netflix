package parser;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domain.TvSeries;
import java.io.IOException;


public class OmdbParser extends JsonRetriever {
    
    private String apikey;

    public OmdbParser(String apikey) {
        this.apikey = apikey;
    }
    
    
    private String buildUrl(String search) {
        String baseUrl = "http://www.omdbapi.com/?apikey=" + this.apikey + "&t=";
        String trimmed = search.trim().toLowerCase().replaceAll("\\s+", "+");
        
        return baseUrl + trimmed;
    }
    
    public TvSeries read(String search) throws IOException {
        JsonObject fullJsonResponse = super.readFromUrl(buildUrl(search));
        boolean response = fullJsonResponse.get("Response").getAsBoolean();
        if (response) {
            return new Gson().fromJson(fullJsonResponse.toString(), TvSeries.class);
        } else {
            return null;
        }
        
    }
    
    
}
