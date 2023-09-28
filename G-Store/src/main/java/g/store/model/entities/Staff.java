package g.store.model.entities;

import g.store.manager.CurrencyManager;
import g.store.types.GenderTypes;
import g.store.types.staff_roles.StaffAuthority;
import g.store.types.staff_roles.StaffPosition;

import java.util.UUID;
import java.util.function.Supplier;

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
    private StaffPosition position;

    /// Authority of staff
    private StaffAuthority authority;

    /// Gender of staff
    private GenderTypes genderTypes;

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
                + "Authority: " + authority.toString() + "\n"
                + "Gender: " + genderTypes.toString() + "\n"
                + "Salary: " + CurrencyManager.getLocalCurrencyFromDouble(salary)
                ;

        return welcome + staffInfo;
    }

    public Staff(
            String firstName,
            String lastName,
            String emailAddress,
            UUID staffId,
            StaffPosition position,
            StaffAuthority authority,
            GenderTypes genderTypes,
            double salary
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.staffId = staffId;
        this.emailAddress = emailAddress;
        this.position = position;
        this.authority = authority;
        this.genderTypes = genderTypes;
        this.salary = salary;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    public UUID getStaffId() {
        return staffId;
    }

    public void setStaffId(UUID staffId) {
        this.staffId = staffId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress.trim();
    }

    public StaffPosition getPosition() {
        return position;
    }

    public void setPosition(StaffPosition position) {
        this.position = position;
    }

    public StaffAuthority getAuthority() {
        return authority;
    }

    public void setAuthority(StaffAuthority authority) {
        this.authority = authority;
    }

    public GenderTypes getGender() {
        return genderTypes;
    }

    public void setGender(GenderTypes genderTypes) {
        this.genderTypes = genderTypes;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setStaff(Staff staff) {
        this.setAuthority(staff.getAuthority());
        this.setGender(staff.getGender());
        this.setEmailAddress(staff.getEmailAddress());
        this.setPosition(staff.getPosition());
        this.setSalary(staff.getSalary());
        this.setFirstName(staff.getFirstName());
        this.setLastName(staff.getLastName());
        this.setStaffId(staff.getStaffId());
    }
}
