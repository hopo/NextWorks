package kr.or.nextit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.nextit.member.service.MemberVo;

public class InterceptorSession extends HandlerInterceptorAdapter {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(
			HttpServletRequest req,
			HttpServletResponse resp,
			Object handler
		) throws Exception {
		
		log.debug(">>> 로그인체크 Interceptor.preHandle()");
		
		// /session/loginForm 로그이니 안되어 있을 경우 이동 경로
		
		MemberVo loginInfo = null;
		loginInfo = (MemberVo) req.getSession().getAttribute("loginInfo");
		
		if (loginInfo != null) {
			// 정상 로그인
			log.debug(">>> loginInfo: {}", loginInfo);
			log.debug(">>> loginInfo.memId: {}", loginInfo.getMemId());
			log.debug(">>> loginInfo.memName: {}", loginInfo.getMemName());

		} else {
			// not 로그인
			ModelAndView modelAndView = new ModelAndView("redirect:/session/login");
			throw new ModelAndViewDefiningException(modelAndView);
		}

		return true;
	}
}
