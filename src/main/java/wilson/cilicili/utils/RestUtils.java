package wilson.cilicili.utils;

import org.springframework.web.client.RestTemplate;

public class RestUtils {

    private final static RestTemplate restTemplate = new RestTemplate();

    public static String post(String url, String json) {
          return restTemplate.postForObject(url, json, String.class);
    }
}
