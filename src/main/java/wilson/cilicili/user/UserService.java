package wilson.cilicili.user;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.model.ciModel.snapshot.SnapshotRequest;
import com.qcloud.cos.model.ciModel.snapshot.SnapshotResponse;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import jakarta.annotation.Resource;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wilson.cilicili.model.entities.UserInfo;
import wilson.cilicili.utils.CosUtils;

import java.io.File;

@Log4j2
@Service
@Setter
public class UserService {
    
    @Resource
    private UserRepository userRepository;

    @Resource
    private COSClient cosClient;

    private final String bucketName = "test-1307055480";

    public void uploadVideo(UserInfo userInfo, MultipartFile multipartFile) throws Exception {
        TransferManager transferManager = CosUtils.createTransferManager(cosClient);
        var key = "video/test_video_123.mov";
        String fileName = multipartFile.getOriginalFilename();
        assert fileName != null;
        String prefix = fileName.substring(fileName.lastIndexOf("."));

        File file = File.createTempFile(fileName, prefix);
        multipartFile.transferTo(file);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
        Upload upload = transferManager.upload(putObjectRequest);
        UploadResult uploadResult = upload.waitForUploadResult();

        SnapshotRequest request = new SnapshotRequest();
        request.setBucketName(bucketName);
        request.getInput().setObject(key);
        request.getOutput().setBucket(bucketName);
        request.getOutput().setRegion("ap-guangzhou");
        request.getOutput().setObject("pic/test.jpg");
        request.setTime("2");
        SnapshotResponse response = cosClient.generateSnapshot(request);
    }


}


