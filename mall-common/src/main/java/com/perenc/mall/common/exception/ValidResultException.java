package com.perenc.mall.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * @ClassName: ValidResultException
 * @Description: 校验结果异常
 *
 * @Author: GR
 * @Date: 2019/9/17 12:44 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/17     GR     		
 */
public class ValidResultException extends RuntimeException {
    @Getter
    @Setter
    private int code = 1;

    @Getter
    @Setter
    private String message;

    public ValidResultException() {
    }

    public ValidResultException(BindingResult bindingResult) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                stringBuilder.append(error.getDefaultMessage());
                stringBuilder.append(";");
            }
        }
        this.message = stringBuilder.toString();
    }

    public ValidResultException(Throwable cause) {
        super(cause);
    }

    public ValidResultException(String message) {
        super(message);
    }

    public ValidResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidResultException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ValidResultException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
    }
}
