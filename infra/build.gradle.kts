apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")
apply(plugin = "org.jetbrains.kotlin.jvm")
apply(plugin = "org.jetbrains.kotlin.plugin.spring")

dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.0") // バージョン指定したら解決した
}

tasks {
    getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
        mainClass.set("com.example.app.web.BookApiApplication")
    }
}