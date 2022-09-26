package aaa.tavern.utils;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.repository.CrudRepository;

public class ServiceUtil {
    private ServiceUtil(){

    }
    public static <T> T getEntity(CrudRepository<T,Integer> repository, Integer id) throws EntityNotFoundException{
        Optional<T> optEntity = repository.findById(id);
		
		if (optEntity.isEmpty())
			throw new EntityNotFoundException();
		
		return optEntity.get();
    }
}
