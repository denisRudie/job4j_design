package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

/**
 * Поиск максимального и минимального элемента списка переданному критерию java.util.Comparator.
 */
public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return getMaxMin(value, comparator, MaxMinOption.MAX);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return getMaxMin(value, comparator, MaxMinOption.MIN);
    }

    private <T> T getMaxMin(List<T> value, Comparator<T> comparator, MaxMinOption option) {
        T rsl = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            T compValue = value.get(i);
            if (option.getResultByOption(
                    comparator.compare(compValue, rsl))) {
                rsl = value.get(i);
            }
        }
        return rsl;
    }

    private enum MaxMinOption {
        MAX {
            @Override
            public boolean getResultByOption(int compRsl) {
                return compRsl > 0;
            }
        },
        MIN {
            @Override
            public boolean getResultByOption(int compRsl) {
                return compRsl < 0;
            }
        };

        public abstract boolean getResultByOption(int compRsl);
    }
}
