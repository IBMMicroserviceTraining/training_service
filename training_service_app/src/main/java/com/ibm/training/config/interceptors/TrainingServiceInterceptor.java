package com.ibm.training.config.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TrainingServiceInterceptor extends HandlerInterceptorAdapter {

	
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object object) throws Exception {
		    log.info("we are intercepting the request::"+request.getRequestURI());
		    log.debug("debug logs enabled");
		    return true;
	 }
	
	 @Override
	 public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object object, ModelAndView model)
			throws Exception {
		 log.info("_________________________________________");
		 log.info("In postHandle request processing "
				+ "completed by @RestController");
		 log.info("_________________________________________");
		
	 }
	 
	 
	 @Override
	 public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object object, Exception arg3)
			throws Exception {
		 log.info("________________________________________");
		 log.info("In afterCompletion Request Completed");
		 log.info("________________________________________");
	 }
}
