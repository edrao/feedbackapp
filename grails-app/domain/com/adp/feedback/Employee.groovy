package com.adp.feedback

/**
 * Assumption : Employee details  are retrieved from DB. Using LDAP is Out of scope of this Excercise
 */

class Employee {
    //employee fistName
    String firstName

    //employee Last Name
    String lastName

    //employee Id - assuming integer
    Integer employeeId

    //employee userName
    String username

    static constraints = {
        username nullable: false,blank: false
        firstName nullable: false,blank: false
        lastName nullable: false, blank: false
        employeeId nullable: false
    }
}
