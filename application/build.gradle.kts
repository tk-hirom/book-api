dependencies{
    implementation(project(":domain"))
}

tasks {
    getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
        mainClass.set("com.example.app.web.BookApiApplication")
    }
}