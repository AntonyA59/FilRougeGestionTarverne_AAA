package aaa.tavern.CrudRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.Entity.Place;


@Repository
public interface PlaceRepository extends CrudRepository<Place, Integer> {
    
    
}
