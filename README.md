# Leave Management System (Java OOP)

A console-based Java application that manages employee records and leave requests. It demonstrates core Object-Oriented Programming concepts through an employee inheritance hierarchy and employee-specific leave policies.

## Features

- Register and display employees.
- Support Full-Time, Part-Time, Intern, and Manager employee types.
- Apply for leave with an optional reason.
- Cancel an approved leave request and restore the leave balance.
- View current leave balance and leave history.
- Enforce employee-specific maximum consecutive leave limits.
- Validate input and handle invalid operations with exceptions.

## Leave policy used in this assignment

These are example rules chosen for the assignment, not real company policy.

| Employee type | Initial annual balance | Maximum consecutive days |
|---|---:|---:|
| Full-Time | 20 | 10 |
| Part-Time | 10 | 5 |
| Intern | 5 | 2 |
| Manager | 30 | 15 |

A leave request is approved immediately in this simplified demonstration. A production system would normally include dates, a pending/approval workflow, holidays, accrual, and persistence.

## Requirements

- Java 17 or later
- Maven (optional; direct `javac` compilation also works)

## Run the application

### Option 1: Maven

```bash
mvn clean package
mvn exec:java -Dexec.mainClass="com.company.leave.Main"
```

If the Maven exec plugin is not available in your local setup, use:

```bash
mvn clean package
java -cp target/classes com.company.leave.Main
```

### Option 2: Java compiler

From the project root:

```bash
mkdir -p out
javac -d out src/main/java/com/company/leave/*.java
java -cp out com.company.leave.Main
```

On Windows PowerShell, you can use:

```powershell
New-Item -ItemType Directory -Force out
javac -d out src/main/java/com/company/leave/*.java
java -cp out com.company.leave.Main
```

## Project structure

```text
LeaveManagementSystem/
├── docs/
│   └── class-diagram.puml
├── src/main/java/com/company/leave/
│   ├── Employee.java
│   ├── FullTimeEmployee.java
│   ├── PartTimeEmployee.java
│   ├── Intern.java
│   ├── Manager.java
│   ├── LeaveRequest.java
│   ├── LeaveStatus.java
│   ├── LeaveManagementSystem.java
│   └── Main.java
├── sample-output/
│   └── console-output.txt
├── pom.xml
└── README.md
```

## Class design and relationships

- `Employee` is an abstract base class containing shared employee data and leave operations.
- `FullTimeEmployee`, `PartTimeEmployee`, `Intern`, and `Manager` extend `Employee` and provide their own leave rules.
- `LeaveRequest` stores the details and status of an individual request.
- `LeaveStatus` represents the request state (`APPROVED` or `CANCELLED`).
- `LeaveManagementSystem` manages employee registration and lookup.
- `Main` creates sample employees and demonstrates the application.

See [`docs/class-diagram.puml`](docs/class-diagram.puml) for the UML class diagram source. It can be rendered with PlantUML or a compatible editor.

## OOP concepts demonstrated

1. **Encapsulation:** Employee fields and leave-request fields are private. State is accessed through public methods. The leave-request list is exposed as an unmodifiable view.
2. **Inheritance:** The four employee types extend the common `Employee` class.
3. **Polymorphism:** `LeaveManagementSystem` stores employees using the base `Employee` type. The same method calls work with every subtype.
4. **Abstraction:** `Employee` is abstract and requires each subtype to define its employee type and maximum consecutive leave duration.
5. **Method overriding:** Each subtype overrides `getEmployeeType()` and `getMaximumConsecutiveLeaveDays()`.
6. **Method overloading:** `applyLeave(int)` and `applyLeave(int, String)` provide two ways to apply. `cancelLeave(int)` and `cancelLeave(LeaveRequest)` provide two cancellation signatures.
7. **Access modifiers:** Private fields protect state; protected constructors support subclass initialization; public methods form the class interface.

## Change request: Manager

The Manager class was added without changing the leave-management workflow. It extends `Employee`, inherits common behaviour, and overrides the policy methods. Its initial balance is 30 days and its maximum consecutive leave duration is 15 days. This keeps the existing design reusable.

## Refactoring notes

The following improvements were made:

| Improvement | Before / problem | Refactored solution | Benefit |
|---|---|---|---|
| Shared employee behaviour | Each employee type could duplicate leave logic | Common validation, balance updates, cancellation, and display logic live in `Employee` | Less duplicate code |
| Employee-specific rules | Leave limits could be scattered through conditionals | Each subtype overrides policy methods | Easier to add employee types |
| Separate responsibilities | Employee registry and leave-request data were mixed into the demo | `LeaveManagementSystem` manages employees; `LeaveRequest` stores request data | Clearer class responsibilities |
| Encapsulation | Direct changes to balance or request status could create inconsistent state | Private fields and controlled methods | Safer state changes |
| Validation and exceptions | Invalid days, duplicate IDs, and missing employees could go unnoticed | Explicit validation and meaningful exceptions | More predictable failures |
| Naming and constants | Policy values could be unexplained literals | Descriptive constant names | Easier maintenance |

## Sample execution

A complete sample run is stored in [`sample-output/console-output.txt`](sample-output/console-output.txt). Run the program locally to generate a fresh console output. The request date will reflect the date on which it is run.

## Git practice

The repository is organized so that the work can be committed in meaningful stages. Suggested commit sequence:

```bash
git init
git add README.md pom.xml docs
git commit -m "Initial project design and class diagram"

git add src/main/java/com/company/leave/Employee.java
git commit -m "Add abstract Employee base class"

git add src/main/java/com/company/leave/LeaveRequest.java src/main/java/com/company/leave/LeaveStatus.java
git commit -m "Implement leave request tracking"

git add src/main/java/com/company/leave/FullTimeEmployee.java src/main/java/com/company/leave/PartTimeEmployee.java src/main/java/com/company/leave/Intern.java
git commit -m "Add employee inheritance and leave policies"

git add src/main/java/com/company/leave/LeaveManagementSystem.java src/main/java/com/company/leave/Main.java
git commit -m "Implement employee registry and polymorphic demo"

git add src/main/java/com/company/leave/Manager.java
git commit -m "Add Manager employee type"

git add sample-output
git commit -m "Document sample execution and refactoring"

git status
git log --oneline
```

The downloadable project includes a Git repository with staged development commits. If you prefer to create the history yourself, delete the `.git` directory before running the commands above.

## Upload to GitHub

1. Create a new empty repository on GitHub.
2. Extract this project ZIP.
3. Open a terminal in the extracted `LeaveManagementSystem` folder.
4. If retaining the included Git history, run:

```bash
git remote add origin https://github.com/YOUR_USERNAME/LeaveManagementSystem.git
git branch -M main
git push -u origin main
```

Replace `YOUR_USERNAME` with your GitHub username. Do not create a README on GitHub if you are pushing this existing repository.

## Scope and limitations

This is an educational console application. It uses in-memory storage, so employee and leave data reset when the program exits. It does not include login, database persistence, date-range overlap checks, weekends/holidays, or a manager approval workflow.
