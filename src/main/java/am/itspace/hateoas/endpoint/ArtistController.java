package am.itspace.hateoas.endpoint;

import am.itspace.hateoas.model.Artist;
import am.itspace.hateoas.service.MusicService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping(value = "/artist")
@AllArgsConstructor
public class ArtistController {

    private final MusicService musicService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Resource<Artist>> getArtist(@PathVariable(value = "id") String id) {
        Artist artist = musicService.getArtist(id);
        Resource<Artist> resource = new Resource<>(artist);
        resource.add(linkTo(methodOn(ArtistController.class).getArtist(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

}