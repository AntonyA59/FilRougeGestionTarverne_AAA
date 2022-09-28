package aaa.tavern.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.Entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    
}
