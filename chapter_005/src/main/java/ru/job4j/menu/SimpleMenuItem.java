package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;

public class SimpleMenuItem implements MenuItem {

    private String name;
    private List<MenuItem> children = new ArrayList<>();
    private int depth = 0;

    public SimpleMenuItem(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<MenuItem> getChildren() {
        return children;
    }

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public void setChildren(MenuItem child) {
        child.setDepth(this.getDepth() + 1);
        this.children.add(child);
    }

    @Override
    public List<MenuItem> getAllChildren() {
        List<MenuItem> rsl = new ArrayList<>();
        addAllChildren(this, rsl);
        return rsl;
    }

    private void addAllChildren(MenuItem item, List<MenuItem> list) {
        list.add(item);
        if (!item.getChildren().isEmpty()) {
            for (MenuItem child : item.getChildren()) {
                addAllChildren(child, list);
            }
        }
    }
}
