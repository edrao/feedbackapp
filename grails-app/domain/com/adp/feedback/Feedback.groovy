package com.adp.feedback

/**
 * Feedback Domain class
 */
class Feedback {

    //feedback submitted by
    Employee submitter

    Employee receiver



    enum status{
        DRAFT, SENT
    }

    String comments

    static hasMany = [ratings:Rating]

    Date submittedDate = new Date()

    static constraints = {
        submitter nullable: true
        receiver nullable: false
    }

    static mapping = {
        ratings join
    }


}
