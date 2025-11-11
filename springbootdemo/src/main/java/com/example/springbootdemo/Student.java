package com.example.springbootdemo;

import jakarta.persistence.*;                  // JPA annotations: @Entity, @Id, etc.
import jakarta.validation.constraints.*;       // Validation annotations: @NotBlank, @Email
import java.time.LocalDateTime;                // For createdAt and updatedAt

// This class maps to a database table named "students"
@Entity
@Table(name = "students")
public class Student {

    // Primary key with auto-generated value (like IDENTITY in SQL)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name column - cannot be null or blank
    @Column(nullable = false)
    @NotBlank(message = "Name is required")
    private String name;

    // Email column - must be unique and properly formatted
    @Column(nullable = false, unique = true)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    // Course column - optional
    private String course;

    // Auto timestamps
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // Default constructor (required by JPA)
    public Student() {}

    // Parameterized constructor (for convenience)
    public Student(String name, String email, String course) {
        this.name = name;
        this.email = email;
        this.course = course;
    }

    // Runs before record is first saved
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    // Runs before record is updated
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
