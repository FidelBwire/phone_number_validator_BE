package com.app.config;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.app.exception.ApiError;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException {
		ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, "Access is denied", e,
				"Access to the requested resources has been denied");
		httpServletResponse.setStatus(200);

		OutputStream out = httpServletResponse.getOutputStream();

		JsonFactory jsonFactory = new JsonFactory();
		JsonGenerator generator = jsonFactory.createGenerator(out);

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(generator, apiError);
		generator.close();
		out.flush();
	}

}
