package com.acrony.kotlinregistration

object RegistrationAdapter {
    val BASE_URL = "https://acronymsolutions.in/jsondata/"
    val registerServiceForm: RegisterServiceForm?
        get() {
            return RetrofitClient.getClient(BASE_URL)?.create(RegisterServiceForm::class.java)
        }
}