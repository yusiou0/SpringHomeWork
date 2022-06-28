package tw.theone.model;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public class ResumeDaoImpl implements ResumeDao {
	
	//建立Session工廠，並取得session
	@Autowired
	private SessionFactory factory;

	
	//實作介面中的方法
	@Override
	public boolean isDup(int pk) {
		Session session = factory.getCurrentSession();
		Resume resume = (Resume) session.get(Resume.class, pk);
		if (resume != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int save(Resume resume) {
		Session session = factory.getCurrentSession();
		int pk = 0;
		pk = (int) session.save(resume);
		return pk;
	}

	@Override
	public List<Resume> getAllResumes() {
		Session session = factory.getCurrentSession();
		List<Resume> resumes = null;
		String hql = "FROM Resume";
		resumes = session.createQuery(hql, Resume.class).getResultList();
		return resumes;
	}

	@Override
	public Resume getResume(int pk) {
		Session session = factory.getCurrentSession();
		Resume resume = session.get(Resume.class, pk);
		return resume;
	}

	@Override
	public void deleteResume(int pk) {
		Session session = factory.getCurrentSession();
		Resume resume = new Resume();
		resume.setResume_id(pk);
		session.delete(resume);
	}

	@Override
	public void updateResume(Resume resume) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(resume);
	}
	
	
}
