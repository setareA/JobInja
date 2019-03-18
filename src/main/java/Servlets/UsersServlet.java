package Servlets;

import ContentProviders.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONArray;

@WebServlet(name = "UsersServlet",  urlPatterns = { "/users" })
public class UsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        JSONArray map = userContentProvider.getHTMLContentsForAllUsers("1");
        response.setStatus(response.SC_OK);
        PrintWriter out = response.getWriter();
        out.println(map);

    }
}
