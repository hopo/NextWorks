package kr.or.nextit.customer.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.nextit.customer.service.CustomerSearchVo;
import kr.or.nextit.customer.service.CustomerService;
import kr.or.nextit.customer.service.CustomerVo;

@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public List<CustomerVo> selectCustomerList(CustomerSearchVo customerSearchVo) throws Exception {
		return customerMapper.selectCustomerList(customerSearchVo);
	}
	
	@Override
	public int selectTotalCount(CustomerSearchVo customerSearchVo) throws Exception {
		return customerMapper.selectTotalCount(customerSearchVo);
	}
	
	@Override
	public List<HashMap<String, Object>> selectCustomerChart(HashMap<String, Object> params) throws Exception {
		return customerMapper.selectCustomerChart(params);
	}

}
