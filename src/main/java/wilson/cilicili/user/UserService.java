package wilson.cilicili.user;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import wilson.cilicili.model.dto.CreateUserDto;
import wilson.cilicili.model.dto.LoginUserDto;
import wilson.cilicili.model.entities.UserInfo;

import java.util.Optional;

@Log4j2
@Service
@Setter
public class UserService {
    
    @Resource
    private UserRepository userRepository;

    public void createUser(CreateUserDto request) {
        UserInfo save = userRepository.save(UserInfo.builder()
                .username(request.username())
                .password(request.password())
                .email(request.email())
                .build());
        log.info("User created: {}", save);
    }

    public Optional<UserInfo> login(LoginUserDto request) {
        return userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());

    }

    public UserInfo findById(Long id) {
        Optional<UserInfo> res = userRepository.findById(id);
        if (res.isPresent()) {
            return res.get();
        }
        throw new EntityNotFoundException();
    }


}
