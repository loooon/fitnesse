package com.qa.utils;

import java.io.File;
import java.util.HashMap;

//import com.youku.uploader.FileUtil;
//import com.youku.uploader.YoukuUploader;

//import constants.YoukuConstants;

public class UploaderYouku {
//    private static final String TAG = "TestLogin";
//    private YoukuUploader uploader;
//    private HashMap<String, String> params;
//    private HashMap<String, String> uploadInfo;
//    private OpenApi o;
//    private UserInfo u;
//    private Youkuprofile y;
//    private String result;
//
//    public UploaderYouku() {
//        params = new HashMap<String, String>();
//        uploadInfo = new HashMap<String, String>();
//        o = new OpenApi();
//        u = new UserInfo();
//        y = new Youkuprofile();
//        this.loginParams();
//        this.getOpenAPI();
//        this.uploaderInfo();
//    }
//
//    private class UserInfo {
//        private String username; // Youku username or email
//        private String access_token; // Youku asscess token
//        private String password; // Youku password
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public void setPassword(String password) {
//            this.password = password;
//        }
//
//        public String getAccess_token() {
//            return access_token;
//        }
//
//        public void setAccess_token(String access_token) {
//            this.access_token = access_token;
//        }
//    }
//
//    private class OpenApi {
//        private String client_id; // OpenAPI client_id
//        private String client_secret; // OpenAPI client_secret
//
//        public String getClient_id() {
//            return client_id;
//        }
//
//        public void setClient_id(String client_id) {
//            this.client_id = client_id;
//        }
//
//        public String getClient_secret() {
//            return client_secret;
//        }
//
//        public void setClient_secret(String client_secret) {
//            this.client_secret = client_secret;
//        }
//    }
//
//    private class Youkuprofile {
//        private String fileName;
//        private String title;
//        private String tags;
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getFileName() {
//            return fileName;
//        }
//
//        public void setFileName(String fileName) {
//            this.fileName = fileName;
//        }
//
//        public String getTags() {
//            return tags;
//        }
//
//        public void setTags(String tags) {
//            this.tags = tags;
//        }
//    }
//
//    private void loginParams() {
//        u.setUsername(YoukuConstants.username);
//        u.setPassword(YoukuConstants.password);
//        u.setAccess_token(YoukuConstants.token);
//        params.put("username", u.getUsername());
//        params.put("password", u.getPassword());
//        params.put("access_token", u.getAccess_token());
//    }
//
//    public void uploader(String fileName) {
//        File uploadFile = new File(fileName);
//        uploader = new YoukuUploader(o.getClient_id(), o.getClient_secret());
//        byte[] data = FileUtil.getBytesFromFile(uploadFile);
//        String result = uploader.upload(params, uploadInfo, data, false);
//        this.setResult(result);
//    }
//
//    private void getOpenAPI() {
//        o.setClient_id(YoukuConstants.client_id);
//        o.setClient_secret(YoukuConstants.client_secret);
//    }
//
//    private void uploaderInfo() {
//        y.setFileName(YoukuConstants.fileName);
//        y.setTitle(YoukuConstants.title);
//        y.setTags(YoukuConstants.tags);
//        uploadInfo.put("file_name", y.getFileName());
//        uploadInfo.put("title", y.getTitle());
//        uploadInfo.put("tags", y.getTags());
//    }
//
//    public String getResult() {
//        return result;
//    }
//
//    private void setResult(String result) {
//        this.result = result;
//    }
//
}
