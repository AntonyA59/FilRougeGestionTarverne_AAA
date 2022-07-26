package filrougeaaa;
import filrougeaaa.utils.Model;
public class User extends Model{
    private String email;
    private String password;
    protected String nickName;
    protected Manager[] partie; 
	@Override
	public boolean get(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}
//#region get/set
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Manager[] getPartie() {
		return partie;
	}

	public void setPartie(Manager[] partie) {
		this.partie = partie;
	}
//#endregion
	
} 