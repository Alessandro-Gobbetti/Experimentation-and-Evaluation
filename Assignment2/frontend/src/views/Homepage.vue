<template>
  <div class="h-100 w-100 align-center text-center">
    <!-- add a top banner with an image and the app name on top of it  -->

    <div class="w-100">
      <img class="w-100 cover" src="@/assets/images/rambo.gif" alt="logo" />
      <h1 class="mt-2">camelCase vs kebab-case</h1>
    </div>

    <!-- quick explanation of the app -->
    <div class="mt-16 mx-16 mb-8 text">
      <p>
        camelCase vs kebab-case is a quiz app that helps you learn the difference between
        camelCase and kebab-case.
      </p>
      <p>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque porta
        euismod consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc
        eget elementum elit. In dignissim nisi sit amet nisl accumsan rhoncus id sit amet
        felis. Donec dui elit, auctor ac efficitur in, sagittis et diam. Donec purus diam,
        maximus at enim id, ultricies consectetur eros. Nulla pharetra est vel tristique
        aliquet. Aliquam tincidunt ipsum vitae tempor posuere. Maecenas nisi turpis,
        pretium eget enim vitae, hendrerit varius justo. Integer metus eros, pellentesque
        at tellus nec, gravida dapibus lectus. Suspendisse potenti. Proin facilisis cursus
        orci sed gravida. Ut non elementum tellus. Curabitur facilisis ligula eros, quis
        tempus mi euismod vel. Maecenas id nunc elit. Curabitur fringilla ante eleifend
        feugiat convallis. Integer sit amet malesuada orci, et feugiat orci. Maecenas
        dictum leo libero. Nullam quis tristique diam. Proin quis vestibulum nibh.
        Vestibulum mi neque, auctor in pulvinar eget, vehicula sit amet quam. In mattis
        neque massa, at auctor velit dapibus at. In nisi mauris, venenatis nec turpis ac,
        varius pellentesque neque. Nam vitae arcu elit. Praesent lobortis nibh quis massa
        tincidunt, id pharetra neque posuere. Etiam eleifend dui nec elit imperdiet,
        vehicula condimentum turpis tincidunt. Nulla accumsan, neque a egestas
        sollicitudin, velit nunc sodales nunc, non fringilla nibh ante id lacus. In nibh
        nisl, mollis et vulputate vel, posuere quis justo. Duis sagittis blandit enim, vel
        aliquam ante elementum fermentum. Pellentesque non tincidunt nunc. Fusce eu ex sit
        amet massa blandit congue et eget arcu. Proin efficitur metus in varius sagittis.
        Proin efficitur nulla ac sem tempor, nec commodo augue hendrerit. Suspendisse
        volutpat eros ac orci iaculis vulputate. Cras hendrerit efficitur lacus nec
        molestie. Ut bibendum vulputate nisi, vitae tempus ante tempus nec. Suspendisse
        dignissim enim eu lacus commodo, vitae viverra urna pharetra. Donec aliquam
        imperdiet lacus, lobortis tempus eros vestibulum quis. Proin aliquet justo urna,
        id sollicitudin lorem pulvinar nec. Morbi vulputate non libero at tincidunt.
        Vestibulum arcu lacus, volutpat ut ipsum sed, egestas tempor sem. In hac habitasse
        platea dictumst. Aliquam erat volutpat. Suspendisse ultrices eros vel libero
        venenatis, tincidunt bibendum mauris accumsan. Aliquam molestie massa in hendrerit
        aliquet.
      </p>
    </div>

    <!-- button to go to the quiz page -->
    <v-btn variant="tonal" color="blue" href="/quiz"> Take the quiz </v-btn>

    <!-- Results -->
    <div class="mt-16 mx-16 mb-8 text">
      <h2>Results</h2>
      <p>Some plots of the results, this may be component, we may use vue-plotly</p>
      <p>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque porta
        euismod consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc
        eget elementum elit. In dignissim nisi sit amet nisl accumsan rhoncus id sit amet
        felis. Donec dui elit, auctor ac efficitur in, sagittis et diam. Donec purus diam,
        maximus at enim id, ultricies consectetur eros. Nulla pharetra est vel tristique
        aliquet. Aliquam tincidunt ipsum vitae tempor posuere. Maecenas nisi turpis,
        pretium eget enim vitae, hendrerit varius justo. Integer metus eros, pellentesque
        at tellus nec, gravida dapibus lectus. Suspendisse potenti. Proin facilisis cursus
        orci sed gravida. Ut non elementum tellus. Curabitur facilisis ligula eros, quis
        tempus mi euismod vel. Maecenas id nunc elit. Curabitur fringilla ante eleifend
        feugiat convallis. Integer sit amet malesuada orci, et feugiat orci. Maecenas
        dictum leo libero. Nullam quis tristique diam. Proin quis vestibulum nibh.
        Vestibulum mi neque, auctor in pulvinar eget, vehicula sit amet quam. In mattis
        neque massa, at auctor velit dapibus at. In nisi mauris, venenatis nec turpis ac,
        varius pellentesque neque. Nam vitae arcu elit. Praesent lobortis nibh quis massa
        tincidunt, id pharetra neque posuere. Etiam eleifend dui nec elit imperdiet,
        vehicula condimentum turpis tincidunt. Nulla accumsan, neque a egestas
        sollicitudin, velit nunc sodales nunc, non fringilla nibh ante id lacus. In nibh
        nisl, mollis et vulputate vel, posuere quis justo. Duis sagittis
      </p>
    </div>
  </div>
</template>

<script>
import { defineComponent } from "vue";

export default defineComponent({
  name: "Homepage",
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
</style>
