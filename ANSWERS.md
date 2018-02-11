1. .gitignore contains extensions, commands, actions, directories, data, logs, etc
    that we want GitHub to ignore when we commit and push our project to GitHub.
    
2.  Gradle helps us handle multiple projects and determines which parts are up to 
    date and what dependencies are needed and those dependencies are stored in the
    build.gradle file.
    
3.  The purpose of Travis CI is to keep track of our project history so that we can
    track it's history of development and make changes where necessary. Travis CI
    also shows us whether our projects tests pass or fail.
    
4.  A route is made up of three parts. The verb, path, and callback. The verb, like
    get() takes input that it can do something with. Then it is given a path such as
    /hello, then returns a callback of where it is being routed to.

5.  The umm3601.Server class handles redirecting and routing to different places.
    The umm3601.user.UserController class manages requests for info about users.
    The page users takes us to the users page with nothing in it. Api/users will
    show us a list of all the users. Putting ?age=25 will filter them by showing
    us all users aged 25 first. Then, the long id 588935f5de613130e931ffd5 shows us
    the id of one specific user.
    
6.  The contents are all of the visible website stuff that you can see when opening
    in a browsers. The css is the styling of the html and javascript helps perform
    verbs or actions. The three html files, about index and users are each their
    own individual file/page that you can navigate to and edit.
    
7.  If we are filtering with the age 25 everybody aged 25 will be listed. We
    can still see all of the users. The request that is sent to the server is to
    show all the people with age 25 to be displayed first and the rest are shown
    according to their id, which is done by giving it /api/users?age=25. It receives
    a JSON object as a database with information on users.

8.  It is at src/main/resources/javascript/users.js.
