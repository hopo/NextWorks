package kr.or.nextit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.nextit.notice.service.NoticeService;

public class InterceptorCommon extends HandlerInterceptorAdapter {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NoticeService noticeService;

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler,
			ModelAndView modelAndView
		) throws Exception {

		log.debug(">>> {}.postHandle()", this.getClass().getCanonicalName());
		
		/*
		NoticeSearchVo noticeSearchVo =  new NoticeSearchVo();
		noticeSearchVo.setTotalCount(noticeService.selectTotalCount(noticeSearchVo));
		noticeSearchVo.setPageBlockSize(3);
		noticeSearchVo.setScreenSize(5);
		noticeSearchVo.pageSetting();
		List<NoticeVo> noticeItems =  noticeService.selectNoticeList(noticeSearchVo);
		modelAndView.addObject("postItems", noticeItems);
		
		HashMap<String, Object> item = new HashMap<>();
		item.put("title", "매우 졸립다~~~");
		item.put("name", "별은김");
		modelAndView.addObject("postItem", item);
		*/
	}

}
