package servlets;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
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

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>"+responseValue+"</h1>");
    }
}
