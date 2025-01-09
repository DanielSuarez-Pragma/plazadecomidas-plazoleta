package com.plazoleta.infraestructure.security;

import com.plazoleta.infraestructure.input.rest.client.UserAuthDto;
import com.plazoleta.infraestructure.input.rest.client.UserFeingClient;
import com.plazoleta.infraestructure.security.dto.AuthLoginRequest;
import com.plazoleta.infraestructure.security.dto.AuthResponse;
import com.plazoleta.infraestructure.security.util.JwtUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserFeingClient userFeingClient;
    private PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public UserDetailServiceImpl(UserFeingClient userFeingClient, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userFeingClient = userFeingClient;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthDto userAuthDto = userFeingClient.getUserByEmail(username);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        Long roleId = userAuthDto.getRoleId();

        // Asignar autoridad según el ID del rol
        switch (roleId.intValue()) {
            case 0:
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                authorities.add(new SimpleGrantedAuthority("ADMIN"));
                break;
            case 1:
                authorities.add(new SimpleGrantedAuthority("ROLE_OWNER"));
                authorities.add(new SimpleGrantedAuthority("OWNER"));
                break;
            case 3:
                authorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
                authorities.add(new SimpleGrantedAuthority("EMPLOYEE"));
                break;
            case 4:
                authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
                authorities.add(new SimpleGrantedAuthority("CLIENT"));
                break;
            default:
                throw new IllegalArgumentException("Rol no reconocido para el ID: " + roleId);
        }
        return new User(userAuthDto.getEmail(), userAuthDto.getPassword(), authorities);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {

        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        return new AuthResponse(username, "User loged successfuly", accessToken, true);

    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);
        if(userDetails == null){
            throw new BadCredentialsException("Invalid username or password");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
