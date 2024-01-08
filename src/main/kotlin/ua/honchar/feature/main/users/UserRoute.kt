package ua.honchar.feature.main.users

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ua.honchar.domain.repository.user.UserRepository

fun Route.users() {

    val repository by inject<UserRepository>()

    post("login") {
        val controller = UsersController(repository, call)
        controller.login()
    }

    post("register") {
        val controller = UsersController(repository, call)
        controller.register()
    }
}