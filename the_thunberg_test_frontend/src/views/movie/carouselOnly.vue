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
              :src="
                detailList.backdrop_path === null ||
                detailList.backdrop_path === undefined
                  ? '#'
                  : baseUrl + detailList.backdrop_path
              "
              class="d-block w-100"
              alt="..."
              style="height: 600px; opacity: 0.3"
            />
            <div class="carousel-caption d-none d-md-block">
              <div class="row">
                <div class="col-6">
                  <h1 class="text-dark">
                    {{
                      this.mediaType === "movie"
                        ? detailList.title
                        : detailList.name
                    }}
                  </h1>
                  <p class="text-dark">
                    {{
                      this.mediaType === "movie"
                        ? detailList.release_date
                        : detailList.last_episode_to_air.air_date
                    }}
                  </p>
                  <p
                    v-for="(genres, index) in genresList"
                    :key="index"
                    class="text-dark"
                    style="display: inline"
                  >
                    {{ genres.name }}<i class="bi bi-dot"></i>
                  </p>
                  <p
                    v-if="this.mediaType === 'movie'"
                    class="text-dark"
                    style="display: inline"
                  >
                    {{ Math.floor(detailList.runtime / 60) }}h
                    {{ detailList.runtime % 60 }}m
                  </p>
                  <p
                    v-for="(language, index) in spoken_languages"
                    :key="index + 'language'"
                    class="text-dark"
                    style="display: inline"
                  >
                    <i class="bi bi-dot"></i>{{ language.english_name }}
                  </p>
                  <p v-if="this.mediaType === 'tv'" class="text-dark">
                    {{ detailList.episode_run_time[0] }}min<i
                      class="bi bi-dot"
                    ></i
                    >Season Number:
                    {{ detailList.last_episode_to_air.season_number }}
                  </p>
                  <div class="row justify-content-center"></div>
                  <div class="d-flex justify-content-center">
                    <button class="btn btn-dark mt-2">
                      Thunberg Score:
                      {{ this.score == null ? 'none' : `${(this.score * 10).toFixed(2)}%`}}
                    </button>
                  </div>
                  <h5 class="text-dark mt-4">overview</h5>
                  <p class="text-dark">
                    {{ detailList.overview }}
                  </p>
                </div>
                <div class="col-6">
                  <img
                    id="FilmPoster"
                    :src="
                      detailList.poster_path === null ||
                      detailList.poster_path === undefined
                        ? '#'
                        : baseUrl + detailList.poster_path
                    "
                    style="width: 250px; height: 350px"
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Related information -->
    <section class="p-5 bg-dark">
      <div class="container">
        <div class="row">
          <div class="col-8">
            <h2 class="text-light">Related information:</h2>
            <div v-if="this.mediaType === 'movie'" class="row pt-3">
              <div class="col-3">
                <p class="text-light">Status: {{ detailList.status }}</p>
                <p class="text-light"></p>
              </div>
              <div class="col-3">
                <p class="text-light">Budget: ${{ detailList.budget }}</p>
                <p class="text-light"></p>
              </div>
              <div class="col-3">
                <p class="text-light">Revenue: ${{ detailList.revenue }}</p>
                <p class="text-light"></p>
              </div>
            </div>

            <div v-if="this.mediaType === 'tv'" class="row pt-3">
              <div class="col-3">
                <p class="text-light">Status: {{ detailList.status }}</p>
                <p class="text-light"></p>
              </div>
              <div class="col-3">
                <p class="text-light">Type: {{ detailList.type }}</p>
                <p class="text-light"></p>
              </div>
              <div class="col-3">
                <p class="text-light">
                  Last Episode Number:
                  {{ detailList.last_episode_to_air.episode_number }}
                </p>
                <p class="text-light"></p>
              </div>
            </div>
          </div>
          <div class="col-3">
            <h2 class="text-light">Rate the Film:</h2>
            <router-link :to="'/Rate?id=' + id + '&media_type=' + mediaType">
              <button class="btn btn-light btn-sm mt-3">Rate Now!</button>
            </router-link>
          </div>
        </div>
      </div>
    </section>
  </section>
</template>

<script>
import { Details } from "@/api/film";
import { DetailsTV } from "@/api/film";
import { serverQueryFilmRate } from "@/api/film";

export default {
  name: "carouselOnly",
  props: ["id", "mediaType"],
  data() {
    return {
      baseUrl: "https://image.tmdb.org/t/p/original",
      detailList: [],
      genresList: [],
      spoken_languages: [],
      score: 0,
    };
  },
  mounted() {
    if (this.mediaType === "movie") {
      Details(this.id, "en-US").then((res) => {
        this.detailList = res;
        this.spoken_languages = res.spoken_languages;
        this.genresList = res.genres;
      });
    } else {
      DetailsTV(this.id).then((res) => {
        this.detailList = res;
        this.spoken_languages = res.spoken_languages;
        this.genresList = res.genres;
      });
    }

    serverQueryFilmRate(this.id, this.mediaType).then((res) => {
      if (res.data == null) {
        this.score = null;
      } else {
        this.score = res.data.score;
      }
    });
  },
};
</script>