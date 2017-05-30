package com.adp.feedback

class Request {

    Employee from

    Employee to

    String emailMessage


    static constraints = {
    }

    static mappings = {
        table 'request'
    }
}
