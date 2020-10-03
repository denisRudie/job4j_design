package ru.job4j.post;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private List<User> users;

    public Post() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Метод поочередно сравнивает каждый email каждого юзера с email'ами остальных юзеров.
     * Если у второго юзера найден email, который есть среди списка email первого юзера - список
     * email второго юзера объединяется со списком email первого юзера. Остаются только
     * уникальные email. После второй юзер удаляется из списка.
     *
     * @return Схлопнутый список юзеров.
     */
    public List<User> union() {
        User left;
        User right;
        boolean complete = false;

        while (!complete) {
            complete = true;
            for (int i = 0; i < users.size(); i++) {
                left = users.get(i);
                for (int j = i + 1; j < users.size(); j++) {
                    right = users.get(j);
                    for (String s : right.getEmail()) {
                        if (left.getEmail().contains(s)) {
                            left.getEmail().addAll(right.getEmail());
                            users.remove(right);
                            complete = false;
                            break;
                        }
                    }
                }
            }
        }
        return users;
    }
}
