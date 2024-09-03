package com.eazybytes.accounts.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class) // Steps to get values to Audit columns - Step 3
public class BaseEntity {

    // Steps to get values to Audit column - Step 1 - Use relevent annotations to
    // get values to audit columns. CreatedAt and UpdatedAt values can be obtained
    // from servers, inorder to
    // get values to CreatedBy and UpdatedBy we need to use implementation of
    // "AuditorAware" that is step 2.
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;
    // Since we have used CreatedBy and CreatedDate annotations here, no need to
    // manually set them in AccountServiceImpl. Removing that line of code it
    // step-5.
}
