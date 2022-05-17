package ru.guwfa.auction.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String Username;
    private String password;
    private Boolean active;
    private Long balance;
    private String email;
    private String activationCode;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "User_role", joinColumns = @JoinColumn(name = "User_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(
            name = "subscription",
            joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "LotInAu_id")
    )
    private Set<LotInAu> LotInAus = new HashSet<>();


    public User() {
    }

    public User(String Username, String password, Boolean active, Set<Role> roles) {
        this.Username = Username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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
        return isActive();
    }

    public boolean isAdmin(){
        return roles.contains(Role.ADMIN);
    }

    public boolean isUser(){
        return roles.contains(Role.User);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Set<LotInAu> getLotInAus() {
        return LotInAus;
    }

    private void setLotInAus(Set<LotInAu> LotInAus) {
        this.LotInAus = LotInAus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User User = (User) o;
        return id.equals(User.id) &&
                Username.equals(User.Username) &&
                email.equals(User.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Username, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Username='" + Username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
