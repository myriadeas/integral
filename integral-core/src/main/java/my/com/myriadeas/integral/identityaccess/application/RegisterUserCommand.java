// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 12/1/2014 4:33:57 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   RegisterUserCommand.java

package my.com.myriadeas.integral.identityaccess.application;

import my.com.myriadeas.integral.core.application.Command;

public class RegisterUserCommand
    implements Command
{

    public RegisterUserCommand(String username, String password)
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
        return (new StringBuilder("RegisterUserCommand [username=")).append(username).append(", password=").append(password).append("]").toString();
    }

    private String username;
    private String password;
}