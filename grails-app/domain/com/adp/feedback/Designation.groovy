package com.adp.feedback

/**
 * Created by dharma on 31/05/17.
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
