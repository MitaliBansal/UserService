package com.addrsharingtool.userservice.constant;

public class APIPath {

    private APIPath() {}

    public static final String HEALTH_CHECK = "/health";

    public static class User {

        private User() {}

        public static final String CREATE_USER = "/api/user/create/v1";
        public static final String UPDATE_USER = "/api/user/update/v1";
        public static final String DELETE_USER = "/api/user/delete/v1";
        public static final String FETCH_USER = "/api/user/fetch/v1";

    }

    public static class UserVerification {

        private UserVerification() {}

        public static final String SEND_OTP = "/api/send/otp/v1";
        public static final String VERIFY_OTP = "/api/verify/otp/v1";
        public static final String VERIFY_TOKEN = "/api/verify/token/v1";

    }
    
}