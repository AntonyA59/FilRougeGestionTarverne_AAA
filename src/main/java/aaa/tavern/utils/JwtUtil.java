package aaa.tavern.utils;

public class JwtUtil {
    public static final String AUTH_HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";
    public static final long EXPIRE_ACCESS_TOKEN = 10 * 60 * 1000;
    public static final long EXPIRE_REFRESH_TOKEN = 5 * 60 * 60 * 1000;

}