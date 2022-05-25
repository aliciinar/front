# Ceng453-TermProject-Group-4-frontend

Game Play:
In order to start game we should login or register after that we can start game with play single button. In the upper part of the game scene we can see player information which are money of the player and score of the player.Also we can see current user name in current user panel. Actions of the player and button actions are explained below.


End Turn Button: This button should pressed in order to finish the current player turn. If player do not prefer to buy a purchase, player should press end turn button.Player should press end turn button when bot turn finished.

Purchase Button: This button activate when player on a deed which do not have an owner and player money is more than deed cost. Press this button in order to buy the deed.

Jail Time Button: If a player in jail, in order to stay in jail, he must press jail time button.After pressing he must press end turn button in order to finish the turn. Player must press this button if bot in jail too.

Player Movement : Game play is continue sequentially. User start to play with pressing roll button. According to the roll value player image will move. If player stop on a deed which is not purchased purchase button will be active automatically. Player can prefer to buy deed with pressing purchase button. Player can end its turn with end turn button. Player must finish its turn with end turn button. If player is on a deed and not prefer to buy a purchase it should press end turn button. If player in jail it must be press jail time button in order to play one turn in jail.When a player money is less than 0 when we want to press end turn game finished and game score panel opened.

Bot Movement:Bot roll and move automatically. Bot buy deed if its money is bigger than 300. In order to finish bots turn player must press end turn button since we use thread for move on game board animation and if we do not control thread make mistakes on render, we can not handle that problem. Also player must to play jail time button when bot is on prison. Property owners, Railways, Ferry port rent prices and user information updated dynamically in GUI when something happen in the game.

Login and Register Game:

Register:In order to register game we need to set username, password and email. If we already register with same email we can not register and we will be warn.

Login:In order to login the game we should give email and username. If we do not register this account  we will be warn.

Forget Password:We use 453group4@gmail.com for sending reset link to the user mail. Because of security issues  this mail security settings should always change before the sending  forgot link.We change security settings but this settings always changing to default settings.https://help.streamingvideoprovider.com/en/articles/5013317-setting-up-an-smtp-server-with-gmail this link should be followed in order to change the settings. mail : 453group4@gmail.com password: prj_group4 