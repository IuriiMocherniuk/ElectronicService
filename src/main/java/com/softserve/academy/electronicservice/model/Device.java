package com.softserve.academy.electronicservice.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table (name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;
    private String mane;
    private Long code;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "owner_id")
    private Owner owner;

    private String status;

    @Column(name = "create_date")
    private LocalDateTime createdDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(type, device.type) && Objects.equals(mane, device.mane) && Objects.equals(code, device.code) && Objects.equals(owner, device.owner) && Objects.equals(status, device.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, mane, code, owner, status);
    }
}
