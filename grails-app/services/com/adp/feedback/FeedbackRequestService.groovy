package com.adp.feedback

/**
 * Created by dharma on 31/05/17.
 * Feedback Request Service
 */

import grails.transaction.Transactional

@Transactional
class FeedbackRequestService {

    //retrieves feedback requests asked by employee

    List<FeedbackRequest> getFeedbackRequestsForEmployee(long employeeId){

        List<Employee> requests = FeedbackRequest.findAllByTo(Employee.get(employeeId))

        return requests

    }
}
