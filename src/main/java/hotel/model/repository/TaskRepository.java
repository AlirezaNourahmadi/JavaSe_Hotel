package hotel.model.repository;

import hotel.model.entity.Task;
import hotel.model.tools.ConnectionProvider;
import hotel.model.tools.TaskMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements Repository<Task, Integer>, AutoCloseable {
    private PreparedStatement preparedStatement;
    private final Connection connection;
    private final TaskMapper taskMapper = new TaskMapper();

    public TaskRepository() throws Exception {
        connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void save(Task task) throws Exception {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO tasks (task_id, description, due_date, assigned_employee_id, status) VALUES (?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, task.getTaskId());
        preparedStatement.setString(2, task.getDescription());
        if (task.getDueDate() != null)
            preparedStatement.setDate(3, java.sql.Date.valueOf(task.getDueDate()));
        else
            preparedStatement.setDate(3, null);
        preparedStatement.setInt(4, task.getAssignedEmployee().getId());
        preparedStatement.setString(5, task.getStatus().name());
        preparedStatement.execute();
    }

    @Override
    public void edit(Task task) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE tasks SET description=?, due_date=?, assigned_employee_id=?, status=? WHERE task_id=?"
        );
        preparedStatement.setString(1, task.getDescription());
        if (task.getDueDate() != null)
            preparedStatement.setDate(2, java.sql.Date.valueOf(task.getDueDate()));
        else
            preparedStatement.setDate(2, null);
        preparedStatement.setInt(3, task.getAssignedEmployee().getId());
        preparedStatement.setString(4, task.getStatus().name());
        preparedStatement.setInt(5, task.getTaskId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM tasks WHERE task_id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Task> findAll() throws Exception {
        List<Task> taskList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM tasks");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            taskList.add(taskMapper.taskMapper(resultSet));
        }
        return taskList;
    }

    @Override
    public Task findById(Integer id) throws Exception {
        Task task = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM tasks WHERE task_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            task = taskMapper.taskMapper(resultSet);
        }
        return task;
    }

    @Override
    public void close() throws Exception {
        if (preparedStatement != null) preparedStatement.close();
        connection.close();
    }
}