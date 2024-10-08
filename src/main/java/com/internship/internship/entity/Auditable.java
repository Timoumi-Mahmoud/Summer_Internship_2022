package com.internship.internship.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;
import static javax.persistence.TemporalType.TIMESTAMP;
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    protected U createdBy;
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    @Temporal(TIMESTAMP)
    protected Date createdDate;
    @LastModifiedBy
    protected U lastModifiedBy;
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date lastModifiedDate;
    public U getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(U createdBy) {
        this.createdBy = createdBy;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public U getLastModifiedBy() {
        return lastModifiedBy;
    }
    public void setLastModifiedBy(U lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
    public Date getLastModifiedDate() {
        return lastModifiedDate ;
    }
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
