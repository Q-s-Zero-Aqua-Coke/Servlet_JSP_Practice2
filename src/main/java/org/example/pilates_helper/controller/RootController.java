package org.example.pilates_helper.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet ("/")
public class RootController extends Controller {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        view(req, resp, "index");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String question = req.getParameter("question");
        HttpSession session = req.getSession();
        if (question == null || question.isEmpty()) {
            session.setAttribute("message", "질문이 비어 있습니다!");
            view(req, resp, "index");
        }
        session.setAttribute("message", null);
        resp.sendRedirect(req.getContextPath() + "/answer");
    }
}
