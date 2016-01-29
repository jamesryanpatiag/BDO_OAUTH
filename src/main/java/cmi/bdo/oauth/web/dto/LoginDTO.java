package cmi.bdo.oauth.web.dto;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/24/2016
 *         Time: 11:39 AM
 */

public class LoginDTO {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
