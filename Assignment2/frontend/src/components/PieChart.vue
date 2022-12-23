<template>
  <Pie id="my-chart-id" :options="chartOptions" :data="data" />
</template>

<script>
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
import ChartDataLabels from "chartjs-plugin-datalabels";
import { Pie } from "vue-chartjs";

ChartJS.register(ArcElement, Tooltip, Legend, ChartDataLabels);

export default {
  name: "BarChart",
  components: { Pie },

  props: {
    data: {
      type: Object,
      required: true,
    },
  },

  created() {
    console.log(this.data);
  },

  data() {
    return {
      chartOptions: {
        responsive: false,
        maintainAspectRatio: true,
        plugins: {
          legend: {
            display: true,
          },
          datalabels: {
            color: "black",
            textShadowBlur: 1,
            textShadowColor: "black",
            formatter: Math.round,
            font: {
              weight: "bold",
              size: 20,
            },
          },
        },
      },
    };
  },
};
</script>
