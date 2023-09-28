package g.store.model.entities;

import g.store.utils.CurrencyUtil;
import g.store.enums.others.Gender;
import g.store.enums.roles.StaffRole;

import java.util.UUID;

/**
 * This is the G-Store Staff Class which will be a wrapper for all G-Store staffs
 */
public abstract class Staff extends Store {
    /// First name of staff
    private String firstName;

    /// Last Name of staff
    private String lastName;

    /// ID of staff
    private UUID staffId;

    /// Email Address of staff
    private String emailAddress;

    /// Position of staff
    private StaffRole position;

    /// Gender of staff
    private Gender gender;

    /// Salary amount of Staff
    private double salary;

    @Override
    public String toString() {
        String welcome = "Welcome to G-Store Database. The staff information includes:\n\n";

        String staffInfo = "FirstName: " + firstName + "\n"
                + "LastName: " + lastName + "\n"
                + "EmailAddress: " + emailAddress + "\n"
                + "StaffID: " + staffId.toString() + "\n"
                + "Position: " + position.toString() + "\n"
                + "Gender: " + gender.toString() + "\n"
                + "Salary: " + CurrencyUtil.getLocalCurrencyFromDouble(salary)
                ;

        return welcome + staffInfo;
    }

    public Staff(
            String firstName,
            String lastName,
            String emailAddress,
            UUID staffId,
            StaffRole position,
            Gender gender,
            double salary
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.staffId = staffId;
        this.emailAddress = emailAddress;
        this.position = position;
        this.gender = gender;
        this.salary = salary;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName.trim();
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName.trim();
//    }

    public UUID getStaffId() {
        return staffId;
    }

//    public void setStaffId(UUID staffId) {
//        this.staffId = staffId;
//    }

    public String getEmailAddress() {
        return emailAddress;
    }

//    public void setEmailAddress(String emailAddress) {
//        this.emailAddress = emailAddress.trim();
//    }

    public StaffRole getPosition() {
        return position;
    }

//    public void setPosition(StaffRole position) {
//        this.position = position;
//    }

    public Gender getGender() {
        return gender;
    }

//    public void setGender(Gender gender) {
//        this.gender = gender;
//    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
