package ch03.par6

class User(val id: Int, val name: String, val address: String)



fun saveUser(user: User) {
    fun User.validateBeforeSave() {
        fun validate(
            value: String,
            fieldName: String
        ) {
            if (value.isEmpty()) throw IllegalArgumentException("can't save user $id: empty $fieldName")
        }
        validate(name, "Name")
        validate(address, "Address")
    }
    user.validateBeforeSave()
    saveUserToDatabase(user)
}

fun saveUserToDatabase(user: User) {
    println("user saved")
}

fun main() {
    saveUser(User(1, "", ""))
}