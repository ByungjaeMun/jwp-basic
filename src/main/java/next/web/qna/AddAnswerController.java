package next.web.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

public class AddAnswerController extends AbstractController {
    private AnswerDao answerDao = new AnswerDao();
    
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Answer answer = new Answer(
                request.getParameter("writer"),
                request.getParameter("contents"),
                ServletRequestUtils.getRequiredLongParameter(request, "questionId"));
        
        Answer savedAnswer = answerDao.insert(answer);
        ModelAndView mav = jsonView();
        mav.addObject("answer", savedAnswer);
        
        QuestionDao questionDB = new QuestionDao();
        Question newQuestion = questionDB.increaseCountOfCommentById(ServletRequestUtils.getRequiredLongParameter(request, "questionId"));
        mav.addObject("newQuestion", newQuestion);
        
        return mav;
    }
}
