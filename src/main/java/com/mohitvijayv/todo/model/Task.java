package com.mohitvijayv.todo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="tasks")
@ApiModel
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Task's ID", position = 1)
    private Long taskId;
    @ApiModelProperty(notes = "User ID with which task is associated", position = 2)
    private Long userId;
    @ApiModelProperty(notes = "Task's name", position = 3)
    private String taskName;
    @ApiModelProperty(notes = "Task's description", position = 4)
    private String description;
    @ApiModelProperty(notes = "Task's due date", position = 5)
    private Timestamp taskDueDate;
    @ApiModelProperty(notes = "Task's status ID", position = 6)
    private boolean statusId;

    @ApiModelProperty(notes = "Username with which task is associated", position = 7)
    private String username;

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @ApiModelProperty(notes = "Task's last modified date", position = 8)
    private Timestamp lastModified;

    @ApiModelProperty(notes = "User who created task", position = 9)
    private String creator;

    public Task(){}

    public Task(Long userId, String taskName, String description, Timestamp taskDueDate, boolean statusId) {
        this.userId = userId;
        this.taskName = taskName;
        this.description = description;
        this.taskDueDate = taskDueDate;
        this.statusId = statusId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(Timestamp taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public boolean getStatusId() {
        return statusId;
    }

    public void setStatusId(boolean statusId) {
        this.statusId = statusId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
