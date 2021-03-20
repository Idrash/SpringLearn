package org.idrash.persistence.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "authority")
public class Authority{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String authority;

    public Authority() {}

    public Authority(String authority) {
        this.authority = authority;
    }


    public String getAuthority() {
        return authority;
    }
    // getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority1 = (Authority) o;
        return id.equals(authority1.id);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}