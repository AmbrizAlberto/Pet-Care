# Spike
Spike: Veterinary Services and Pet Care

## Objectives and/or SDG Topics Addressed: 
The &quot;Spike&quot; is aligned with the
Sustainable Development Goal (SDG) number 3: &quot;Health and Wellbeing&quot;. It has like
main objective to improve the quality of life of pets and facilitate access to
quality veterinary services through a platform that centralizes the management and
pet care.

## Detailed Description of the Project:
The &quot;Spike&quot; looking to develop an application
mobile that acts as a comprehensive manager for veterinary clinics and care services
of pets. The problem it solves is the lack of a centralized system and
accessible for pet owners to manage appointments, maintain a history
doctor, and contract additional services efficiently. This platform also
will allow veterinarians and clinic staff to effectively manage their
services, clients and the staff involved.

## Main features of the system:
- Main Administrator: Management of the entire system with access to all the functionalities.
- Customer Registration and Pet Data: Secure storage and Organized information about clients and their pets.
- Management of Contracted Services: Creation, consultation, modification, and
elimination of services, clients and pets.
- User Roles: Differentiation of access and functionalities according to the user's role: user (administrator, veterinarian, support staff, client).
- Report Generation: Creation of detailed reports on the use of the services, pet health, and internal management of staff and clients.

## Technology Stack:
- **Front-end:**  
  Mobile development using **Kotlin Native**, ensuring compatibility with Android devices for user experiences.
- **Back-end:**  
  Developed using **Node.js** with **Prisma** and **Express** for scalable server-side functionality.
- **Database:**
  **PostgreSQL** with **Supabase** hosting for flexible and efficient storage of client, pet and clinic data.
- **Cloud Services:**  
  - API hosted on **Vercel**: [https://api-spike-five.vercel.app](https://api-spike-five.vercel.app). For more information, consult the API documentation [API Spike](https://github.com/MartinFitS/api_spike) 
  - Images and other assets hosted on **Cloudinary** for efficient media management.

## Impact:
The development of this application will have a positive impact by facilitating communication.
between veterinarians and pet owners, as well as by improving internal management of
veterinary clinics, contributing to better animal care and well-being.

## Prerequisites

- [Android Studio](https://developer.android.com/studio)
- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

## Project Setup

1. Clone the repository:
    ```sh
    git clone <REPOSITORY_URL>
    cd Spike
    ```

2. Open the project in Android Studio:
    - Click on `File > Open...`
    - Select the `Spike` directory

3. Sync the project with Gradle:
    - Click on `File > Sync Project with Gradle Files`

## Project Structure

- **app/**: Contains the application source code.
  - **src/**: Source code and resources of the application.
  - **build.gradle.kts**: Gradle configuration specific to the `app` module.
  - **proguard-rules.pro**: ProGuard rules for code optimization and obfuscation.

- **gradle/**: Gradle configuration at the project level.
  - **libs.versions.toml**: Dependency versions file.
  - **wrapper/**: Gradle wrapper files.


- **build.gradle.kts**: Gradle configuration at the project level.
- **gradle.properties**: Gradle configuration properties.
- **settings.gradle.kts**: Project module settings.

## Build and Run

To build and run the application in Android Studio:

1. Open the project in Android Studio.
2. Click on the `Run` button in the toolbar or press `Shift + F10`.

## Members:
- [@JDAA4](https://www.github.com/JDAA4)
- [@AmbrizAlberto](https://www.github.com/AmbrizAlberto)
- [@Paco-Taco](https://www.github.com/Paco-Taco)
- [@arielrosasc](https://www.github.com/arielrosasc)
- [@CitlalyEstefania](https://www.github.com/CitlalyEstefania)
- [@MartinFitS](https://www.github.com/MartinFitS)
