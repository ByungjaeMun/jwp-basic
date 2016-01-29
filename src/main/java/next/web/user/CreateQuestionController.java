package next.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;

public class CreateQuestionController extends AbstractController implements Controller
{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String newWriter = request.getParameter("writer");
		String newTitle = request.getParameter("title");
		String newContents = request.getParameter("contents");

		Question question = new Question(newWriter, newTitle, newContents);
		QuestionDao questionDB = new QuestionDao();
		questionDB.insert(question);

		return jstlView("redirect:/qna/list.next");
	}
}
