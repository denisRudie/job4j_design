package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class RoleStoreTest {

    @Test
    public void whenAddRoleCanGetItBackById() {
        RoleStore rl = new RoleStore();
        rl.add(new Role("1"));
        assertThat(rl.findById("1").getId(), is("1"));
    }

    @Test
    public void haveNullValueWhenTryToFindReplacedRole() {
        RoleStore rl = new RoleStore();
        rl.add(new Role("1"));
        rl.replace("1", new Role("10"));
        assertThat(rl.findById("1"), is(nullValue()));
    }

    @Test
    public void afterReplacementCanFindNewObjectById() {
        RoleStore rl = new RoleStore();
        rl.add(new Role("1"));
        rl.replace("1", new Role("10"));
        assertThat(rl.findById("10").getId(), is("10"));
    }

    @Test
    public void haveNSEExceptionWhenTryToFindDeletedRole() {
        RoleStore rl = new RoleStore();
        rl.add(new Role("1"));
        rl.delete("1");
        assertThat(rl.findById("1"), is(nullValue()));
    }

    @Test
    public void getFalseWithReplaceMethodWhenPutIncorrectId() {
        RoleStore rl = new RoleStore();
        assertThat(rl.replace("1", new Role("10")), is(false));
    }
}