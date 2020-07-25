package ru.job4j.generics;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class RoleStoreTest {

    @Test
    public void whenAddRoleCanGetItBackById() {
        RoleStore rl = new RoleStore();
        rl.add(new Role("1"));
        assertThat(rl.findById("1").getId(), is("1"));
    }

    @Test (expected = NoSuchElementException.class)
    public void haveNSEExceptionWhenTryToFindReplacedRole() {
        RoleStore rl = new RoleStore();
        rl.add(new Role("1"));
        rl.replace("1", new Role("10"));
        rl.findById("1");
    }

    @Test
    public void afterReplacementCanFindNewObjectById() {
        RoleStore rl = new RoleStore();
        rl.add(new Role("1"));
        rl.replace("1", new Role("10"));
        assertThat(rl.findById("10").getId(), is("10"));
    }

    @Test (expected = NoSuchElementException.class)
    public void haveNSEExceptionWhenTryToFindDeletedRole() {
        RoleStore rl = new RoleStore();
        rl.add(new Role("1"));
        rl.delete("1");
        rl.findById("1");
    }

}