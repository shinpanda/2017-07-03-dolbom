package com.bepum.web.controller.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import com.bepum.web.dao.BoardCmtDao;
import com.bepum.web.dao.SecretBoardDao;
import com.bepum.web.dao.jdbc.JdbcBoardCmtDao;
import com.bepum.web.dao.jdbc.JdbcSecretBoardDao;
import com.bepum.web.entity.Board;
import com.bepum.web.entity.BoardView;
import com.bepum.web.entity.SecretBoardView;

@WebServlet("/board/question-detail")
public class QuestionDetailController extends HttpServlet {
	String boardName = "FAQ";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String no = request.getParameter("no");
		String btnOption = request.getParameter("sec-btn");
		if (btnOption != null && !btnOption.equals("")) {
			
			String _privateKey = request.getParameter("secretKey");
			System.out.println(_privateKey);
			if (_privateKey == null || _privateKey.equals(""))
				response.sendRedirect("question");
			else {
				SecretBoardDao dao = new JdbcSecretBoardDao();
				String key = dao.getSecretKey(no, boardName);
				if (_privateKey.equals(key)) {
					int result = dao.updateHit(no, boardName);
					SecretBoardView b = dao.get(no, boardName);
					request.setAttribute("b", b);
					request.setAttribute("br", "<br/>");
					request.setAttribute("cn", "\n");
					if (b.getCountCmt() > 0) {
						BoardCmtDao cmtDao = new JdbcBoardCmtDao();
						request.setAttribute("cmtList", cmtDao.getList(no, boardName));
					}
					request.getRequestDispatcher("/WEB-INF/views/board/question/detail.jsp").forward(request, response);
				} else
					response.sendRedirect("question");
			}
		} else {
			// 댓글 부분
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			Object _id = session.getAttribute("id");
			btnOption = request.getParameter("cmt-btn");
			if (btnOption.equals("등록")) {
				if (_id == null)
					out.write("<script> alert('로그인이 필요한 요청입니다.'); history.back(); </script>");
				else {
					String cmt = request.getParameter("cmt");
					System.out.println(cmt);
					if (cmt != null && !cmt.equals("")) {
						String id = _id.toString();
						BoardCmtDao cmtDao = new JdbcBoardCmtDao();
						int result = cmtDao.insert(no, id, cmt, boardName);
						response.sendRedirect(request.getHeader("Referer"));
					} else {
						out.write("<script> alert('내용을 작성해주세요.'); history.back(); </script>");
					}
				}
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Object _id = request.getSession().getAttribute("id");
		String no = request.getParameter("no");

		SecretBoardDao dao = new JdbcSecretBoardDao();
		int isSecret = dao.isSecret(no, boardName);

		if (_id != null && !_id.equals("")) {
			String id = _id.toString();

			if (id.equals(dao.getId(no, boardName)))
				isSecret = 0;
		}

		request.setAttribute("isSecret", isSecret);
		if (isSecret == 0) {
			int result = dao.updateHit(no, boardName);
			SecretBoardView b = dao.get(no, boardName);
			request.setAttribute("b", b);
			request.setAttribute("br", "<br/>");
			request.setAttribute("cn", "\n");
			if (b.getCountCmt() > 0) {
				BoardCmtDao cmtDao = new JdbcBoardCmtDao();
				request.setAttribute("cmtList", cmtDao.getList(no, boardName));
			}
		}
		/* response.sendRedirect("notice.jsp"); */
		request.getRequestDispatcher("/WEB-INF/views/board/question/detail.jsp").forward(request, response);

	}
}
