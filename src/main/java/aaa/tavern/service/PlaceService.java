package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dao.PlaceRepository;
import aaa.tavern.dto.PlaceDto;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.Place;
import aaa.tavern.utils.ServiceUtil;

@Service
public class PlaceService {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    ManagerRepository managerRepository;

    /**
     * Returns a manager's placeDto list
     * 
     * @param managerId
     * @return List<PlaceDto>
     */
    public List<PlaceDto> findPlaceByManagerId(int managerId) {
        Manager manager = ServiceUtil.getEntity(managerRepository, managerId);

        List<Place> listPlaces = placeRepository.findByManager(manager);

        if (listPlaces.isEmpty()) {
            throw new EntityNotFoundException();
        }

        List<PlaceDto> listPlacesDto = new ArrayList<PlaceDto>();

        for (Place place : listPlaces) {
            PlaceDto placeDto = new PlaceDto(place);
            listPlacesDto.add(placeDto);
        }

        return listPlacesDto;
    }
}
