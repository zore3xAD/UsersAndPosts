package com.anton.dobrogorsky.usersposts.model

data class Address(val city: String, val street: String, val suite: String) {

    override fun toString(): String {
        return "$city, $street, $street"
    }

}