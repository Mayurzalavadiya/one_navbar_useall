package com.myapplication.api;

public class RestConstants {
    /* -----------------------             API name           ------------------------------*/


    /* -----------------------             KEY name           ------------------------------*/
    public static final String KEY_USER_ID = "userid";
    public static final String KEY_TYPE = "type";

    public final class ApiLogin {
        public static final String API_LOGIN = "login";

        public static final String KEY_EMAIL = "email";
        public static final String KEY_PASSWORD = "password";
    }

    public final class ApiSendOTPSignup {
        public static final String API_SEND_OTP_SIGNUP = "SendOTPSignupMobile";
    }

    public final class ApiSendOtpForgot {
        public static final String API_SEND_OTP_FORGOT = "SendOTPForgotMobile";

        public static final String KEY_EMAIL_OR_PHONE = "phone_email";
    }

    public final class ApiVerifyOTPForgotMobile {
        public static final String API_VERIFY_OTP_FORGOT = "VerifyOTPForgotMobile";

        public static final String KEY_EMAIL_OR_PHONE = "phone_email";
        public static final String KEY_OTP = "otp";
    }

    public final class ApiVerifyOTPSignup {
        public static final String API_VERIFY_OTP = "VerifyOTPSignupMobile";

        public static final String KEY_NAME = "name";
        public static final String KEY_PHONE = "phone";
        public static final String KEY_EMAIL = "email";
        public static final String KEY_OTP = "otp";

        public static final String KEY_CITY = "city";
        public static final String KEY_PASSWORD = "password";
        public static final String KEY_LATITUDE = "latitude";
        public static final String KEY_LONGITUDE = "longitude";
        public static final String KEY_F_NAME = "fname";
        public static final String KEY_L_NAME = "lname";

    }

    public final class ApiResendOtp {
        public static final String API_RESEND_OTP = "ResendOTPSignupMobile";

        public static final String KEY_PHONE = "phone";
        public static final String KEY_EMAIL = "email";
    }

    public final class ApiChangePassword {
        public static final String API_CHANGE_PASSWORD = "ChangePasswordMobile";

        public static final String KEY_EMAIL_OR_PHONE = "phone_email";
        public static final String KEY_PASSWORD = "password";
    }

    public final class ApiResendOTPForgot{
        public static final String API_CHANGE_PASSWORD = "ResendOTPForgotMobile";

        public static final String KEY_NAME = "name";
        public static final String KEY_EMAIL_OR_PHONE = "phone_email";
    }

    public final class ApiViewProfile {
        public static final String API_VIEW_PROFILE = "viewProfile";
    }

    public final class ApiEditProfile {
        public static final String API_EDIT_PROFILE = "editProfile";
    }

    /*public final class ApiChangePassword {
        public static final String API_CHANGE_PASSWORD = "changepassword";

        public static final String KEY_CURRENT_PASSWORD = "currentpassword";
        public static final String KEY_NEW_PASSWORD = "newpassword";
    }*/

    public final class ApiResetPassword {
        public static final String API_RESET_PASSWORD = "resetpassword";

        public static final String KEY_V_PASSWORD = "vPassword";
        public static final String KEY_V_CONFIRM_PASSWORD = "vConfirmPassword";
    }

}
