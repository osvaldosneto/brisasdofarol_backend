package com.osn.locadora.domain;

import com.osn.locadora.domain.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="jardim")
public class Jardim implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer status;

    private LocalDate data;

    public Jardim(){
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return Status.toEnum(status);
    }



    public void setStatus(Status status) {
        this.status = status.getCod();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jardim jardim = (Jardim) o;
        return Objects.equals(id, jardim.id) &&
                Objects.equals(status, jardim.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }
}
