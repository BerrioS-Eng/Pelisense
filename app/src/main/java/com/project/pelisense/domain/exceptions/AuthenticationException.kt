package com.project.pelisense.domain.exceptions

class AuthenticationException(override val message: String): RuntimeException(message) {
}