package wilson.cilicili.user;

import jakarta.annotation.Resource;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@Setter
public class UserService {
    
    @Resource
    private UserRepository userRepository;

}
