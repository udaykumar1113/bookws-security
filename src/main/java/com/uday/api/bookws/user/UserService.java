package com.uday.api.bookws.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {

        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity != null) {
            return createUserFromUserEntity(userEntity);
        } else {
            return null;
        }
    }

    private User createUserFromUserEntity(UserEntity ue) {

        User user = new User(ue.getUsername(), ue.getPassword(), ue.isEnabled());
        user.setAuthorities(ue.getAuthoritiesEntities().stream()
                .map(ae -> new SimpleGrantedAuthority(ae.getAuthority()))
                .collect(Collectors.toSet()));
        return user;
    }
}