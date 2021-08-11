

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/*")
public class MemberController extends HttpServlet{
	MemberDAO memberDAO;
	
	@Override
	public void init() throws ServletException {
		memberDAO = new MemberDAO();
		System.out.println("memberDao 생성");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}
	
	private void doHandle(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String nextPage = null;
		String action = req.getPathInfo();
		System.out.println(action);
		
		if(action.equals("/listMember.do")) {
			List<MemberVO> memberList = memberDAO.listMembers();
			req.setAttribute("memberList", memberList);
			nextPage = "/listMembers.jsp";
			
		}else if(action.equals("/addMember.do")) {
			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd");
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.addMember(memberVO);
			nextPage = "/member/listMember.do";
			
		}else if(action.equals("/memberForm.do")) {
			nextPage =  "/memberForm.jsp";
			
		}else if(action.equals("/modMemberForm.do")) {
			String id = req.getParameter("id");
			MemberVO memInfo = memberDAO.getMember(id);
			req.setAttribute("memInfo", memInfo);
			nextPage="/modMemberForm.jsp";	
			
		}else if(action.equals("/modMember.do")) {
			String id = req.getParameter("id");
			System.out.println(id+"----------");
			String pwd = req.getParameter("pwd");
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.modMember(memberVO);
			req.setAttribute("msg", "modified");
			nextPage="/member/listMember.do";
			
		}else if(action.equals("/delMember.do")) {
			String id = req.getParameter("id");
			memberDAO.delMember(id);
			req.setAttribute("msg", "deleted");
			nextPage="/member/listMember.do";
			
		}else {
			List<MemberVO> memberList = memberDAO.listMembers();
			req.setAttribute("memberList", memberList);
			nextPage = "/listMembers.jsp";
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(nextPage);
		dispatcher.forward(req, resp);
		
	}
	
}
















