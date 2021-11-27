package com.adkan.adkan.employee;

import com.adkan.adkan.config.dto.AuthenticationRequest;
import com.adkan.adkan.config.dto.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
@Service
public class AdkanEmployeeService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Employee> employee  = employeeRepository.findByEmailAndStatus(email, true);
        if (!employee.isPresent())
            throw new UsernameNotFoundException("User not found by name: " + email);

        return new org.springframework.security.core.userdetails.User(
                employee.get().getEmail(),
                "{noop}" + employee.get().getPassword(),
                new ArrayList<>());
    }


    public Optional<Employee> authenticate(AuthenticationRequest request) {
        Optional<Employee> employee = employeeRepository.findByEmailAndStatus(request.getEmail(), true);
        // If we can find the email return Optional.empty
        if (!employee.isPresent()) return Optional.empty();

        /**
         * BCryptPasswordEncoder help us to scrypt the password because
         * the application should not know user's password
         */
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Check if the encrypted password is the same as the password they sent
        if (!passwordEncoder.matches(request.getPassword(), employee.get().getPassword())) return Optional.empty();

        return employee;
    }


}
