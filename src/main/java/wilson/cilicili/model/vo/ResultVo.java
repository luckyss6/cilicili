package wilson.cilicili.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wilson.cilicili.constants.enums.CodeEnum;

import static wilson.cilicili.constants.enums.CodeEnum.SUCCESS;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo<T> {
    int code;
    String msg;
    Object data;

    public static ResultVo<?> success(Object data) {
        return new ResultVo<>(SUCCESS.code, SUCCESS.message, data);
    }

    public static ResultVo<?> failed(int code, String msg) {
        return new ResultVo<>(code, msg, null);
    }

    public static ResultVo<?> failed(CodeEnum codeEnum) {
        return new ResultVo<>(codeEnum.code, codeEnum.message, null);
    }
}
