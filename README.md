# ms-user

## Описание

ms-user отвечает за управление пользователями и их профилями. Сервис предоставляет CRUD-операции для работы с пользователями, а также возможность их авторизации и аутентификации.

## Основные функции

- **Регистрация пользователя**: создание новой учётной записи.
- **Аутентификация**: проверка данных пользователя и выдача JWT-токена.
- **CRUD-операции**: управление сущностями пользователя и профиля.
- **Валидация токенов**: проверка JWT-токенов для обеспечения безопасности.
- Интеграционные тесты: сервисы и контроллеры покрыты интеграционными тестами для проверки работоспособности.

## Технологии

- **Java 21**
- **Spring Boot**
- **PostgreSQL**
- **JSON Web Tokens (JWT)**

## Установка и запуск

1. Склонируйте репозиторий:
   ```bash
   git clone <repository_url>
   cd ms-user
   ```
2. Утилиты

org.projectlombok:lombok — для упрощения работы с POJO (геттеры, сеттеры и т.д.).
com.fasterxml.jackson.core:jackson-databind — для работы с JSON.
org.mindrot:jbcrypt — для хэширования паролей.
## Структура проекта
3. Соберите и запустите приложение:
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

- **UserController**:

  - `POST /api/v1/register`: регистрация нового пользователя.
  - `POST /api/v1/login`: аутентификация пользователя и выдача JWT-токена.
  - `GET /api/v1/user/{id}`: получение информации о пользователе по ID.
  - `PUT /api/v1/user/{id}`: обновление данных пользователя.
  - `DELETE /api/v1/user/{id}`: удаление пользователя.

- **UserService**:

  - Обрабатывает бизнес-логику для управления пользователями.
  - `login(String login, String password)`: проверяет данные пользователя и создаёт JWT-токен.

- **TokenController**:

  - `GET /validate-token`: проверяет валидность JWT-токена.
  - Методы:
    - `isTokenValid(String token)`: валидирует токен с использованием секрета JWT.
    - `getUserIdFromToken(String token)`: извлекает идентификатор пользователя из токена.

- **UserRepository**:

  - Слой доступа к данным, использующий PostgreSQL.

- **UserProfile**:

  - Связанная сущность для хранения дополнительных данных пользователя (например, дата рождения, описание).

## Тестирование

1. Используйте Postman для тестирования эндпоинтов регистрации и аутентификации.
2. Проверьте CRUD-операции для пользователей.
3. Проверьте эндпоинт валидации токена с корректным и некорректным токенами.

##

