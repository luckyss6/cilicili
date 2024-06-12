package wilson.cilicili.user;

import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wilson.cilicili.model.entities.UserInfo;

@Service
public class AuthenticationService {
    @Resource
    private JwtService jwtService;

    @Resource
    private UserRepository userRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private AuthenticationManager authenticationManager;

    public String register(String username, String password, String email) {
        UserInfo user = UserInfo.builder().username(username).password(password).email(email).build();

        userRepository.save(user);

        return jwtService.generateToken(user);
    }
}
