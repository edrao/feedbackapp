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

    //Feedback request - if it is asked by an employee
    FbkRequest fbkRequest

    //Constraints
    static constraints = {
        submitter nullable: true
        receiver nullable: false
        fbkRequest nullable: true
    }


    //DB mapping
    static mapping = {
        table 'feedback'
        version false
        id column: 'id'
        submitter column: 'submitter'
        receiver column: 'receiver'
        status column: 'status'
        fbkRequest column: 'request'
        hasMany joinTable: [name: 'feedback_ratings',
                            key: 'comment_id',
                            column: 'rating']

    }


}
