<template>
  <div class="w-100 align-center text-center body">
    <div
      v-if="currentQuestion === 0"
      class="h-100 w-100 align-center text-center d-flex flex-column mt-16"
    >
      <h1 class="cap">The Experiment</h1>
      <p class="w-50 text mb-2 mt-2">
        The aim of this experiment is to evaluate the readability of
        <b>camelCase</b> and <b>kebab-case</b> coding styles. Please,
        <b>read the following instructions carefully </b>before starting the experiment.
      </p>

      <!-- quick explanation on camelCase and kebab-case -->
      <h2 class="w-50 cap" style="text-align: justify; margin-left: -2rem">The topic</h2>
      <p class="w-50 text mb-2">
        CamelCase and kebab-case are two different naming conventions used for variables
        functions, and other identifiers in programming languages. <br />
        A camelCase identifier is a compound word or phrase in which each word or
        abbreviation begins with a capital letter, with no intervening spaces or
        punctuation, such as <code> myCamelCaseIdentifier</code>. <br />

        A kebab-case identifier, instead, is a compound word or phrase in which each word
        or abbreviation is separated by a hyphen, with no intervening spaces or
        punctuation, such as <code>my-kebab-case-identifier</code>.
      </p>

      <h2 class="w-50 cap" style="text-align: justify; margin-left: -2rem">The Quiz</h2>
      <p class="w-50 text mb-2">
        You will be asked to fill one form with some general information about you, then
        you will be asked to answer <b>{{ this.questions.length }} questions </b>.
      </p>

      <p class="w-50 text mb-2">
        At the beginning a short phrase is shown to you, then, when you are ready, you can
        proceed to the quesiton: 4 answers are shown to you, you have to choose the
        correct one (only one is correct) <b>as fast as possible</b>, but without making
        mistakes.
      </p>

      <p class="w-50 text mb-2">
        Since the results depend on your performance, we kindly ask you to focus during
        the experiment and <b>avoid any external distractions</b>. For a better
        experience, we recommend you to use a desktop computer.
      </p>

      <p class="w-50 text mb-2">
        The experiment will take about 5 minutes. At the end of the experiment, you will
        see a chart with the time you needed to answer each question.
      </p>

      <v-btn class="ma-8" variant="tonal" color="blue" @click="this.currentQuestion = -2">
        Start tutorial
      </v-btn>
    </div>

    <!-- TUTORIAL -->
    <div
      v-else-if="currentQuestion <= 0"
      class="h-100 w-100 align-center text-center d-flex flex-column pt-20"
    >
      <div v-if="!is_question && !is_countdown">
        <h1 class="mb-8 cap">{{ tutorial[Math.abs(currentQuestion) - 1].question }}</h1>
        <v-btn variant="tonal" color="blue" @click="countdown()"> Answer </v-btn>
      </div>

      <div v-else-if="is_countdown">
        <h1 class="mb-8 cap">{{ tutorial[Math.abs(currentQuestion) - 1].question }}</h1>
        <h1 class="mb-8">{{ currentTime }}</h1>
      </div>

      <div v-else>
        <h1 class="mb-8 cap">{{ tutorial[Math.abs(currentQuestion) - 1].question }}</h1>
        <!-- grid 2 by 2 -->
        <div class="grid mb-8">
          <v-btn
            v-for="(answer, index) in tutorial[Math.abs(currentQuestion) - 1].answers"
            :key="index"
            :color="
              tutorial[Math.abs(currentQuestion) - 1].time === 0
                ? 'blue'
                : tutorial[Math.abs(currentQuestion) - 1].correct_answer === answer
                ? 'green'
                : 'red'
            "
            variant="tonal"
            size="x-large"
            min-width="20vw"
            min-height="15vh"
            rounded
            class="answer-button"
            @click="checkTutorialAnswer(index, answer)"
          >
            {{ answer }}
          </v-btn>
        </div>
        <v-btn
          class="justify-end"
          v-if="
            tutorial[Math.abs(currentQuestion) - 1].time !== 0 && currentQuestion == -2
          "
          variant="tonal"
          color="blue"
          @click="
            this.is_question = false;
            this.currentQuestion = -1;
          "
        >
          Next question
        </v-btn>
        <v-btn
          class="justify-end"
          v-else-if="
            tutorial[Math.abs(currentQuestion) - 1].time !== 0 && currentQuestion == -1
          "
          variant="tonal"
          size="x-large"
          color="black"
          @click="
            this.is_question = false;
            this.currentQuestion = 1;
          "
        >
          Start quiz
        </v-btn>
      </div>
    </div>

    <!-- QUESTIONS -->

    <div
      v-else-if="currentQuestion == 1"
      class="h-100 w-100 align-center text-left d-flex flex-column mt-8"
    >
      <h1 class="cap">About you!</h1>
      <div class="mb-8 w-50">
        <h2>Gender:</h2>
        <v-radio-group v-model="user_info.gender" row class="">
          <v-radio label="Male" value="Male"></v-radio>
          <v-radio label="Female" value="Female"></v-radio>
          <v-radio label="Other" value="Other"></v-radio>
        </v-radio-group>
        <h2>Age:</h2>
        <v-slider
          v-model="user_info.age"
          :ticks="{
            0: '<18',
            1: '18-25',
            2: '26-35',
            3: '36-45',
            4: '46-55',
            5: '56-65',
            6: '>65',
          }"
          :max="6"
          step="1"
          show-ticks="always"
          tick-size="3"
          class="mb-6"
        ></v-slider>
        <!-- education_level -->
        <h2>What is your education level?</h2>
        <v-select
          v-model="user_info.education_level"
          :items="[
            'None',
            'Primary school',
            'Secondary school',
            'High school',
            'Bachelor',
            'Master',
            'PhD',
          ]"
          label="Education level"
          class="mb-6"
          variant="solo"
        ></v-select>
        <!-- pc_usage_h_per_day -->
        <h2>How many hours do you use a computer per day?</h2>
        <v-slider
          v-model="user_info.pc_usage_h_per_day"
          :ticks="{
            0: '0',
            1: '1-2',
            2: '3-4',
            3: '5-6',
            4: '7-8',
            5: '9-10',
            6: '>10',
          }"
          :max="6"
          step="1"
          show-ticks="always"
          tick-size="3"
          class="mb-6"
        ></v-slider>
        <!-- programming_experience -->
        <h2>How many years of programming experience do you have?</h2>
        <v-slider
          v-model="user_info.programming_experience"
          :ticks="{
            0: 'None',
            1: '1-2',
            2: '3-5',
            3: '6-10',
            4: '11-15',
            5: '16-20',
            6: '>20',
          }"
          :max="6"
          step="1"
          show-ticks="always"
          tick-size="3"
          class="mb-6"
        ></v-slider>

        <h2>What do you use the most between camelCase and kebab-case?</h2>
        <v-slider
          v-model="user_info.knowledge_of_camel_kebab"
          :ticks="{
            0: 'camelCase',
            1: 'None/Same',
            2: 'kebab-case',
          }"
          :max="2"
          step="1"
          show-ticks="always"
          tick-size="3"
          class="mb-6"
        ></v-slider>
      </div>
      <v-btn
        v-if="
          user_info.age !== undefined &&
          user_info.gender !== undefined &&
          user_info.education_level !== undefined &&
          user_info.pc_usage_h_per_day !== undefined &&
          user_info.programming_experience !== undefined
        "
        class="mb-16"
        variant="tonal"
        color="blue"
        @click="nextQuestion()"
      >
        Next question
      </v-btn>
      <v-btn v-else class="mb-16" variant="tonal" color="blue" disabled>
        Next question
      </v-btn>
    </div>

    <div
      v-else-if="currentQuestion - 2 < questions.length"
      class="h-100 w-100 align-center text-center d-flex flex-column pt-20"
    >
      <div v-if="!is_question && !is_countdown">
        <h1 class="mb-8 cap">{{ questions[currentQuestion - 2].question }}</h1>
        <v-btn variant="tonal" color="blue" @click="countdown()"> Answer </v-btn>
      </div>
      <div v-else-if="is_countdown">
        <h1 class="mb-8 cap">{{ questions[currentQuestion - 2].question }}</h1>
        <h1 class="mb-8">{{ currentTime }}</h1>
      </div>
      <div v-else>
        <h1 class="mb-8 cap">{{ questions[currentQuestion - 2].question }}</h1>
        <!-- grid 2 by 2 -->
        <div class="grid mb-8">
          <v-btn
            v-for="(answer, index) in questions[currentQuestion - 2].answers"
            :key="index"
            :color="
              questions[currentQuestion - 2].time === 0
                ? 'blue'
                : questions[currentQuestion - 2].correct_answer === answer
                ? 'green'
                : 'red'
            "
            variant="tonal"
            size="x-large"
            min-width="20vw"
            min-height="15vh"
            rounded
            class="answer-button"
            @click="checkAnswer(index, answer)"
          >
            {{ answer }}
          </v-btn>
        </div>
        <v-btn
          class="justify-end"
          v-if="questions[currentQuestion - 2].time !== 0"
          variant="tonal"
          color="blue"
          @click="nextQuestion()"
        >
          Next question
        </v-btn>
      </div>
    </div>

    <div v-else class="mx-16 mt-16 w-50 mx-auto">
      <h1 class="mb-8">Quiz completed!</h1>
      <p class="text">
        You have succesfully completed the quiz! Thank you for your participation.
      </p>

      <p class="text">
        You have answered
        <b>{{ countCorrect() }} out of {{ questions.length }}</b> questions correctly,
        with an average time of <b>{{ getAverageTime() }}</b> seconds.
      </p>
      <p class="text mt-6">
        In the following chart you can see the time you needed to answer each question.
        The green bar represents the correct answers, the red bar represents the wrong
        ones.
      </p>
      <BarChart :data="getChartData()" class="mt-16 w-100 mx-auto" />
    </div>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import BarChart from "@/components/BarChart.vue";
