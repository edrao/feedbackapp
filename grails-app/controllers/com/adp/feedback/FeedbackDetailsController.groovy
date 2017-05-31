package com.adp.feedback

import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FeedbackDetailsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    FeedbackDetailsService feedbackDetailsService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond FeedbackDetails.list(params), model:[feedbackDetailsCount: FeedbackDetails.count()]
    }

    def show(FeedbackDetails feedbackDetails) {
        respond feedbackDetails
    }

    def create() {
        respond new FeedbackDetails(params)
    }

    @Transactional
    def save(FeedbackDetails feedbackDetails) {
        if (feedbackDetails == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (feedbackDetails.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond feedbackDetails.errors, view:'create'
            return
        }

        feedbackDetails.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'feedbackDetails.label', default: 'FeedbackDetails'), feedbackDetails.id])
                redirect feedbackDetails
            }
            '*' { respond feedbackDetails, [status: CREATED] }
        }
    }

    def edit(FeedbackDetails feedbackDetails) {
        respond feedbackDetails
    }

    @Transactional
    def update(FeedbackDetails feedbackDetails) {
        if (feedbackDetails == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (feedbackDetails.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond feedbackDetails.errors, view:'edit'
            return
        }

        feedbackDetails.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'feedbackDetails.label', default: 'FeedbackDetails'), feedbackDetails.id])
                redirect feedbackDetails
            }
            '*'{ respond feedbackDetails, [status: OK] }
        }
    }

    @Transactional
    def delete(FeedbackDetails feedbackDetails) {

        if (feedbackDetails == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        feedbackDetails.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'feedbackDetails.label', default: 'FeedbackDetails'), feedbackDetails.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'feedbackDetails.label', default: 'FeedbackDetails'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def retrieveFeedbackOfEmployee(long id){
        Employee employee = Employee.get(id)
        List<FeedbackDetails> feedbackList = feedbackDetailsService.retrieveFeedbackOfEmployee(employee)
       def fbList =  feedbackList.collect {
            ['from':it.submitter.username, to:it.receiver.username,'submittedDate':it.submittedDate,'rating':it.ratings.find{it1->(it1.category.name=='OverallRating')}]
        }
        render fbList as JSON
    }
}
