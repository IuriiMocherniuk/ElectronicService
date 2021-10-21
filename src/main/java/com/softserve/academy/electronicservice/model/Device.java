package com.softserve.academy.electronicservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;
    private String name;
    private long code;

    //    @OneToMany   //TODO CHECK
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @Column(name = "owner_id")
    private long ownerId;


    @ManyToOne   //TODO CHECK
    @JoinColumn(name = "owner_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Owner owner;

    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "create_date")
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    public Device(String type, String name, long code, long ownerId, String status) {
        this.type = type;
        this.name = name;
        this.code = code;
        this.ownerId = ownerId;
        this.status = status;
        this.createdDate = LocalDateTime.now();
    }

    public Device(long id, String type, String name, long code, long ownerId, String status) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.code = code;
        this.ownerId = ownerId;
        this.status = status;
    }

    public Device() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String mane) {
        this.name = mane;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return id == device.id
                && code == device.code
                && ownerId == device.ownerId
                && Objects.equals(type, device.type)
                && Objects.equals(name, device.name)
                && Objects.equals(status, device.status)
                && Objects.equals(createdDate.truncatedTo(ChronoUnit.SECONDS), device.createdDate.truncatedTo(ChronoUnit.SECONDS));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, code, ownerId, status, createdDate);
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", ownerId=" + ownerId +
                ", owner=" + owner +
                ", status='" + status + '\'' +
                ", createdDate=" + createdDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
