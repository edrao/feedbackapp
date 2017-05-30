package com.adp.feedback

class Rating {

    Category category

    float rating

    static constraints = {
    }

    static mapping = {
        table 'rating'
        version false
        rating column: 'rating'

    }
}
