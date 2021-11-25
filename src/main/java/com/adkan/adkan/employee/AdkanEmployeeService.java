package com.adkan.adkan.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

}
