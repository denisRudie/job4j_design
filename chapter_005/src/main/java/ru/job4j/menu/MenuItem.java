package ru.job4j.menu;

import java.util.List;

public interface MenuItem {

    String getName();

    List<MenuItem> getChildren();

    int getDepth();

    void setDepth(int depth);

    void setChildren(MenuItem child);

    List<MenuItem> getAllChildren();
}
