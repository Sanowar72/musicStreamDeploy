# musicStreamDeploy

# Music API Endpoints

Below are the endpoints available in the Music API.

## Add Admin

- Method: POST
- URL: /admin
- Request Body: UserAdmin object
- Description: Adds a new admin user.
- Response: SignUpOutput object containing the status and message.

## Sign In

- Method: GET
- URL: /admin
- Request Body: SignIn object
- Description: Verifies admin user's credentials and allows them to sign in.
- Response: SignUpOutput object containing the status and message.

## Get All Admin Users

- Method: GET
- URL: /all-admin
- Request Body: SignIn object
- Description: Retrieves all admin users if the provided credentials are valid.
- Response: List of UserAdmin objects (Admin users' details).

## Add Songs

- Method: POST
- URL: /song/{email}/{password}
- Path Variables: email, password
- Request Body: List of Song objects
- Description: Adds new songs to the system if the provided credentials are valid.
- Response: String message indicating successful addition of songs.

## Update Song

- Method: PUT
- URL: /song/{email}/{password}
- Path Variables: email, password
- Request Body: Song object
- Description: Updates a song's details if the provided credentials are valid.
- Response: String message indicating successful update.

## Delete Song by ID

- Method: DELETE
- URL: /byid/{id}/{email}/{password}
- Path Variables: id, email, password
- Description: Deletes a song by its ID if the provided credentials are valid.
- Response: String message indicating successful deletion or an error message if the song with the given ID is not found.

Please ensure to replace `{sonu@admin.com}`, `{Sonu@123}`, and other placeholders with actual values when making requests to these endpoints. The request and response data structures might differ based on your specific implementation, so please update the markdown accordingly with the correct data types and structures.

# User API Endpoints

Below are the endpoints available in the User API.

## Add User

- Method: POST
- URL: /user
- Request Body: User object
- Description: Adds a new user.
- Response: SignUpOutput object containing the status and message.

## Get All Songs

- Method: GET
- URL: /allsong/{email}/{password}
- Path Variables: email, password
- Description: Retrieves all songs if the provided credentials are valid.
- Response: List of Song objects (List of all songs).

## Add Song to Playlist

- Method: POST
- URL: /add-song-to-playlist/{email}/{password}/{songId}
- Path Variables: email, password, songId
- Description: Adds a song to the user's playlist if the provided credentials are valid and the songId is valid.
- Response: SignUpOutput object containing the status and message.

## Get User Playlist

- Method: GET
- URL: /playlist/{email}/{password}
- Path Variables: email, password
- Description: Retrieves the user's playlist if the provided credentials are valid.
- Response: List of Song objects (List of songs in the user's playlist).

Please ensure to replace `{email}`, `{password}`, and `{songId}`, and other placeholders with actual values when making requests to these endpoints. The request and response data structures might differ based on your specific implementation, so please update the markdown accordingly with the correct data types and structures.
# UserAdmin Model

The `UserAdmin` model represents the administrator users in the system.

## Properties

- `userAdminId` (Long): The unique identifier for the user admin (auto-generated).
- `userAdminName` (String): The name of the user admin.
- `userAdminMobNo` (String): The mobile number of the user admin.
- `userAdminEmail` (String): The email address of the user admin.
- `userAdminPassword` (String): The password of the user admin.

## Entity Mapping

The `UserAdmin` model is mapped to an entity in the database with the following details:

- Entity: UserAdmin
- Table: The table name is automatically generated based on the entity name.
- Primary Key: The `userAdminId` property is used as the primary key (auto-generated).
- ID Generation Strategy: Auto-generated using `GenerationType.AUTO`.
- Lombok Annotations:
    - `@Data`: Generates getter, setter, and other boilerplate methods.
    - `@AllArgsConstructor`: Generates a constructor with all arguments.
    - `@NoArgsConstructor`: Generates a default no-argument constructor.
- Spring Data JPA Annotations:
    - `@Entity`: Indicates that this class is an entity and should be mapped to a database table.
    - `@Id`: Indicates the primary key field.
    - `@GeneratedValue`: Specifies the generation strategy for the primary key.

# User Model

The `User` model represents the regular users in the system.

## Properties

- `userId` (Long): The unique identifier for the user (auto-generated).
- `userName` (String): The name of the user.
- `userEmail` (String): The email address of the user.
- `userPassword` (String): The password of the user.
- `userMobNo` (String): The mobile number of the user.
- `songs` (List of Song objects): The list of songs in the user's playlist.

## Entity Mapping

The `User` model is mapped to an entity in the database with the following details:

- Entity: User
- Table: The table name is "myUser" (specified by `@Table(name = "myUser")`).
- Primary Key: The `userId` property is used as the primary key (auto-generated).
- ID Generation Strategy: Auto-generated using `GenerationType.AUTO`.
- Lombok Annotations:
    - `@Data`: Generates getter, setter, and other boilerplate methods.
    - `@AllArgsConstructor`: Generates a constructor with all arguments.
    - `@NoArgsConstructor`: Generates a default no-argument constructor.
- Jackson Annotations:
    - `@JsonIdentityInfo`: Used to prevent infinite loops during JSON serialization/deserialization.
- Spring Data JPA Annotations:
    - `@Entity`: Indicates that this class is an entity and should be mapped to a database table.
    - `@Id`: Indicates the primary key field.
    - `@GeneratedValue`: Specifies the generation strategy for the primary key.
- Many-to-Many Relationship:
    - The `User` model has a many-to-many relationship with the `Song` model.
    - The relationship is defined using the `@ManyToMany` annotation.
    - The relationship is bidirectional, so the `Song` model will also have a list of users.
    - The join table name is "song_user_join_table" (specified by `@JoinTable` annotation).
    - The join table's foreign keys are linked to the `userId` column in the `User` table and the `songId` column in the `Song` table.

# SongRepo Repository

The `SongRepo` repository provides access to the database for the `Song` model.

## JpaRepository

The `SongRepo` interface extends the `JpaRepository` interface, which provides basic CRUD (Create, Read, Update, Delete) operations for the `Song` model. The repository is automatically implemented by Spring Data JPA, and you can use it to interact with the `Song` entity in the database.

## Custom Query Methods

In addition to the basic CRUD operations, the `SongRepo` repository also defines two custom query methods:

1. `findAllBySongIdIn`: Retrieves a list of songs with the specified IDs from the database.
    - Method Signature: `List<Song> findAllBySongIdIn(List<Long> songIds)`

2. `findFirstBySongId`: Retrieves the first song with the specified ID from the database.
    - Method Signature: `Song findFirstBySongId(Long songId)`

These custom query methods allow you to perform more specific and complex queries on the `Song` entity based on the provided song IDs.

## Annotations

- `@Repository`: Indicates that this interface is a Spring component and serves as a repository that provides access to the database.

## Inheritance

- The `SongRepo` interface inherits the generic type arguments `Song` and `Long` from `JpaRepository<Song, Long>`. These arguments specify the entity type (`Song`) and the type of the entity's primary key (`Long`).

# UserAdminRepo Repository

The `UserAdminRepo` repository provides access to the database for the `UserAdmin` model.

## JpaRepository

The `UserAdminRepo` interface extends the `JpaRepository` interface, which provides basic CRUD (Create, Read, Update, Delete) operations for the `UserAdmin` model. The repository is automatically implemented by Spring Data JPA, and you can use it to interact with the `UserAdmin` entity in the database.

## Custom Query Method

In addition to the basic CRUD operations, the `UserAdminRepo` repository defines one custom query method:

- `findByUserAdminEmail`: Retrieves the user admin with the specified email address from the database.
    - Method Signature: `UserAdmin findByUserAdminEmail(String userAdminEmail)`

This custom query method allows you to find a user admin based on their email address.

## Annotations

- `@Repository`: Indicates that this interface is a Spring component and serves as a repository that provides access to the database.

## Inheritance

- The `UserAdminRepo` interface inherits the generic type arguments `UserAdmin` and `Long` from `JpaRepository<UserAdmin, Long>`. These arguments specify the entity type (`UserAdmin`) and the type of the entity's primary key (`Long`).

# UserRepo Repository

The `UserRepo` repository provides access to the database for the `User` model.

## JpaRepository

The `UserRepo` interface extends the `JpaRepository` interface, which provides basic CRUD (Create, Read, Update, Delete) operations for the `User` model. The repository is automatically implemented by Spring Data JPA, and you can use it to interact with the `User` entity in the database.

## Custom Query Method

In addition to the basic CRUD operations, the `UserRepo` repository defines one custom query method:

- `findByUserEmail`: Retrieves the user with the specified email address from the database.
    - Method Signature: `User findByUserEmail(String userEmail)`

This custom query method allows you to find a user based on their email address.

## Annotations

- `@Repository`: Indicates that this interface is a Spring component and serves as a repository that provides access to the database.

## Inheritance

- The `UserRepo` interface inherits the generic type arguments `User` and `Long` from `JpaRepository<User, Long>`. These arguments specify the entity type (`User`) and the type of the entity's primary key (`Long`).

# SongService

The `SongService` class provides business logic and operations related to the `Song` entity.

## Dependencies

- `SongRepo`: The `SongRepo` repository is autowired into the `SongService` class to perform CRUD operations on the `Song` entity in the database.

## Service Methods

1. `saveSong(List<Song> song)`: Saves a list of songs in the database.
    - Parameters:
        - `song` (List of Song objects): The list of songs to be saved.
    - Description: This method takes a list of songs and saves them to the database using the `SongRepo` repository's `saveAll` method.

2. `allsong()`: Retrieves all songs from the database.
    - Return Type: `List<Song>`: A list of all songs present in the database.
    - Description: This method retrieves all songs from the database using the `SongRepo` repository's `findAll` method.

3. `getSongById(Long songId)`: Retrieves a song by its ID from the database.
    - Parameters:
        - `songId` (Long): The ID of the song to retrieve.
    - Return Type: `Song`: The song with the specified ID, if found.
    - Description: This method retrieves a song from the database using the `SongRepo` repository's `findFirstBySongId` method. It returns the first song with the specified ID, or `null` if not found.

4. `deleteById(Long id)`: Deletes a song by its ID from the database.
    - Parameters:
        - `id` (Long): The ID of the song to delete.
    - Description: This method deletes a song from the database using the `SongRepo` repository's `deleteById` method.

5. `updateSong(Song song)`: Updates a song in the database.
    - Parameters:
        - `song` (Song): The song object to update.
    - Description: This method updates a song in the database using the `SongRepo` repository's `save` method.

## Annotations

- `@Service`: Indicates that this class is a Spring service component and should be automatically detected and registered as a bean in the Spring application context.

# UserAdminService

The `UserAdminService` class provides business logic and operations related to the `UserAdmin` entity.

## Dependencies

- `UserAdminRepo`: The `UserAdminRepo` repository is autowired into the `UserAdminService` class to perform CRUD operations on the `UserAdmin` entity in the database.
- `PasswordEncoder`: The `PasswordEncoder` is used to encode passwords before saving them to the database.
- `PasswordMatcher`: The `PasswordMatcher` is used to match passwords during user sign-in.

## Service Methods

1. `verifyUserCredentials(String userEmail, String userPassword)`: Verifies the user's credentials during sign-in.
    - Parameters:
        - `userEmail` (String): The email of the user trying to sign in.
        - `userPassword` (String): The password provided by the user during sign-in.
    - Return Type: `boolean`: Returns `true` if the provided credentials are valid, otherwise `false`.
    - Description: This method retrieves the user with the specified email from the database using the `UserAdminRepo` repository. It then compares the hashed version of the provided password with the stored hashed password to verify the credentials.

2. `addAdmin(UserAdmin userAdmin)`: Adds a new user admin to the database.
    - Parameters:
        - `userAdmin` (UserAdmin): The user admin object to be added.
    - Return Type: `SignUpOutput`: A custom DTO (Data Transfer Object) containing the sign-up status and message.
    - Description: This method checks if the provided email is already registered in the database using the `UserAdminRepo` repository. If not, it encodes the password, saves the new user admin to the database, and returns a success message. If the email is already registered, it returns an error message.

3. `signin(SignIn signIn)`: Handles user sign-in.
    - Parameters:
        - `signIn` (SignIn): A DTO containing the email and password for user sign-in.
    - Return Type: `SignUpOutput`: A custom DTO (Data Transfer Object) containing the sign-in status and message.
    - Description: This method retrieves the user with the provided email from the database using the `UserAdminRepo` repository. If the user is found, it calls the `verifyUserCredentials` method to check the password. If the provided credentials are valid, it returns a success message; otherwise, it returns an error message.

4. `getAllAdmin()`: Retrieves all user admins from the database.
    - Return Type: `List<UserAdmin>`: A list of all user admins present in the database.
    - Description: This method retrieves all user admins from the database using the `UserAdminRepo` repository.

## Annotations

- `@Service`: Indicates that this class is a Spring service component and should be automatically detected and registered as a bean in the Spring application context.

# UserService

The `UserService` class provides business logic and operations related to the `User` entity.

## Dependencies

- `UserRepo`: The `UserRepo` repository is autowired into the `UserService` class to perform CRUD operations on the `User` entity in the database.
- `PasswordEncoder`: The `PasswordEncoder` is used to encode passwords before saving them to the database.
- `PasswordMatcher`: The `PasswordMatcher` is used to match passwords during user sign-in.

## Service Methods

1. `verifyUserCredentials(String userEmail, String userPassword)`: Verifies the user's credentials during sign-in.
    - Parameters:
        - `userEmail` (String): The email of the user trying to sign in.
        - `userPassword` (String): The password provided by the user during sign-in.
    - Return Type: `boolean`: Returns `true` if the provided credentials are valid, otherwise `false`.
    - Description: This method retrieves the user with the specified email from the database using the `UserRepo` repository. It then compares the hashed version of the provided password with the stored hashed password to verify the credentials.

2. `adduser(User user)`: Adds a new user to the database.
    - Parameters:
        - `user` (User): The user object to be added.
    - Return Type: `SignUpOutput`: A custom DTO (Data Transfer Object) containing the sign-up status and message.
    - Description: This method checks if the provided email is already registered in the database using the `UserRepo` repository. If not, it encodes the password, saves the new user to the database, and returns a success message. If the email is already registered, it returns an error message.

3. `getUserByEmail(String email)`: Retrieves a user by their email.
    - Parameters:
        - `email` (String): The email of the user to retrieve.
    - Return Type: `User`: The user object corresponding to the provided email.
    - Description: This method retrieves the user with the specified email from the database using the `UserRepo` repository and returns the user object.

4. `saveUser(User user)`: Saves a user object to the database.
    - Parameters:
        - `user` (User): The user object to be saved.
    - Return Type: `void`
    - Description: This method saves the provided user object to the database using the `UserRepo` repository.

Note: The `UserService` class is responsible for managing user-related operations, including authentication, user registration, and data retrieval.
