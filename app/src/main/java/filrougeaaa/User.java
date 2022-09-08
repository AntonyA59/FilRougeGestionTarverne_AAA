package filrougeaaa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@Column(name = "email")
	private String email;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "password")
	private String password;

	
	//#region get / set
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
} 	//#endregion