package hotel.model.repository;

import hotel.model.entity.Branch;
import hotel.model.tools.BranchMapper;
import hotel.model.tools.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BranchRepository implements Repository<Branch, Integer> , AutoCloseable {
    private PreparedStatement preparedStatement;
    private final Connection connection;
    private final BranchMapper branchMapper = new BranchMapper();


    public BranchRepository() throws Exception {
        connection = ConnectionProvider.getProvider().getConnection();
    }





    @Override
    public void save(Branch branch) throws Exception {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO branches (address, room_list, employee_list) VALUES (?,?,?)"
        );


        preparedStatement.setString(1, branch.getAddress());
        preparedStatement.setObject(2, branch.getRoomList());
        preparedStatement.setObject(3, branch.getEmployeeList());
        preparedStatement.execute();
    }

    @Override
    public void edit(Branch branch) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE branches SET address=?, room_list=?, employee_list=? WHERE branch_id=?"
        );

        preparedStatement.setString(1, branch.getAddress());
        preparedStatement.setObject(2, branch.getRoomList());
        preparedStatement.setObject(3, branch.getEmployeeList());
        preparedStatement.setInt(4, branch.getBranchId());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM branches WHERE branch_id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public java.util.List<Branch> findAll() throws Exception {
        List<Branch> branchList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM branches"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Branch branch = branchMapper.branchMapper(resultSet);
            try (EmployeeRepository employeeRepository = new EmployeeRepository();
                 RoomRepository roomRepository = new RoomRepository()) {
                branch.setEmployeeList(employeeRepository.findByBranchId(branch.getBranchId()));
                branch.setRoomList(roomRepository.findByBranchId(branch.getBranchId()));
            }
            branchList.add(branch);
        }
        return branchList ;
    }

    @Override
    public Branch findById(Integer id) throws Exception {
        Branch branch = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM branches WHERE branch_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            branch = branchMapper.branchMapper(resultSet);
            try (EmployeeRepository employeeRepository = new EmployeeRepository();
                 RoomRepository roomRepository = new RoomRepository()) {
                branch.setEmployeeList(employeeRepository.findByBranchId(branch.getBranchId()));
                branch.setRoomList(roomRepository.findByBranchId(branch.getBranchId()));
            }
        }
        return branch;

    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
