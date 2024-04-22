package ro.sek1.RESTful

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RESTfulApplication

fun main(args: Array<String>) {
	runApplication<RESTfulApplication>(*args)
}