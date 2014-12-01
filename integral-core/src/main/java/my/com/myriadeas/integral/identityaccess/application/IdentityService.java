// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 12/1/2014 4:33:57 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   IdentityService.java

package my.com.myriadeas.integral.identityaccess.application;

import my.com.myriadeas.integral.identityaccess.domain.model.User;

// Referenced classes of package my.com.myriadeas.integral.identityaccess.application:
//            RegisterUserCommand

public interface IdentityService
{

    public abstract User registerUser(RegisterUserCommand registerusercommand);
}