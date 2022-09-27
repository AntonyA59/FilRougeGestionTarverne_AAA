package aaa.tavern.Entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;



@Entity
@Table(name = "manager_customer")
public class ManagerCustomer implements Serializable{
    
    @EmbeddedId
    private ManagerCustomerKey id = new ManagerCustomerKey();

    @ManyToOne
    @MapsId("idManager")
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @ManyToOne
    @MapsId("idCustomer")
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
	 * Deux ManagerCustomer sont les mêmes si ils ont le même identifiant.
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

            ManagerCustomer that = (ManagerCustomer) o;
        return Objects.equals(id, that.id);
    }

	/**
	 * L'identifiant définit le hash.
	 */
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
