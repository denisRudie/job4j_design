package ru.job4j.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vacancy_bank")
public class VacancyBank {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Vacancy> vacancies = new ArrayList<>();

    public static VacancyBank of(String name) {
        VacancyBank vacancyBank = new VacancyBank();
        vacancyBank.setName(name);
        return vacancyBank;
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

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void addVacancy(Vacancy vac) {
        this.vacancies.add(vac);
    }

    @Override
    public String toString() {
        return "VacancyBank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vacancies=" + vacancies +
                '}';
    }
}
