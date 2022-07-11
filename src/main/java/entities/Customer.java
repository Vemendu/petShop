package entities;

import java.sql.Date;

public class Customer {
    private long id;
    private String firstName;
    private String lastName;
    private Date birthdayDate;
    private String phoneNumber;
    private String address;
    private String language;
    private String country;
    private int roleId;
    private String username;
    private String password;
    private String email;

    public Customer() {

    }

    public Customer(String firstName, String lastName, Date birthdayDate, String phoneNumber, String address, String language, String country, String username, String password, String email)
    {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthdayDate(birthdayDate);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setLanguage(language);
        setCountry(country);
        setUsername(username);
        setPassword(password);
        setEmail(email);
    }

    public Customer(String firstName, String lastName, Date birthdayDate, String phoneNumber, String address, String language, String country, int roleId, String username, String password, String email)
    {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthdayDate(birthdayDate);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setLanguage(language);
        setCountry(country);
        setRoleId(roleId);
        setUsername(username);
        setPassword(password);
        setEmail(email);
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

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int role_id) {
        this.roleId = role_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
