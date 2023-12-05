# waste-and-waist
Waste &amp; Waist

## Modules

### User

Basic User Management

### Household

Keep track of members that frequently eat together.

### Stock

Keep track of all products available within a household.
Stock is responsible to warn other services when a product is going to spoil.

### Store

Keep track of all available products in stores.
Similar products are grouped within the concept of a more generic ingredient.

### Planner

Let households plan their meals together.
Reserve/Request products in stock/store based on chosen recipes.

### Kitchen

Repository of recipes based on ingredients.
Keep track of cooking steps. (GPS like)

### Notification

Asynchronous communication of required interventions towards the user.

E.g. An item in stock has disappeared (eaten, spoiled...), but is required for a planned dish in the near future.

### Web

The UI.

### App

An executable jar to run the application.

## Getting Started

### Install Scoop (Windows only)

#### Windows

1. Open a PowerShell terminal.
2. Run:

          $ Set-ExecutionPolicy RemoteSigned -Scope CurrentUser # Optional: Needed to run a remote script the first time

          $ irm get.scoop.sh | iex

### Install Brew (Mac OS X only)

#### Mac OS X

1. Open a PowerShell terminal.
2. Run:

          $ /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

### Install Kubectl

#### Windows

    $ scoop install kubectl

#### Mac OS X

    $ brew install kubectl

### Install Helm

#### Windows

    $ scoop install helm

#### Mac OS X

    $ brew install helm

### Install Okteto

#### Windows

    $ scoop install okteto

#### Mac OS X

    $ brew install okteto

### Okteto

#### Configuration with Okteto Cloud

The first thing you need to do is configure Okteto CLI to use Okteto Cloud.
To do this, run the command below:

    $ okteto context use https://cloud.okteto.com

#### Launch

    $ git clone <YOUR_REPOSITORY>
    $ cd <your_repo_directory>

    $ okteto up

## Set Up (once done, no longer needed)

    $ helm create charts

    $ okteto init