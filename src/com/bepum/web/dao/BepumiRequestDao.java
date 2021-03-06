package com.bepum.web.dao;

import java.util.List;

import com.bepum.web.entity.BepumiRequest;

public interface BepumiRequestDao {

	int insert(String reqID, String applicationFormCopy, String applicationForm, String hCCopy, String hC, String fRCCopy, String fRC,
			String vCCopy, String vC);

	List<BepumiRequest> getList(int page, String query, String cName);
	
	int getCount();

	BepumiRequest get(String id);

	int update(String reqID, String applicationFormStatus, String fRCStatus, String hCStatus, String vCStatus);

	int updateDoc(String reqID, String applicationFormCopy, String applicationForm, String hCCopy, String hC,
			String fRCCopy, String fRC, String vCCopy, String vC, String applicationFormStatus, String hCStatus, String fRCStatus, String vCStatus);
}
