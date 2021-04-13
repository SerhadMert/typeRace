package com.example.typerace.activity

class User{
    var id: String? = null
    var username: String? = null
    var topScore: Int? = null

    constructor() {}

    constructor(username: String, topScore: Int) {
        this.username = username
        this.topScore = topScore
    }
    constructor(id :String ,username: String, topScore: Int) {
        this.id = id
        this.username = username
        this.topScore = topScore
    }
}