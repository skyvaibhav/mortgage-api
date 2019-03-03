package com.Mortgage.MortgageLoanAPI.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Mortgage.MortgageLoanAPI.bo.TaskBo;
import com.Mortgage.MortgageLoanAPI.configuration.LoginRequired;
import com.Mortgage.MortgageLoanAPI.models.Task;
import com.Mortgage.MortgageLoanAPI.utils.Constants;

@RestController
@RequestMapping("/api/task")
public class TaskController {

	@Autowired
	private TaskBo taskBo;

	private ResponseEntity<Map<String, Object>> createUpdate(Task task) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Map<String, Object> rsMap = new HashMap<>();
		try {
			rsMap = taskBo.saveUpdate(task);
			if (rsMap.get("message").equals(Constants.SUCCESS)) {
				status = HttpStatus.OK;
			} else if(rsMap.get("message").equals(Constants.BAD_REQUEST)) {
				status = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(rsMap, status);
	}

	@LoginRequired
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> create(@RequestBody Task task) {
		return createUpdate(task);
	}

	@LoginRequired
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> update(@RequestBody Task task) {
		return createUpdate(task);
	}

	@LoginRequired
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Task>> getAll() {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		List<Task> list = new ArrayList<>();

		try {
			list = taskBo.findAll();
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<List<Task>>(list, status);
	}
	
	@LoginRequired
	@RequestMapping(value = "/all/emp/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Task>> getTaskByEmpId(@PathVariable("id") Long id) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		List<Task> list = new ArrayList<>();

		try {
			list = taskBo.getTaskByEmpId(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<List<Task>>(list, status);
	}

	@LoginRequired
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<Task> findById(@PathVariable("id") Long id) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Task t = new Task();
		try {
			t = taskBo.findById(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Task>(t, status);
	}

	@LoginRequired
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> remove(@PathVariable("id") Long id) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Map<String, Object> rsMap = new HashMap<>();
		try {
			rsMap = taskBo.remove(id);
			if (!rsMap.get("message").equals(Constants.FAILED)) {
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(rsMap, status);
	}
	
	@LoginRequired
	@RequestMapping(value = "/read/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> changeRead(@PathVariable("id") Long id) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Map<String, Object> rsMap = new HashMap<>();
		try {
			rsMap = taskBo.changeRead(id);
			if (!rsMap.get("message").equals(Constants.FAILED)) {
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(rsMap, status);
	}
}
