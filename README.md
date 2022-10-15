# Tinder-Project
Deployed on Heroku, you can see the project following this link:

https://agile-crag-34919.herokuapp.com/

Logins: 

yennefer.vengerberg@gmail.com

mia.wallace@gmail.com

tommy.kumar@gmail.com

joe.anthony@gmail.com

Password: 1111

-After the log in, you can like/dislike several profiles. When you have taken a decision on all the available profiles (around 10), you will be redirected to the "Messages" page, where you will see all the profiles that you have liked. Also you will be able to chat with them, and the conversations will be saved in the database.

/Technical specifications
- Application runs on Jetty webserver with Servlets. 
- MVC design pattern was used to split the application between DAO/Service/Controller layers.
- A filter was added which will redirect any  unauthorized request to the login page.
- FreeMarker was used to dinamically insert parameters into the HTML pages
- Postgre database provided by Heroku was used to store all the information
