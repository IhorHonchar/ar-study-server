package ua.honchar.domain.repository.user

import ua.honchar.domain.model.UserDTO
import ua.honchar.domain.repository.Result

interface UserRepository {

    fun logIn(login: String, pass: String): Result<UserDTO>
    fun register(login: String, pass: String, name: String?): Result<UserDTO>
}