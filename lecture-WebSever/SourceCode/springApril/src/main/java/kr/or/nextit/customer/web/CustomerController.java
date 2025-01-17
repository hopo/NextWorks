package kr.or.nextit.customer.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.nextit.customer.service.CustomerSearchVo;
import kr.or.nextit.customer.service.CustomerService;
import kr.or.nextit.customer.service.CustomerVo;

@Controller
public class CustomerController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "CustomerService")
	private CustomerService customerService;

	@RequestMapping(value = "/customer/customerList")
	public String customerList(HashMap<String, Object> hmap,
			@ModelAttribute(name = "customerSearchVo") CustomerSearchVo customerSearchVo) {

		log.debug(">>> '/customer/customerList'");
		log.debug(">>> HashMap hmap: {}", hmap);

		try {

			customerSearchVo.setTotalCount(customerService.selectTotalCount(customerSearchVo));
			customerSearchVo.setPageBlockSize(5);
			customerSearchVo.setScreenSize(10);
			customerSearchVo.pageSetting();

			log.debug(">>> ====== customerSearchVo ======");
			log.debug(">>> TotalCount: {}", customerSearchVo.getTotalCount());
			log.debug(">>> ScreenSize: {}", customerSearchVo.getScreenSize());
			log.debug(">>> TotalPageCount: {}", customerSearchVo.getTotalPageCount());
			log.debug(">>> CurPage: {}", customerSearchVo.getCurPage());
			log.debug(">>> EndPage: {}", customerSearchVo.getEndPage());
			log.debug(">>> StartRow: {}", customerSearchVo.getStartRow());
			log.debug(">>> EndRow: {}", customerSearchVo.getEndRow());
			log.debug(">>> ===============================");

			List<CustomerVo> items = customerService.selectCustomerList(customerSearchVo);
			hmap.put("items", items);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "customer/customerList";
	}

	@RequestMapping(value = "/customer/customerChart")
	public String customerChart() throws Exception {
		log.debug(">>> '/customer/customerChart'");

		return "customer/customerChart";
	}

	@RequestMapping(value = "/customer/customerChartProc")
	@ResponseBody
	public HashMap<String, Object> customerChartProc(
			@RequestParam HashMap<String, Object> params
		) throws Exception {

		List<HashMap<String, Object>> items = customerService.selectCustomerChart(params);
		log.debug(">>> items: {}", items);

		HashMap<String, Object> result = new HashMap<>();
		
		result.put("items", items);
		result.put("result", true);
		result.put("message", "성공하였다.");

		/*
		for (HashMap<String, Object> item : items) {
			if (item.get("income") == null) {
				result.put("NULL", item.get("cnt"));
			} else {
				result.put((String) item.get("income"), item.get("cnt"));
			}
		}
		*/

		return result;

	}

	@RequestMapping(value = "/customer/customerTest")
	public String customerTest() {

		return "customer/customerTest";
	}

}
