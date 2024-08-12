package mindteck.com.Controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mindteck.com.Entity.Employee;
import mindteck.com.Repository.FileBasedEmployeeRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private FileBasedEmployeeRepository repository;

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) throws IOException {
        List<Employee> employees = repository.findAll();
        employee.setId((long) (employees.size() + 1));
        repository.save(employee);
        return employee;
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) throws IOException {
        return repository.findById(id);
    }

    @GetMapping
    public List<Employee> getEmployees(@RequestParam(required = false) String name,
                                       @RequestParam(required = false) Double fromSalary,
                                       @RequestParam(required = false) Double toSalary) throws IOException {
        return repository.findByNameAndSalaryRange(name, fromSalary, toSalary);
    }
}

