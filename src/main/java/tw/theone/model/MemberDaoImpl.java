package tw.theone.model;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository     //寫在dao 
@Transactional  //交易
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	SessionFactory factory;
	
	
	@Override
	public boolean isDup(int pk) {
		Session session = factory.getCurrentSession();
		Member member = (Member) session.get(Member.class, pk);
		if (member != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int save(Member member) {
		Session session = factory.getCurrentSession();
		int pk = 0;
		pk = (int) session.save(member);
		return pk;
	}

	@Override
	public List<Member> getAllMembers() {
		Session session = factory.getCurrentSession();
		List<Member> members = null;
		String hql = "FROM Member";
		members = session.createQuery(hql, Member.class).getResultList();
		return members;
		
	}

	@Override
	public Member getMember(int pk) {
		Session session = factory.getCurrentSession();
		Member member = session.get(Member.class, pk);
		return member;
		
	}

	@Override
	public Member checkAccount(String userid) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Member WHERE userid = :userid";  //冒號後面aaa
		Member account = session.createQuery(hql,Member.class)
		                        .setParameter("userid", userid)  // "aaa" 
		                        .getSingleResult(); //DB找不到這邊會丟例外
		return account;
		
	}
	
	
	@Override
	public void deleteMember(int pk) {
		Session session = factory.getCurrentSession();
		Member member = new Member();
		member.setIdNumber(pk);
		session.delete(member);
		
	}

	
	@Override
	public void updateMember(Member member) {

		Session session = factory.getCurrentSession();
		System.out.println(member);
		session.saveOrUpdate(member);
	}
	
}
