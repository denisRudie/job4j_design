package ru.job4j.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Predicate;

public class SimpleMenu implements MenuAdvanced {

    private final List<MenuItem> roots;

    public SimpleMenu() {
        this.roots = new ArrayList<>();
    }

    @Override
    public void add(MenuItem menuItem) {
        roots.add(menuItem);
    }

    @Override
    public Optional<MenuItem> findItemByPredicate(Predicate<MenuItem> predicate) {
        for (MenuItem root : roots) {
            if (predicate.test(root)) {
                return Optional.of(root);
            }
            Queue<MenuItem> items = new LinkedList<>(root.getChildren());
            while (items.size() != 0) {
                MenuItem item = items.poll();
                if (predicate.test(item)) {
                    return Optional.of(item);
                }
                items.addAll(item.getChildren());
            }
        }
        return Optional.empty();
}

    @Override
    public void workWithMenu() {
        System.out.println(this);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                StringBuilder sb = new StringBuilder();
                String request = reader.readLine();

                if (request.equals("exit")) {
                    break;
                }

                Optional<MenuItem> response = findItemByPredicate(i -> i.getName().equals(request));
                if (response.isPresent()) {
                    sb.append("You have been choosen MenuItem: ")
                            .append(response.get().getName())
                            .append(System.lineSeparator())
                            .append("Deep level: ")
                            .append(response.get().getDepth());
                    System.out.println(sb.toString());
                } else {
                    System.out.println("Menu item doesn't exist");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (MenuItem root : roots) {
            List<MenuItem> rootChildren = root.getAllChildren();
            for (MenuItem rootChild : rootChildren) {
                sb.append("  ".repeat(Math.max(0, rootChild.getDepth())))
                        .append(rootChild.getName())
                        .append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MenuAdvanced simpleMenu = new SimpleMenu();

        SimpleMenuItem simpleMenuItem = new SimpleMenuItem("1");
        SimpleMenuItem simpleMenuItem2 = new SimpleMenuItem("2");
        SimpleMenuItem son1 = new SimpleMenuItem("1.1");
        SimpleMenuItem son11 = new SimpleMenuItem("1.1.1");
        SimpleMenuItem son2 = new SimpleMenuItem("1.2");
        SimpleMenuItem son21 = new SimpleMenuItem("1.2.1");
        SimpleMenuItem son22 = new SimpleMenuItem("1.2.2");
        simpleMenuItem.setChildren(son1);
        simpleMenuItem.setChildren(son2);
        son1.setChildren(son11);
        son2.setChildren(son21);
        son2.setChildren(son22);

        simpleMenu.add(simpleMenuItem);
        simpleMenu.add(simpleMenuItem2);

//        Optional<MenuItem> rsl = simpleMenu.findItemByPredicate(i -> i.getName().equals("1.1.1"));
//        if (rsl.isPresent()) {
//            System.out.println(rsl.get().getName());
//        } else {
//            System.out.println("Элемент отсутствует");
//        }
        simpleMenu.workWithMenu();

    }
}
