package org.example.oop_and_javafx_assignment;

public class Profile {

    private int    id;
    private String firstName;
    private String lastName;
    private String department;
    private String major;
    private String email;
    private String imageURL;

    public Profile(int id, String firstName, String lastName,
                   String department, String major,
                   String email, String imageURL) {
        this.id         = id;
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.department = department;
        this.major      = major;
        this.email      = email;
        this.imageURL   = imageURL;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
