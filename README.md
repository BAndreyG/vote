До писать curl протестировать его!
обложить тестами классы

почитать задание! 


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

#### create Vote
`curl -s -X POST -d '{"dateTime":"2020-01-11T10:00","description":"Created vote","restoran":100003}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/100003`

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
`curl -s -X POST -d '{"dateTime":"2020-01-11T10:00","description":"Created user","name":"user1"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/v1/users/`

#### get All Restoran
`curl -s http://localhost:8080/api/v1/restorans`

#### get Restoran 100003
`curl -s http://localhost:8080/api/v1/restorans/100003`

#### delete Restoran
`curl -s -X DELETE http://localhost:8080/api/v1/restorans/100003`

#### create Restoran
`curl -s -X POST -d '{"dateTime":"2020-01-11T10:00","description":"Created restoran","name":"mandarin"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/v1/restorans/`



#### get Meals not found
`curl -s -v http://localhost:8080/topjava/rest/profile/meals/100008`
