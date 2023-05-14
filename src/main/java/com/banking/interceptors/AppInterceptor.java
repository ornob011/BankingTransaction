package com.banking.interceptors;

import com.banking.models.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// This class is used to intercept the requests.
@Component // This annotation is used to tell the spring that this class is a component.
public class AppInterceptor  implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception { // This method is used to handle the request before the execution of the handler.
        System.out.println("In Pre Handle Interceptor Method");

        // Check Request URI:
        if(request.getRequestURI().startsWith("/app")){
            // Get Session:
            HttpSession session = request.getSession();

            // Get Token Stored In Session:
            String token = (String) session.getAttribute("token");
            System.out.println(token);

            // Get User Object Stored In Session:
            User user = (User)session.getAttribute("user");

            // TODO: Check if Authenticated:
            // boolean isAuthenticated = (boolean) session.getAttribute("authenticated");

            // Validate Session Attributes / Objects:
            if(token == null || user == null){
                response.sendRedirect("/login");
                return false;
            }
            // End Of Validate Session Attributes / Objects.
        }
        // End Of Check Request URI.
        return true;
    }
    // End Of Pre Handle Method.


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception { // This method is used to handle the request after the execution of the handler.
        System.out.println("In Post Handle Interceptor Method");
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception { // This method is used to handle the request after the completion of the handler.
        System.out.println("In After Completion Interceptor Method");
    }
}
// End Of Interceptor Class.
