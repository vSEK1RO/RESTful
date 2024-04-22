package ro.sek1.RESTful.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.servers.Server
import io.swagger.v3.oas.annotations.servers.Servers

@OpenAPIDefinition(
    info = Info(
        title = "RESTful surveys API",
        description = "Homework prj for KFD",
        version = "0.0.1",
        contact = Contact(
            name = "Vladislav (SEK1RO) Litvinov",
            email = "vlad@sek1.ro",
            url = "sek1.ro"
        )
    ),
    servers = [
        Server(
            url = "restful.sek1.ro"
        )
    ]
)
class OpenApiConfig {
}