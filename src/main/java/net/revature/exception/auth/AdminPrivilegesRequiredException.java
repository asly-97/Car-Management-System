package net.revature.exception.auth;

public class AdminPrivilegesRequiredException extends Exception {
    public AdminPrivilegesRequiredException() {
        super("Admin privileges are required to perform this operation.");
    }

    public AdminPrivilegesRequiredException(String message) {
        super(message);
    }
}

