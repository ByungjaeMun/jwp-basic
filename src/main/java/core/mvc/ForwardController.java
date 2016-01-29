package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import next.model.User;

public class ForwardController extends AbstractController {
	private String forwardUrl;

	public ForwardController(String forwardUrl) {
		this.forwardUrl = forwardUrl;
		
	}

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (forwardUrl == null) 
		{
			throw new NullPointerException(
					"forwardUrl is null. 이동할 URL을 입력하세요.");
		}

		if(forwardUrl == "/qna/form.jsp")
		{
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			
			if (user == null) 
			{
				System.out.println("로그인 안함");
				return jstlView("redirect:/qna/list.next");
			}
			else
			{
				ModelAndView mav = jstlView("/qna/form.jsp");
				mav.addObject("user", user);
				return mav;
			        
			}
		}
		return jstlView(forwardUrl);
	}
}
