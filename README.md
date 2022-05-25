# Ceng453-TermProject-Group-4-frontend

#Demo video
* https://youtu.be/GDoJhrHV1DU
#Requirements
* To play the game please run **ClientApplication.java** file under the src/main/java/com/client/ClientApplication.java
* Please read forget password section in the need of password reset.
* First time user open the game there will be some lag. This is due to heroku connection. So for the first time please wait after pressing the button. If there is no response after some time please restart the game.

#Game Play:
In order to start game we should login or register after that we can start game with play single button. In the upper part of the game scene we can see player information which are money of the player and score of the player.Also we can see current user name in current user panel. Actions of the player and button actions are explained below.


#End Turn Button: 
This button should pressed in order to finish the current player turn. If player do not prefer to buy a purchase, player should press end turn button.Player should press end turn button when bot turn finished.

#Purchase Button: 
This button activate when player on a deed which do not have an owner and player money is more than deed cost. Press this button in order to buy the deed.

#Jail Time Button: 
If a player in jail, in order to stay in jail, he must press jail time button.After pressing he must press end turn button in order to finish the turn. Player must press this button if bot in jail too.

#Player Movement :
Game play is continue sequentially. User start to play with pressing roll button. According to the roll value player image will move. If player stop on a deed which is not purchased purchase button will be active automatically. Player can prefer to buy deed with pressing purchase button. Player can end its turn with end turn button. Player must finish its turn with end turn button. If player is on a deed and not prefer to buy a purchase it should press end turn button. If player in jail it must be press jail time button in order to play one turn in jail.When a player money is less than 0 when we want to press end turn game finished and game score panel opened.

#Bot Movement:
Bot roll and move automatically. Bot buy deed if its money is bigger than 300. In order to finish bots turn player must press end turn button since we use thread for move on game board animation and if we do not control thread make mistakes on render, we can not handle that problem. Also player must to play jail time button when bot is on prison. Property owners, Railways, Ferry port rent prices and user information updated dynamically in GUI when something happen in the game.


#Register:
In order to register game we need to set username, password and email. If we already register with same email we can not register and we will be warn.
After successful register user will be sent to log in screen.

#Login:
In order to log in the game we should give email and username. If we do not register this account  we will be warn.

#Forget Password:


In order to use forget password section. Admin must follow this link **https://help.streamingvideoprovider.com/en/articles/5013317-setting-up-an-smtp-server-with-gmail** after logged in as **453group4@gmail.com** with the password **prj_group4**. This part is important since heroku had issues with the mail security and for some reason disabling the security settings from the link once did not give permanent solution.
After certain time admin must click on the button in the link.

#LeaderBoards
User can see the leaderboard after logged in from LeaderBoard button