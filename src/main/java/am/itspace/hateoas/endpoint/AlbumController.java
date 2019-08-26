package am.itspace.hateoas.endpoint;

import am.itspace.hateoas.model.Album;
import am.itspace.hateoas.service.MusicService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/album")
public class AlbumController {

    private final MusicService musicService;

    @GetMapping
    public ResponseEntity<Collection<Resource<Album>>> getAllAlbums() {
        Collection<Album> albums = musicService.getAllAlbums();
        List<Resource<Album>> resources = new ArrayList<>();
        albums.forEach(album -> resources.add(getAlbumResource(album)));
        return ResponseEntity.ok(resources);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Resource<Album>> getAlbum(@PathVariable(value = "id") String id) {
        Album album = musicService.getAlbum(id);
        return ResponseEntity.ok(getAlbumResource(album));
    }

    @GetMapping(value = "/purchase/{id}")
    public ResponseEntity<Resource<Album>> purchaseAlbum(@PathVariable(value = "id") String id) {
        Album album = musicService.getAlbum(id);
        album.setStockLevel(album.getStockLevel() - 1);
        Resource<Album> resource = new Resource<>(album);
        resource.add(linkTo(methodOn(AlbumController.class).getAlbum(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    private Resource<Album> getAlbumResource(Album album) {
        Resource<Album> resource = new Resource<>(album);
        // Link to Album
        resource.add(linkTo(methodOn(AlbumController.class).getAlbum(album.getId())).withSelfRel());
        // Link to Artist
        resource.add(linkTo(methodOn(ArtistController.class).getArtist(album.getArtist().getId())).withRel("artist"));
        // Option to purchase Album
        if (album.getStockLevel() > 0) {
            resource.add(linkTo(methodOn(AlbumController.class).purchaseAlbum(album.getId())).withRel("album.purchase"));
        }
        return resource;
    }

}
