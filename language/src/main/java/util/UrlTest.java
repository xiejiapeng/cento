package util;

import java.io.UnsupportedEncodingException;
import java.net.*;

public class UrlTest {
    public static String toPath(String url) throws MalformedURLException {
        URL u = new URL(url);
        return u.getPath();
    }
    public static void main(String[] args) throws MalformedURLException, URISyntaxException, UnsupportedEncodingException {
        String url = "http://a.com/x/y/z.mp4?u=10&v=20";
        System.out.println(toPath(url));

        URI uri = new URI("/x/y/z.mp4?a?b");
        System.out.println(uri.getPath());

//        URI u = new URI("https://release-qn.233lyly.com/upload/spread/generated/8d5d6f7e40cf64a28513e213b3fc07c6821098aa/233\\xE4\\xB9\\x90\\xE5\\x9B\\xAD.apk");
//        System.out.println(u.getPath());

        String us = "https://release-qn.233lyly.com/upload/spread/generated/8d5d6f7e40cf64a28513e213b3fc07c6821098aa/233\\xE4\\xB9\\x90\\xE5\\x9B\\xAD.apk";

        System.out.println(URLEncoder.encode(us, "UTF-8"));
    }
}
