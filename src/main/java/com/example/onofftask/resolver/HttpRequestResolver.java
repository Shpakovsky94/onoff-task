package com.example.onofftask.resolver;

import com.example.onofftask.exception.InvalidInputException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class HttpRequestResolver {

    public String getParam(
        final String name,
        final HttpServletRequest request
    ) throws InvalidInputException {
        String value = request.getParameter(name);

        if (value != null && !value.isEmpty()) {
            return value;
        } else {
            throw new InvalidInputException();
        }
    }

    public Double getDoubleParam(
        final String name,
        final HttpServletRequest request
    ) throws InvalidInputException {
        String s = getParam(name, request);

        if (s != null && !s.isEmpty() && !s.equals("0")) {
            return Double.valueOf(s);
        } else {
            throw new InvalidInputException();
        }
    }

}
