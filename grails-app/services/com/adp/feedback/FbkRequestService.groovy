package com.adp.feedback

import grails.transaction.Transactional

@Transactional
class FbkRequestService {

    //To retrieve feedbacks that need to be given by Employee

    List<FbkRequest> getFeedbackRequests(long employeeId){

        List<Employee> requests = FbkRequest.findAllByTo(Employee.get(employeeId))

        return requests

    }
}
