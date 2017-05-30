package com.adp.feedback

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DesignationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Designation.list(params), model:[designationCount: Designation.count()]
    }

    def show(Designation designation) {
        respond designation
    }

    def create() {
        respond new Designation(params)
    }

    @Transactional
    def save(Designation designation) {
        if (designation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (designation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond designation.errors, view:'create'
            return
        }

        designation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'designation.label', default: 'Designation'), designation.id])
                redirect designation
            }
            '*' { respond designation, [status: CREATED] }
        }
    }

    def edit(Designation designation) {
        respond designation
    }

    @Transactional
    def update(Designation designation) {
        if (designation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (designation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond designation.errors, view:'edit'
            return
        }

        designation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'designation.label', default: 'Designation'), designation.id])
                redirect designation
            }
            '*'{ respond designation, [status: OK] }
        }
    }

    @Transactional
    def delete(Designation designation) {

        if (designation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        designation.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'designation.label', default: 'Designation'), designation.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'designation.label', default: 'Designation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
