package aaa.tavern.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dao.PlaceRepository;
import aaa.tavern.dto.PlaceDto;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.Place;

@SpringBootTest
public class PlaceServiceTest {

    @MockBean
    ManagerRepository managerRepository;

    @MockBean
    PlaceRepository placeRepository;

    @Autowired
    PlaceService placeService;

    @Test
    public void givenManagerAnd5place_whenFindPlaceByManagerId_thenReturnListPlaceDto() {
        Manager manager = new Manager();
        Mockito.when(managerRepository.findById(0)).thenReturn(Optional.of(manager));
        List<Place> listPlaces = new ArrayList<Place>();

        for (int i = 0; i < 5; i++) {
            Place place = new Place();
            place.setLevel(i+1);
            place.setManager(manager);
            place.setName("Place " + (i+1));
            place.setType(2);
            listPlaces.add(place);
        }
        Mockito.when(placeRepository.findByManager(manager)).thenReturn(listPlaces);

        List<PlaceDto> listPlaceDto = new ArrayList<PlaceDto>();

        for (Place place : listPlaces) {
            PlaceDto placeDto = new PlaceDto(place);
            listPlaceDto.add(placeDto);
        }

        List<PlaceDto> listPlaceDto2 = placeService.findPlaceByManagerId(0);

        assertEquals(listPlaceDto, listPlaceDto2);
    }

}
