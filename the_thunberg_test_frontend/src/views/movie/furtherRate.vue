<template>
  <section>
    <div class="container">
      <h3 class="mt-5">Do you want to RATE further?</h3>
      <div
        v-for="(merge, index) in mergedList"
        :key="index"
        class="row justify-content-md-center mt-3"
      >
        <div
          v-for="(bioSingle, indexBio) in bio.slice(index, index + 1)"
          :key="indexBio"
          class="card h-50 col-2"
        >
          <img
            :src="
              bioSingle.profile_path === null
                ? '#'
                : baseUrl + bioSingle.profile_path
            "
            class="card-img-top"
            alt="..."
          />
          <div class="card-body">
            <h5 class="card-title">{{ bioSingle.known_for_department }}</h5>
            <p class="card-text">{{ bioSingle.name }}</p>
          </div>
        </div>
        <div class="col-1"></div>
        <div class="director-list col-9">
          <br /><br />
          <div class="little-title d-flex">
            <img class="bi bi-balloon-heart" />
            <h2 class="text-center text-dark">Related for You to Rate</h2>
          </div>
          <br />
          <div class="card-group">
            <div class="row">
              <div
                v-for="(castOne, indexCast) in cast.slice(index, index + 5)"
                :key="indexCast"
                class="col"
              >
                <div class="card" style="height: 500px">
                  <router-link
                    :to="'/film?id=' + castOne.id + '&media_type=' + castOne.media_type"
                  >
                    <img
                      :src="
                        castOne.poster_path === null
                          ? '#'
                          : baseUrl + castOne.poster_path
                      "
                      class="card-img-top"
                      alt="..."
                      style="height: 255px; width: 150px"
                    />
                  </router-link>
                  <div class="card-body">
                    <h3 class="card-title">
                      <i class="bi bi-star-fill"></i> {{ scores[indexCast] }}
                    </h3>
                    <router-link
                      :to="
                        '/Rate?id=' +
                        castOne.id +
                        '&media_type=' +
                        castOne.media_type
                      "
                    >
                      <button class="btn btn-dark ms-auto btn-sm">
                        Rate Now!
                      </button>
                    </router-link>
                    <h6 class="card-text">{{ castOne.title }}</h6>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { CombinedCredits } from "@/api/film";
import { DetailsPeople } from "@/api/film";
import { serverQueryFilmRate } from "@/api/film";
import { mapState } from "vuex";

export default {
  name: "furtherRate",
  computed: {
    ...mapState(["writerList", "producerList", "directorList", "actorList"]),
  },
  data() {
    return {
      baseUrl: "https://image.tmdb.org/t/p/original",
      cast: [],
      bio: [],
      mergedList: [],
      scores: [],
    };
  },
  created() {
    this.mergeLists();
    this.loadInfo();
  },
  methods: {
    async mergeLists() {
      await new Promise((resolve) => setTimeout(resolve, 500));
      this.mergedList = this.writerList.concat(
        this.producerList,
        this.directorList,
        this.actorList
      );
    },
    async loadInfo() {
      await new Promise((resolve) => setTimeout(resolve, 500));
      for (let i = 0; i < this.mergedList.length; i++) {
        CombinedCredits(this.mergedList[i].id, "en-US").then((res) => {
          this.cast = this.cast.concat(res.cast.splice(0, 5));

          this.cast.forEach((c) => {
            serverQueryFilmRate(c.id, "movie").then((res) => {
              if (res.data == null) {
                this.scores.push("none");
              } else {
                this.scores.push(res.data.score.toFixed(2));
              }
            });
          });
        });
        DetailsPeople(this.mergedList[i].id, "en-US").then((res) => {
          this.bio.push(res);
        });
      }
    },
    getScore(id) {
      serverQueryFilmRate(id, "movie").then((res) => {
        if (res.data == null) {
          this.scores.push("none");
        } else {
          this.scores.push(res.data.score.toFixed(2));
        }
      });
    },
  },
};
</script>