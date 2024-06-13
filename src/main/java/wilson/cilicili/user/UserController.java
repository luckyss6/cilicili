package wilson.cilicili.user;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.COSObjectSummary;
import com.qcloud.cos.model.ListObjectsRequest;
import com.qcloud.cos.model.ObjectListing;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wilson.cilicili.config.AuthenticationService;
import wilson.cilicili.model.entities.UserInfo;
import wilson.cilicili.model.request.LoginRequest;
import wilson.cilicili.model.request.RegisterRequest;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private AuthenticationService authenticationService;

    @Resource
    private COSClient cosClient;

    private final String bucketName = "test-1307055480";

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
    ResponseEntity<?> uploadVideo(@RequestParam("file") MultipartFile multipartFile) {
         var user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         try {
             userService.uploadVideo(user,multipartFile);
         } catch (Exception e) {
             return ResponseEntity.badRequest().body(e.getMessage());
         }

//        try {
//
//            System.out.println("response = " + response);
//            return ResponseEntity.ok(uploadResult);
//        } catch (Exception e) {
//
//        } finally {
//            transferManager.shutdownNow(false);
//        }
        return null;
    }

    @GetMapping("/video/list")
    ResponseEntity<?> getVideoList() {
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        listObjectsRequest.setBucketName(bucketName);
        listObjectsRequest.setPrefix("");
        listObjectsRequest.setMaxKeys(10);
        ObjectListing objectListing = null;
        try {
            objectListing = cosClient.listObjects(listObjectsRequest);
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }
        List<COSObjectSummary> cosObjectSummaries = objectListing.getObjectSummaries();
        for (COSObjectSummary cosObjectSummary : cosObjectSummaries) {
            String key = cosObjectSummary.getKey();
            return ResponseEntity.ok(cosClient.getObjectUrl(bucketName, key));
        }
        return ResponseEntity.ok("ok");
    }
}
