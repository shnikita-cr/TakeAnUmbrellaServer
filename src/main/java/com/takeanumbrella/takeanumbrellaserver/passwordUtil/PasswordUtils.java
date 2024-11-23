package com.takeanumbrella.takeanumbrellaserver.passwordUtil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Хэширование пароля
    public static String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public static boolean checkPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
