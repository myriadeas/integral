
package my.com.myriadeas.integral.identityaccess.interfaces;


public class RegisterUserResponseDTO
{

    public RegisterUserResponseDTO()
    {
    }

    public RegisterUserResponseDTO(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String toString()
    {
        return (new StringBuilder("RegisterUserResponseDTO [userId=")).append(userId).append("]").toString();
    }

    private Long userId;
}