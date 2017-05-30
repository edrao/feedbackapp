package com.adp.feedback

/**
 * Domain class for to capture Feedback Request ( if asked by an Employee)
 */
class FbkRequest {

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
