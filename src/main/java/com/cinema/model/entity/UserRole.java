package com.cinema.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class describing user role entity
 *
 * @author Anton Spasskikh
 */
@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ROLE_NAME")
    private String roleName;

    public UserRole() {
    }

    public UserRole(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    /**
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * sets user role id
     *
     * @param id {@link #id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return {@link #roleName}
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * sets user role name
     *
     * @param roleName {@link #roleName}
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(id, userRole.id) &&
                Objects.equals(roleName, userRole.roleName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }
}
