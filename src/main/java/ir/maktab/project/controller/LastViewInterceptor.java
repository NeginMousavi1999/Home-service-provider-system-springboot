package ir.maktab.project.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Negin Mousavi
 */
public class LastViewInterceptor implements HandlerInterceptor {
    public static final String LAST_VIEW_ATTRIBIUTE = LastViewInterceptor.class.getName() + ".jsp";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (modelAndView != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute(LAST_VIEW_ATTRIBIUTE, modelAndView.getViewName());
        }
    }
}
