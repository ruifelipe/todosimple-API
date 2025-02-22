package com.ruifelipe.todosimple.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = Task.TABLE_STRING)
public class Task {

    public static final String TABLE_STRING = "task";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @Column(name = "descricao")
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    private String descricao;

    public Task(Long Id, String descricao, User user) {
        this.Id = Id;
        this.user = user;
        this.descricao = descricao;
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Task)) {
            return false;
        }

        Task other = (Task) obj;
        if (this.Id == null)
            if (other.Id != null)
                return false;
            else if (!this.Id.equals(other.Id))
                return false;
        return Objects.equals(this.Id, other.Id) && Objects.equals(this.user, other.user)
                && Objects.equals(this.descricao, other.descricao);

    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.Id == null) ? 0 : this.Id.hashCode());
        return result;
    }

}
