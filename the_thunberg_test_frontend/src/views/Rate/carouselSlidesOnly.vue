<template>
  <section>
    <div class="container">
      <div
        id="carouselExampleSlidesOnly"
        class="carousel slide"
        data-bs-ride="carousel"
      >
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img
              id="rate-background"
              :src="baseUrl + detail.backdrop_path"
              class="d-block w-100"
              alt="..."
              style="height: 600px; opacity: 0.3"
            />
            <div class="carousel-caption d-none d-md-block">
              <h1 class="text-dark">SCREEN CARBON QUESTIONNAIRE</h1>
              <p class="text-dark mt-3">
                The Thunberg Test (TTT) is a suite of educational resources as
                well as a Likert-style rating tool that monitors, educates, and
                raises awareness about the CO2 polluting messaging in film and
                TV drama. Mapped to specific productions, the resources seek to
                educate learners and audiences about the climate-related
                messaging in film and TV, thereby empowering them to critically
                assess and evaluate the carbon footprint of storylines and
                narratives in films and TV productions.
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { Details } from "@/api/film";
import { DetailsTV } from "@/api/film";

export default {
  name: "carouselSlidesOnly",
  props: ["id", "mediaType"],
  data() {
    return {
      baseUrl: "https://image.tmdb.org/t/p/original",
      detail: [],
    };
  },
  mounted() {
    if (this.mediaType === "movie") {
      Details(this.id, "en-US").then((res) => {
        this.detail = res;
      });
    }
    if(this.mediaType === "tv"){
      DetailsTV(this.id).then((res) => {
        this.detail = res;
      });
    }
  },
};
</script>