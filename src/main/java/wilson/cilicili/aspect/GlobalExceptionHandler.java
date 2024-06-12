package wilson.cilicili.aspect;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import wilson.cilicili.constants.enums.CodeEnum;
import wilson.cilicili.model.vo.ResultVo;


@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVo<?> handleException(Exception e) {
        log.error("request failed err: {}", e.getMessage());
        return ResultVo.failed(CodeEnum.FAILED);
    }
}
