<template>
  <div class="w-100 align-center text-center body">
    <div
      v-if="currentQuestion === 0"
      class="h-100 w-100 align-center text-center d-flex flex-column pt-20"
    >
      <h1>The Experiment</h1>
      <p class="w-50 text mb-2">
        The aim of this experiment is to evaluate the readability of
        <b>camelCase</b> and <b>kebab-case</b> coding styles.
      </p>

      <p class="w-50 text mb-2">
        You will be asked <b>{{ this.questions.length }} questions </b> and you will have
        to choose the correct one as fast as you can, but without making mistakes. We will
        <b>measure the time</b> you need to answer each question.
      </p>

      <p class="w-50 text mb-2">
        At the beginning a short phrase is shown to you, then, when you are ready, you can
        proceed to the quesiton: 4 answers are shown to you, you have to choose the
        correct one (only one is correct).
      </p>

      <p class="w-50 text mb-2">
        Since the results depend on your performance, we kindly ask you to focus during
        the experiment and avoid any external distractions. For a better experience, we
        recommend you to use a desktop computer.
      </p>

      <p class="w-50 text mb-2">
        The experiment will take about 5 minutes. At the end of the experiment, you will
        see a chart with the time you needed to answer each question.
      </p>

      <v-btn variant="tonal" color="blue" @click="this.currentQuestion = -2">
        Start tutorial
      </v-btn>
    </div>

    <!-- TUTORIAL -->
    <div
      v-else-if="currentQuestion <= 0"
      class="h-100 w-100 align-center text-center d-flex flex-column pt-20"
    >
      <div v-if="!is_question && !is_countdown">
        <h1 class="mb-8">{{ tutorial[Math.abs(currentQuestion) - 1].question }}</h1>
        <v-btn variant="tonal" color="blue" @click="countdown()"> Answer </v-btn>
      </div>

      <div v-else-if="is_countdown">
        <h1 class="mb-8">{{ tutorial[Math.abs(currentQuestion) - 1].question }}</h1>
        <h1 class="mb-8">{{ currentTime }}</h1>
      </div>

      <div v-else>
        <h1 class="mb-8">{{ tutorial[Math.abs(currentQuestion) - 1].question }}</h1>
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
          color="blue"
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
      v-else-if="currentQuestion <= questions.length"
      class="h-100 w-100 align-center text-center d-flex flex-column pt-20"
    >
      <div v-if="!is_question && !is_countdown">
        <h1 class="mb-8">{{ questions[currentQuestion - 1].question }}</h1>
        <v-btn variant="tonal" color="blue" @click="countdown()"> Answer </v-btn>
      </div>
      <div v-else-if="is_countdown">
        <h1 class="mb-8">{{ questions[currentQuestion - 1].question }}</h1>
        <h1 class="mb-8">{{ currentTime }}</h1>
      </div>
      <div v-else>
        <h1 class="mb-8">{{ questions[currentQuestion - 1].question }}</h1>
        <!-- grid 2 by 2 -->
        <div class="grid mb-8">
          <v-btn
            v-for="(answer, index) in questions[currentQuestion - 1].answers"
            :key="index"
            :color="
              questions[currentQuestion - 1].time === 0
                ? 'blue'
                : questions[currentQuestion - 1].correct_answer === answer
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
          v-if="questions[currentQuestion - 1].time !== 0"
          variant="tonal"
          color="blue"
          @click="nextQuestion()"
        >
          Next question
        </v-btn>
      </div>
    </div>

    <div v-else class="mx-16 mt-16 w-75 mx-auto">
      <h1>Quiz completed!</h1>
      <p class="text">
        You have completed the quiz. You have answered {{ countCorrect() }} out of
        {{ questions.length }} questions correctly.
      </p>
      <p class="text">
        You have answered the questions in an average time of
        {{ getAverageTime() }} seconds.
      </p>
      <p class="text">
        In the following chart you can see the time you needed to answer each question.
        The green bar represents the correct answers, the red bar represents the wrong
        ones.
      </p>
      <BarChart :data="getChartData()" class="w-75 h-75 mx-auto" />

      <p class="text">
        In order to submit your results, please send us an email to
        <a href="mailto:alessandro.gobbetti@usi.ch">my email</a>
      </p>
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
        answers: ["the-tutorial", "the-tutoria", "the-tutoral", "the-tutorail"],
        correct_answer: "the-tutorial",
        correct: false,
        time: 0,
        is_kebab: false,
      },
    ],
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
      console.log(this.currentQuestion);
      this.is_question = false;
      if (this.currentQuestion < 0 || this.questions[this.currentQuestion - 1].time > 0)
        return;
      // stop timer
      this.stopTimer();
      // check answer
      this.questions[this.currentQuestion - 1].correct =
        this.questions[this.currentQuestion - 1].correct_answer === answer;
      // save time
      this.questions[this.currentQuestion - 1].time = this.currentTime;
      // reset timer
      this.resetTimer();
      // next question or end
      if (this.currentQuestion >= this.questions.length) {
        this.submitQuiz();
      }
    },

    nextQuestion() {
      this.is_question = false;
      this.currentQuestion++;
    },

    submitQuiz() {
      emailjs
        .send(
          process.env.SERVICE_ID,
          process.env.TEMPLATE_ID,
          {
            // TODO: properly send data
            average_time: this.getAverageTime(),
            questions: this.questions,
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
  font-size: 3rem;
}

.pt-20 {
  padding-top: 20vh;
}
</style>
