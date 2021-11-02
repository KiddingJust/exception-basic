package hello.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try {
            if(ex instanceof IllegalArgumentException){
                log.info("IllegalArgumentException resolver to 400");
                //그냥 400을 던져버림.
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                //빈값으로 넘기면 쭉 return 됨. 예외를 그냥 먹어버린 것.
                return new ModelAndView();
            }
        } catch (IOException e) {
            log.error("resolver ex", e);
        }

        return null;
    }
}
