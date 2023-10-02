<template>
  <section class="p-5 bg-dark">
    <div class="container">
      <div class="little-title d-flex">
        <img class="bi bi-balloon-heart" />
        <h2 class="text-center text-light">
          <i class="bi bi-three-dots-vertical"></i>Top Billed Cast
        </h2>
      </div>
      <div class="card-group">
        <div class="row">
          <div v-for="(actor, index) in actorList" :key="index" class="col">
            <div class="card" style="height: 500px">
              <router-link :to="'/Artist?id=' + actor.id">
                <img
                  :src="
                    actor.profile_path === null ||
                    actor.profile_path === undefined
                      ? '#'
                      : baseUrl + actor.profile_path
                  "
                  class="card-img-top"
                  alt="..."
                  style="height: 375px; width: 238px"
                />
              </router-link>
              <div class="card-body">
                <span
                  class="glyphicon glyphicon-star"
                  aria-hidden="true"
                ></span>
                <div class="d-flex">
                  <h3 class="card-title">
                    <i class="bi bi-star-fill"></i>
                    {{ scores[index] == null ? "none" : scores[index] }}
                  </h3>
                </div>
                <router-link :to="'/Artist?id=' + actor.id">
                  <h5 class="card-text">
                    {{ actor.name }}
                  </h5>
                </router-link>
                <h6 class="card-text">
                  {{ actor.character }}
                </h6>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
    
<script>
import { Credits } from "@/api/film";
import { CreditsTV } from "@/api/film";
import { serverQueryCelebRate } from "@/api/film";

export default {
  name: "Poster3",
  props: ["id", "mediaType"],
  data() {
    return {
      baseUrl: "https://image.tmdb.org/t/p/original",
      cast: [],
      actorList: [],
      actorIds: [],
      scores: [],
    };
  },
  created() {
    if (this.mediaType === "movie") {
      Credits(this.id, "en-US").then((res) => {
        this.cast = res.cast;

        this.actorList = this.cast.filter(
          (credit) => credit.known_for_department === "Acting"
        );
        this.actorList = this.actorList.splice(0, 5);
        this.$store.commit("setActorList", this.actorList);
        this.actorIds = this.actorList.map((actor) => actor.id);
        console.log(this.actorList);
        console.log(this.actorIds);
        serverQueryCelebRate(this.actorIds).then((res) => {
          console.log(res);
        });
      });
    } else {
      CreditsTV(this.id, "en-US").then((res) => {
        this.cast = res.cast;

        this.actorList = this.cast.filter(
          (credit) => credit.known_for_department === "Acting"
        );
        this.actorList = this.actorList.splice(0, 5);
        this.$store.commit("setActorList", this.actorList);
        this.actorIds = this.actorList.map((actor) => actor.id);
        console.log(this.actorList);
        console.log(this.actorIds);
        if (this.actorIds.length !== 0) {
          serverQueryCelebRate(this.actorIds).then((res) => {
            console.log(res);
            if (res.data.length) {
              scores = res.data.map((celebRate) => celebRate.score.toFixed(2));
            }
          });
        }
      });
    }
  },
};
</script>