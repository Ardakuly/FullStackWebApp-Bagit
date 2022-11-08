package bagit.com.services.serviceImplementation;

import bagit.com.Exceptions.ResourceNotFoundException;
import bagit.com.entities.Place;
import bagit.com.repositories.PlacesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlaceServiceImplementationTest {

    private PlaceServiceImplementation placeServiceImplementation;

    @Mock private PlacesRepository placesRepository;

    private Place temp;

    public PlaceServiceImplementationTest () {

        MockitoAnnotations.openMocks(this);
       this.placeServiceImplementation = new PlaceServiceImplementation(placesRepository);

    }

    @BeforeEach
    void setUp() {

        Place temp = new Place();
        temp.setName("Aktau");
        temp.setDescription("City");
        temp.setDescription("City near Kaspian sea");
        temp.setVideo("sdfanjkfnadkjnafsjkdas");
        temp.setNavigator("asjdinjaisnajfsn");

        this.temp = temp;
    }

    @Test
    void findAllPlaces() {

        //given

        Set<Place> places = new HashSet<>();
        places.add(temp);

        //when

        placeServiceImplementation.findAllPlaces();

        //then

        OngoingStubbing<Iterable<Place>> returned = when(placesRepository.findAll()).thenReturn(places);


        assertNotNull(returned);

        verify(placesRepository, times(1)).findAll();

    }

    @Test

    void findPlaceByNameNotFind() {

        //given

        String name = "Strange Place";

        //when
        //then
        assertThatThrownBy(() -> placeServiceImplementation.findPlaceByName(name))
                .isInstanceOf(ResourceNotFoundException.class).hasMessageContaining("place not found in database");
        when(placesRepository).thenReturn(null);

    }

    @Test
    void findPlaceByName() {

        //given

        String name = "Strange Place";

        //when

        when(placesRepository.findByName(name)).thenReturn(new Place());
        placeServiceImplementation.findPlaceByName(name);


        //then

        verify(placesRepository, times(1)).findByName(name);
    }

    @Test
    void createPlaceReturnFalse() {

        //given

        //when

        when(placesRepository.save(temp)).thenReturn(null);
        boolean result = placeServiceImplementation.createPlace(temp);

        //then


        assertThat(result).isFalse();


    }


    @Test
    void createPlace() {

        //given

        //when

        when(placesRepository.save(temp)).thenReturn(temp);
        boolean result = placeServiceImplementation.createPlace(temp);

        //then

        assertThat(result).isTrue();


    }

    @Test
    void updatePlaceName() {

        //given


        //when

        //for null //when(placesRepository.findByName(temp.getName())).thenReturn(null);
        when(placesRepository.findByName(temp.getName())).thenReturn(temp);
        placeServiceImplementation.updatePlaceName(temp.getName(),"Semey City");

        //then

        //assertThatThrownBy(()-> placeServiceImplementation.updatePlaceName(temp.getName(), "fafafa"))
        //        .isInstanceOf(ResourceNotFoundException.class).hasMessageContaining("place not found in database");

        assertEquals(temp.getName(), "Semey City");


    }

    @Test
    void updatePlaceDescription() {

        //given

        //when

          //  when(placesRepository.findByName(temp.getName())).thenReturn(null);
           when(placesRepository.findByName(temp.getName())).thenReturn(temp);
           placeServiceImplementation.updatePlaceDescription(temp.getName(),"City near river");

        //then

//        assertThatThrownBy(()-> placeServiceImplementation.updatePlaceName(temp.getName(), "fafafa"))
//               .isInstanceOf(ResourceNotFoundException.class).hasMessageContaining("place not found in database");

        assertEquals(temp.getDescription(), "City near river");
        verify(placesRepository, times(1)).save(temp);
    }


    @Test
    void updatePlaceVideo() {

        //given


        //when

        //for null //when(placesRepository.findByName(temp.getName())).thenReturn(null);
        when(placesRepository.findByName(temp.getName())).thenReturn(temp);
        placeServiceImplementation.updatePlaceVideo(temp.getName(),"Some video link");


        //then

        //assertThatThrownBy(()-> placeServiceImplementation.updatePlaceName(temp.getName(), "fafafa"))
        //        .isInstanceOf(ResourceNotFoundException.class).hasMessageContaining("place not found in database");

        assertEquals(temp.getVideo(), "Some video link");
        verify(placesRepository, times(1)).save(temp);
    }

    @Test
    void updatePlaceNavigator() {

        //given

        //when

        when(placesRepository.findByName(temp.getName())).thenReturn(temp);
        placeServiceImplementation.updatePlaceNavigator(temp.getName(),"Some Yandex navigator link");


        //then

        //assertThatThrownBy(()-> placeServiceImplementation.updatePlaceName(temp.getName(), "fafafa"))
        //        .isInstanceOf(ResourceNotFoundException.class).hasMessageContaining("place not found in database");

        assertEquals(temp.getNavigator(), "Some Yandex navigator link");
        verify(placesRepository, times(1)).save(temp);
    }

    @Test
    void delete() {

        //given

        //when
        when(placesRepository.findByName(temp.getName())).thenReturn(temp);
        //when(placesRepository.findByName(temp.getName())).thenReturn(null);
        boolean result = placeServiceImplementation.delete(temp.getName());


        //then

//        assertThatThrownBy(()-> placeServiceImplementation.delete(temp.getName()))
//                .isInstanceOf(ResourceNotFoundException.class).hasMessageContaining("place not found in database");

        assertThat(result).isTrue();
        verify(placesRepository, times(1)).delete(temp);
    }

    @Test
    void addComment() {

        //given
        //when
        when(placesRepository.findByName(temp.getName())).thenReturn(temp);
        //when(placesRepository.findByName(temp.getName())).thenReturn(null);
        boolean result = placeServiceImplementation.addComment(temp.getName(), "Nice Place. I have been there in summer.");

        //then

        assertEquals(temp.getComments().get(0), "Nice Place. I have been there in summer.");

        verify(placesRepository, times(1)).save(temp);
        assertThat(result).isTrue();


    }
}