# SPELL-Self-Processing-English-Language-Lender

<div style="text-align: center;">
  <img src="readme/SPELLLogo.png" width="50%" />
</div>

## Info

- **Developer:** [Aron Arboleda](https://github.com/Aron-Arboleda) (Lead)
- **Project Started:** May 2024
- **License:** [LICENSE](./LICENSE)

## A Java Swing desktop app that can manually and automatically edit text, and also provides grammar checking and spelling correction.

## Features

- **Manual Text Editing:**
  - Case converter
  - Space remover
  - Alphabetizer
  - Bullet editor
- **Automatic Text Editing:**
  - Continuously checks clipboard for copied/cut text (every 500ms).
  - Formats text in the background.
  - Edited text is available on paste.
- **Grammar and Spelling Correction:**
  - Powered by the [LanguageTool API](https://github.com/languagetool-org/languagetool).

### Planned Features

- Signing the exe file to recognize as a safe application
- Bug Fixing
- Adding a database to apply history feature

## Tech Stack

- **Language:** Java
- **Framework:** Swing (for GUI)
- **Libraries:**
  - [LanguageTool API](https://github.com/languagetool-org/languagetool) (for grammar and spelling)
- **Build Tool:** Maven

## Files Directory Structure

```
├── LICENSE
├── pom.xml
├── README.md
├── config/
└── src/
    ├── main/
    │   ├── java/
    │   └── resources/
    └── test/
        └── java/
```

## Instructions

### To use the app

- download and run the **setup file** from the releases tab.
- It will display a lot of caution and warning messages because i don't know for the life of me how to make the program to be recognized as a safe application (sorry i'm a noob at deploying apps) but just click the "keep anyway" button or anything else to continue downloading the file. Then when the file is downloaded, you can run the installer, it will ask for admin priviledges to run the file(because it will place the program on the Program Files folder of your pc, it needs permission to do that), then windows defender thing will pop up, saying it's not a safe file or something, just click "More info" and continue the application installment. Go through the installer and after that, the program should be installed on your end.

## SPELL Team

Front-end Designers:

- [Lester](https://github.com/L-E-S-T-E-R)
- [Alex](https://www.instagram.com/lexsusicat)

Back-end Handlers:

- [Aron](https://github.com/Aron-Arboleda)
- [Hannah](https://github.com/404hannah)

Documentation:

- [Jenny](https://www.instagram.com/jentiglao_)
- [Victoria](https://github.com/vic-7oria)

## Images

<div style="text-align: center;">
  <img src="readme/LoadingPage.png" width="49%" />
  <img src="readme/InstructionsPage.png" width="49%" />
</div>

<div style="text-align: center;">
  <img src="readme/HomePageDark.png" width="49%" />
  <img src="readme/HomePageLight.png" width="49%" />
</div>

<div style="text-align: center;">
  <img src="readme/ManualPage.png" width="100%" />
</div>

<div style="text-align: center;">
  <img src="readme/AutomaticPage.png" width="100%" />
</div>

<br>&copy; 2025 Aron-Arboleda. All rights reserved.
