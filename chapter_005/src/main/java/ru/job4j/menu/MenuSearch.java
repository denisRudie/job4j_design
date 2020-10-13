package ru.job4j.menu;

import java.util.Optional;
import java.util.function.Predicate;

public interface MenuSearch {

    Optional<MenuItem> findItemByPredicate(Predicate<MenuItem> predicate);
}
