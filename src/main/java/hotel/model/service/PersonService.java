package hotel.model.service;

import hotel.model.entity.Person;
import hotel.model.repository.PersonRepository;
import lombok.Getter;

import java.util.List;

public final class PersonService  {
    @Getter
    private static PersonService service = new PersonService();

    private PersonService() {}

    public void save(Person person) throws Exception {
        try(PersonRepository personRepository = new PersonRepository()) {
            if (personRepository.findByUserNameAndPassWord(person.getUserName(), person.getPassword()) == null) {
                personRepository.save(person);
            } else {
                throw new Exception("User name already exists");
            }

        }

    }

    public void update(Person person) throws Exception {
        try(PersonRepository personRepository = new PersonRepository()) {
            personRepository.edit(person);
        }
    }

    public void delete(Integer id) throws Exception {
        try(PersonRepository personRepository = new PersonRepository()) {
            personRepository.delete(id);
        }
    }

    public void edit(Person person) throws Exception {
        try(PersonRepository personRepository = new PersonRepository()) {
            personRepository.edit(person);
        }
    }

    public void findById(Integer id) throws Exception {
        try(PersonRepository personRepository = new PersonRepository()) {
            personRepository.findById(id);
        }
    }


    public List<Person> findAll() throws Exception {
        try (PersonRepository personRepository = new PersonRepository()) {
            return personRepository.findAll();
        }
    }


    public List<Person> findByUserNameAndPassWord(String userName, String password) throws Exception {
        try(PersonRepository personRepository = new PersonRepository()) {
            return personRepository.findByUserNameAndPassWord(userName, password);
        }
    }

    public List<Person> findByNameAndFamily(String name, String family) throws Exception {
        try (PersonRepository personRepository = new PersonRepository()) {
            return personRepository.findByNameAndFamily(name, family);
        }
    }



}
