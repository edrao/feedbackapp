package com.adp.feedback

/**
 * Created by dharma on 31/05/17.
 * FeedbackDetails rating Domain class
 */

class Rating {

    //feedback  category

    Category category

    //rating value

    Float rating

    static constraints = {

        category nullable: false
        rating nullable: false
    }

    static mapping = {
        table 'rating'
        version false
        rating column: 'rating'

    }
}
