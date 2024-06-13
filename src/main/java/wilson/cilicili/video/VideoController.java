
package wilson.cilicili.video;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import wilson.cilicili.model.entities.UserInfo;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * VideoController
 */
@RestController
@RequestMapping("/video")
public class VideoController {

    @Resource
    private VideoService videoService;

    @GetMapping("")
    public ArrayList<UserInfo> getVideo(@RequestParam int page, @RequestParam int size) {
        ArrayList<UserInfo> list = videoService.getVideo(page, size);
        return list;
    }

}