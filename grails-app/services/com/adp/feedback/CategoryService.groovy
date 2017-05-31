package com.adp.feedback

import grails.transaction.Transactional

@Transactional
class CategoryService {

    List<Category> retrieveCategories(Map params) {

        return Category.list(params)

    }
}
