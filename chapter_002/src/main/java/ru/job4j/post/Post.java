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
                    if (left.equals(right)) {
                        left.getEmail().addAll(right.getEmail());
                        users.remove(right);
                        complete = false;
                        break;
                    }
                }
            }
        }
        return users;
    }

    /**
     * Метод сравнивает 2 соседних элемента листа users.
     * Если их нельзя схлопнуть - правый элемент сдвигается на одно значение вправо.
     * Если можно схлопнуть - левый элемент сдвигается на одно значение вправо.
     * Сравнения выполняются до сих пор, пока весь лист не будет пройден без модификаций.
     *
     * @return Схлопнутый список юзеров.
     */
    public List<User> bubbleUnion() {

        boolean complete = false;

        while (!complete) {
            User left;
            User right;
            int cursor = 0;
            int delta = 1;
            complete = true;

            while (cursor < users.size() - delta) {
                left = users.get(cursor);
                right = users.get(cursor + delta);
                if (left.equals(right)) {
                    left.getEmail().addAll(right.getEmail());
                    users.remove(right);
                    complete = false;
                    cursor++;
                    delta = 1;
                } else {
                    delta++;
                }
            }
        }

        return users;
    }
}
