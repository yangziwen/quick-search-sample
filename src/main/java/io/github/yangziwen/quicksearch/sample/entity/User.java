package io.github.yangziwen.quicksearch.sample.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.github.yangziwen.quicksearch.sample.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_index")
public class User {

    @Id
    @Column
    @GeneratedValue
    private String id;

    @Column
    private String name;

    @Column
    private Gender gender;

    @Column
    private String city;

    @Column
    private Integer age;

    @Column
    private Date createTime;

    @Transient
    private BigDecimal avgAge;

}
