package com.adp.feedback

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FeedBackController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Feedback.list(params), model:[feedBackCount: Feedback.count()]
    }

    def show(Feedback feedBack) {
        respond feedBack
    }

    def create() {
        respond new Feedback(params)
    }

    @Transactional
    def save(Feedback feedBack) {
        if (feedBack == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (feedBack.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond feedBack.errors, view:'create'
            return
        }

        feedBack.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'feedBack.label', default: 'Feedback'), feedBack.id])
                redirect feedBack
            }
            '*' { respond feedBack, [status: CREATED] }
        }
    }

    def edit(Feedback feedBack) {
        respond feedBack
    }

    @Transactional
    def update(Feedback feedBack) {
        if (feedBack == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (feedBack.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond feedBack.errors, view:'edit'
            return
        }

        feedBack.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'feedBack.label', default: 'Feedback'), feedBack.id])
                redirect feedBack
            }
            '*'{ respond feedBack, [status: OK] }
        }
    }

    @Transactional
    def delete(Feedback feedBack) {

        if (feedBack == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        feedBack.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'feedBack.label', default: 'Feedback'), feedBack.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'feedBack.label', default: 'Feedback'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
