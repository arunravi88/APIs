package mindteck.com.Repository;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import mindteck.com.Entity.Employee;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FileBasedEmployeeRepository {
    private final String FILE_PATH = "employees.json";
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Employee> findAll() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, new TypeReference<List<Employee>>() {});
    }

    public Optional<Employee> findById(Long id) throws IOException {
        return findAll().stream().filter(emp -> emp.getId().equals(id)).findFirst();
    }

    public void save(Employee employee) throws IOException {
        List<Employee> employees = findAll();
        employees.add(employee);
        objectMapper.writeValue(new File(FILE_PATH), employees);
    }

    public List<Employee> findByNameAndSalaryRange(String name, Double fromSalary, Double toSalary) throws IOException {
        return findAll().stream().filter(emp -> {
            boolean nameMatch = (name == null || emp.getFirstName().contains(name) || emp.getLastName().contains(name));
            boolean salaryMatch = (fromSalary == null || emp.getSalary() >= fromSalary) &&
                    (toSalary == null || emp.getSalary() <= toSalary);
            return nameMatch && salaryMatch;
        }).collect(Collectors.toList());
    }
}

