package com.adp.feedback

/**
 * Created by dharma on 31/05/17.
 * Domain class for to capture FeedbackDetails Request ( if asked by an Employee)
 */
class FeedbackRequest {

    // feedback asked by
    Employee from

    // feedback asked from
    Employee to

    Status status

    //Email message body
    String emailMessage


    static constraints = {
    }

    static mapping = {
        table 'feedbackrequest'
    }
}
