package com.eazybytes.eazystore.security;

import com.eazybytes.eazystore.entity.Customer;
import com.eazybytes.eazystore.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class EazyStoreUsernamePwdAuthenticationProvider implements AuthenticationProvider {
    private  final CustomerRepository customerRepository;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
     String username=authentication.getName();
     String pwd=authentication.getCredentials().toString();
        Customer customer=customerRepository.findByEmail(username).orElseThrow(

                ()-> new UsernameNotFoundException("user details  not found for the user:"+ username)
        );
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
