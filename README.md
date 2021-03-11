## Author.
* Kelvin Joshua Bale
* link : https://firstapiip.herokuapp.com/ 
##  Description.
* The project is a Java REST API application built with GSON serialization/deserialization library which converts Java Objects into JSON and back, thus one is able to query and receive information from the news organization's API.
##  Setup
* Clone this Repository.
* Open in IDE -intellij and navigate to root folder.
* Run database setup.
* Replace  machine credentials of user and password in DB.java ,comment out usages of connection string.
* Run,post man and replace post and get methods appropriately for creating Json objects,reference App.java.

## Miscellaneous
* Use http  in your routes while in postman.
##  Known bugs
* Association routes commented out to prevent get errors.
* The app works as excepted however error 404 in live link.
* Expected status code 201 ,got 200 from request meaning request succesfull but no resource was retrieved.
  * Below are the routes fired at postman with our app's live link.
   * path get("/news) ;
  ![herokulink1](https://user-images.githubusercontent.com/60692205/110860055-57ed2d00-82cd-11eb-8519-c7d156f38a7e.jpg)
  *  path get("/users);
  ![herokulink2](https://user-images.githubusercontent.com/60692205/110860538-f2e60700-82cd-11eb-94d8-0e1fc119ba4a.jpg)

## Database setup
* In terminal run psql < create.sql.- this creates a new databse with its fields.


## Technologies used
* Java.
* Postgres.
* Heroku.
* Postman.

## license.
copyright (c) 2021 kelvinjoshua

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
