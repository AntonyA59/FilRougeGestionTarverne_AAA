package aaa.tavern.entity;

import java.util.Objects;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class Player {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPlayer;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "nickname", unique = true)
	private String nickname;

	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
	private boolean enabled;




	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

	public Player(){}
	
	public Player(String email, String nickname, String password, boolean enabled,Collection<Role> roles){
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.enabled = enabled ;
		this.roles = roles ;
	}
	
	/**
	 * Deux Players sont les mêmes si ils ont le même identifiant.
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Player that = (Player) o;
        return Objects.equals(idPlayer, that.idPlayer);
    }

	/**
	 * L'identifiant définit le hash.
	 */
    @Override
    public int hashCode() {
        return idPlayer.hashCode();
    }

	//#region get / set
	public Integer getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(Integer idPlayer) {
		this.idPlayer = idPlayer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	
	
} 	//#endregion