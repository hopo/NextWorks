package kr.or.nextit.customer.service;

import java.util.HashMap;
import java.util.List;

public interface CustomerService {

	/**
	 * @param customerSearchVo
	 * @return
	 * @throws Exception
	 */
	public List<CustomerVo> selectCustomerList(CustomerSearchVo customerSearchVo) throws Exception;

	/**
	 * @param customerSearchVo
	 * @return
	 * @throws Exception
	 */
	public int selectTotalCount(CustomerSearchVo customerSearchVo) throws Exception;

	/**
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> selectCustomerChart(HashMap<String, Object> params) throws Exception;

}
