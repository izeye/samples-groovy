import static org.apache.commons.lang3.StringUtils.*;

/**
 * Created by izeye on 2014. 11. 20..
 */
class Greet {
    def name

    Greet(who) {
        name = who[0].toUpperCase() + who[1..-1]
    }

    def salute() {
        println "Hello $name!"
    }
}

g = new Greet('world')
g.salute()

class Greeter extends Greet {
    Greeter(who) {
        super(capitalize(who))
    }
}

new Greeter('world').salute()
