package com.example.Systems1221.TestTask.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    Integer id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "email", nullable = false)
    String email;

    @Column(name = "age", nullable = false)
    Integer age;

    @Column(name = "weight", nullable = false)
    Double weight;

    @Column(name = "height", nullable = false)
    Double height;

    @Column(name = "target", nullable = false)
    @Enumerated(EnumType.STRING)
    UserTarget userTarget;

    @Column(name = "basic_calorie_requirements")
    Double basicCalorieRequirements;

    public UserEntity() {
    }

    public UserEntity(String name, String email, Integer age, Double weight, Double height,
                      UserTarget userTarget, Double basicCalorieRequirements) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.userTarget = userTarget;
        this.basicCalorieRequirements = basicCalorieRequirements;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public UserTarget getTarget() {
        return userTarget;
    }

    public void setTarget(UserTarget userTarget) {
        this.userTarget = userTarget;
    }

    public Double getBasicCalorieRequirements() {
        return basicCalorieRequirements;
    }

    public void setBasicCalorieRequirements(Double basicCalorieRequirements) {
        this.basicCalorieRequirements = basicCalorieRequirements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(age, that.age) && Objects.equals(weight, that.weight) && Objects.equals(height, that.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age, weight, height);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }
}
