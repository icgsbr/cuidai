package br.com.example.cuidai.services.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String createPassword(String password) {
        return encoder.encode(password);
    }

    public static Boolean authenticatePassword(String providedPassword, String storedPassword) {
        return encoder.matches(providedPassword, storedPassword);
    }

    public static String changePassword(String storedPassword, String currentPassword, String newPassword) {
        if (encoder.matches(currentPassword, storedPassword)) {
            return encoder.encode(newPassword);
        }
        return null;
    }

    public enum PasswordStrength {
        WEAK,
        MEDIUM,
        STRONG
    }

    public static PasswordStrength verifyPasswordStrength(String password) {
        int length = password.length();
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialCharacter = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                hasSpecialCharacter = true;
            }
        }

        if (length < 8 || !hasUppercase || !hasLowercase || !hasDigit || !hasSpecialCharacter) {
            return PasswordStrength.WEAK;
        } else if (length < 12 || !(hasUppercase && hasLowercase && hasDigit) || !hasSpecialCharacter) {
            return PasswordStrength.MEDIUM;
        } else {
            return PasswordStrength.STRONG;
        }
    }
}
