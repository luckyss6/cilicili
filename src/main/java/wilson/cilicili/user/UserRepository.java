package wilson.cilicili.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wilson.cilicili.model.entities.UserInfo;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUsernameAndPassword(String username, String password);

    Optional<UserInfo> findByUsername(String username);


}
