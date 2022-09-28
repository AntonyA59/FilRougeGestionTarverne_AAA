package aaa.tavern.Entity;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import javax.persistence.Id;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name; 

    @ManyToMany(mappedBy = "roles")
    private Collection<Player> users;

    @ManyToMany
    @JoinTable(name = "roles_privileges", 
    joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private List<Role> privileges;
    
    public Role() {
    }

    public Role(String name, List<Player> users, List<Role> privileges) {
        this.name = name;
        //this.users = users;
        this.privileges = privileges;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Role> privileges) {
        this.privileges = privileges;
    }

    

    public Collection<Player> getUsers() {
        return users;
    }

    public void setUsers(Collection<Player> users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;
            
        if (getClass() != obj.getClass())
            return false;

        Role other = (Role) obj;
        return Objects.equals(id,other.id) ;
    }
}
