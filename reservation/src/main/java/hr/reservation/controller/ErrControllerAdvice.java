package hr.reservation.controller;

import hr.reservation.domain.error.BindingResultException;
import hr.reservation.domain.error.Constants;
import hr.reservation.domain.error.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice // 여러 컨트롤러에 있는 오류들을 한 곳에서 관리
public class ErrControllerAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    /*public ErrorResult unknownException(Exception e) { // 오류 - 코드
        log.error("[unknownException] ", e);
        return new ErrorResult(Constants.INTERNAL_SERVER_CODE, Constants.INTERNAL_SERVER_MSG);
    }*/
    public ModelAndView unknownException(Exception e) { // 오류 - 페이지
        log.error("[unknownException] ", e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/500");
        return modelAndView;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST) // "200 OK"가 아닌 "400"으로 응답가게 하기 위해 추가
    public ErrorResult missingServletRequestParameterException(MissingServletRequestParameterException e) { // HttpMessageNotReadableException 해당 에러가 발생하면 처리하는 로직
        log.error("[missingServletRequestParameterException] ", e);
        return new ErrorResult(Constants.BAD_REQUEST_CODE, Constants.BAD_REQUEST_MSG);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST) // "200 OK"가 아닌 "400"으로 응답가게 하기 위해 추가
    public ErrorResult bindingResultException(BindingResultException e) {
        log.error("[bindingResultException] ", e);
        return new ErrorResult(Constants.BAD_REQUEST_CODE, Constants.BAD_REQUEST_MSG);
    }

    /*@ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult constraintViolationException(ConstraintViolationException e) {
        log.error("[constraintViolationException] ", e);
        return new ErrorResult(Constants.BAD_PARAMETER_CODE, Constants.BAD_PARAMETER_MSG);
    }*/
}
