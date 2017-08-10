package com.example.demo.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by georgi.peychev on 8/8/17.
 */
@Entity
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String status;
  private LocalDateTime lockDate;
  private String lock;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String toString(){
    return name==null?null:name.toString();
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public LocalDateTime getLockDate() {
    return lockDate;
  }

  public void setLockDate(LocalDateTime lockDate) {
    this.lockDate = lockDate;
  }

  public String getLock() {
    return lock;
  }

  public void setLock(String lock) {
    this.lock = lock;
  }
}
