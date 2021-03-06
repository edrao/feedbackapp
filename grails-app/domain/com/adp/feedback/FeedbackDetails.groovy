package com.adp.feedback

/**
 * Created by dharma on 31/05/17.
 * FeedbackDetails Domain class - To store all the feedbacks
 */
class FeedbackDetails {

    //feedback submitted by
    Employee submitter
    //feedback received by
    Employee receiver

    //enum status
    Status status

    String comments

    static hasMany = [ratings:Rating]

    Date submittedDate = new Date()

    //FeedbackDetails request - if it is asked by an employee
    FeedbackRequest fbkRequest

    //Constraints
    static constraints = {
        submitter nullable: true
        receiver nullable: false
        fbkRequest nullable: true
    }


    //DB mapping
    static mapping = {
        table 'feedbackdetails'
        version false
        id column: 'id'
        submitter column: 'submitter'
        receiver column: 'receiver'
        status column: 'status'
        fbkRequest column: 'request'


    }


}
