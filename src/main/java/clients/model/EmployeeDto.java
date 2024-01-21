package clients.model;

public class EmployeeDto {

    private int id;
    private String firstName;
    private String lastName;
    private int companyId;
    private String email;
    private String phone;

    public EmployeeDto(int id, String firstName, String lastName, int companyId, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyId = companyId;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
