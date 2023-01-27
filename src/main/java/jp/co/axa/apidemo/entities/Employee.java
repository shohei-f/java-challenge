package jp.co.axa.apidemo.entities;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

/**
 * Employee Entity
 * @author shohei
 */
@Data
@ToString
@Entity
@Table(name="EMPLOYEE")
public class Employee {

    // table field name
    public static final String FN_ID               = "ID";
    public static final String FN_EMPLOYEE_NAME   = "EMPLOYEE_NAME";
    public static final String FN_EMPLOYEE_SALARY = "EMPLOYEE_SALARY";
    public static final String FN_DEPARTMENT      = "DEPARTMENT";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name=FN_ID)
    private Long id;

    @Column(name=FN_EMPLOYEE_NAME)
    @NotBlank
    @Size(max=50)
    private String name;

    @Column(name=FN_EMPLOYEE_SALARY)
    @NotNull
    @DecimalMax(value="999999999")
    private BigInteger salary;

    @Column(name=FN_DEPARTMENT)
    @NotBlank
    @Size(max=50)
    private String department;

}
