<template>
  <div class="">
    <!-- <BarChart :data="getChartData()" class="mt-16 w-100 mx-auto" /> -->
    <h1 class="cap">Results</h1>
    <div class="h-100 w-100 align-center text-center d-flex flex-column mt-16">
      <h2 class="cap">Population Age</h2>
      <PieChart :data="getAgePieData()" class="mb-16 w-25 h-25 mx-auto" />
      <h2 class="cap">Population Gender</h2>
      <PieChart :data="getGenderPieData()" class="mb-16 w-25 h-25 mx-auto" />
      <h2 class="cap">Population Education</h2>
      <PieChart :data="getEducationLevelPieData()" class="mb-16 w-25 h-25 mx-auto" />

      <h2 class="cap">Population pc usage per day</h2>
      <BarChart :data="getPcUsageBarData()" class="mb-16 w-50 h-50 mx-auto" />
      <h2 class="cap">Population programming experience (years)</h2>
      <BarChart
        :data="getProgrammingExperienceBarData()"
        class="mb-16 w-50 h-50 mx-auto"
      />
      <h2 class="cap">Population preference for camelCase or kebab-case</h2>
      <PieChart :data="getKnowledgeKebabCamelPieData()" class="mb-16 w-25 h-25 mx-auto" />
      <h2 class="cap">Times</h2>
      <BoxChart :data="getAnswersBoxData()" class="mb-16 w-50 mx-auto" />
      <h2 class="cap">Correctness</h2>
      <BarChart :data="getCorrectnessBarData()" class="mb-16 w-50 h-50 mx-auto" />
      <h2 class="cap">Times by age group</h2>
      <BoxChart :data="getTimesByAgeGroup()" class="mb-16 w-50 h-50 mx-auto" />
      <h2 class="cap">Times by years of programming experience</h2>
      <BoxChart :data="getTimesByExperienceGroup()" class="mb-16 w-50 h-50 mx-auto" />
      <h2 class="cap">Times by gender</h2>
      <BoxChart :data="getTimesByGenderGroup()" class="mb-16 w-50 h-50 mx-auto" />
      <h2 class="cap">Times by gender for case style</h2>
      <BoxChart
        :data="getTimesByGenderCamelKebabGroup()"
        class="mb-16 w-50 h-50 mx-auto"
      />
      <h2 class="cap">Times by programming experience years for case style</h2>
      <BoxChart
        :data="getTimesByProgrammingExperienceCamelKebabGroup()"
        class="mb-16 w-50 h-50 mx-auto"
      />
    </div>
  </div>
</template>

<script>
import exp_data from "@/assets/data.json";
import BarChart from "@/components/BarChart.vue";
import PieChart from "@/components/PieChart.vue";
import BoxChart from "@/components/BoxChart.vue";

