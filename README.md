
##Сервис библиотеки для показа примера работы с использованием Config server, Eureka, Zuul, Feign client
docker-compose.yml - запуска в docker-compose

### Сервер конфигурации (config-server, порт: 8888)
* Хранит настройки всех приложений
* Реализован с помощью Spring Config Server

### Реестр служб (service-discovery-server, порт: 8761)
* Помогает службам (приложениям/микросервисам) находить дург-друга
* Реализован с помощью Eureka

### Микросервис facade-gateway 
фасад доступа

### Микросервис otus-library-ui
содержит ui - Thymeleaf + jquery

### Микросервис otus-library-rest
содержит rest доступа к данным 
