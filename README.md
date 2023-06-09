# **Text-based Adventure Game**

I want to make a text-based adventure game.

I hope to create a text-based simulation that offers branching choices for the player to select to continue the story. I want to add a save function where the user can save their progress and select their save file from a list of saves to continue their game.
## Application details:

- This application would allow the user to play an adventure game, where items can be picked up and stored in a list in order to be used later in the run.
- People who want to play an adventure game will use this application
- This project is of interest to me because I enjoy playing games, and I love writing stories. 

## User Stories

- As a user, I want to be able to play an adventure game with a branching storyline.
- As a user, I want to be able to add items into my inventory.
- As a user, I want to be able to make choices that influence the course of the story.
- As a user, I want to be able to save my game.
- As a user, I want to be able to reload my latest save.

## Instructions for Viewer

- You can add an arbitrary number of weapons to the inventory by locating the inventory button at the bottom of your screen, clicking it, and click the "add a useless weapon" button. Here you will be able to see items added to your inventory, as well as previously picked up items. (To pick up items, just progress through the game. You will have an option to grab an item at the beginning and in the first floor of the Nest.) ((Resize the windows if necessary))
- On the right of the item list, you will find a stat that shows you the strongest weapon in your inventory based on their weapon damage. You will see its weapon damage right beside it. Weapon damage numbers are slightly randomized, so feel free to click around and see how strong each weapon is.
- Currently, the button that allows you to win the game (Go to the roof) is not operational.
- You can save the state of the application by clicking on the save game button. (Inventory items will be saved)
- You can load the state of the application by clicking on the load game button.
- - This game is made for the purpose of familiarizing myself with program design principles, therefore it is very simple and relatively unpolished.

## Inventory explanation

- Nothing will be logged when initially opening the application. This is because nothing has been added into your inventory yet. The EventLog will only log events related to adding weapons into the inventory.
- To see the events logged, try choosing machete as the first choice. This will add the machete into your inventory and set your strongest weapon to machete. 
- Sample:
  Thu Dec 01 01:29:58 PST 2022
  Added machete to inventory 

  Thu Dec 01 01:29:58 PST 2022 
  Strongest weapon changed to machete(1 damage) 
- Go to the Nest, then to the first floor, then grab a rusty knife. This will add a rusty knife into your inventory and set the strongest weapon to rusty knife.
- Sample:
  Thu Dec 01 01:30:04 PST 2022
  Added rusty knife to inventory

  Thu Dec 01 01:30:04 PST 2022
  Strongest weapon changed to rusty knife(3 damage)
- Go into inventory and add a weapon into your inventory. This will add my tears into the inventory, but the strongest weapon remains the same.
- Sample:
  Thu Dec 01 01:30:12 PST 2022
  Added the tears of the one who coded this to inventory

## Improvements that could be made
- I would like to improve the GUI. The inventory tab needs to be resized, and I'd like to make it so that when a new item is added into the inventory, the inventory just updates and doesn't pop out a new tab. Furthermore, I'd like the same with the gameplay - when a new scene is played, I'd like the frame to update rather than have a new frame pop up.
- For the loadGame() and actionPerformed() functions, I want to optimize the code in a way that allows me to add more scenes into the game without needing to add new cases in the function to account for them, because currently every case has been manually entered.
- Removal of the unused player health system.

 
