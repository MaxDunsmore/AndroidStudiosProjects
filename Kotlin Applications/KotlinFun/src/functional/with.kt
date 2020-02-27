package functional

fun main(args: Array<String>) {
    val probs = System.getProperties()
    with(probs){
        list(System.out)
        println(propertyNames().toList())
        println(getProperty("user.home"))

    }
}