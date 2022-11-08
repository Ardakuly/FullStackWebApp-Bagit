package bagit.com.services.interfaces;

import bagit.com.entities.Place;

public interface PlaceService extends CrudService<Place, Integer> {

    Boolean updatePlaceName(String name, String newName);

    Boolean updatePlaceDescription(String name, String description);

    Boolean updatePlaceVideo(String name, String video);

    Boolean updatePlaceNavigator(String name, String navigator);

    Boolean addComment(String place, String comment);

}
