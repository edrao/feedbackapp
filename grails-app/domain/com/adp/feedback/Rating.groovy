package com.adp.feedback

/**
 * Created by dharma on 31/05/17.
 * FeedbackDetails rating Domain class
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
