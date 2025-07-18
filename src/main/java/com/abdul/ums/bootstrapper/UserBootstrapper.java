package com.abdul.ums.bootstrapper;

import com.abdul.ums.entity.Role;
import com.abdul.ums.entity.User;
import com.abdul.ums.enums.RoleEnum;
import com.abdul.ums.repository.RoleRepository;
import com.abdul.ums.repository.UserRepository;
import com.abdul.ums.utils.UtilMethods;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserBootstrapper implements Bootstrapper<List<User>> {

    private final UserRepository userRepository;
    private final RoleRepository repository;

    @PostConstruct
    @Override
    public List<User> bootstrap() {
        addRoleIfNeeded("abdul@gmail.com");
        return userRepository.findAll();
    }

    private void addRoleIfNeeded(String email) {
        if (!userRepository.findByEmail(email).isPresent()) {
            Optional<Role> userRole = repository.findByName(RoleEnum.ROLE_USER.name());
            if (userRole.isPresent()) {
                User user = User.from("Abdul","abdul@gmail.com","test123",Set.of(userRole.get()));
                userRepository.save(user);
            }
        }
    }

}
