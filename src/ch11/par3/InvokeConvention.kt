package ch11.par3

/*
* Соглашение invoke позволяет вызывать объекты как функции.
* Для этого класс должен определить функцию invoke с модификатором operator
* */
class Greeter(val greeting: String) {
    operator fun invoke(name: String) {
        println("$greeting, $name!")
    }
}

/*
* invoke может иметь любую сигнатуру; его можно перегрузить несколько раз под разные сигнатуры
* */

/*
* лямбда-выражения (кроме встраиваемых) компилируются в классы, реализующие интерфейсы функций (FunctionN), а эти
* интерфейсы обладают методом invoke с соответствующей сигнатурой:
* interface Function2 <in P1, in P2, out R> {
*   operator fun invoke(p1: P1, p2: P2): R
* }
* */

data class Issue(
    val id: String,
    val project: String,
    val type: String,
    val priority: String,
    val description: String
)

class ImportantIssuesPredicate(val project: String) : (Issue) -> Boolean {
    override fun invoke(issue: Issue): Boolean {
        return issue.project == project && issue.isImportant()
    }

    private fun Issue.isImportant(): Boolean {
        return type == "Bug" && (priority == "Major" || priority == "Critical")
    }
}

class DependencyHandler {
    fun compile(coordinate: String) {
        println("Added dependency on $coordinate")
    }

    operator fun invoke(
        body: DependencyHandler.() -> Unit) {
        body()
    }
}

fun main() {
    val bavarianGreeter = Greeter("Servus")
    bavarianGreeter("Dmitry") // Servus, Dmitry!
    /*
    * вызов объекта bavarianGreeter(name) как функции транслируется в вызов bavarianGreeter.invoke(name)
    * */

    val i1 = Issue("IDEA-154446", "IDEA", "Bug", "Major", "Save settings failed")
    val i2 = Issue(
        "KT-12183", "Kotlin", "Feature", "Normal",
        "Intention: convert several calls on the same receiver to with/apply"
    )
    val predicate = ImportantIssuesPredicate("IDEA")
    for (issue in listOf(i1, i2).filter(predicate)) {
        println(issue.id) // IDEA-154446
    }

    val dependencies = DependencyHandler()
    dependencies.compile("org.jetbrains.kotlin:kotlin-stdlib:1.0.0")
    // Added dependency on org.jetbrains.kotlin:kotlin-stdlib:1.0.0
    dependencies {
        compile("org.jetbrains.kotlin:kotlin-reflect:1.0.0")
    }
    // Added dependency on org.jetbrains.kotlin:kotlin-reflect:1.0.0
}
