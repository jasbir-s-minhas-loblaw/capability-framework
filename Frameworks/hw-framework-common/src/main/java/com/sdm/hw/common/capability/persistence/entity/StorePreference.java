package com.sdm.hw.common.capability.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This JPA class is an entity object mapping to STORE_PREFRENCE table.
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2017-11-20
 *
 */

@Entity
@Table(name="store_preference", schema="HW")
public class StorePreference {

    @Id
    @Column(name = "CDSP")
    private String cdsp;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "DESCRIPTION")
    private String description;

    public StorePreference() {
    }

    public StorePreference(String cdsp, String value, String description) {
        this.cdsp = cdsp;
        this.value = value;
        this.description = description;
    }

    public String getCdsp() {
        return cdsp;
    }

    public void setCdsp(String cdsp) {
        this.cdsp = cdsp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "StorePreference{" +
                "cdsp='" + cdsp + '\'' +
                ", value='" + value + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
