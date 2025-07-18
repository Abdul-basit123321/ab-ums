package com.abdul.ums.bootstrapper;

import com.abdul.ums.entity.Role;
import com.abdul.ums.enums.RoleEnum;
import com.abdul.ums.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleBootstrapper implements Bootstrapper<List<Role>>{

    private final RoleRepository roleRepository;

    @PostConstruct
    @Override
    public List<Role> bootstrap() {
        Arrays.stream(RoleEnum.values()).forEach(this::addRoleIfNeeded);
        return roleRepository.findAll();
    }

    private void addRoleIfNeeded(RoleEnum event) {
        if (!roleRepository.findByName(event.name()).isPresent()) {
            Role role = new Role();
            role.setName(event.name());
            roleRepository.save(role);
        }
    }

}
