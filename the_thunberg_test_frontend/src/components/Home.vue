<template>
  <div class="all">
    <Carousel></Carousel>
    <Slogan></Slogan>
    <Poster></Poster>
    <Poster2></Poster2>
  </div>
</template>
  
  <script>
import Carousel from "../views/Carousel.vue";
import Slogan from "@/views/Slogan.vue";
import Poster from "@/views/Poster.vue";
import Poster2 from "@/views/Poster2.vue";
import "bootstrap-icons/font/bootstrap-icons.css";

export default {
  name: "Home",
  components: { Carousel, Slogan, Poster, Poster2 },
  methods: {
    handleButtonClick() {
      this.$router.push("/destination");
    },
  },
};
</script>