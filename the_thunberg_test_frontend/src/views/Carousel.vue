<template>
  <!-- Carousel -->
  <div id="carousel" class="carousel slide" data-bs-ride="carousel">
    <!-- The slideshow/carousel -->
    <div class="carousel-inner">
      <div
        v-for="(carousel, index) in carouselList"
        :key="index"
        :class="{ 'carousel-item': true, active: index === 0 }"
      >
        <router-link
          :to="'/film?id=' + carousel.id + '&media_type=' + carousel.media_type
          "
        >
          <img
            :src="baseUrl + carousel.backdrop_path"
            class="d-block"
            style="width: 100%; height: 700px"
          />
        </router-link>
        <div class="carousel-caption">
          <h3>
            {{
              carousel.media_type === "movie" ? carousel.title : carousel.name
            }}
          </h3>
          <p class="d-none d-md-block">
            {{ carousel.overview }}
          </p>
          <router-link
            :to="
              '/Rate?id=' + carousel.id + '&media_type=' + carousel.media_type
            "
          >
            <button type="button" class="btn btn-dark">Rate Now!</button>
          </router-link>
        </div>
      </div>
    </div>

    <!-- Left and right controls/icons -->
    <button
      class="carousel-control-prev"
      type="button"
      data-bs-target="#carousel"
      data-bs-slide="prev"
    >
      <span class="carousel-control-prev-icon"></span>
    </button>
    <button
      class="carousel-control-next"
      type="button"
      data-bs-target="#carousel"
      data-bs-slide="next"
    >
      <span class="carousel-control-next-icon"></span>
    </button>
  </div>
</template>

<script>
import { Trending } from "@/api/film";

export default {
  name: "Carousel",
  data() {
    return {
      baseUrl: "https://image.tmdb.org/t/p/original",
      carouselList: [],
    };
  },
  mounted() {
    Trending().then((res) => {
      this.carouselList = res.results;
    });
  },
};
</script>