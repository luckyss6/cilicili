package wilson.cilicili;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.Bucket;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CosTest {
    @Resource
    private COSClient cosClient;

    @Test
    public void CosGetBucketTest() {
        List<Bucket> buckets = cosClient.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println("bucket = " + bucket.getName());
        }
    }
}
