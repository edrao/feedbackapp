package com.adp.feedback

/**
 * Employee Domain class
 * Assumption : Employee details  are stored in DB. Using LDAP is Out of scope of this Case Study
 */

class Employee {
    //employee fistName
    String firstName

    //employee Last Name
    String lastName

    String email

    //employee Id - assuming integer
    Integer employeeId

    //employee userName
    String username

    //employee password
    //TODO - encode it while saving
    String password

    //Employee reportsTo
    Employee reportsTo

    //Employee designation

    Designation designation

    //Constraints
    static constraints = {
        username nullable: false,blank: false
        firstName nullable: false,blank: false
        lastName nullable: false, blank: false
        employeeId nullable: false
        email email: true, blank: false
        password size: 5..15, blank: false,nullable: false
        reportsTo nullable: true
    }

    //DB mappings
    static mapping = {
        table 'employee'
        version false
        id column: 'id'
        employeeId column: 'employeeid'
        firstName column: 'firstname'
        lastName column: 'lastname'
        username column: 'username'
        password column: 'password'
        reportsTo column: 'reports_to'
        designation column: 'designation'
    }

    String toString() {
        return this.firstName+" "+this.lastName
    }
}
