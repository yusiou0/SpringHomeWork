package tw.theone.model;

import java.util.List;


public interface MemberService {
	boolean isDup(int pk);

	int save(Member member);

	List<Member> getAllMembers();

	Member getMember(int pk);
	
	Member checkAccount(String account);
	
	void deleteMember(int pk);

	void updateMember(Member member);

}
