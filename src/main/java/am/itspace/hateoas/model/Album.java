package am.itspace.hateoas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Album {

    private String id;
    private String title;
    private Artist artist;
    private int stockLevel;

}