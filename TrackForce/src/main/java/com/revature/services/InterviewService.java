package com.revature.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.revature.dao.InterviewDao;
import com.revature.dao.InterviewDaoHibernate;
import com.revature.entity.TfInterview;
import com.revature.model.InterviewInfo;
import com.revature.request.model.InterviewFromClient;

public class InterviewService {
	private InterviewDao interviewDao;

	public InterviewService() {
		this.interviewDao = new InterviewDaoHibernate();
	}

	/**
	 * injectable dao for easier testing
	 *
	 * @param TechDao
	 */
	public InterviewService(InterviewDao interviewDao) {
		this.interviewDao = interviewDao;
	}

	public Set<InterviewInfo> getAllInterviews() {
		Set<InterviewInfo> interviews = interviewDao.getInterviewFromCache();

		if (interviews == null || interviews.isEmpty()) {
			interviewDao.cacheAllInterviews();
			return interviewDao.getInterviewFromCache();
		}

		return interviews;
	}

	public ArrayList<InterviewInfo> getAllInterviews(String sort) {
		ArrayList<InterviewInfo> interviews = new ArrayList<>(interviewDao.getAllInterviews().values());
		int order = "asc".equals(sort) ? 1 : -1;

		// TODO this should be sorted in the hibernate layer
		Collections.sort(interviews, new Comparator<InterviewInfo>() {
			@Override
			public int compare(InterviewInfo x, InterviewInfo y) {
				return order * x.getTfInterviewDate().compareTo(y.getTfInterviewDate());
			}
		});

		return interviews;
	}

	public void addInterviewByAssociate(int associateId, InterviewFromClient ifc) {
		interviewDao.addInterviewForAssociate(associateId, ifc);
	}
	public void updateInterview(int associateId, InterviewFromClient ifc){
		interviewDao.updateInterview(associateId, ifc);
	}

	public List<InterviewInfo> getInterviewConflicts(int associateId) throws IOException {
		Map<Integer, InterviewInfo> interviews = interviewDao.getInterviewsByAssociate(associateId);
		List<InterviewInfo> conflicts = new ArrayList<InterviewInfo>();

		Iterator it = interviews.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			InterviewInfo temp = (InterviewInfo) pair.getValue();

			Iterator it2 = interviews.entrySet().iterator();
			while (it2.hasNext()) {
				Map.Entry pair2 = (Map.Entry) it.next();
				InterviewInfo temp2 = (InterviewInfo) pair2.getValue();

				if (temp.getTfInterviewDate().equals(temp2.getTfInterviewDate())) {
					conflicts.add(temp);
					conflicts.add(temp2);
				}
			}

		}
		
	 

		return conflicts;
	}
	public TfInterview getInterviewById(Integer parmInterviewId) {
		
		return interviewDao.getInterviewById(parmInterviewId);
		
	}
public List<TfInterview> getInterviewsByAssoicateId(Integer parmAssoicateId) {
		
		return interviewDao.getInterviewsByAssoicateId(parmAssoicateId);
		
	}

}