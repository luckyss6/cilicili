package wilson.cilicili.video;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import wilson.cilicili.model.entities.UserInfo;

/**
 * VideoService
 */
@Service
public class VideoService {
    @Resource
    private VideoRepository videoRepository;

	public ArrayList<UserInfo> getVideo(int page, int size) {
            
        
		throw new UnsupportedOperationException("Unimplemented method 'getVideo'");
	}

    
}