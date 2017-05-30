package com.adp.feedback

/**
 * Feedback Domain class
 */
class Feedback {

    //feedback submitted by
    Employee submitter
    //feedback received by
    Employee receiver

    enum status{
        DRAFT, SENT
    }

    String comments

    static hasMany = [ratings:Rating]

    Date submittedDate = new Date()

    //Constraints
    static constraints = {
        submitter nullable: true
        receiver nullable: false
    }


    //DB mapping
    static mapping = {
        table 'feedback'
        version false
        id column: 'comment_id'
        submitter column: 'submitter'
        receiver column: 'receiver'
        status column: 'status'
        hasMany joinTable: [name: 'feedback_ratings',
                            key: 'comment_id',
                            column: 'rating']

    }


}
