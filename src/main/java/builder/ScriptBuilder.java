package builder;

import domain.Torrent;
import domain.TvSeries;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import parser.EztvParser;
import parser.OmdbParser;

public class ScriptBuilder {
    
    private EztvParser eztv;
    private OmdbParser omdb;

    public ScriptBuilder(String apikey) {
        this.eztv = new EztvParser();
        this.omdb = new OmdbParser(apikey);
    }

    
    public int build(String keyword, int season, int episode) throws FileNotFoundException, UnsupportedEncodingException {
        /*
        RESPONSE:
        0 => successfull
        1 => tv series not found (OMDb)
        2 => tv series not found (eztv)
        */
        
        TvSeries tvSeries;
        Torrent torrent;
        String magnetUrl;
        try {
            tvSeries = this.omdb.read(keyword);
            tvSeries.getImdbID().length();
        } catch (IOException | NullPointerException ex) {
            return 1;
        }
        
        try {
            torrent = this.eztv.read(tvSeries.getImdbID(), season, episode);
            magnetUrl = torrent.getMagnet_url();
        } catch (IOException | NullPointerException ex) {
            return 2;
        }
        
        PrintWriter writer = new PrintWriter("./download.sh", "UTF-8");

        writer.println("#!/bin/bash");
        writer.println("KILLTORRENT=$(mktemp)");
        writer.println("chmod +x $KILLTORRENT");
        writer.println("echo 'killall transmission-cli' > $KILLTORRENT");
        writer.println("transmission-cli -w $HOME/PoorMansNetflixVideos -f $KILLTORRENT " + magnetUrl);

        writer.close();
        
        return 0;
    } 
    
 }
