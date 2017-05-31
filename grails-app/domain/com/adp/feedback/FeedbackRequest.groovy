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

    enum status{
        GIVEN,NOTGIVEN
    }

    //Email message body
    String emailMessage


    static constraints = {
    }

    static mappings = {
        table 'request'
    }
}
