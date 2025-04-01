package com.example.Systems1221.TestTask.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "dishes")
public class DishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    Integer id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "caloriesPerServing", nullable = false)
    Double caloriesPerServing;

    @Column(name = "proteins", nullable = false)
    Double proteins;

    @Column(name = "fats", nullable = false)
    Double fats;

    @Column(name = "carbohydrates", nullable = false)
    Double carbohydrates;

    public DishEntity() {
    }

    public DishEntity(String name, Double caloriesPerServing, Double proteins, Double fats, Double carbohydrates) {
        this.name = name;
        this.caloriesPerServing = caloriesPerServing;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCaloriesPerServing() {
        return caloriesPerServing;
    }

    public void setCaloriesPerServing(Double caloriesPerServing) {
        this.caloriesPerServing = caloriesPerServing;
    }

    public Double getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishEntity that = (DishEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name)
                && Objects.equals(caloriesPerServing, that.caloriesPerServing)
                && Objects.equals(proteins, that.proteins) && Objects.equals(fats, that.fats)
                && Objects.equals(carbohydrates, that.carbohydrates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, caloriesPerServing, proteins, fats, carbohydrates);
    }

    @Override
    public String toString() {
        return "DishEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", caloriesPerServing=" + caloriesPerServing +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                '}';
    }
}
