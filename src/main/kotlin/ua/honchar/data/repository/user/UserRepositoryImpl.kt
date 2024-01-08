package ua.honchar.data.repository.user

import ua.honchar.data.db.DatabaseManager
import ua.honchar.domain.model.UserDTO
import ua.honchar.domain.repository.Result
import ua.honchar.domain.repository.user.UserRepository

class UserRepositoryImpl(private val databaseManager: DatabaseManager): UserRepository {

    override fun logIn(login: String, pass: String): Result<UserDTO> {
        val user = databaseManager.getUserData(login, pass)
        return if (user != null) {
            Result.Success(user)
        } else {
            Result.Error("invalid login or password")
        }
    }

    override fun register(login: String, pass: String, name: String?): Result<UserDTO> {
        val user = databaseManager.insertUser(login, pass, name.orEmpty())
        return if (user != null) {
            Result.Success(user)
        } else {
            Result.Error("User with login $login already exists.")
        }
    }
}