package com.adp.feedback

/**
 * Created by dharma on 31/05/17.
 * Feedback Details Service
 */

import grails.transaction.Transactional
import org.hibernate.Criteria
import org.hibernate.criterion.CriteriaSpecification

@Transactional
class FeedbackDetailsService {

    //retrieves all feedbacks given and received by employee

    List<FeedbackDetails> retrieveFeedbackOfEmployee(Employee employee){

        Criteria criteria = FeedbackDetails.createCriteria()

        List<FeedbackDetails> feedbackList= criteria.list {
            or {

                    eq('submitter', employee)
                    eq('receiver', employee)

            }
        }

        return feedbackList


    }

}
