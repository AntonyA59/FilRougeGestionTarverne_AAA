package filrougeaaa;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ReservationKey implements Serializable {
    
    @Column(name = "id_customer")
    private  Integer customerId;

    @Column(name = "id_manager")
    private Integer managerId;

    //#region get/set
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
    //#endregion
    
}
