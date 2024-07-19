package net.revature.exception.person;

public class InvalidAdminCredentialsException extends Exception {
    public InvalidAdminCredentialsException() {
        super("Invalid credentials for admin authentication.");
    }

    public InvalidAdminCredentialsException(String message) {
        super(message);
    }
}

