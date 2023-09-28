package com.vtortsev.quizapp.sequrity2.controller;

import com.vtortsev.quizapp.sequrity2.config.jwt.JwtUtils;
import com.vtortsev.quizapp.sequrity2.dao.RoleDao;
import com.vtortsev.quizapp.sequrity2.dao.UserDao;
import com.vtortsev.quizapp.sequrity2.entities.ERole;
import com.vtortsev.quizapp.sequrity2.entities.Role;
import com.vtortsev.quizapp.sequrity2.entities.User;
import com.vtortsev.quizapp.sequrity2.pojo.requests.LoginRequest;
import com.vtortsev.quizapp.sequrity2.pojo.requests.SignupRequest;
import com.vtortsev.quizapp.sequrity2.pojo.responses.JwtResponse;
import com.vtortsev.quizapp.sequrity2.pojo.responses.MessageResponse;
import com.vtortsev.quizapp.sequrity2.service.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    AuthenticationManager authenticationManager;
    UserDao userDao;
    RoleDao roleDao;
    PasswordEncoder passwordEncoder;
    JwtUtils jwtUtils;

    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {

        if (userDao.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Такой username существует"));
        }

        if (userDao.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Такой email существует"));
        }

        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> reqRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (reqRoles == null) {
            Role userRole = roleDao
                    .findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error, Роль не найдена"));
            roles.add(userRole);
        } else {
            reqRoles.forEach(r -> {
                switch (r) {
                    case "admin":
                        Role adminRole = roleDao
                                .findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error, Роль ADMIN не найдена"));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleDao
                                .findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error, Роль MODERATOR не найдена"));
                        roles.add(modRole);

                        break;

                    default:
                        Role userRole = roleDao
                                .findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error, Роль USER не найдена"));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userDao.save(user);
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }

}
