<template>
  <section class="p-5 bg-dark">
    <div class="container">
      <div class="little-title d-flex">
        <img class="bi bi-balloon-heart" />
        <h2 class="text-center text-light">
          <i class="bi bi-three-dots-vertical"></i>New Releases for You to Rate
        </h2>
      </div>
      <div class="card-group">
        <div class="row">
          <div v-for="(poster, index) in posterList" :key="index" class="col">
            <div class="card" style="height: 475px">
              <router-link :to="'/film?id=' + poster.id + '&media_type=movie'">
                <img
                  :src="baseUrl + poster.poster_path"
                  class="card-img-top"
                  alt="..."
                />
              </router-link>
              <div class="card-body">
                <span
                  class="glyphicon glyphicon-star"
                  aria-hidden="true"
                ></span>
                <div class="d-flex">
                  <h3 class="card-title">
                    <i class="bi bi-star-fill"></i> {{ scores[index] }}
                  </h3>
                  <button class="btn btn-dark ms-auto btn-sm">Rate Now!</button>
                </div>
                <router-link
                  :to="'/film?id=' + poster.id + '&media_type=movie'"
                  style="color: black; text-decoration: none"
                >
                  <h6 class="card-text">{{ poster.original_title }}</h6>
                </router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
  
  <script>
import { Popular } from "@/api/film";
import { serverQueryFilmRate } from "@/api/film";

export default {
  name: "Poster2",
  data() {
    return {
      baseUrl: "https://image.tmdb.org/t/p/original",
      posterList: [],
      scores: [],
    };
  },
  mounted() {
    Popular("en-US", "1").then((res) => {
      this.posterList = res.results.slice(0, 5);
      this.extractedPostIds.forEach((id, index) => {
        serverQueryFilmRate(id, "movie").then((res) => {
          if (res.data == null) {
            this.scores.splice(index, 0, "none");
          } else {
            this.scores.splice(index, 0, res.data.score.toFixed(2));
          }
        });
      });
    });
  },
  computed: {
    extractedPostIds() {
      return this.posterList.map((poster) => poster.id);
    },
  },
};
</script>