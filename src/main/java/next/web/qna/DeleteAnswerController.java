package next.web.qna;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.model.Answer;
import next.model.Result;

public class DeleteAnswerController extends AbstractController implements Controller
{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		long answerIdToDelete = Long.parseLong(request.getParameter("answerId"));
		AnswerDao  answerDB = new AnswerDao();
		Answer answerToDelete = answerDB.findById(answerIdToDelete);
		ModelAndView mav = jsonView();
		
		Result result;
		try 
		{
			answerDB.deleteAnswerById(answerIdToDelete);
			result = Result.ok();
		} 
		catch (Exception e) 
		{
			result = Result.fail("삭제실패");
		}
			
			
		mav.addObject("deleteAnswer", result);
		return mav;
	}
}
