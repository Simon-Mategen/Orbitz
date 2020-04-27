package Model;

public class Song
{
    String artist;
    String songName;
    String path;

    public Song(String artist, String songName, String path)
    {
        this.artist = artist;
        this.songName = songName;
        this.path = path;
    }

    public String getArtist() {
        return artist;
    }

    public String getPath() {
        return path;
    }

    public String getSongName() {
        return songName;
    }

    public String toString()
    {
        String res = (artist + " - " + songName);
        return res;
    }
}
