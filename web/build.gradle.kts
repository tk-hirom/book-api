dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation(project(":application"))
    implementation(project(":domain"))
    implementation(project(":infra"))
}

tasks {
    getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
        mainClass.set("com.example.app.web.BookApiApplication")
    }
}