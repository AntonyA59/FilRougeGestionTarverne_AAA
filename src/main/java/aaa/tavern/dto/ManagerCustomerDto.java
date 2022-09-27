package aaa.tavern.dto;

public class ManagerCustomerDto {
    
    
    protected ManagerCustomerDto(){

    }

    public ManagerCustomerDto(ManagerDto managerDto, CustomerDto customerDto) {
        this.managerDto = managerDto;
        this.customerDto = customerDto;
    }

    private ManagerDto managerDto;
    private CustomerDto customerDto;

    //#region Get
    public ManagerDto getManagerDto() {
        return managerDto;
    }


    public CustomerDto getCustomerDto() {
        return customerDto;
    }
    //#endregion



    
}
