package kr.or.nextit.session.web;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import kr.or.nextit.member.service.MemberVo;
import kr.or.nextit.session.service.SessionService;

@Controller
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	// @Resource(name = "SessionService") // *SessionServiceImpl @Service
	@Autowired // ;;;위의 어노테이션의 대안 but implement가 하나
	private SessionService sessionService;

	/**
	 * 로그인컨트롤러: 로그인 폼
	 * 
	 * @param modelAndView
	 * @param memberVo
	 * @param params
	 * @return
	 */
	@RequestMapping(value = { "/session/loginForm" })
	public ModelAndView loginForm(ModelAndView modelAndView, @ModelAttribute MemberVo memberVo,
			@RequestParam HashMap<String, Object> params) {

		modelAndView.setViewName("session/loginForm");

		log.debug("memberVo : {}", memberVo);
		log.debug("params : {}", params);

		try {

			MemberVo result = sessionService.loginCheck(memberVo, params);
			log.debug("result : {}", result);

		} catch (Exception e) {
			// e.printStackTrace();
		}

		return modelAndView;
	}

	/**
	 * 로그인컨트롤러: 로그인 프로세서
	 * 
	 * @param modelAndView
	 * @param memberVo
	 * @param params
	 * @param session
	 * @return
	 */
	@RequestMapping(value = { "/session/loginProc" })
	public ModelAndView loginProc(ModelAndView modelAndView, @ModelAttribute MemberVo memberVo,
			@RequestParam HashMap<String, Object> params, HttpSession session) {

		modelAndView.setViewName("session/loginForm");

		log.debug("memberVo : {}", memberVo);
		log.debug("params : {}", params);

		MemberVo result = new MemberVo();

		try {

			result = sessionService.loginCheck(memberVo, params);

			session.setAttribute("loginInfo", result);

			log.debug("result {}", result);

			// ;redirect 방법1)
			// RedirectView redirectView = new RedirectView();
			// redirectView.setUrl("/index");
			// modelAndView.setView(redirectView);

			// ;redirect 방법2)
			modelAndView.setViewName("redirect:/index");

		} catch (Exception e) {

			e.printStackTrace();
			session.setAttribute("loginInfo", null);
		}

		return modelAndView;
	}

}
