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

#### get All Restoran
`curl -s http://127.0.0.1:8080`

#### get Menu Restotan_id 100001
`curl -s http://127.0.0.1:8080/100001`

#### get All Users
`curl -s http://localhost:8080/api/v1/users`

#### get User 100001
`curl -s http://localhost:8080/api/v1/users/100001`

#### filter Meals
`curl -s "http://localhost:8080/topjava/rest/profile/meals/filter?startDate=2015-05-30&startTime=07:00:00&endDate=2015-05-31&endTime=11:00:00"`

#### get Meals not found
`curl -s -v http://localhost:8080/topjava/rest/profile/meals/100008`

#### delete Meals
`curl -s -X DELETE http://localhost:8080/topjava/rest/profile/meals/100002`

#### create Meals
`curl -s -X POST -d '{"dateTime":"2015-06-01T12:00","description":"Created lunch","calories":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava/rest/profile/meals`

#### update Meals
`curl -s -X PUT -d '{"dateTime":"2015-05-30T07:00", "description":"Updated breakfast", "calories":200}' -H 'Content-Type: application/json' http://localhost:8080/topjava/rest/profile/meals/100003`