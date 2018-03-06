package com.mtl.po;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "actor")
public class Actor implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actorid;
    @Column(name = "idcard")
    @Pattern(regexp="(^(\\d{14}|\\d{17})(\\d|[xX])$)?" ,message="not valid idcard！")
    private String idcard;

    @Column(name = "firstname")
    @Length(min = 1,max = 30,message = "the firstname' length should not gt 30 or lt 1!")
    private String firstname;

    @Column(name = "lastname")
    @Length(min = 1,max = 30,message = "the lastname' length should not gt 30 or lt 1!")
    private String lastname;

    public Integer getActorid() {
        return actorid;
    }

    public void setActorid(Integer actorid) {
        this.actorid = actorid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
}
