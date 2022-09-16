package aaa.tavern.CrudRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.Entity.TableRest;


@Repository
public interface TableRestRepository extends CrudRepository<TableRest, Integer> {
    
}
