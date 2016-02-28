package servlets;


import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Admin on 24.02.2016.
 */
public class MirrorRequestsServlet extends HttpServlet {

    private String responseValue="";
    public MirrorRequestsServlet(){}
    public MirrorRequestsServlet(String greeting)
    {
        this.responseValue=greeting;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        Map<String, String[]> pageVariables = request.getParameterMap();

        if(pageVariables.size() > 0){
            for (String[] value : pageVariables.values()) {
                if(value.length > 0) responseValue = value[0];
            }
        }else{
            responseValue = "empty VALUE";
        }
        Map<String, Object> pageVar = new HashMap<>();
        pageVar.put("key",responseValue);
        response.getWriter().println(PageGenerator.instance().getPage("test_page.html", pageVar));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
