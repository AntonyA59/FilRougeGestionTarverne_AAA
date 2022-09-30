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

import aaa.tavern.dao.CustomerRepository;
import aaa.tavern.dao.ManagerCustomerRepository;
import aaa.tavern.dao.ManagerRepository;

import aaa.tavern.dto.CustomerDto;

import aaa.tavern.entity.Customer;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.ManagerCustomer;

@SpringBootTest
public class CustomerManagerServiceTest {

	@MockBean
	private ManagerRepository managerRepository;

	@MockBean
	private ManagerCustomerRepository managerCustomerRepository;

	@MockBean
	private CustomerRepository customerRepository;

	@Autowired
	private ManagerCustomerService managerCustomerService;

	@Test
	public void whenLoadCustomer_thenReturnListCustomerDto() {
		Manager manager = new Manager();

		Mockito.when(managerRepository.findById(1)).thenReturn(Optional.of(manager));
		;

		List<ManagerCustomer> listManagersCustomer = new ArrayList<ManagerCustomer>();
		ManagerCustomer managerCustomer1 = new ManagerCustomer(manager, new Customer());
		ManagerCustomer managerCustomer2 = new ManagerCustomer(manager, new Customer());
		ManagerCustomer managerCustomer3 = new ManagerCustomer(manager, new Customer());
		listManagersCustomer.add(managerCustomer1);
		listManagersCustomer.add(managerCustomer2);
		listManagersCustomer.add(managerCustomer3);

		Mockito.when(managerCustomerRepository.findByManager(manager)).thenReturn(listManagersCustomer);

		List<CustomerDto> listCustomerDtos = new ArrayList<CustomerDto>();
		CustomerDto customerDto1 = new CustomerDto(managerCustomer1.getCustomer());
		CustomerDto customerDto2 = new CustomerDto(managerCustomer2.getCustomer());
		CustomerDto customerDto3 = new CustomerDto(managerCustomer3.getCustomer());
		listCustomerDtos.add(customerDto1);
		listCustomerDtos.add(customerDto2);
		listCustomerDtos.add(customerDto3);

		List<CustomerDto> listCustomerDtos2 = managerCustomerService.loadCustomerByManager(1);

		assertEquals(listCustomerDtos, listCustomerDtos2);

	}
}
