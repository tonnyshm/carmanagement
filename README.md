# carmanagement

My project is a car management app , where a user will signup first and then authenticate a user while logging in , after a user login he is allowed to see his/her dashboard which consist of 5 nav bars (Home, My Car, report, schedule, Due maintenance, logout)   “My Car” is a drop down Menu where a user could add a car , upload its picture car, add fuel usage, add car expenses, and car maintenance and view his car photo and delete or download it if they want , in “Report” a user will insert his/her email and all reports including expenses, fuel usage, due maintenance and his cars and their details will be emailed to his/her account in a good detailed pdf

In “Schedule” a user is allowed to schedule a maintenance of his chosen car and spring schedule will schedule the maintenance every 3 months

In “Due maintenance” a user is allowed to see his/her pending maintenance with their due dates and complete them if he/she want


Then finally in “logout” section a user logout of their account

All this functionality has  CRUD operations on top of it so a User is allowed to make modifications all they want, delete and view their data in a clean user friendly interface

Note that here we have implemented a fully functional spring security meaning every User will see their respective data no User is allowed to see others, data is pulled from the db using logged in User instance.

⇨All endpoints are secured by spring security 


⇨I have used java spring boot and thymeleaf frameworks to implement all of this 

And i used spring security to implement the authentication and sessions and filtering to secure all endpoints 

⇨Used spring schedule class to implement the schedule tasks

⇨Used itext , pdf and java Mail libraries to implement the Report functionality

⇨Spring Caching , validation and error handling are present 
