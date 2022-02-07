package ir.maktab.project.config;

import ir.maktab.project.data.entity.members.Manager;
import ir.maktab.project.data.repository.ManagerRepository;
import ir.maktab.project.data.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

/*
    @author Negin Mousavi
 */

public class SystemUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    public SystemUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder
            , ManagerRepository managerRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.managerRepository = managerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ir.maktab.project.data.entity.members.User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isEmpty()) {
            Optional<Manager> optionalManager = managerRepository.findByEmail(username);
            if (optionalManager.isEmpty())
                throw new UsernameNotFoundException(username);
            else {
                Manager manager = optionalManager.get();
                return User.withUsername(manager.getEmail())
                        .password(passwordEncoder.encode(manager.getPassword())).roles("ADMIN").build();
            }
        }
        ir.maktab.project.data.entity.members.User user = optionalUser.get();
        return User.withUsername(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword())).roles(user.getUserRole().toString()).build();
    }
}
