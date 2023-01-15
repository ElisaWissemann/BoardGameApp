# Courseproject for Mobile Software Engineering 
## @ IU Internationale University of applied science

Dieses Projekt entstand  im Rahmen des Kurses Mobile Software Engineering IWMB2 an der IU Hochschule.

Projektstart: 12.09.2022 

## Lesson learned: 
- Android Architecture (MVVM)   
- SingleActivityApplication  
- Android Lifecycles  
- DataBinding, ViewBinding  
- LiveData, StateFlow   
- ConstraintLayout  
  
- Navigation with NavArgs  
- Fragments   
- DialogFragment  
- Spinner  
- RecyclerView, AdapterPattern and ListAdapterPattern  
- Toast   
- Notifications  
  
- RoomDB  
- Coroutines, Flows  
- JoinQueries, CrossReferences  
- Testing coroutines and DAO (UnitTest with Mockk, InstrumentedTest RoomDB)  
  
- Emulator, Physical Device  
- Gradle  
- Git  
  
## Open lessons:
- State-handling     
- Material Design     
- Animations    
- FirebaseDB    
- Login/Registration    
- Upload to AppStore    
- Compose    

## ScreenFlow:
NavBar -> ProfileScreen (User)  
Upcoming Events (HomeScreen) --> EventFragment  
EventFragment: -> (SuggestGamesDialog, FoodStyleDialog, HostratingDialog, DelayedDialog, AttendenceDialog, ProfileScreen (host of the event))  

## How to use the App:
When you start the app your User's name is Steve. Choose an event on the startscreen.   
In the event click:  
#### 1st Button: Suggest a game
--> Choose a game and click confirm, you will get back to the Eventscreen, there you can see all suggested events  
#### 2nd Button: select a foodstyle
--> choose a foodstyle and click confirm, you will get back to the Eventscreen, there all selected Foodstyles will be displayed  
#### 3rd Button: attendence
--> If you click accept or refuse, the dialog will be closed and you ll be back on the Eventscreen, if you reopen the attendenceDialog you can see that Steve was added or deleted at the field attend/cancelled  
#### 4th Button: I'm delayed
--> inProgress  
#### 5th Button: HostRating
--> If you add a rating to the HostRatingDialog and submit it, the dialog will be closed again. Opening it again shows you the new rating  

## Open Todo's:
Navigation:
- fix glitch in title

#### HomeScreen/UpcomingEvents:
- implement AddEvent

#### EventFragment: 
- Show chosen Foodstyles
- Show who comes to late
- Show who attends

#### EventFragment/SuggestGameDialog: 
- handle UI-state, when choosing a game and rotating the screen the state is lost

#### EventFragment/FoodStyleDialog:
- change fontColor to Material color (see SuggestGameDialog)
- handle UI-state, when choosing a foodStyle and rotating the screen the state is lost
- show selected FoodStyle to the EventScreen

#### EventFragment/AttendenceDialog:
- handle UI-state

#### AttendenceViewModel:
- overwork updatedEventWithAttendence Method

#### EventFragment/DelayedDialog:
- implementLogic
- handle UI-State

#### EventFragment/HostRatingDialog:
done

#### ProfileScreen: 
- fix it app crashes
- Make edit-button visible if the User overlooks his own profile
- Make edit-button invisible if the user overlooks the profile of the host of an event

#### Repository: 
- overwork namingconvention


## Aufgabenstellung: Board Gamer App
Eine Gruppe von Brettspielfans möchte ihren regelmäßigen abendlichen Spieltermin besser organisieren. Hierzu
haben die Spieler:innen folgende User Stories entwickelt:  
- Als Spieler:in möchte ich immer wissen, wann und wo der nächste Spieltermin stattfindet, um ihn nicht
zu verpassen.
- Als Spieler:in möchte ich ein oder mehrere Spiele vorschlagen können, um den Spieleabend mitgestalten
zu können.
- Als Spieler:in möchte ich den/die Gastgeber:in, bewerten können.
- Als Spieler:in möchte ich meine Verspätung mitteilen können.
- Optional: Als Spieler:in möchte ich in Vorbereitung des Fast-Food-Bestellprozesses rechtzeitig daran
erinnert werden, meine Lieblingsessensrichtung zu wählen (Italienisch, Griechisch, Türkisch, usw.).
- Optional: Als Gastgeber:in möchte ich rechtzeitig über die mehrheitlich gewünschte Essensrichtung
informiert werden, um einen passenden lokalen Lieferdienst auszusuchen.
- Optional: Als Spieler:in möchte ich rechtzeitig das Menü des ausgewählten Lieferdienstes sehen können,
um rechtzeitig meine Bestellung an den/die Gastgeber:in übermitteln zu können.
