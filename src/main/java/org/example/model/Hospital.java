package org.example.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "hospitals")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String address;

    @CreationTimestamp
    private LocalDateTime estimated;

    public Hospital() {
    }

    public Hospital(int id, String name, String address, LocalDateTime estimated) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.estimated = estimated;
    }

    public Hospital(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getEstimated() {
        return estimated;
    }

    public void setEstimated(LocalDateTime estimated) {
        this.estimated = estimated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hospital)) return false;
        Hospital hospital = (Hospital) o;
        return id == hospital.id && Objects.equals(name, hospital.name) && Objects.equals(address, hospital.address) && Objects.equals(estimated, hospital.estimated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, estimated);
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", estimated=" + estimated +
                '}';
    }
}
