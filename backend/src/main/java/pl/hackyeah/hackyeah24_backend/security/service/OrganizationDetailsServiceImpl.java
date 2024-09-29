package pl.hackyeah.hackyeah24_backend.security.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.hackyeah.hackyeah24_backend.models.Organization;
import pl.hackyeah.hackyeah24_backend.repositories.OrganizationRepository;

@Service
public class OrganizationDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return loadUserByEmail(email);
    }

    @Transactional
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        Organization organization = null;
        try {
            organization = (Organization) organizationRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return OrganizationDetailsImpl.build(organization);
    }

}
