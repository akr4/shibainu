package net.physalis.shibainu.presentation.csrf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxCsrfPreventionInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxCsrfPreventionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        PreventCsrf annotation = findAnnotation(handlerMethod);
        if (annotation == null) {
            return true;
        }

        if (request.getHeader("X-Requested-With") == null) {
            LOGGER.info("Request does not have a valid header.");
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return false;
        } else {
            return true;
        }
    }

    private PreventCsrf findAnnotation(HandlerMethod handlerMethod) {
        PreventCsrf a = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), PreventCsrf.class);
        if (a == null) {
            a = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), PreventCsrf.class);
        }
        return a;
    }
}
