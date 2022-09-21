package aaa.tavern.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class Player {

	public Player(){
		
	}
	public Player(String email, String nickname, String password){
		this.email = email;
		this.nickname = nickname;
		this.password = password;
	}

	@Id
	@Column(name = "id_player")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer playerId;

	@Column(name = "email")
	private String email;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "password")
	private String password;

	
	//#region get / set
	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
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