package am.itspace.hateoas.service;


import am.itspace.hateoas.model.Album;
import am.itspace.hateoas.model.Artist;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Service
public class MusicService {

    private Map<String, Album> albums;
    private Map<String, Artist> artists;

    /**
     * Constructor populates the 'database' (i.e. Maps) of Artists and Albums.
     */
    public MusicService() {

        albums = new HashMap<>();
        artists = new HashMap<>();

        Artist artist1 = Artist.builder()
                .id("em")
                .name("Eminem")
                .build();

        Artist artist2 = Artist.builder()
                .id("cent")
                .name("50 Cent")
                .build();

        artists.put(artist1.getId(), artist1);
        artists.put(artist2.getId(), artist2);

        Album album1 = Album.builder()
                .id("1")
                .title("Kamikaze")
                .artist(artist1)
                .stockLevel(2)
                .build();

        Album album2 = Album.builder()
                .id("2")
                .title("Get Rich or Die Tryin’")
                .artist(artist2)
                .stockLevel(3)
                .build();

        albums.put(album1.getId(), album1);
        albums.put(album2.getId(), album2);
    }

    public Collection<Album> getAllAlbums() {
        return albums.values();
    }

    public Album getAlbum(String id) {
        return albums.get(id);
    }

    public Artist getArtist(String id) {
        return artists.get(id);
    }

}