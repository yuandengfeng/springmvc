package com.rsa;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.SocketException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017-03-23.
 */
public class PostData1 {

    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        try{
            HttpPost post = new HttpPost("http://jyt.kunteng.org/posinfo/pos/posRecord");
            post.setHeader("Content-Type","application/x-www-form-urlencoded");
            // 加密后的内容
            String base64content = "Hu35gkDFltxEBWz+CSlkcp1Dm/RqP/lxBIn+yughcu+HXn90sD7Fs/cIS47f6XXC0AfkycSxVYU+3WWaUi3e/fLD2lbL2UQXuMaAVVwUnf7CQ9Yd+2mcurh1w7uwFf8lWxUpoMlloNpG5bLfkRwC83Vc4VIbviULdv5hmAcCmbc0hCwnYMwvimAgITfwvIWuCocRssP/d9cAN6uxSDicNIETvH3pJbpimIRXvh0XRJZCe/kj2rRm4+nWSaM3tX6Sz7uhs+Zy8FntR/8hqXkbTfjzQrILj+vImJzRPIJ6DpGIZGCP0W3YdDwS1jayHmzwuxxg2G0Hw86CSws+ko5go2jRiDu5pwcKFkv3n99Wq/8iL8YwWyDQLAH6WUubBCWCE6ijebzAaPRXNqR/CCVRGMpJJvGlsCgeK0Q9GBsZvQXZk2xo2rfeX6iYZBymZmizydv8qcQJfrA8oom54ZkD8/KsmyYZfjlWYcTTd68M9I1ixLpl+E2M0rFz7JLZI0S3JrORy0xC2vGYfsAmUj+9Px5eHTwNM51nXBlDeOcothgiYUGdYWATes/xwya3T7Z6ilrHDGSitqcSqRobibZ+m1KPjc05ELorec8AWYfNJWvvkUibCFGp8ti+AglA/BCENr+dTye+Zf7DYJnjHoXW+R4ErtVAhzgvebRMIRzffwZv4B8t09EOevMFMQ/duuGYeItnxjPwVFST8qKh+LA6hUZclO6qNCFb93E90Lxe926JD443DC7stB7tNE/jq17e1aTxyzJd6WB7OE2uy4cWDAzvLwFQztMiXPAbCEyqNLLHRS00mK7RNGIKSMqfZEMnKfUcHU6ao889teNpJUNiTDkwPDIYfI6LY5u6TjLTkKtIefbvN4bAeWg/WjIr7vFIRe7I2ZW3ZM6vypcMWzQqExyMhx19nzNf5E5e9Nx7zD45mkknbmq1Aiw2/pboCJC08m3cLVDH15jV3XNGsC6CKj/cZEgIXWlHGCArqJJzFpn4rzeSgFkOTLfgw7rrSE01";
            StringEntity body = new StringEntity("content="+ URLEncoder.encode(base64content, "utf-8"));
            post.setEntity(body);
            HttpResponse httpResponse = httpClient.execute(post);
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                String responseString = EntityUtils.toString(entity);
                System.out.println(responseString);
            }
        }catch (SocketException s){
            s.printStackTrace();
        }finally {
        }
    }
}