import emailjs from "emailjs-com";

export default defineComponent({
  name: "quiz",
  components: { BarChart },

  data: () => ({
    tutorial: [
      {
        question: "the tutorial",
        answers: ["theTutorial", "theTutoria", "theTutoral", "theTutorail"],
        correct_answer: "theTutorial",
        correct: false,
        time: 0,
        is_kebab: true,
      },
      {
        question: "the tutorial",
        answers: ["the-tutoria", "the-tutoral", "the-tutorial", "the-tutorail"],
        correct_answer: "the-tutorial",
        correct: false,
        time: 0,
        is_kebab: false,
      },
    ],
    user_info: {
      gender: undefined,
      age: 0,
      education_level: undefined,
      pc_usage_h_per_day: 0,
      programming_experience: 0,
      knowledge_of_camel_kebab: 1,
    },
    questions: [
      {
        question: "copy and paste",
        answers: ["copyAndPaste", "copyAdPaste", "copyAndPsate", "copyAndPeist"],
        correct_answer: "copyAndPaste",
        correct: false,
        time: 0,
        is_kebab: false,
      },
      {
        question: "hello world",
        answers: ["helloWorld", "heloWorld", "hellWorld", "helloWord"],
        correct_answer: "helloWorld",
        correct: false,
        time: 0,
        is_kebab: false,
      },
      {
        question: "add event listener",
        answers: [
          "addEventListener",
          "addEventListner",
          "addEventLisener",
          "addEvantListener",
        ],
        correct_answer: "addEventListener",
        correct: false,
        time: 0,
        is_kebab: false,
      },
      {
        question: "copy and paste",
        answers: ["copy-and-paste", "copy-and-psate", "copy-ad-paste", "copy-and-pazte"],
        correct_answer: "copy-and-paste",
        correct: false,
        time: 0,
        is_kebab: true,
      },
      {
        question: "hello world",
        answers: ["hello-world", "hell-world", "helo-world", "hello-word"],
        correct_answer: "hello-world",
        correct: false,
        time: 0,
        is_kebab: true,
      },
      {
        question: "add event listener",
        answers: [
          "add-event-listener",
          "add-event-listner",
          "add-event-lisener",
          "add-evant-listener",
        ],
        correct_answer: "add-event-listener",
        correct: false,
        time: 0,
        is_kebab: true,
      },
      {
        question: "retake the exam",
        answers: [
          "retake-the-exam",
          "reteke-the-exam",
          "retake-the-exan",
          "retake-the-exem",
        ],
        correct_answer: "retake-the-exam",
        correct: false,
        time: 0,
        is_kebab: true,
      },
      {
        question: "retake the exam",
        answers: ["retakeTheExam", "retekeTheExam", "retakeTheExan", "retakeTheExem"],
        correct_answer: "retakeTheExam",
        correct: false,
        time: 0,
        is_kebab: false,
      },
      {
        question: "do not use",
        answers: ["doNotUse", "doNotUze", "doNodUse", "doMotUse"],
        correct_answer: "doNotUse",
        correct: false,
        time: 0,
        is_kebab: false,
      },
      {
        question: "do not use",
        answers: ["do-not-use", "do-not-uze", "do-nod-use", "do-mot-use"],
        correct_answer: "do-not-use",
        correct: false,
        time: 0,
        is_kebab: true,
      },
      {
        question: "do something now",
        answers: ["doSomethingNow", "doSomethinNow", "doSomethinNaw", "doSomethinNoww"],
        correct_answer: "doSomethingNow",
        correct: false,
        time: 0,
        is_kebab: false,
      },
      {
        question: "do something now",
        answers: [
          "do-something-now",
          "do-somethin-now",
          "do-somethin-naw",
          "do-somethin-noww",
        ],
        correct_answer: "do-something-now",
        correct: false,
        time: 0,
        is_kebab: true,
      },
      {
        question: "if you want to",
        answers: ["ifYouWantTo", "ifYouWontTo", "ifYouWontToo", "ifVouWantTo"],
        correct_answer: "ifYouWantTo",
        correct: false,
        time: 0,
        is_kebab: false,
      },
      {
        question: "if you want to",
        answers: [
          "if-you-want-to",
          "if-you-wont-to",
          "if-you-wont-too",
          "if-vou-want-to",
        ],
        correct_answer: "if-you-want-to",
        correct: false,
        time: 0,
        is_kebab: true,
      },
    ],
    currentQuestion: 0,
    is_question: false,
    currentTime: 0,
    is_countdown: false,
  }),

  created() {
    // shuffle answers
    this.questions.forEach((question) => {
      question.answers = this.shuffleArray(question.answers);
    });
    // shuffle tutorial
    this.tutorial.forEach((question) => {
      question.answers = this.shuffleArray(question.answers);
    });
    // shuffle questions
    this.questions = this.shuffleArray(this.questions);
  },

  beforeRouteLeave(to, from, next) {
    // called when the route that renders this component is about to
    // be navigated away from.
    // has access to `this` component instance.
    // show alert
    this.$dialog
      .confirm("Do you want to proceed?")
      .then(function () {
        next();
      })
      .catch(function () {
        next(false);
      });
  },

  methods: {
    startTimer() {
      this.currentTime = 0;
      this.timer = setInterval(() => {
        this.currentTime++;
      }, 1);
    },

    stopTimer() {
      clearInterval(this.timer);
    },

    resetTimer() {
      this.currentTime = 0;
    },

    displayTimer() {
      // convert to seconds
      let seconds = this.currentTime / 1000;
    },

    countdown() {
      this.is_countdown = true;
      // set countdown time
      this.currentTime = 3;

      this.countdownTimer = setInterval(() => {
        this.currentTime--;
        if (this.currentTime === 0) {
          this.is_countdown = false;
          this.startAnswer();
          clearInterval(this.countdownTimer);
        }
      }, 1000);
    },

    startAnswer() {
      this.is_question = true;
      this.resetTimer();
      this.startTimer();
    },

    checkTutorialAnswer(index, answer) {
      if (this.tutorial[Math.abs(this.currentQuestion) - 1].time > 0) return;
      // stop timer
      this.stopTimer();
      // check answer
      this.tutorial[Math.abs(this.currentQuestion) - 1].correct =
        this.tutorial[Math.abs(this.currentQuestion) - 1].correct_answer === answer;
      // save time
      this.tutorial[Math.abs(this.currentQuestion) - 1].time = this.currentTime;
      // reset timer
      this.resetTimer();
    },

    checkAnswer(index, answer) {
      if (this.questions[this.currentQuestion - 2].time > 0) return;
      // stop timer
      this.stopTimer();
      // check answer
      this.questions[this.currentQuestion - 2].correct =
        this.questions[this.currentQuestion - 2].correct_answer === answer;
      // save time
      this.questions[this.currentQuestion - 2].time = this.currentTime;
      // reset timer
      this.resetTimer();
      // next question or end
      if (this.currentQuestion > this.questions.length) {
        this.submitQuiz();
      }
    },

    nextQuestion() {
      this.is_question = false;
      this.currentQuestion++;
    },

    questionsToJSON() {
      let questions = [];
      this.questions.forEach((question) => {
        questions.push({
          question: question.question,
          correct_answer: question.correct_answer,
          correct: question.correct,
          time: question.time,
          is_kebab: question.is_kebab,
        });
      });
      return questions;
    },

    submitQuiz() {
      emailjs
        .send(
          process.env.SERVICE_ID,
          process.env.TEMPLATE_ID,
          {
            user_info: JSON.stringify(this.user_info, null, 2),
            questions: JSON.stringify(this.questionsToJSON(), null, 2),
          },
          process.env.USER_ID
        )
        .then(
          function (response) {
            console.log("SUCCESS!", response.status, response.text);
          },
          function (error) {
            console.log("FAILED...", error);
          }
        );
    },

    getAverageTime() {
      let total = 0;
      this.questions.forEach((question) => {
        total += question.time;
      });
      return (total / this.questions.length / 1000).toFixed(2);
    },

    shuffleArray(arr) {
      for (let i = arr.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [arr[i], arr[j]] = [arr[j], arr[i]];
      }
      return arr;
    },

    countCorrect() {
      let count = 0;
      this.questions.forEach((question) => {
        if (question.correct) count++;
      });
      return count;
    },

    getChartData() {
      let lables = [];
      this.questions.forEach((question) => {
        lables.push(question.question);
      });

      let data = [];
      this.questions.forEach((question) => {
        data.push(question.time);
      });

      let colors = [];
      this.questions.forEach((question) => {
        colors.push(question.correct ? "#04f759" : "#ed1e1e");
      });

      return {
        labels: lables,
        datasets: [
          {
            data: data,
            backgroundColor: colors,
          },
        ],
      };
    },
  },
});
</script>

<style scoped>
.cover {
  height: 20vh;
  object-fit: cover;
}

.text {
  text-align: justify;
  text-justify: inter-word;
  font-size: 1.2rem;
}

.grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 1rem;
}

.answer-button {
  text-transform: none;
  font-size: 3rem;
}
h1 {
  font-size: 4rem;
}

h2.cap {
  font-size: 2rem;
}

.pt-20 {
  padding-top: 20vh;
}

.cap {
  font-variant: small-caps;
  text-transform: capitalize;
}
</style>
