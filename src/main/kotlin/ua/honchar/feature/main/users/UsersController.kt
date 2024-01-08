package ua.honchar.feature.main.users

import io.ktor.server.application.*
import io.ktor.server.request.*
import ua.honchar.domain.repository.Result
import ua.honchar.domain.repository.user.UserRepository
import ua.honchar.feature.BaseController

class UsersController(
    private val repository: UserRepository,
    call: ApplicationCall,
) : BaseController(call) {

    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex()

    suspend fun login() {
        val userData = call.receive<ReceiveUserData>()
        val login = userData.login
        val pass = userData.pass
        val res = when {
            login == null || !isValidEmail(login) -> {
                Result.Error("login is invalid")
            }

            pass == null || pass.length < MINIMUM_PASS_LENGTH -> {
                Result.Error("password must be at least $MINIMUM_PASS_LENGTH characters long")
            }

            else -> {
                repository.logIn(login, pass)
            }
        }
        call.respondResult(res)
    }

    suspend fun register() {
        val userData = call.receive<ReceiveUserData>()
        val login = userData.login
        val pass = userData.pass
        val res = when {
            login == null || !isValidEmail(login) -> {
                Result.Error("login is invalid")
            }

            pass == null || pass.length < MINIMUM_PASS_LENGTH -> {
                Result.Error("password must be at least $MINIMUM_PASS_LENGTH characters long")
            }

            else -> {
                repository.register(login, pass, userData.name)
            }
        }
        call.respondResult(res)
    }

    private fun isValidEmail(email: String): Boolean {
        return emailRegex.matches(email)
    }

    companion object {
        private const val MINIMUM_PASS_LENGTH = 8
    }
}