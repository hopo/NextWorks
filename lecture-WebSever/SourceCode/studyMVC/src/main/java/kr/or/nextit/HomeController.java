package kr.or.nextit;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	// ;참고: resources/log4j.xml
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class); // ;;;log4j꼭 찍어요!

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "/index", "/home" })
	public String home(Locale locale, Model model, @RequestParam HashMap<String, Object> params, HttpServletRequest req,
			HttpServletResponse resp) {

		logger.info("Welcome home! locale: {}.", locale);
		logger.info("Welcome home! locale: {}.", locale.getDisplayLanguage());

		logger.info("Welcome home! request: {}.", req.getRemoteAddr());
		logger.info("Welcome home! request: {}.", req.getAuthType());

		logger.info("Welcome home! response: {}.", resp.getCharacterEncoding());
		logger.info("Welcome home! response: {}.", resp.getContentType());

		logger.debug("model = {}", model);
		logger.debug("params = {}", params);
		logger.debug("params.get(\"abc\") = {}", params.get("abc"));

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = { "/test" })
	public String getTest(Model model) {
		// ;servlet-context.xml beans 참고

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		return "home";
	}

	@RequestMapping(value = { "/test2" })
	public ModelAndView getTest(ModelAndView modelAndView) {
		// ;최상위 객체 - ModelAndView
		modelAndView.setViewName("home"); // ;;;이 메서드가 보여줄 home.jsp

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);

		String formattedDate = dateFormat.format(date);

		modelAndView.addObject("serverTime", formattedDate);

		return modelAndView;
	}

}
