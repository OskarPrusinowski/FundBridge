package pl.hackyeah.hackyeah24_backend.security.service;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.hackyeah.hackyeah24_backend.models.Organization;

import java.io.Serial;
import java.util.Collection;
import java.util.Objects;

@Data
public class OrganizationDetailsImpl implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;


    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public OrganizationDetailsImpl(int id,  String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public static OrganizationDetailsImpl build(Organization organization) {

        return new OrganizationDetailsImpl(
                organization.getId(),
                organization.getEmail(),
                organization.getPassword());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        OrganizationDetailsImpl organization = (OrganizationDetailsImpl) o;
        return Objects.equals(id, organization.id);
    }
}
