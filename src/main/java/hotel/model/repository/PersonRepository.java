package hotel.model.repository;

import hotel.model.entity.Person;
import hotel.model.tools.ConnectionProvider;
import hotel.model.tools.PersonMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;


public class PersonRepository implements Repository<Person,Integer>,AutoCloseable {
    private PreparedStatement preparedStatement;
    private final Connection connection;
    private final PersonMapper personMapper = new PersonMapper();


    public PersonRepository() throws Exception {
        connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void save(Person person) throws Exception {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO persons (first_name,last_name,email,user_name,password,phone,address,birth_date)"
                        +
                        " VALUES (?,?,?,?,?,?,?,?)"

        );
        preparedStatement.setString(1, person.getFirstName());
        preparedStatement.setString(2, person.getLastName());
        preparedStatement.setString(3, person.getEmail());
        preparedStatement.setString(4, person.getUserName());
        preparedStatement.setString(5, person.getPassword());
        preparedStatement.setString(6, person.getPhone());
        preparedStatement.setString(7, person.getAddress());
        preparedStatement.setDate(8, Date.valueOf(person.getBirthDate()));
        preparedStatement.execute();


    }

    @Override
    public void edit(Person person) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE persons SET first_name=?,last_name=?,email=?,user_name=?,password=?,phone=?,address=?,birth_date=? WHERE id=?"
        );

        preparedStatement.setString(1, person.getFirstName());
        preparedStatement.setString(2, person.getLastName());
        preparedStatement.setString(3, person.getEmail());
        preparedStatement.setString(4, person.getUserName());
        preparedStatement.setString(5, person.getPassword());
        preparedStatement.setString(6, person.getPhone());
        preparedStatement.setString(7, person.getAddress());
        preparedStatement.setDate(8, Date.valueOf(person.getBirthDate()));
        preparedStatement.setInt(9, person.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM persons WHERE id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }

    @Override
    public List<Person> findAll() throws Exception {
        List<Person> personList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM persons");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            personList.add(personMapper.personMapper(resultSet));
        }
        return  personList;
    }

    @Override
    public Person findById(Integer id) throws Exception {
        Person person = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM persons WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            person = personMapper.personMapper(resultSet);
        }
        return person;
    }

    public List<Person> findByUserNameAndPassWord(String userName, String passWord) throws Exception {
        List<Person> personList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM persons WHERE user_name=? AND password=?");
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, passWord);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            personList.add(personMapper.personMapper(resultSet));
        }
        return  personList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
