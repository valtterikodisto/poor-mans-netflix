package builder;

import domain.Torrent;
import domain.TvSeries;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import parser.EztvParser;
import parser.OmdbParser;

public class ScriptBuilder {
    
    private EztvParser eztv;
    private OmdbParser omdb;

    public ScriptBuilder(String apikey) {
        this.eztv = new EztvParser();
        this.omdb = new OmdbParser(apikey);
    }

    
    public void build(String keyword, int season, int episode) throws IOException, InterruptedException {
        TvSeries tvSeries = this.omdb.read(keyword);
        Torrent torrent = this.eztv.read(tvSeries.getImdbID(), season, episode);
        String magnetUrl = torrent.getMagnet_url();
        
        PrintWriter writer = new PrintWriter("./download.sh", "UTF-8");

        writer.println("#!/bin/bash");
        writer.println("KILLTORRENT=$(mktemp)");
        writer.println("chmod +x $KILLTORRENT");
        writer.println("echo 'killall transmission-cli' > $KILLTORRENT");
        writer.println("transmission-cli -w $HOME/PoorMansNetflixVideos -f $KILLTORRENT " + magnetUrl);

        writer.close();

    } 
    
 }
