package ru.job4j.model;

import javax.persistence.*;

@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int experience;
    private float salary;
    @OneToOne(fetch = FetchType.LAZY)
    private VacancyBank vacancyBank;

    public static Candidate of(String name, int experience, float salary, VacancyBank bank) {
        Candidate cand = new Candidate();
        cand.name = name;
        cand.experience = experience;
        cand.salary = salary;
        cand.vacancyBank = bank;
        return cand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public VacancyBank getVacancyBank() {
        return vacancyBank;
    }

    public void setVacancyBank(VacancyBank vacancyBank) {
        this.vacancyBank = vacancyBank;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", experience=" + experience +
                ", salary=" + salary +
                ", vacancyBank=" + vacancyBank +
                '}';
    }
}
