<template>
  <div class="">
    <canvas ref="canvas"></canvas>
    <!-- <BoxPlotChart :chart-data="datacollection" /> -->
    <!-- <button @click="fillData()">Randomize</button> -->
  </div>
</template>

<script>
import { BoxPlotChart } from "@sgratzl/chartjs-chart-boxplot";
export default {
  name: "BoxChart",
  components: {
    BoxPlotChart,
  },
  props: {
    data: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      chartOptions: {
        type: "boxplot",
        aspectRatio: 1,
        responsive: true,
        plugins: {
          legend: {
            display: false,
          },
          datalabels: {
            display: true,
            color: "black",
            textShadowBlur: 5,
            textShadowColor: "white",
            anchor: "start",
            formatter: function (context, chart_obj) {
              // compute median value
              let median;
              if (context.length > 0) {
                const sorted = context.slice().sort((a, b) => a - b);
                const middle = Math.floor(sorted.length / 2);
                if (sorted.length % 2) {
                  median = sorted[middle];
                } else {
                  median = (sorted[middle - 1] + sorted[middle]) / 2.0;
                }
              }
              return Math.round(median);
            },
            font: {
              weight: "bold",
              size: 15,
            },
          },
        },
      },
    };
  },

  mounted() {
    // init the chart
    new BoxPlotChart(this.$refs.canvas, {
      data: this.data,
      options: this.chartOptions,
    });
  },

  methods: {},
};
</script>

<style>
.small {
  max-width: 600px;
  margin: 150px auto;
}
</style>
