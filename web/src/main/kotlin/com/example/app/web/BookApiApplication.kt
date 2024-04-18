package com.example.app.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.example.app.application", "com.example.app.infra", "com.example.app.web")
class BookApiApplication

fun main(args: Array<String>) {
	runApplication<BookApiApplication>(*args)
}
