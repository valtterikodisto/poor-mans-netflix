package parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.MalformedURLException;
import com.google.gson.JsonObject;
import domain.Torrent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EztvParser extends JsonRetriever {
    
    private int pageLimit;
       
    public EztvParser() {
        this.pageLimit = 100;
        
    }
    
    public Torrent read(String imdbId, int season, int episode) throws MalformedURLException, IOException {
        int page = 1;
        imdbId = imdbId.replaceAll("[^0-9]", "");
        ArrayList<Torrent> torrents = new ArrayList<>();

        while (true) {
            String url = buildUrlWithImdbId(imdbId, page);
            JsonObject fullJsonResponse = super.readFromUrl(url);

            // Extract data from JSON
            page = fullJsonResponse.get("page").getAsInt();
            
            int results = fullJsonResponse.get("torrents_count").getAsInt();
            
            String torrentsJson = fullJsonResponse.getAsJsonArray("torrents").toString();
            torrents.addAll(new Gson().fromJson(torrentsJson, new TypeToken<List<Torrent>>() {}.getType()));

            if (!hasNextPage(page, results)) break;
            else page++;
        }
        
        Torrent viable = null;
        
        if (!torrents.isEmpty()) {
            viable = searchBySeasonAndEpisode(torrents, season, episode);
        }
        
        return viable;
    }
    
    private String buildUrlWithImdbId(String imdbID, int page) {
        String stringUrl = "https://eztv.io/api/get-torrents?imdb_id=" + imdbID + "&limit=" + this.pageLimit + "&page=" + page;
        return stringUrl;
    }
    
    private boolean hasNextPage(int page, int results) {
        int numberOfPages = results / this.pageLimit + 1;
        if (results % this.pageLimit != 0) {
            return page < numberOfPages;
        } else {
            numberOfPages--;
            return page < numberOfPages;
        }
    }
    
    private Torrent searchBySeasonAndEpisode(List<Torrent> torrents, int season, int episode) {
        List<Torrent> possibleTorrents = new ArrayList<>();

        for (Torrent torrent : torrents) {
            if (torrent.getSeason() == season && torrent.getEpisode() == episode) {
                possibleTorrents.add(torrent);
            }
        }
        Collections.sort(possibleTorrents);
        try {
            Torrent viable = possibleTorrents.get(0);
            return viable;
        } catch (Exception e) {
            // Couldn't find season + episode torrents
            return null;
        }
        
        
    }
      
    
    
}
