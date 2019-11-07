package com.mvc.base.common.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * XSS过滤
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-01 10:20
 */
public class XssFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
				(HttpServletRequest) request);
		HttpServletResponse xssResponse = (HttpServletResponse) response;
		String[] allowDomains = {"http://192.168.188.175:8001","http://localhost:8001"};
		Set allowOrigins = new HashSet(Arrays.asList(allowDomains));
		String originHeads = xssRequest.getHeader("Origin");
		if(allowOrigins.contains(originHeads)){
			xssResponse.setHeader("Access-Control-Allow-Origin",originHeads);
			xssResponse.setHeader("Access-Control-Allow-Credentials", "true");
			xssResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, token, Accept, " +
					"WG-App-Version, WG-Device-Id, WG-Network-Type, WG-Vendor, WG-OS-Type, WG-OS-Version, WG-Device-Model, WG-CPU, WG-Sid, WG-App-Id, WG-Token");
		}
		chain.doFilter(xssRequest, response);
	}

	@Override
	public void destroy() {
	}

}
