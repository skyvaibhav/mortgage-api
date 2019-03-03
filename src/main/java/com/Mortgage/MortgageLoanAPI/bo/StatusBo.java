package com.Mortgage.MortgageLoanAPI.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Mortgage.MortgageLoanAPI.dao.IStatusDao;
import com.Mortgage.MortgageLoanAPI.models.Status;
import com.Mortgage.MortgageLoanAPI.utils.Constants;

@Service
public class StatusBo {

	@Autowired
	private IStatusDao iStatusDao;

	public Map<String, Object> saveUpdate(Status status) {
		Status s = new Status();
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		try {

			s = iStatusDao.save(status);
			rsMap.put("status", s);
			rsMap.put("message", Constants.SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return rsMap;
	}

	public Status findById(Long id) {
		Status s = new Status();
		try {
			s = iStatusDao.findById(id).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return s;
	}
	
	public List<Status>  findAll(){
		List<Status> list=new ArrayList<>();
		try {
			list=(List<Status>) iStatusDao.findAll();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
