package ua.honchar.data.db

import org.ktorm.database.Database

object DatabaseFactory {
    fun create() = Database.connect(
        url = "jdbc:mysql://localhost:3306/ar_study_db",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "root-developer"
    )
}