package ru.job4j.analizator;

import java.util.*;

public class Analize {

    /**
     * Метод сравнивает 2 листа на предмет добавленных, удаленных и измененных элементов.
     *
     * @param previous предыдущий список пользователей.
     * @param current  текущитй список пользователей.
     * @return отчет о добавленных, удаленных и измененных пользователях.
     */
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, String> map = new HashMap<>();

        for (User prev : previous) {
            map.put(prev.getId(), prev.getName());
        }

        for (User curr : current) {
            if (map.containsKey(curr.getId())) {
                if (!map.get(curr.getId()).equals(curr.getName())) {
                    info.incChanged();
                }
                map.remove(curr.getId());
            } else {
                info.incAdded();
            }
        }

        info.setDeleted(map.size());

        return info;
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static class Info {

        private int added;
        private int changed;
        private int deleted;

        public Info() {
            this.added = 0;
            this.changed = 0;
            this.deleted = 0;
        }

        public void incAdded() {
            added++;
        }

        public void incChanged() {
            changed++;
        }

        public void setDeleted(int value) {
            deleted = value;
        }

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }
}
