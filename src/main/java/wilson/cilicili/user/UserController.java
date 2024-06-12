package wilson.cilicili.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "user")
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private AuthenticationService authenticationService;

    @RestController
    static class Auth {

    }
}
