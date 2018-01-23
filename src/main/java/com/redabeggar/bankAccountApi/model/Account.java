package com.redabeggar.bankAccountApi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {

    @Id
    @NonNull
    private Long accountNumber;
    @NonNull
    private double balance;
    @CreationTimestamp
    private Date creationDate;
    @UpdateTimestamp
    private Date updateDate;
    @Getter(onMethod = @__( @JsonIgnore ))
    @Setter
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Operation> operations;


}
