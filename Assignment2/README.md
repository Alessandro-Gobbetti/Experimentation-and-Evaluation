# Experimentation and Evaluation
## camelCase vs kebab-case

In this experiment, we compare the two most popular naming conventions for variables and functions in programming languages: camelCase and kebab-case.

The users are asked to complete a form with their information and to complete quiz where we measure their performance in picking the correct spelled identifier.

This quiz is available at the following link: https://alessandro-gobbetti.github.io/Experimentation-and-Evaluation/

This repository contains the code, data and report for the Experiment.


## Replicating the experiment

To run the frontend server locally, you need to navigate to the `Assignment2/frontend` folder and install the dependencies:

```bash
yarn install
```

Then, you can run the server by running the following command:

```bash
yarn run serve
```

Once the users have completed the quiz an automatic email is sent to you with the results.
In order to receive the emails, you need to properly set up `email.js` with a proper email `SERVICE_ID`, `TEMPLATE_ID` and `USER_ID` (see [email.js](https://www.emailjs.com/docs/sdk/send/) for more information).

The results for the experiment are stored in the `/frontend/src/assets/data.json` file.
From this file, the plots are automatically generated and diplayed in the main page of the frontend.