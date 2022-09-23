package aaa.tavern.service;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PlayerDto {
    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotBlank(message = "username is mandatory")
	private String nickname;

    @NotNull
    @Size(min=8, max=30)
    @NotBlank(message = "New password is mandatory")
	private String password;

    protected PlayerDto(String email, String nickname, String password){
        this.email = email.toLowerCase() ;
        this.nickname = nickname ;
        this.password = password ;
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
}
