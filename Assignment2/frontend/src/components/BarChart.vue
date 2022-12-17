<template>
  <Bar
    id="my-chart-id"
    :options="chartOptions"
    :data="data"
    :backgroundColor="data.backgroundColor"
  />
</template>

<script>
import { Bar } from "vue-chartjs";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
} from "chart.js";

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale);

export default {
  name: "BarChart",
  components: { Bar },

  props: {
    data: {
      type: Object,
      required: true,
    },
  },

  data() {
    return {
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
};
</script>
