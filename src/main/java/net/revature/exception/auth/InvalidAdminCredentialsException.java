package net.revature.exception.auth;

public class InvalidAdminCredentialsException extends Exception {
    public InvalidAdminCredentialsException() {
        super("Invalid credentials for admin authentication.");
    }

    public InvalidAdminCredentialsException(String message) {
        super(message);
    }
}

