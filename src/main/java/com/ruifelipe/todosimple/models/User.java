package com.ruifelipe.todosimple.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = User.TABLE_STRING)
public class User {

    public interface CreateUser {
    }

    public interface UpdateUser {
    }

    public static final String TABLE_STRING = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull(groups = CreateUser.class)
    @NotEmpty(groups = CreateUser.class)
    @Size(groups = CreateUser.class, min = 2, max = 100)
    private String userName;

    @JsonProperty (access = Access.WRITE_ONLY)
    @Column(name = "password", length = 60, nullable = false)
    @NotNull(groups = { CreateUser.class, UpdateUser.class }) // Validando a senha, verificar se a senha não ta null
    @NotEmpty(groups = { CreateUser.class, UpdateUser.class }) // Validando senha, verificar se a senha não ta vazia
    @Size(groups = { CreateUser.class, UpdateUser.class }, min = 8, max = 60) // Validando senha, verificar se a senha
                                                                              // ta no valor min ou max
    private String password;

    // private List <Task> task = new ArrayList<task>();

    public User() {
    }

    public User(Long Id, String userName, String password) {
        this.Id = Id;
        this.userName = userName;
        this.password = password;
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
        if (this.Id == null)
            if (other.Id != null)
                return false;
            else if (!this.Id.equals(other.Id))
                return false;
        return Objects.equals(this.Id, other.Id) && Objects.equals(this.userName, other.userName)
                && Objects.equals(this.password, other.password);

    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.Id == null) ? 0 : this.Id.hashCode());
        return result;
    }
}
