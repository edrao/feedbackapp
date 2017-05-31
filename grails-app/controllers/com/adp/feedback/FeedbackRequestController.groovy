package com.adp.feedback

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FeedbackRequestController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond FeedbackRequest.list(params), model:[feedbackRequestCount: FeedbackRequest.count()]
    }

    def show(FeedbackRequest feedbackRequest) {
        respond feedbackRequest
    }

    def create() {
        respond new FeedbackRequest(params)
    }

    @Transactional
    def save(FeedbackRequest feedbackRequest) {
        if (feedbackRequest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (feedbackRequest.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond feedbackRequest.errors, view:'create'
            return
        }

        feedbackRequest.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'feedbackRequest.label', default: 'FeedbackRequest'), feedbackRequest.id])
                redirect feedbackRequest
            }
            '*' { respond feedbackRequest, [status: CREATED] }
        }
    }

    def edit(FeedbackRequest feedbackRequest) {
        respond feedbackRequest
    }

    @Transactional
    def update(FeedbackRequest feedbackRequest) {
        if (feedbackRequest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (feedbackRequest.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond feedbackRequest.errors, view:'edit'
            return
        }

        feedbackRequest.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'feedbackRequest.label', default: 'FeedbackRequest'), feedbackRequest.id])
                redirect feedbackRequest
            }
            '*'{ respond feedbackRequest, [status: OK] }
        }
    }

    @Transactional
    def delete(FeedbackRequest feedbackRequest) {

        if (feedbackRequest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        feedbackRequest.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'feedbackRequest.label', default: 'FeedbackRequest'), feedbackRequest.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'feedbackRequest.label', default: 'FeedbackRequest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
