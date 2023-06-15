package com.springproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.dao.DeptDao;
import com.springproject.dto.Dept;

//1. service 의 역활을 하는 클래스
//2. 자동으로 bean이 되어서 스프링 컨테이너에 등록이 된다.
@Service
public class DeptServiceImpl implements DeptService  {
	
	@Autowired // 스프링컨테이너가 의존성을 만들어줌
	DeptDao deptDao;
	
//	1.의존성 만드는법
//	DeptDao deptDao;
//	public DeptServiceImpl(DeptDao deptDao){
//	this.deptDao = deptDao;	
//}

//2. 의존성 만드는 법 두번째
// DeptDao deptDao;
// public void setDeptDao(DeptDao deptDao){
//	this.deptDao = deptDao;
//	}
	
	@Override
	public List<Dept> selectList() {
		return deptDao.selectList();
	}
		
}
