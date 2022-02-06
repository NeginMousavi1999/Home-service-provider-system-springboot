/*
package ir.maktab.project.config;

import ir.maktab.project.data.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

*/
/*
 * @author Negin Mousavi
 *//*


public class SystemUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SystemUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ir.maktab.project.data.entity.members.User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isEmpty())
            throw new UsernameNotFoundException(username);
        ir.maktab.project.data.entity.members.User user = optionalUser.get();
        return User.withUsername(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword())).roles(user.getUserRole().toString()).build();
    }
}
*/
