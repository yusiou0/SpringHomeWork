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
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	//private SessionFactory factory;
	private ResumeDao resumeDao;

	
	@Override
	public boolean isDup(int pk) {
		
		return resumeDao.isDup(pk);
	}

	@Override
	public int save(Resume resume) {
		
		return resumeDao.save(resume);
	}

	@Override
	public List<Resume> getAllResumes() {
		
		return resumeDao.getAllResumes();
	}

	@Override
	public Resume getResume(int pk) {
		
		return resumeDao.getResume(pk);
	}

	@Override
	public void deleteResume(int pk) {
		resumeDao.deleteResume(pk);
	}

	@Override
	public void updateResume(Resume resume) {
		resumeDao.updateResume(resume);
	}
	
	
}
