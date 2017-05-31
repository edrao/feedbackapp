package com.adp.feedback

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RatingController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Rating.list(params), model:[ratingCount: Rating.count()]
    }

    def show(Rating rating) {
        respond rating
    }

    def create() {
        respond new Rating(params)
    }

    @Transactional
    def save(Rating rating) {
        if (rating == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (rating.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond rating.errors, view:'create'
            return
        }

        rating.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'rating.label', default: 'Rating'), rating.id])
                redirect rating
            }
            '*' { respond rating, [status: CREATED] }
        }
    }

    def edit(Rating rating) {
        respond rating
    }

    @Transactional
    def update(Rating rating) {
        if (rating == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (rating.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond rating.errors, view:'edit'
            return
        }

        rating.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'rating.label', default: 'Rating'), rating.id])
                redirect rating
            }
            '*'{ respond rating, [status: OK] }
        }
    }

    @Transactional
    def delete(Rating rating) {

        if (rating == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        rating.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'rating.label', default: 'Rating'), rating.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'rating.label', default: 'Rating'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
