package wilson.cilicili.user;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wilson.cilicili.model.request.RegisterRequest;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private AuthenticationService authenticationService;

    @PostMapping("/auth/register")
    String register(@RequestBody @Valid RegisterRequest request) {
        return authenticationService.register(request.getUsername(),request.getPassword(),request.getEmail());
    }
}
