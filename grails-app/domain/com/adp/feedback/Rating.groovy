package com.adp.feedback

/**
 * Feedback rating Domain class
 */

class Rating {

    //feedback  category

    Category category

    //rating value

    float rating

    static constraints = {
    }

    static mapping = {
        table 'rating'
        version false
        rating column: 'rating'

    }
}
