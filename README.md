### login   user@yandex.ru
### pass    pasword
### role    user

### login   admin@gmail.com
### pass    admin
### role    admin

### login   cook@gmail.com
### pass    cook
### role    NO_ROLE


### curl samples (application deployed in application context `vote`).
> For windows use `Git Bash`

#### _Login ROLE_USER
#### get All Restoran
`curl -s http://127.0.0.1:8080`

#### get Menu Restotan_id 100003
`curl -s http://127.0.0.1:8080/100003`

#### create/update Vote
`curl -s -X POST -d '{}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/100003`

#### update Vote
`curl -s -X POST -d '{"dateTime":"2020-01-11T10:30","description":"Update vote","restoran":100004}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/100004`

#### _Login ROLE_ADMIN
#### get All Users
`curl -s http://localhost:8080/api/v1/users`

#### get User 100001
`curl -s http://localhost:8080/api/v1/users/100001`

#### delete User 
`curl -s -X DELETE http://localhost:8080/api/v1/users/100002`

#### create User
`curl -s -X POST -d '{"name": "userTest","email":"test@gmail.com","password":"12345678","roles":["ROLE_USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/v1/users/`

#### put User
`curl -s -X PUT -d '{"name": "userTe","email":"tt@gmail.com","password":"123456789","role":"ROLE_ADMIN"}' -H 'Content-Type: application/json' http://localhost:8080/api/v1/users/100002`


#### get All Restoran
`curl -s http://localhost:8080/api/v1/restorans`

#### get Restoran 100003
`curl -s http://localhost:8080/api/v1/restorans/100003`

#### delete Restoran
`curl -s -X DELETE http://localhost:8080/api/v1/restorans/100004`

#### create Restoran
`curl -s -X POST -d '{"name":"Mandarin"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/v1/restorans/`

#### put Restoran
`curl -s -X PUT -d '{"name":"Grang"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/v1/restorans/100003`




#### get Meals not found
`curl -s -v http://localhost:8080/topjava/rest/profile/meals/100008`
