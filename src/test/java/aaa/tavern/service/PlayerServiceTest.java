package aaa.tavern.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import aaa.tavern.DAO.PlayerRepository;

@SpringBootTest
public class PlayerServiceTest {
    @MockBean
	PlayerRepository playerRepository;
	
	@Autowired 
	PlayerService PlayerService ;


}
