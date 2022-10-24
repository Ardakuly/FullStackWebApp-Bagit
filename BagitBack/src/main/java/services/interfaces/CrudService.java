package services.interfaces;


import java.util.Set;

public interface CrudService <T, ID>{

    Set<T> findAllPlaces();

    T findPlaceByName(String name);

    Boolean createPlace(T obj);

    Boolean delete(String name);

}
