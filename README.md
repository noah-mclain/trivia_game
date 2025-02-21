# Trivia Game
Triviagame is a trivia game application built using Java and JavaFX. It supports both single-player and multiplayer modes, with real-time multiplayer and an AWS-hosted database.

## Features
- **Single-Player Mode:** Choose a genre and answer 10 questions.
- **Multiplayer Mode:** The host selects a genre for all players.
- **Chat Functionality:** Real-time messaging in the waiting room.
- **Dynamic Waiting Room:** "Is typing" notifications, audio cues, and visual feedback.
- **JavaFX UI:** Responsive interfaces built with FXML.
- **Sound Effects:** Engaging audio cues for interactions.


## Game Mechanics
### Genre Selection
- **Single-Player:** The player selects a genre.
- **Multiplayer:** The host selects the genre for all players.

### Game Flow
1. The selected genre determines **10 sequential questions**.
2. Players answer, and scores are recorded in real-time.

### Multiplayer & Database
- **Networking:** Players connect to a shared AWS-hosted database.
- **Room Management:** The database stores room numbers and question IDs.
- **Setup:** Update AWS credentials in `DatabaseConnection`.

## Installation
1. **Clone the Repository**
   ```bash
   git clone https://github.com/noah-mclain/trivia_game && cd Triviagame

## Game Preview
![Home Page](/readmeMedia/trivia1.png)
![Seclection Page](/readmeMedia/trivia2.png)
![Genre Selection Page](/readmeMedia/trivia3.png)
![Question Page](/readmeMedia/trivia4.png)
![End Screen](/readmeMedia/trivia5.png)

>Project Done By:
>- Nada Ayman, 221007645
>- Zeina Ahmed, 221017888
>- Rola Khaled, 221017871
>- Omar Khaled, 221027817
