/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Bruno
 */
public class AsanaCSV {
    private String taskId;
    private String createdAt;
    private String completedAt;
    private String lastModified;
    private String name;
    private String assignee;
    private String assigneeEmail;
    private String startDate;
    private String dueDate;
    private String tags;
    private String notes;
    private String projects;
    private String parentTask;

    public AsanaCSV() {
    }

    public AsanaCSV(String taskId, String createdAt, String completedAt, String lastModified, String name, String assignee, String assigneeEmail, String startDate, String dueDate, String tags, String notes, String projects, String parentTask) {
        this.taskId = taskId;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
        this.lastModified = lastModified;
        this.name = name;
        this.assignee = assignee;
        this.assigneeEmail = assigneeEmail;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.tags = tags;
        this.notes = notes;
        this.projects = projects;
        this.parentTask = parentTask;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getAssigneeEmail() {
        return assigneeEmail;
    }

    public void setAssigneeEmail(String assigneeEmail) {
        this.assigneeEmail = assigneeEmail;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getParentTask() {
        return parentTask;
    }

    public void setParentTask(String parentTask) {
        this.parentTask = parentTask;
    }
    
    


}
