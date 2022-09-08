package filrougeaaa;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
@Table(name = "reservation")
public class Reservation{
    
    @EmbeddedId
    private ReservationKey id = new ReservationKey();

    @ManyToOne
    @MapsId("managerId")
    @JoinColumn(name = "id_manager")
    private Manager managerId;

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "id_customer")
    private Customer customerId;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    
    
    //#region get/set
    public ReservationKey getId() {
        return id;
    }

    public void setId(ReservationKey id) {
        this.id = id;
    }

    public Manager getManagerId() {
        return managerId;
    }

    public void setManagerId(Manager managerId) {
        this.managerId = managerId;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    //#endregion

}
