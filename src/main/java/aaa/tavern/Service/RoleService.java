package aaa.tavern.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.DAO.RoleRepository;
import aaa.tavern.Entity.Role;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository ;

    public void createRoleIfNotExists(String roleName){
        Optional<Role> optRole = roleRepository.findByName(roleName);

        if(optRole.isEmpty())
            roleRepository.save(new Role(roleName)) ;
    }


}
