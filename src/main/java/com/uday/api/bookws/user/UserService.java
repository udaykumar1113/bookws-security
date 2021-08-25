package com.uday.api.bookws.user;

import org.springframework.stereotype.Service;

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

        return new User(ue.getUsername(), ue.getPassword(), ue.isEnabled());
    }
}