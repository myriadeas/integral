package my.com.myriadeas.integral.identityaccess.domain.model;

import java.util.Iterator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("groupMemberService")
public class GroupMemberService {

	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	public GroupMemberService(UserRepository aUserRepository,
			GroupRepository aGroupRepository) {

		super();

		this.groupRepository = aGroupRepository;
		this.userRepository = aUserRepository;
	}

	public boolean confirmUser(Group aGroup, User aUser) {
		boolean userConfirmed = true;

		User confirmedUser = this.userRepository().findByUsername(
				aUser.getUsername());

		if (confirmedUser == null) {
			userConfirmed = false;
		}

		return userConfirmed;
	}

	public boolean isMemberGroup(Group aGroup, GroupMember aMemberGroup) {
		boolean isMember = false;

		Iterator<GroupMember> iter = aGroup.groupMembers().iterator();

		while (!isMember && iter.hasNext()) {
			GroupMember member = iter.next();
			if (member.isGroup()) {
				if (aMemberGroup.equals(member)) {
					isMember = true;
				} else {
					Group group = this.groupRepository().findByName(
							member.name());
					if (group != null) {
						isMember = this.isMemberGroup(group, aMemberGroup);
					}
				}
			}
		}

		return isMember;
	}

	public boolean isUserInNestedGroup(Group aGroup, User aUser) {
		boolean isInNestedGroup = false;

		Iterator<GroupMember> iter = aGroup.groupMembers().iterator();

		while (!isInNestedGroup && iter.hasNext()) {
			GroupMember member = iter.next();
			if (member.isGroup()) {
				Group group = this.groupRepository().findByName(member.name());
				if (group != null) {
					isInNestedGroup = group.isMember(aUser, this);
				}
			}
		}

		return isInNestedGroup;
	}

	private GroupRepository groupRepository() {
		return this.groupRepository;
	}

	private UserRepository userRepository() {
		return this.userRepository;
	}
}