package ua.honchar.data.db.user

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DbUser : Table<DbUserEntity>("user") {
    val login = varchar("login").primaryKey().bindTo { it.login }
    val name = varchar("name").bindTo { it.name }
    val pass = varchar("pass").bindTo { it.pass }
}

interface DbUserEntity : Entity<DbUserEntity> {
    val name: String
    val pass: String
    val login: String

    companion object : Entity.Factory<DbUserEntity>()
}