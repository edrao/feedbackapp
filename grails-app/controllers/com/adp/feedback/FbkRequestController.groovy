package com.adp.feedback

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FbkRequestController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond FbkRequest.list(params), model:[fbkRequestCount: FbkRequest.count()]
    }

    def show(FbkRequest fbkRequest) {
        respond fbkRequest
    }

    def create() {
        respond new FbkRequest(params)
    }

    @Transactional
    def save(FbkRequest fbkRequest) {
        if (fbkRequest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (fbkRequest.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond fbkRequest.errors, view:'create'
            return
        }

        fbkRequest.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'fbkRequest.label', default: 'FbkRequest'), fbkRequest.id])
                redirect fbkRequest
            }
            '*' { respond fbkRequest, [status: CREATED] }
        }
    }

    def edit(FbkRequest fbkRequest) {
        respond fbkRequest
    }

    @Transactional
    def update(FbkRequest fbkRequest) {
        if (fbkRequest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (fbkRequest.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond fbkRequest.errors, view:'edit'
            return
        }

        fbkRequest.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'fbkRequest.label', default: 'FbkRequest'), fbkRequest.id])
                redirect fbkRequest
            }
            '*'{ respond fbkRequest, [status: OK] }
        }
    }

    @Transactional
    def delete(FbkRequest fbkRequest) {

        if (fbkRequest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        fbkRequest.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'fbkRequest.label', default: 'FbkRequest'), fbkRequest.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'fbkRequest.label', default: 'FbkRequest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
