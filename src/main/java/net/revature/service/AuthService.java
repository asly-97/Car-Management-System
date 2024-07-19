package net.revature.service;

import net.revature.dao.AdminAuthDAO;
import net.revature.exception.auth.InvalidAdminCredentialsException;
import net.revature.model.Admin;
import net.revature.model.LoginDTO;

public class AuthService {

    AdminAuthDAO adminAuthDAO = new AdminAuthDAO();

    public Admin login(LoginDTO lDTO) throws InvalidAdminCredentialsException {

        //Admin authentication

        Admin authAdmin = adminAuthDAO.login(lDTO.getUsername(),lDTO.getPassword());

        if(authAdmin == null){
            throw new InvalidAdminCredentialsException();
        }



        return authAdmin;
    }
}
