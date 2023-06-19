package com.CollageMSM.CollageMSM.Service;

import com.CollageMSM.CollageMSM.Dao.RegistrationRepo;
import com.CollageMSM.CollageMSM.Entity.Registration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final RegistrationRepo registrationRepo;

    public CustomUserDetailService(RegistrationRepo registrationRepo) {
        this.registrationRepo = registrationRepo;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        Registration user = registrationRepo.getByEmail(email);

        if (user == null) {

            throw new UsernameNotFoundException("Invalid User");
        }
        else
        {

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

            if(user.getAdmin())
            {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
            else
            {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }

//            return new User(user.getEmail(), user.getPassword(), grantedAuthorities);
            return new org
                    .springframework
                    .security
                    .core
                    .userdetails
                    .User(user.getEmail(), user.getPassword(), grantedAuthorities);

        }
    }
}