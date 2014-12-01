
package my.com.myriadeas.integral.identityaccess.interfaces;


public class RegisterUserRequestDTO
{

    public RegisterUserRequestDTO()
    {
    }

    public RegisterUserRequestDTO(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String toString()
    {
        return (new StringBuilder("RegisterUserRequestDTO [username=")).append(username).append(", password=").append(password).append("]").toString();
    }

    private String username;
    private String password;
}