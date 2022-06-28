package tw.theone.model;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;
	//private SessionFactory factory;
	
		
	@Override
	public boolean isDup(int pk) {
		
		return memberDao.isDup(pk);
	}

	@Override
	public int save(Member member) {
		
		return memberDao.save(member);
	}

	@Override
	public List<Member> getAllMembers() {
		
		return memberDao.getAllMembers();
	}

	@Override
	public Member getMember(int pk) {
		
		return memberDao.getMember(pk);
	}

	public Member checkAccount(String userid) {
		
		return memberDao.checkAccount(userid);
	}
	
	@Override
	public void deleteMember(int pk) {
		memberDao.deleteMember(pk);
	}
	
	
	@Override
	public void updateMember(Member member) {
		memberDao.updateMember(member);
	}
	
}
