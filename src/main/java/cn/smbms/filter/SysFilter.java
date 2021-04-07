package cn.smbms.filter;

import cn.smbms.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
//		System.out.println("TestFilter init()===========");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("SysFilter doFilter()===========");
		HttpServletRequest rq = (HttpServletRequest)request;
		HttpServletResponse rp = (HttpServletResponse)response;
		User userSession = (User)rq.getSession().getAttribute("userSession");
		HttpServletRequest httpRequest= (HttpServletRequest) request;
		String contextPath = httpRequest.getContextPath();
		/*if(httpRequest.getRequestURI().contains("/test/") || userSession!=null){
			chain.doFilter(request, response);
		}else{
			rp.sendRedirect(contextPath+"/error.jsp");
		}*/

		chain.doFilter(request, response);

		/*if(null == userSession){
			rp.sendRedirect(contextPath+"/error.jsp");
		}else{
			chain.doFilter(request, response);
		}*/
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
//		System.out.println("TestFilter destroy()===========");
	}

}
