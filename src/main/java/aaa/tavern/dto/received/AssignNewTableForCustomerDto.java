package aaa.tavern.dto.received;

public class AssignNewTableForCustomerDto {
    int customerId;
    int tableId;
    
    protected AssignNewTableForCustomerDto(){
        
    }
    public AssignNewTableForCustomerDto(int customerId, int tableId) {
        this.customerId = customerId;
        this.tableId = tableId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public int getTableId() {
        return tableId;
    }
    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    
}
