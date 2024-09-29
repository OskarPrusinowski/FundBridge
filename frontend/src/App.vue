<script setup>
import Header from "../src/components/Header.vue";
import Footer from "../src/components/Footer.vue";
</script>

<template>
  <v-app style="background: #f4f7fa">
    <div style="width: 90vw" class="mx-auto">
      <Header />
      <v-main>
        <router-view></router-view>
      </v-main>
    </div>
    <Footer />
  </v-app>
</template>

<style>
@import "./src/assets/css/fonts.css";
@import "./assets/css/paddings.css";
@import "./assets/css/margins.css";
@import "./assets/css/opacity.css";

.saturated {
  filter: saturate(0);
}

a {
  /* text-decoration: none; */
  /* -webkit-box-shadow: 0px 0px 10px 0px red;
    -moz-box-shadow: 0px 0px 10px 0px red;
    box-shadow: 0px 0px 10px 0px red; */
}

.goTo {
}

.illuminate-link {
  -webkit-box-shadow: 0px 0px 10px 0px red;
  -moz-box-shadow: 0px 0px 10px 0px red;
  box-shadow: 0px 0px 10px 0px red;
}

.non-transition {
  transition: none !important;
}

.space-between-text {
  letter-spacing: 3px;
}

.clickable {
  cursor: pointer;
}

.big-clickable {
  cursor: url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/3174/cursor.png),
    auto !important;
}

.big-cursor {
  cursor: url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/3174/cursor.png),
    auto !important;
}

.uppercase {
  text-transform: uppercase;
}

body {
  font-size: var(--body-font-size);
}

/* Define CSS variables for different screen sizes */
@media only screen and (max-width: 959px) {
  :root {
    --body-font-size: calc(var(--base-font-size) * 0.8);
  }
}

@media only screen and (min-width: 960px) and (max-width: 1279px) {
  :root {
    --body-font-size: calc(var(--base-font-size) * 0.9);
  }
}

@media only screen and (min-width: 1280px) {
  :root {
    --body-font-size: var(--base-font-size);
  }
}

.c-b {
  font-weight: bold !important;
}

.c-sb {
  font-weight: 600 !important;
}

.c-rubik {
  font-family: Rubik !important;
}

.ProseMirror img {
  max-width: 100% !important;
}
</style>
