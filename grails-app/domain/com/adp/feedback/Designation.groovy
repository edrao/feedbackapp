package com.adp.feedback

/**
 * Employee Designation Domain class
 */

class Designation {

    String name;

    static constraints = {
        name nullable: false, blank: false
    }

    static mapping = {
        table 'designation'
        version false
    }

    String toString() {
        return this.name
    }
}
