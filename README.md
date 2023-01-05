#Courseproject for Mobile Software Engineering @ IU Hochschule

Dieses Projekt entstand  im Rahmen des Kurses Mobile Software Engineering IWMB2 an der IU Hochschule

Projektstart: 12.09.2022 

Lesson learned: 
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

- RoomDB
- Coroutines, Flows
- JoinQueries, CrossReferences
- Testing coroutines and DAO (UnitTest with Mockk, InstrumentedTest RoomDB)

- Emulator, Physical Device
- Gradle
- Git

Open lessons:
Statehandling 
Notifications 
Material Design
Animations
FirebaseDB
Login/Registration
Upload to AppStore
Compose

ScreenFlow:
NavBar -> ProfileScreen (User)
Upcoming Events (HomeScreen) --> EventFragment
EventFragment: -> (SuggestGamesDialog, FoodStyleDialog, HostratingDialog, DelayedDialog, AttendenceDialog, ProfileScreen (host of the event))

Open Todo's: 
Navigation:
- fix glitch in title

HomeScreen/UpcomingEvents:
- implement AddEvent

EventFragment: 
- Show chosen Foodstyles
- Show who comes to late
- Show who attends

EventFragment/SuggestGameDialog: 
- handle UI-state, when choosing a game and rotating the screen the state is lost
  
EventFragment/FoodStyleDialog:
- change fontColor to Material color (see SuggestGameDialog)
- handle UI-state, when choosing a foodStyle and rotating the screen the state is lost
- Save selected Foodstyle in the DB and add it to the EventScreen
  
EventFragment/AttendenceDialog:
- Fix it - app crashes

EventFragment/DelayedDialog:
- Fix it - it doesn't show anything
- handle UI-State

EventFragment/HostRatingDialog:
done

ProfileScreen: 
- fix it app crashes
- Make edit-button visible if the User overlooks his own profile
- Make edit-button invisible if the user overlooks the profile of the host of an event


Aufgabenstellung:
1.2. Aufgabenstellung 2: Board Gamer App
Eine Gruppe von Brettspielfans möchte ihren regelmäßigen abendlichen Spieltermin besser organisieren. Hierzu
haben die Spieler:innen folgende User Stories entwickelt:
– Als Spieler:in möchte ich immer wissen, wann und wo der nächste Spieltermin stattfindet, um ihn nicht
zu verpassen.
– Als Spieler:in möchte ich ein oder mehrere Spiele vorschlagen können, um den Spieleabend mitgestalten
zu können.
– Als Spieler:in möchte ich den/die Gastgeber:in, bewerten können.
– Als Spieler:in möchte ich meine Verspätung mitteilen können.
– Optional: Als Spieler:in möchte ich in Vorbereitung des Fast-Food-Bestellprozesses rechtzeitig daran
erinnert werden, meine Lieblingsessensrichtung zu wählen (Italienisch, Griechisch, Türkisch, usw.).
– Optional: Als Gastgeber:in möchte ich rechtzeitig über die mehrheitlich gewünschte Essensrichtung
informiert werden, um einen passenden lokalen Lieferdienst auszusuchen.
– Optional: Als Spieler:in möchte ich rechtzeitig das Menü des ausgewählten Lieferdienstes sehen können,
um rechtzeitig meine Bestellung an den/die Gastgeber:in übermitteln zu können.
