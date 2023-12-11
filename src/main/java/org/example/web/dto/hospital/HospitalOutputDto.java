package org.example.web.dto.hospital;

import java.util.Objects;

public class HospitalOutputDto {
    private final int id;

    private final String name;

    private final String address;

    public HospitalOutputDto(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HospitalOutputDto outputDto = (HospitalOutputDto) o;
        return id == outputDto.id && Objects.equals(name, outputDto.name) && Objects.equals(address, outputDto.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }

    @Override
    public String toString() {
        return "HospitalOutputDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
