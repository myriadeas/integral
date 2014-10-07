package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.UserGroup;

public interface UserGroupData extends RoleData{
	UserGroup CIRCULATION_GROUP = new UserGroup("GROUP_CIRC", "circulation group");
	UserGroup CATALOGUING_GROUP = new UserGroup("GROUP_CAT", "cataloguing group");
}
