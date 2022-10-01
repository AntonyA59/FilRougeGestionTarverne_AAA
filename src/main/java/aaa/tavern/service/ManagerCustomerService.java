package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.ManagerCustomerRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dto.CustomerDto;
import aaa.tavern.entity.Customer;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.ManagerCustomer;
import aaa.tavern.utils.ServiceUtil;

@Service
public class ManagerCustomerService {

	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	private ManagerCustomerRepository managerCustomerRepository;

	/**
	 * Returns a list containing customers of a manager converted to Dto
	 * 
	 * @param idManager
	 * @return List<CustomerDto>
	 * @throws EntityNotFoundException
	 */
	public List<CustomerDto> loadCustomerByManager(int idManager) throws EntityNotFoundException {
		Manager manager = ServiceUtil.getEntity(managerRepository, idManager);

		List<ManagerCustomer> listManagerCustomer = managerCustomerRepository.findByManager(manager);
		if (listManagerCustomer.isEmpty()) {
			throw new EntityNotFoundException();
		}
		List<Customer> listCustomers = new ArrayList<Customer>();
		List<CustomerDto> listCustomersDto = new ArrayList<CustomerDto>();

		for (ManagerCustomer managerCustomer : listManagerCustomer) {
			listCustomers.add(managerCustomer.getCustomer());
		}

		for (Customer customer : listCustomers) {
			CustomerDto customerDto = new CustomerDto(customer);
			listCustomersDto.add(customerDto);
		}

		return listCustomersDto;
	}

}
