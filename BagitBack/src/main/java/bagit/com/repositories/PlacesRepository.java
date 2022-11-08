package bagit.com.repositories;

import bagit.com.entities.Place;
import org.springframework.data.repository.CrudRepository;

public interface PlacesRepository extends CrudRepository<Place, Integer> {

    Place findByName(String name);

}
