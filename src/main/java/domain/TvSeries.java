package domain;

public class TvSeries {
    
    private String Title, Released, imdbID;
    private Integer totalSeasons;

    public TvSeries(String Title, String Released, String imdbID, Integer totalSeasons) {
        this.Title = Title;
        this.Released = Released;
        this.imdbID = imdbID;
        this.totalSeasons = totalSeasons;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getTitle() {
        return Title;
    }

    public String getReleased() {
        return Released;
    }

    public Integer getTotalSeasons() {
        // May be null
        return totalSeasons;
    }

    @Override
    public String toString() {
        return "TvSeries{" + "Title=" + Title + ", Released=" + Released + ", imdbID=" + imdbID + ", totalSeasons=" + totalSeasons + '}';
    }
    
}
