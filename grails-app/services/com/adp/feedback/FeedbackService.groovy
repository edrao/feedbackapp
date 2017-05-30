package com.adp.feedback

import grails.transaction.Transactional

@Transactional
class FeedbackService {

    List<Feedback>  retrieveFeedbacksInDraftStatus(Employee employee) {

        Feedback.findAllWhere(submitter: employee)
    }
}
