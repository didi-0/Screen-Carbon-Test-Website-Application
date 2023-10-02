<template>
  <section class="p-5 bg-dark">
    <div class="container">
      <div class="little-title d-flex">
        <img class="bi bi-balloon-heart" />
        <h2 class="text-center text-light">
          <i class="bi bi-three-dots-vertical"></i>Known for
        </h2>
        <h2 class="text-light ms-auto">
          More <i class="bi bi-chevron-right"></i>
        </h2>
      </div>
      <div class="card-group">
        <div class="row">
          <div v-for="(castOne, index) in cast" :key="index" class="col-3">
            <div class="card" style="height: 550px">
              <img
                :src="
                  castOne.poster_path === null ||
                  castOne.poster_path === undefined
                    ? '#'
                    : baseUrl + castOne.poster_path
                "
                class="card-img-top"
                alt="..."
                style="height: 450px"
              />
              <div class="card-body">
                <span
                  class="glyphicon glyphicon-star"
                  aria-hidden="true"
                ></span>
                <div class="d-flex">
                  <h3 class="card-title">
                    <i class="bi bi-star-fill"></i> 3.5
                  </h3>
                  <button class="btn btn-dark ms-auto btn-sm">Rate Now!</button>
                </div>
                <h5 class="card-text text-truncate">{{ castOne.title }}</h5>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import "bootstrap-icons/font/bootstrap-icons.css";
import { CombinedCredits } from "@/api/film";

export default {
  name: "furtherRate",
  props: ["id"],
  data() {
    return {
      baseUrl: "https://image.tmdb.org/t/p/original",
      cast: [],
    };
  },
  mounted() {
    CombinedCredits(this.id, "en-US").then((res) => {
      this.cast = res.cast;
      this.cast = this.cast.splice(0, 4);
    });
  },
};
</script>