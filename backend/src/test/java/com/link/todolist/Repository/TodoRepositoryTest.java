package com.link.todolist.Repository;

import com.link.todolist.Model.Todo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class TodoRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(TodoRepositoryTest.class);

    @Autowired
    TodoRepository repository;

    @Test
    public void testFindAll() {
        int i = 1;
        for (Todo t : repository.findAll()) {
            assertEquals(i, t.getId());
            assertEquals("测试" + i, t.getContent());
            i++;
        }
    }

    @Test
    public void testFindById() {
        logger.info("test start");
        Long id = 1L;
        assertEquals(false, repository.findById(1L).isEmpty());
        Todo todoModel = repository.findById(id).get();

        assertEquals("测试1", todoModel.getContent());

        id = 3L;
        boolean empty = repository.findById(id).isEmpty();
        assertEquals(true, empty);
    }



    @Test
    public void testSave() {

        Optional<Todo> t = repository.findById(3L);

        assertEquals(true, t.isEmpty());

        Todo s = new Todo();

        s.setContent("测试3");
        s.setDate(new Date());

        s = repository.save(s);

        logger.info(s.toString());

        assertEquals(3, s.getId());

        t = repository.findById(3L);

        assertEquals(false, t.isEmpty());
        assertEquals("测试3", t.get().getContent());
    }

    @Test
    public void testdeleteById() {
        Optional<Todo> t = repository.findById(2L);

        assertEquals(false, t.isEmpty());

        repository.deleteById(2L);

        t = repository.findById(2L);

        assertEquals(true, t.isEmpty());

    }

}