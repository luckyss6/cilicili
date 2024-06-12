package wilson.cilicili.constants.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum CodeEnum {
    SUCCESS(0,"success"),
    FAILED(1,"failed"),
    API_EXCEPTION(2,"interface have some problem"),
    USER_NOT_FOUND(3,"user not found"),;



    public final int code;
    public final String message;
}