export default {
  name: "ResultCharts",
  components: { BarChart, PieChart, BoxChart },

  props: {
    data: {
      type: Object,
      required: true,
    },
  },

  data() {
    return {
      age_keys: ["<18", "18-25", "26-35", "36-45", "46-55", "56-65", ">65"],
      programming_experience_keys: [
        "None",
        "1-2",
        "3-5",
        "6-10",
        "11-15",
        "15-20",
        ">20",
      ],
      colors: [
        "#66c2a5",
        "#fc8d62",
        "#8da0cb",
        "#e78ac3",
        "#a6d854",
        "#ffd92f",
        "#e5c494",
        "#b3b3b3",
      ],
      chartOptions: {
        responsive: true,
        plugins: {
          legend: {
            display: false,
          },
        },
      },
    };
  },
  mounted() {
    this.getPopulationAge();
    this.getAnswers();
    console.log("no", this.getTimesByAgeGroup());
  },

  methods: {
    getData() {
      return exp_data;
    },

    getPopulationAge() {
      let data = exp_data;
      let age = [];

      for (let i = 0; i < data.length; i++) {
        age.push(data[i].user_info.age);
      }

      // count the number of each age
      let age_count = {};
      for (let i = 0; i < age.length; i++) {
        if (age_count[age[i]]) {
          age_count[age[i]]++;
        } else {
          age_count[age[i]] = 1;
        }
      }
      let age_values = Object.values(age_count);
      console.log(this.age_keys, age_values);

      return age_values;
    },

    getAgePieData() {
      return {
        labels: this.age_keys,
        datasets: [
          {
            data: this.getPopulationAge(),
            backgroundColor: this.colors,
          },
        ],
      };
    },

    getPopulationGender() {
      let data = exp_data;
      let gender = [];

      for (let i = 0; i < data.length; i++) {
        gender.push(data[i].user_info.gender);
      }

      // count the number of each gender
      let gender_count = {};
      for (let i = 0; i < gender.length; i++) {
        if (gender_count[gender[i]]) {
          gender_count[gender[i]]++;
        } else {
          gender_count[gender[i]] = 1;
        }
      }
      let gender_keys = Object.keys(gender_count);
      let gender_values = Object.values(gender_count);

      return [gender_keys, gender_values];
    },

    getGenderPieData() {
      let [labels, data] = this.getPopulationGender();

      return {
        labels: labels,
        datasets: [
          {
            data: data,
            backgroundColor: this.colors,
          },
        ],
      };
    },

    getPopulationEducationLevel() {
      let data = exp_data;
      let education_level = [];

      for (let i = 0; i < data.length; i++) {
        education_level.push(data[i].user_info.education_level);
      }

      // count the number of each education level
      let education_level_count = {};
      for (let i = 0; i < education_level.length; i++) {
        if (education_level_count[education_level[i]]) {
          education_level_count[education_level[i]]++;
        } else {
          education_level_count[education_level[i]] = 1;
        }
      }
      let education_level_keys = Object.keys(education_level_count);
      let education_level_values = Object.values(education_level_count);

      return [education_level_keys, education_level_values];
    },

    getEducationLevelPieData() {
      let [labels, data] = this.getPopulationEducationLevel();

      return {
        labels: labels,
        datasets: [
          {
            data: data,
            backgroundColor: this.colors,
          },
        ],
      };
    },

    getPcUsage() {
      let data = exp_data;
      let pc_usage = [];

      for (let i = 0; i < data.length; i++) {
        pc_usage.push(data[i].user_info.pc_usage_h_per_day);
      }

      // count the number of each pc usage
      let pc_usage_count = {};
      for (let i = 0; i < pc_usage.length; i++) {
        if (pc_usage_count[pc_usage[i]]) {
          pc_usage_count[pc_usage[i]]++;
        } else {
          pc_usage_count[pc_usage[i]] = 1;
        }
      }
      let pc_usage_keys = ["0", "1-2", "3-4", "5-6", "7-8", "9-10", ">10"];
      let pc_usage_values = Object.values(pc_usage_count);

      return [pc_usage_keys, pc_usage_values];
    },

    getPcUsageBarData() {
      let [labels, data] = this.getPcUsage();

      console.log("ciao", labels, data);

      return {
        labels: labels,
        datasets: [
          {
            data: data,
            backgroundColor: this.colors,
          },
        ],
      };
    },

    getPopulationProgrammingExperience() {
      let data = exp_data;
      let programming_experience = [];

      for (let i = 0; i < data.length; i++) {
        programming_experience.push(data[i].user_info.programming_experience);
      }

      // count the number of each programming experience
      let programming_experience_count = {};
      for (let i = 0; i < programming_experience.length; i++) {
        if (programming_experience_count[programming_experience[i]]) {
          programming_experience_count[programming_experience[i]]++;
        } else {
          programming_experience_count[programming_experience[i]] = 1;
        }
      }

      console.log("ciao", programming_experience_count);
      let programming_experience_keys = [
        "None",
        "1-2",
        "3-5",
        "6-10",
        "11-15",
        "16-20",
        ">20",
      ];

      let programming_experience_values = [];
      for (let i = 0; i < programming_experience_keys.length; i++) {
        if (programming_experience_count[i]) {
          programming_experience_values.push(programming_experience_count[i]);
        } else {
          programming_experience_values.push(0);
        }
      }

      return [programming_experience_keys, programming_experience_values];
    },

    getProgrammingExperienceBarData() {
      let [labels, data] = this.getPopulationProgrammingExperience();

      return {
        labels: labels,
        datasets: [
          {
            data: data,
            backgroundColor: this.colors,
          },
        ],
      };
    },

    getPopulationKnowledgeKebabCamel() {
      let data = exp_data;
      let knowledge_kebab_camel = [];

      for (let i = 0; i < data.length; i++) {
        knowledge_kebab_camel.push(data[i].user_info.knowledge_of_camel_kebab);
      }

      // count the number of each knowledge kebab camel
      let knowledge_kebab_camel_count = {};
      for (let i = 0; i < knowledge_kebab_camel.length; i++) {
        if (knowledge_kebab_camel_count[knowledge_kebab_camel[i]]) {
          knowledge_kebab_camel_count[knowledge_kebab_camel[i]]++;
        } else {
          knowledge_kebab_camel_count[knowledge_kebab_camel[i]] = 1;
        }
      }

      let knowledge_kebab_camel_keys = ["camelCase", "None/Same", "kebab-case"];
      let knowledge_kebab_camel_values = Object.values(knowledge_kebab_camel_count);

      return [knowledge_kebab_camel_keys, knowledge_kebab_camel_values];
    },

    getKnowledgeKebabCamelPieData() {
      let [labels, data] = this.getPopulationKnowledgeKebabCamel();

      return {
        labels: labels,
        datasets: [
          {
            data: data,
            backgroundColor: this.colors,
          },
        ],
      };
    },

    getAnswers() {
      let data = exp_data;
      let answers = [];
      let answer_times = [];
      let correctness = [];

      // sort the answers for each user
      for (let i = 0; i < data.length; i++) {
        let user_answers = data[i].answers;
        console.log(user_answers);

        // sort the answers by correct-answer
        user_answers.sort((a, b) => {
          // reverse a.correct_answer and b.correct_answer
          let answer1 = a.correct_answer.split("").reverse().join("");
          let answer2 = b.correct_answer.split("").reverse().join("");
          if (answer1 < answer2) {
            return -1;
          }
          if (answer1 > answer2) {
            return 1;
          }
          return 0;
        });

        let user_answer_times = [];
        for (let j = 0; j < user_answers.length; j++) {
          user_answer_times.push(user_answers[j].time);
        }

        answer_times.push(user_answer_times);

        let user_correctness = [];
        for (let j = 0; j < user_answers.length; j++) {
          user_correctness.push(user_answers[j].correct);
        }
        correctness.push(user_correctness);

        answers = user_answers;
      }

      return [answers, answer_times, correctness];
    },

    getAnswersBoxData() {
      let [answers, answer_times] = this.getAnswers();

      let labels = [];
      let data = [];

      for (let i = 0; i < answers.length; i++) {
        labels.push(answers[i].correct_answer);
      }

      // answer_times are grouped by user, we need to group them by answer
      let answer_times_grouped = [];
      for (let i = 0; i < answer_times.length; i++) {
        for (let j = 0; j < answer_times[i].length; j++) {
          if (answer_times_grouped[j]) {
            answer_times_grouped[j].push(answer_times[i][j]);
          } else {
            answer_times_grouped[j] = [answer_times[i][j]];
          }
        }
      }

      return {
        labels: labels,
        outlierColor: "#ff0000",
        datasets: [
          {
            outlierColor: "#ff0000",
            padding: 10,
            itemRadius: 6,
            itemBorderColor: "#333333",
            outlierRadius: 4,
            borderWidth: 1,
            borderColor: "#333333",
            data: answer_times_grouped,
            backgroundColor: this.colors,
          },
        ],
      };
    },

    getCorrectnessBarData() {
      let [answers, answer_times, correctness] = this.getAnswers();

      let labels = [];
      let data = [];

      for (let i = 0; i < answers.length; i++) {
        labels.push(answers[i].correct_answer);
      }

      // correctness are grouped by user, we need to group them by answer
      let correctness_grouped = [];
      for (let i = 0; i < correctness.length; i++) {
        for (let j = 0; j < correctness[i].length; j++) {
          if (correctness_grouped[j]) {
            correctness_grouped[j].push(correctness[i][j]);
          } else {
            correctness_grouped[j] = [correctness[i][j]];
          }
        }
      }

      let correctness_count = [];
      for (let i = 0; i < correctness_grouped.length; i++) {
        let correct = 0;
        let incorrect = 0;
        for (let j = 0; j < correctness_grouped[i].length; j++) {
          if (correctness_grouped[i][j]) {
            correct++;
          } else {
            incorrect++;
          }
        }
        correctness_count.push([correct, incorrect]);
      }

      return {
        labels: labels,
        datasets: [
          {
            label: "Correct",
            backgroundColor: "#55dd55",
            data: correctness_count.map((x) => x[0]),
          },
          {
            label: "Incorrect",
            backgroundColor: "#dd5555",
            data: correctness_count.map((x) => x[1]),
          },
        ],
      };
    },

    getTimesByAgeGroup() {
      let data = exp_data;
      let times = [];
      let age_groups = [];

      for (let i = 0; i < data.length; i++) {
        let user_answers = data[i].answers;
        let user_age = data[i].user_info.age;
        let user_times = [];

        for (let j = 0; j < user_answers.length; j++) {
          user_times.push(user_answers[j].time);
        }

        switch (user_age) {
          case 0:
            age_groups.push("<18");
            break;
          case 1:
            age_groups.push("18-24");
            break;
          case 2:
            age_groups.push("25-34");
            break;
          case 3:
            age_groups.push("35-44");
            break;
          case 4:
            age_groups.push("45-54");
            break;
          case 5:
            age_groups.push("55-64");
            break;
          case 6:
            age_groups.push(">65");
            break;
        }

        times.push(user_times);
      }

      // group times by age group
      let times_grouped = [];
      for (let i = 0; i < times.length; i++) {
        switch (age_groups[i]) {
          case "<18":
            if (times_grouped[0]) {
              times_grouped[0].push(times[i]);
            } else {
              times_grouped[0] = [times[i]];
            }
            break;
          case "18-24":
            if (times_grouped[1]) {
              times_grouped[1].push(times[i]);
            } else {
              times_grouped[1] = [times[i]];
            }
            break;
          case "25-34":
            if (times_grouped[2]) {
              times_grouped[2].push(times[i]);
            } else {
              times_grouped[2] = [times[i]];
            }
            break;
          case "35-44":
            if (times_grouped[3]) {
              times_grouped[3].push(times[i]);
            } else {
              times_grouped[3] = [times[i]];
            }
            break;
          case "45-54":
            if (times_grouped[4]) {
              times_grouped[4].push(times[i]);
            } else {
              times_grouped[4] = [times[i]];
            }
            break;
          case "55-64":
            if (times_grouped[5]) {
              times_grouped[5].push(times[i]);
            } else {
              times_grouped[5] = [times[i]];
            }
            break;
          case ">65":
            if (times_grouped[6]) {
              times_grouped[6].push(times[i]);
            } else {
              times_grouped[6] = [times[i]];
            }
            break;
        }
      }

      // flatten each item in times_grouped
      for (let i = 0; i < times_grouped.length; i++) {
        times_grouped[i] = times_grouped[i].flat();
      }

      return {
        labels: this.age_keys,
        outlierColor: "#ff0000",
        datasets: [
          {
            outlierColor: "#ff0000",
            padding: 10,
            itemRadius: 6,
            itemBorderColor: "#333333",
            outlierRadius: 4,
            borderWidth: 1,
            borderColor: "#333333",
            data: times_grouped,
            backgroundColor: this.colors,
          },
        ],
      };
    },

    getTimesByExperienceGroup() {
      let data = exp_data;
      let times = [];
      let experience_group = [];

      for (let i = 0; i < data.length; i++) {
        let user_answers = data[i].answers;
        let user_experience = data[i].user_info.programming_experience;
        let user_times = [];

        for (let j = 0; j < user_answers.length; j++) {
          user_times.push(user_answers[j].time);
        }

        switch (user_experience) {
          case 0:
            experience_group.push("None");
            break;
          case 1:
            experience_group.push("1-2");
            break;
          case 2:
            experience_group.push("3-5");
            break;
          case 3:
            experience_group.push("6-10");
            break;
          case 4:
            experience_group.push("11-15");
            break;
          case 5:
            experience_group.push("16-20");
            break;
          case 6:
            experience_group.push(">20");
            break;
        }

        times.push(user_times);
      }
      // flat times

      // group times by age group
      let times_grouped = [];
      for (let i = 0; i < times.length; i++) {
        switch (experience_group[i]) {
          case "None":
            if (times_grouped[0]) {
              times_grouped[0].push(times[i]);
            } else {
              times_grouped[0] = [times[i]];
            }
            break;
          case "1-2":
            if (times_grouped[1]) {
              times_grouped[1].push(times[i]);
            } else {
              times_grouped[1] = [times[i]];
            }
            break;
          case "3-5":
            if (times_grouped[2]) {
              times_grouped[2].push(times[i]);
            } else {
              times_grouped[2] = [times[i]];
            }
            break;
          case "6-10":
            if (times_grouped[3]) {
              times_grouped[3].push(times[i]);
            } else {
              times_grouped[3] = [times[i]];
            }
            break;
          case "11-15":
            if (times_grouped[4]) {
              times_grouped[4].push(times[i]);
            } else {
              times_grouped[4] = [times[i]];
            }
            break;
          case "16-20":
            if (times_grouped[5]) {
              times_grouped[5].push(times[i]);
            } else {
              times_grouped[5] = [times[i]];
            }
            break;
          case ">20":
            if (times_grouped[6]) {
              times_grouped[6].push(times[i]);
            } else {
              times_grouped[6] = [times[i]];
            }
            break;
        }
      }

      // flatten each item in times_grouped
      for (let i = 0; i < times_grouped.length; i++) {
        times_grouped[i] = times_grouped[i].flat();
      }

      return {
        labels: this.programming_experience_keys,
        outlierColor: "#ff0000",
        datasets: [
          {
            outlierColor: "#ff0000",
            padding: 10,
            itemRadius: 6,
            itemBorderColor: "#333333",
            outlierRadius: 4,
            borderWidth: 1,
            borderColor: "#333333",
            data: times_grouped,
            backgroundColor: this.colors,
          },
        ],
      };
    },

    getTimesByGenderGroup() {
      let data = exp_data;
      let times = [];
      let gender_group = [];

      for (let i = 0; i < data.length; i++) {
        let user_answers = data[i].answers;
        let user_gender = data[i].user_info.gender;
        let user_times = [];

        for (let j = 0; j < user_answers.length; j++) {
          user_times.push(user_answers[j].time);
        }

        gender_group.push(user_gender);

        times.push(user_times);
      }
      // flat times

      // group times by age group
      let times_grouped = [];
      for (let i = 0; i < times.length; i++) {
        switch (gender_group[i]) {
          case "Female":
            if (times_grouped[0]) {
              times_grouped[0].push(times[i]);
            } else {
              times_grouped[0] = [times[i]];
            }
            break;
          case "Male":
            if (times_grouped[1]) {
              times_grouped[1].push(times[i]);
            } else {
              times_grouped[1] = [times[i]];
            }
            break;
          case "Other":
            if (times_grouped[2]) {
              times_grouped[2].push(times[i]);
            } else {
              times_grouped[2] = [times[i]];
            }
            break;
        }
      }

      // flatten each item in times_grouped
      for (let i = 0; i < times_grouped.length; i++) {
        times_grouped[i] = times_grouped[i].flat();
      }

      return {
        labels: ["Female", "Male", "Other"],
        outlierColor: "#ff0000",
        datasets: [
          {
            outlierColor: "#ff0000",
            padding: 10,
            itemRadius: 6,
            itemBorderColor: "#333333",
            outlierRadius: 4,
            borderWidth: 1,
            borderColor: "#333333",
            data: times_grouped,
            backgroundColor: this.colors,
          },
        ],
      };
    },

    getTimesByGenderCamelKebabGroup() {
      let data = exp_data;
      let times = [];
      let gender_group = [];

      for (let i = 0; i < data.length; i++) {
        let user_answers = data[i].answers;
        let user_gender = data[i].user_info.gender;
        let user_times = [];

        let user_times_camel = [];
        let user_times_kebab = [];

        for (let j = 0; j < user_answers.length; j++) {
          console.log("sdf", user_answers[j].is_kebab);
          if (user_answers[j].is_kebab) {
            user_times_kebab.push(user_answers[j].time);
          } else {
            user_times_camel.push(user_answers[j].time);
          }
        }

        gender_group.push(user_gender + " kebab", user_gender + " camel");

        times.push(user_times_kebab);
        times.push(user_times_camel);
      }

      console.log("sdfa", times);
      // flat times

      // group times by age group
      let times_grouped = [];
      for (let i = 0; i < times.length; i++) {
        switch (gender_group[i]) {
          case "Female kebab":
            if (times_grouped[0]) {
              times_grouped[0].push(times[i]);
            } else {
              times_grouped[0] = [times[i]];
            }
            break;
          case "Female camel":
            if (times_grouped[1]) {
              times_grouped[1].push(times[i]);
            } else {
              times_grouped[1] = [times[i]];
            }
            break;
          case "Male kebab":
            if (times_grouped[2]) {
              times_grouped[2].push(times[i]);
            } else {
              times_grouped[2] = [times[i]];
            }
            break;
          case "Male camel":
            if (times_grouped[3]) {
              times_grouped[3].push(times[i]);
            } else {
              times_grouped[3] = [times[i]];
            }
            break;
          case "Other kebab":
            if (times_grouped[4]) {
              times_grouped[4].push(times[i]);
            } else {
              times_grouped[4] = [times[i]];
            }
            break;
          case "Other camel":
            if (times_grouped[5]) {
              times_grouped[5].push(times[i]);
            } else {
              times_grouped[5] = [times[i]];
            }
            break;
        }
      }

      // flatten each item in times_grouped
      for (let i = 0; i < times_grouped.length; i++) {
        times_grouped[i] = times_grouped[i].flat();
      }

      console.log("sdfa", times_grouped);

      return {
        labels: [
          "Female kebab",
          "Female camel",
          "Male kebab",
          "Male camel",
          "Other kebab",
          "Other camel",
        ],
        outlierColor: "#ff0000",
        datasets: [
          {
            outlierColor: "#ff0000",
            padding: 10,
            itemRadius: 6,
            itemBorderColor: "#333333",
            outlierRadius: 4,
            borderWidth: 1,
            borderColor: "#333333",
            data: times_grouped,
            backgroundColor: this.colors,
          },
        ],
      };
    },

    getProgrammingExperienceString(exp) {
      switch (exp) {
        case 0:
          return "None";
        case 1:
          return "1-2";
        case 2:
          return "3-5";
        case 3:
          return "6-10";
        case 4:
          return "11-15";
        case 5:
          return "16-20";
        case 6:
          return ">20";
      }
    },

    getTimesByProgrammingExperienceCamelKebabGroup() {
      let data = exp_data;
      let times = [];
      let gender_group = [];

      for (let i = 0; i < data.length; i++) {
        let user_answers = data[i].answers;
        let user_exp = data[i].user_info.programming_experience;

        let user_times_camel = [];
        let user_times_kebab = [];

        for (let j = 0; j < user_answers.length; j++) {
          console.log("sdf", user_answers[j].is_kebab);
          if (user_answers[j].is_kebab) {
            user_times_kebab.push(user_answers[j].time);
          } else {
            user_times_camel.push(user_answers[j].time);
          }
        }

        gender_group.push(
          this.getProgrammingExperienceString(user_exp) + " kebab",
          this.getProgrammingExperienceString(user_exp) + " camel"
        );

        times.push(user_times_kebab);
        times.push(user_times_camel);
      }

      console.log("sdfa", times);
      // flat times

      // group times by age group
      let times_grouped = [];
      for (let i = 0; i < times.length; i++) {
        switch (gender_group[i]) {
          case "None kebab":
            if (times_grouped[0]) {
              times_grouped[0].push(times[i]);
            } else {
              times_grouped[0] = [times[i]];
            }
            break;
          case "None camel":
            if (times_grouped[1]) {
              times_grouped[1].push(times[i]);
            } else {
              times_grouped[1] = [times[i]];
            }
            break;
          case "1-2 kebab":
            if (times_grouped[2]) {
              times_grouped[2].push(times[i]);
            } else {
              times_grouped[2] = [times[i]];
            }
            break;
          case "1-2 camel":
            if (times_grouped[3]) {
              times_grouped[3].push(times[i]);
            } else {
              times_grouped[3] = [times[i]];
            }
            break;
          case "3-5 kebab":
            if (times_grouped[4]) {
              times_grouped[4].push(times[i]);
            } else {
              times_grouped[4] = [times[i]];
            }
            break;
          case "3-5 camel":
            if (times_grouped[5]) {
              times_grouped[5].push(times[i]);
            } else {
              times_grouped[5] = [times[i]];
            }
            break;
          case "6-10 kebab":
            if (times_grouped[6]) {
              times_grouped[6].push(times[i]);
            } else {
              times_grouped[6] = [times[i]];
            }
            break;
          case "6-10 camel":
            if (times_grouped[7]) {
              times_grouped[7].push(times[i]);
            } else {
              times_grouped[7] = [times[i]];
            }
            break;
          case "11-15 kebab":
            if (times_grouped[8]) {
              times_grouped[8].push(times[i]);
            } else {
              times_grouped[8] = [times[i]];
            }
            break;
          case "11-15 camel":
            if (times_grouped[9]) {
              times_grouped[9].push(times[i]);
            } else {
              times_grouped[9] = [times[i]];
            }
            break;
          case "16-20 kebab":
            if (times_grouped[10]) {
              times_grouped[10].push(times[i]);
            } else {
              times_grouped[10] = [times[i]];
            }
            break;
          case "16-20 camel":
            if (times_grouped[11]) {
              times_grouped[11].push(times[i]);
            } else {
              times_grouped[11] = [times[i]];
            }
            break;
          case ">20 kebab":
            if (times_grouped[12]) {
              times_grouped[12].push(times[i]);
            } else {
              times_grouped[12] = [times[i]];
            }
            break;
          case ">20 camel":
            if (times_grouped[13]) {
              times_grouped[13].push(times[i]);
            } else {
              times_grouped[13] = [times[i]];
            }
            break;
        }
      }

      // flatten each item in times_grouped
      for (let i = 0; i < times_grouped.length; i++) {
        times_grouped[i] = times_grouped[i].flat();
      }

      console.log("sdfa", times_grouped);

      return {
        labels: [
          "None kebab",
          "None camel",
          "1-2 kebab",
          "1-2 camel",
          "3-5 kebab",
          "3-5 camel",
          "6-10 kebab",
          "6-10 camel",
          "11-15 kebab",
          "11-15 camel",
          "16-20 kebab",
          "16-20 camel",
          ">20 kebab",
          ">20 camel",
        ],
        outlierColor: "#ff0000",
        datasets: [
          {
            outlierColor: "#ff0000",
            padding: 10,
            itemRadius: 6,
            itemBorderColor: "#333333",
            outlierRadius: 4,
            borderWidth: 1,
            borderColor: "#333333",
            data: times_grouped,
            backgroundColor: this.colors,
          },
        ],
      };
    },
  },
};
</script>

<style scoped>
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
