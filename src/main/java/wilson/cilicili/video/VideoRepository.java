package wilson.cilicili.video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wilson.cilicili.model.entities.UserInfo;

/**
 * VideoRepository
 */
@Repository
public interface VideoRepository extends JpaRepository<UserInfo, Long> {

}