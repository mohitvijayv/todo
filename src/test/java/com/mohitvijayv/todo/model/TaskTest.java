package com.mohitvijayv.todo.model;

import com.mohitvijayv.todo.model.Task;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit4.class)
class TaskTest {

    @Test
    public void testTask(){
//        Task task = new Task(1l, "xyz", "qwerty",Timestamp.valueOf("1970-01-01 08:55:45.666"),true );
//        assertNotNull(task);

        Task task = new Task();
        task.setCreator("abc");
        task.setTaskId(1l);
        task.setTaskName("xyz");
        task.setDescription("qwerty");
        task.setTaskDueDate(Timestamp.valueOf("1970-01-01 08:55:45.666"));
        task.setLastModified(Timestamp.valueOf("1970-01-01 08:55:45.666"));
        task.setStatusId(true);
        task.setUserId(1l);
        task.setUsername("abcdef");


        assertEquals( 1l, task.getTaskId());
        assertEquals( "abc", task.getCreator());
        assertEquals("xyz", task.getTaskName());
        assertEquals( "qwerty", task.getDescription());
        assertEquals( Timestamp.valueOf("1970-01-01 08:55:45.666"), task.getTaskDueDate());
        assertEquals(Timestamp.valueOf("1970-01-01 08:55:45.666"), task.getLastModified());
        assertEquals(true, task.getStatusId());
        assertEquals(1l, task.getUserId());
        assertEquals("abcdef", task.getUsername());
    }

}