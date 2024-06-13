package wilson.cilicili.user;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wilson.cilicili.model.entities.UserInfo;
import wilson.cilicili.model.request.LoginRequest;
import wilson.cilicili.model.request.RegisterRequest;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private AuthenticationService authenticationService;

    @PostMapping("/auth/register")
    ResponseEntity<?> register(@RequestBody @Valid RegisterRequest request) {
        var token = "";
        try {
            token = authenticationService.register(request.getUsername(), request.getPassword(), request.getEmail());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(token);
    }

    @PostMapping("/auth/login")
    ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        var token = "";
        try {
            token = authenticationService.login(request.getUsername(), request.getPassword());
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(token);
    }

    @GetMapping("/user")
    ResponseEntity<?> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object details = authentication.getPrincipal();
        UserInfo user = (UserInfo) details;
        System.out.println(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/video/upload")
    ResponseEntity<?> uploadVideo(@RequestParam("file") MultipartFile file) {

        return null;
    }
}
