package com.adp.feedback

/**
 * Created by dharma on 31/05/17.
 * FeedbackDetails Category Domain class
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
