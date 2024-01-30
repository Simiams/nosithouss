package fr.arosaje.nosithouss.validators;


import fr.arosaje.nosithouss.enums.ERole;

public class AuthValidator {
    public static boolean isValidMail(String mail) {
        return mail != null
                && mail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$");
    }

    public static boolean isValidPassword(String password) {
        return password != null
                && password.length() >= 8;
    }

    public static boolean isValidUserName(String userName) {
        return userName != null
                && userName.length() >= 3;
    }

    public static boolean isValidRole(String role) {
        return role != null
                && ERole.fromString(role) != null;
    }
}
