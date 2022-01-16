package com.stecenko.userCrudBoot.config.handler;

import com.stecenko.userCrudBoot.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("user", user);
        httpSession.setAttribute("roles", authentication.getAuthorities());

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(authority -> {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                try {
                    redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/admin");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if (authority.getAuthority().equals("ROLE_USER")) {
                try {
                    redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/user");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                throw new IllegalStateException();
            }
        });
    }
}