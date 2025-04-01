package com.example.Systems1221.TestTask.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "meals")
public class MealEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    Integer id;

    @Column(name = "user_email", nullable = false)
    String userEmail;

    @Column(name = "name", nullable = false)
    String name;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name="meals_dishes",
            joinColumns=  @JoinColumn(name="meal_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="dishes_id", referencedColumnName="id") )
    @Column(name = "list_of_dishes", nullable = false)
    List<DishEntity> listOfDishes = new ArrayList<>();

    @Column(name = "meal_time", nullable = false)
    LocalDateTime mealTime;

    public MealEntity(String userEmail, String name, List<DishEntity> listOfDishes, LocalDateTime mealTime) {
        this.userEmail = userEmail;
        this.name = name;
        this.listOfDishes = listOfDishes;
        this.mealTime = mealTime;
    }

    public MealEntity() {
    }

    public void addDishToListOfDishes(DishEntity dishEntity){
        this.listOfDishes.add(dishEntity);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DishEntity> getListOfDishes() {
        return listOfDishes;
    }

    public void setListOfDishes(List<DishEntity> listOfDishes) {
        this.listOfDishes = listOfDishes;
    }

    public LocalDateTime getMealTime() {
        return mealTime;
    }

    public void setMealTime(LocalDateTime mealTime) {
        this.mealTime = mealTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealEntity that = (MealEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(userEmail, that.userEmail) && Objects.equals(name, that.name) && Objects.equals(listOfDishes, that.listOfDishes) && Objects.equals(mealTime, that.mealTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userEmail, name, listOfDishes, mealTime);
    }

    @Override
    public String toString() {
        return "MealEntity{" +
                "id=" + id +
                ", userEmail='" + userEmail + '\'' +
                ", name='" + name + '\'' +
                ", listOfDishes=" + listOfDishes +
                ", mealTime=" + mealTime +
                '}';
    }
}
