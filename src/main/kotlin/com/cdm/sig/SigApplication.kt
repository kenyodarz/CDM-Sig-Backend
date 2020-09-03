package com.cdm.sig

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SigApplication

fun main(args: Array<String>) {
	runApplication<SigApplication>(*args)
}
