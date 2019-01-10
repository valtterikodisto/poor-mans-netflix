package domain;

public class Torrent implements Comparable<Torrent>{
    
    private String filename, magnet_url, season, episode, seeds, peers, size_bytes;

    public Torrent(String filename, String magnet_url, String season, String episode, String seeds, String peers, String size_bytes) {
        this.filename = filename;
        this.magnet_url = magnet_url;
        this.season = season;
        this.episode = episode;
        this.seeds = seeds;
        this.peers = peers;
        this.size_bytes = size_bytes;
    }

    public String getMagnet_url() {
        return magnet_url;
    }
    
    
    
    public int getSeason() {
        if (this.season == null || this.season.isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(this.season);
        }
    }
    
    public int getEpisode() {
        if (this.episode == null || this.episode.isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(this.episode);
        }
    }
    
    public int getSeeds() {
        if (this.seeds == null || this.seeds.isEmpty()) return 0;
        else return Integer.parseInt(this.seeds);
    }

    @Override
    public String toString() {
        return "Torrent{" + "filename=" + filename + ", magnet_url=" + magnet_url + ", season=" + season + ", episode=" + episode + ", seeds=" + seeds + ", peers=" + peers + ", size_bytes=" + size_bytes + '}';
    }

    @Override
    public int compareTo(Torrent t) {
        return t.getSeeds() - this.getSeeds();
    }
    
    
    
}
