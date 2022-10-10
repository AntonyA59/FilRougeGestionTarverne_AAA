package aaa.tavern.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RoleRepositoryTest {
    @Autowired
	private RoleRepository roleRepository;
    //findByName
}
