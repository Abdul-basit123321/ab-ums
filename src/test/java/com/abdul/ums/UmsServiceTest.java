package com.abdul.ums;

import com.abdul.ums.dto.UserDto;
import com.abdul.ums.dto.UserRequest;
import com.abdul.ums.entity.Role;
import com.abdul.ums.entity.User;
import com.abdul.ums.exception.NoRoleFoundException;
import com.abdul.ums.exception.NoUserFoundException;
import com.abdul.ums.exception.UserAlreadyExistException;
import com.abdul.ums.repository.RoleRepository;
import com.abdul.ums.repository.UserRepository;
import com.abdul.ums.service.UmsService;
import com.abdul.ums.service.impl.UmsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static com.abdul.ums.constant.AppConstant.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UmsServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UmsServiceImpl umsService;

    @Test
    void testGetAllUsers_shouldReturnUserList() {
        //Arrange
        List<User> users = List.of(User.from("abdul","abdul@gmail.com","abc123", Set.of()));
        Mockito.when(userRepository.findAll()).thenReturn(users);

        //Act
        List<UserDto> result = umsService.getAllUsers();

        //Assert
        assertEquals(1, result.size());
        assertEquals("abdul", result.get(0).getName());
    }

    @Test
    void testGetAllUsers_shouldThrowExceptionWhenNoUsersExist() {
        //Arrange
        Mockito.when(userRepository.findAll()).thenReturn(Collections.emptyList());

        // Act & Assert
        NoUserFoundException exception = assertThrows(NoUserFoundException.class,()->{
            umsService.getAllUsers();
        });

        assertEquals(NO_USER_FOUND_CODE, exception.getCode());
        assertEquals(NO_USER_FOUND_MESSAGE, exception.getMessage());
    }

    @Test
    void testAddUser_shouldReturnUserDto() {
        //Arrange
        UserRequest userRequest = new UserRequest("abdul","abdul@gmail.com","abdul123");
        Role mockRole = new Role();
        mockRole.setId(1L);
        mockRole.setName("ROLE_USER");

        User expectedUser = User.from(userRequest.getName(),userRequest.getEmail(),userRequest.getPassword(), Set.of(mockRole));
        UserDto userDto = UserDto.fromUser(expectedUser);

        Mockito.when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(mockRole));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(expectedUser);

        //Act
        UserDto result = umsService.addUser(userRequest);

        // Assert
        assertEquals(userDto.getName(), result.getName());
        assertEquals(userDto.getEmail(), result.getEmail());
    }

    @Test
    void testAddUser_shouldThrowExceptionWhenUserAlreadyExist() {
        // Arrange
        UserRequest request = new UserRequest("abdul", "abdul@email.com", "abdul123");
        Role mockRole = new Role();
        mockRole.setId(1L);
        mockRole.setName("ROLE_USER");

        Mockito.when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(mockRole));
        Mockito.doThrow(new RuntimeException("Duplicate")).when(userRepository).save(Mockito.any(User.class));

        // Act & Assert
        UserAlreadyExistException exception = assertThrows(UserAlreadyExistException.class,()->{
            umsService.addUser(request);
        });

        assertEquals(USER_ALREADY_EXIST_CODE, exception.getCode());
        assertEquals(USER_ALREADY_EXIST_MESSAGE, exception.getMessage());
    }

    @Test
    void testDeleteUser_shouldReturnUserDto() {
        //Arrange
        User expectedUser = User.from("abdul","abdul@gmail.com","abdul123", Set.of());
        Mockito.when(userRepository.findByEmail("abdul@gmail.com")).thenReturn(Optional.of(expectedUser));
        Mockito.doNothing().when(userRepository).delete(Mockito.any(User.class));

        //Act
        UserDto result = umsService.deleteUser("abdul@gmail.com");

        // Assert
        assertEquals(expectedUser.getEmail(), result.getEmail());
    }

    @Test
    void testDeleteUser_shouldThrowExceptionWhenNoUserFound() {
        // Arrange
        Mockito.when(userRepository.findByEmail("abdul@email.com")).thenReturn(Optional.empty());

        // Act & Assert
        NoUserFoundException exception = assertThrows(NoUserFoundException.class, () -> {
            umsService.deleteUser("abdul@email.com");
        });

        assertEquals(NO_USER_FOUND_CODE, exception.getCode());
        assertEquals(NO_USER_FOUND_MESSAGE, exception.getMessage());
    }

    @Test
    void testUpdateUser_shouldReturnUserDto() {
        //Arrange
        User expectedUser = User.from("abdul","abdul@gmail.com","abdul123", Set.of());
        Mockito.when(userRepository.findByEmail("abdul@gmail.com")).thenReturn(Optional.of(expectedUser));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(expectedUser);

        //Act
        UserDto result = umsService.updateUser("abdul@gmail.com","abc123","abdul");

        // Assert
        assertEquals(expectedUser.getEmail(), result.getEmail());
    }


    @Test
    void testUpdateUser_shouldThrowExceptionWhenNoUserFound() {
        // Arrange
        Mockito.when(userRepository.findByEmail("abdul@email.com")).thenReturn(Optional.empty());

        // Act & Assert
        NoUserFoundException exception = assertThrows(NoUserFoundException.class, () -> {
            umsService.deleteUser("abdul@email.com");
        });

        assertEquals(NO_USER_FOUND_CODE, exception.getCode());
        assertEquals(NO_USER_FOUND_MESSAGE, exception.getMessage());
    }

    @Test
    void testUpdateRole_shouldUpdateRolesSuccessfully() {
        // Arrange
        String email = "abdul@gmail.com";
        List<String> roleNames = List.of("ROLE_ADMIN", "ROLE_USER");

        Role mockRoleUser = new Role();
        mockRoleUser.setId(1L);
        mockRoleUser.setName("ROLE_USER");

        Role mockRoleAdmin = new Role();
        mockRoleAdmin.setId(2L);
        mockRoleAdmin.setName("ROLE_ADMIN");

        Set<Role> originalRoles = new HashSet<>(Set.of(mockRoleAdmin));

        User user = User.from("abdul", email, "abdul123", originalRoles);

        Mockito.when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.of(mockRoleAdmin));
        Mockito.when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(mockRoleUser));
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        // Act
        UserDto result = umsService.updateRole(email, roleNames);

        // Assert
        assertEquals(email, result.getEmail());
        assertEquals(2, user.getRoles().size());
        assertTrue(user.getRoles().contains(mockRoleAdmin));
        assertTrue(user.getRoles().contains(mockRoleUser));
    }


    @Test
    void testUpdateRole_shouldThrowExceptionWhenUserNotFound() {
        // Arrange
        String email = "abdul@email.com";
        List<String> roles = List.of("ROLE_USER");

        Role mockRole = new Role();
        mockRole.setId(1L);
        mockRole.setName("ROLE_USER");
        Mockito.when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(mockRole));
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        NoUserFoundException ex = assertThrows(NoUserFoundException.class, () -> {
            umsService.updateRole(email, roles);
        });

        assertEquals(NO_USER_FOUND_CODE, ex.getCode());
    }

    @Test
    void testUpdateRole_shouldThrowExceptionWhenRoleNotFound() {
        // Arrange
        String email = "abdul@email.com";
        List<String> roles = List.of("ROLE_SUPERADMIN");

        Mockito.when(roleRepository.findByName("ROLE_SUPERADMIN")).thenReturn(Optional.empty());

        // Act & Assert
        NoRoleFoundException ex = assertThrows(NoRoleFoundException.class, () -> {
            umsService.updateRole(email, roles);
        });

        assertEquals(NO_ROLE_FOUND_CODE, ex.getCode());
    }


}

