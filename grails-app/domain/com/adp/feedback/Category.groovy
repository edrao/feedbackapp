package com.adp.feedback

/**
 * Feedback Category Domain class
 */

class Category {

    String name

    static constraints = {
        name nullable: false, blank: false
    }

    static mapping = {
        table 'category'
        version false
    }
}
